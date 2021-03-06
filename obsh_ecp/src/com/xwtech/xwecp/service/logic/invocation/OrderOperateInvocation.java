package com.xwtech.xwecp.service.logic.invocation;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.dao.DAOException;
import com.xwtech.xwecp.dao.IReserveDAO;
import com.xwtech.xwecp.dao.SmsDao;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.OrderUpdateInfo;
import com.xwtech.xwecp.service.logic.pojo.RES004Result;
import com.xwtech.xwecp.service.logic.pojo.ReserveOfficeInfo;
import com.xwtech.xwecp.service.logic.pojo.ReserveOrderInfo;
import com.xwtech.xwecp.util.DateTimeUtil;

public class OrderOperateInvocation extends BaseInvocation implements
		ILogicalService {

	// 订单类型  B-业务 M-营销
	private static final String ORDERTYPEMARKET = "M";

	private static final String ORDERTYPEBUSI = "B";

	private static final Logger logger = Logger
			.getLogger(OrderOperateInvocation.class);

	private IReserveDAO reserveDAO;
	
	private SmsDao smsDao;

	public OrderOperateInvocation() {
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.reserveDAO = (IReserveDAO) (springCtx.getBean("reserveDAO"));
		this.smsDao = (SmsDao)(springCtx.getBean("smsDao"));
	}

	public BaseServiceInvocationResult executeService(String accessId,
			ServiceConfig config, List<RequestParameter> params) {
		RES004Result re = new RES004Result();
		try {
			String operType = ""; // 操作类型
			String orderId = ""; // 订单编号
			OrderUpdateInfo orderUpdateInfo = null;
			String orderType = ""; // 订单类型 B:业务 M:营销
			int isSuccess = 0; // SQL执行结果
			for (RequestParameter param : params) {
				if ("operType".equals(param.getParameterName())) {
					operType = String.valueOf(param.getParameterValue());
				}
				if ("orderId".equals(param.getParameterName())) {
					orderId = String.valueOf(param.getParameterValue());
				}
				if ("orderUpdateInfo".equals(param.getParameterName())) {
					orderUpdateInfo = (OrderUpdateInfo) param
							.getParameterValue();
				}
			}
			orderType = orderId.substring(0, 1);
			// 1-取消订单 2-修改订单
			if ("1".equals(operType)) {
				// B-业务 M-营销
				if (ORDERTYPEBUSI.equals(orderType)) {
					isSuccess = reserveDAO.cancelBusiOrderByOrderId(orderId);
				} else if (ORDERTYPEMARKET.endsWith(orderType)) {
					isSuccess = reserveDAO.cancelMarketOrderByOrderId(orderId);
				}
			// 订单修改
			} else {
				if (ORDERTYPEBUSI.equals(orderType)) {
					isSuccess = reserveDAO.updateBusiOrderByOrderId(orderId,
							orderUpdateInfo);
				} else if (ORDERTYPEMARKET.equals(orderType)) {
					isSuccess = reserveDAO.updateMarketOrderByOrderId(orderId,
							orderUpdateInfo);
				}
			}
			//设置返回结果
			setResult(re, isSuccess);
			//发送短信
			sendSMS(isSuccess,operType,orderType,orderId,orderUpdateInfo);
		} catch (Exception e) {
			logger.error("执行订单编号操作类Service失败");
			e.printStackTrace();
			re.setResultCode(LOGIC_ERROR);
			re.setErrorMessage("执行数据库失败");
		}

		return re;
	}

	private void sendSMS(int isSuccess, String operType, String orderType,
			String orderId, OrderUpdateInfo orderUpdateInfo) {
		String smsContent = "";
		if(isSuccess != 1)
		{
			return;
		}
		//获取订单信息
		ReserveOrderInfo orderInfo = getOrderInfo(operType, orderType, orderId);
		if(null != orderInfo)
		{
			//获取营业厅名称
			ReserveOfficeInfo officeInfo = getOfficeInfo(orderInfo);
			String officeName = officeInfo == null ? "" : officeInfo.getOfficeName() ;
			String mobile = orderInfo.getOrderMobile();
			String busiName = orderInfo.getBusiName();
			String marketName = orderInfo.getMarketName();
			
			String fmtexpectPeriod = "";
			String formatExpectTime = "";
			if(null != orderUpdateInfo)
			{
				//转换时间 1：上午 2：下午 3：全天
				fmtexpectPeriod = (StringUtils.isBlank(orderUpdateInfo.getExpectPeriod()) || "3".equals(orderUpdateInfo.getExpectPeriod()))? "":("1".equals(orderUpdateInfo.getExpectPeriod())?"上午":"下午");
				//日期转换为xxxx年xx月xx日
				formatExpectTime = DateTimeUtil.fromChar8ToStandard(orderUpdateInfo.getExpectTime());
			}
			
			smsContent = genSMSContent(formatExpectTime,
					fmtexpectPeriod, orderId, orderType.equals(ORDERTYPEBUSI) ? busiName : marketName,
					officeName,operType.equals("1") ? 3 : 2); 
			try {
				//发送短信
				int smsflag = smsDao.sendSms(mobile, smsContent, "10086", "free");
			} catch (DAOException e) {
				e.printStackTrace();
			}

		}
	}

	private ReserveOfficeInfo getOfficeInfo(ReserveOrderInfo orderInfo) {
		String officeId = orderInfo.getOfficeId();
		ReserveOfficeInfo officeInfo = null;
		try {
			officeInfo = reserveDAO.getReserveOfficeInfo(officeId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return officeInfo;
	}

	private ReserveOrderInfo getOrderInfo(String operType, String orderType,
			String orderId) {
		List<ReserveOrderInfo> orderInfos = null;
		ReserveOrderInfo orderInfo = null;
		try {
			//业务订单信息
			if(ORDERTYPEBUSI.equals(orderType))
			{
				orderInfos = reserveDAO.getBusiOrder(orderId);
			}
			//营销案订单信息
			if(ORDERTYPEMARKET.endsWith(orderType))
			{
				orderInfos = reserveDAO.getMarketOrder(orderId);
			}
			
		} catch (DAOException e) {
			e.printStackTrace();
			return null ;
		}
		if(orderInfos != null && orderInfos.size() > 0)
		{
			orderInfo = orderInfos.get(0);
		}
		return orderInfo;
	}

	private void setResult(RES004Result re, int isSuccess) {
		if (1 == isSuccess) {
			re.setResultCode(LOGIC_SUCESS);
		} else {
			re.setResultCode(LOGIC_ERROR);
		}
	}

}
