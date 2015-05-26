package com.xwtech.xwecp.test;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IB2BQryDistributeErrService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.B2BQryDistributeErrServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.B2B004Result;
import com.xwtech.xwecp.service.logic.pojo.IMEIInfo;

public class B2B004Test
{
	private static final Logger logger = Logger.getLogger(B2B004Test.class);
	
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "terminal_channel");
		props.put("platform.url", "http://127.0.0.1:8080/obsh_ecp/xwecp.do");
		props.put("platform.user", "tyl");
		props.put("platform.password", "tyl");
		
		XWECPLIClient client = XWECPLIClient.createInstance(props);
		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("CZJF_HKCZ");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("20");
//		lic.setUserMobile("13813382424");
		InvocationContext ic = new InvocationContext();
//		ic.addContextParameter("login_msisdn", "13813382424");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "20");
		ic.addContextParameter("ddr_city", "20");///13611542424//1101300022887974
//		ic.addContextParameter("user_id", "1419200008195116");  //13813382424-1419200008195116,13913032424-1419200008195160
		lic.setContextParameter(ic);
		//QQTMS_3Y 
		IB2BQryDistributeErrService co = new B2BQryDistributeErrServiceClientImpl();
		
		B2B004Result re = co.b2bQryDistributeErr("99","99410662", "AMOUNT", "30","1", "130510051300014449335177");
		logger.info(" ====== 开始返回参数 ======");
		if (re != null)
		{
			logger.info(" === re.getResultCode() === " + re.getResultCode());
			logger.info(" === re.getErrorCode() === " + re.getErrorCode());
			logger.info(" === re.getErrorMessage() === " + re.getErrorMessage());
			if(null != re.getImeiList())
			{
				for(IMEIInfo i : re.getImeiList())
				{
					logger.info(" === imei() === " + i.getImeiId());
					logger.info(" === errmsg === " + i.getErrMsg());
				}
			}
		}
	}
}