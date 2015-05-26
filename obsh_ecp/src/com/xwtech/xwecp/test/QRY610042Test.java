package com.xwtech.xwecp.test;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryCstudentOrderSyncService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryCstudentOrderSyncServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY610042Result;

/**
 * 在线入网订单同步CRM接口
 * @author YangXQ
 * 2015-04-14
 */
public class QRY610042Test {
	private static final Logger logger = Logger.getLogger(QRY610042Test.class);
	
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "obsh_channel");  
		props.put("platform.url", "http://127.0.0.1:8080/obsh_ecp/xwecp.do");  
		props.put("platform.user", "jhr"); 
		props.put("platform.password", "jhr"); 
		
		XWECPLIClient client = XWECPLIClient.createInstance(props);
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand(""); 
		lic.setUserCity("12"); 
		lic.setUserMobile("");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "");
		ic.addContextParameter("route_type", "1");           
		ic.addContextParameter("route_value", "12"); 
		lic.setContextParameter(ic);
		 
		IQueryCstudentOrderSyncService co = new QueryCstudentOrderSyncServiceClientImpl();
		/**
		 * 订单号
		 * 在线入网号码
		 * 新生随机码
		 * 学校识别码
		 * 学校名称
		 * 产品信息
		 * 收货人名称
		 * 收货人地址
		 * 收货人电话
		 */
		QRY610042Result re = co.queryCstudentOrderSync("1","2","3","1","2","3","1","2","3");
		
		logger.info(" ====== 开始返回参数 ======");
		if (re != null)
		{
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());		 
		}
	}
}
