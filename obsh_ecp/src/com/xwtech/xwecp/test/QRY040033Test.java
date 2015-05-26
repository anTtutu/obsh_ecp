package com.xwtech.xwecp.test;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQuerydetailByReceptionService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QuerydetailByReceptionServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.OperLogDetail;
import com.xwtech.xwecp.service.logic.pojo.QRY040033Result;

public class QRY040033Test {
private static final Logger logger = Logger.getLogger(QRY040033Test.class);
	
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "test_channel");
		props.put("platform.url", "http://localhost/js_ecp/xwecp.do");
		props.put("platform.user", "zlbbq");
		props.put("platform.password", "zlbbq99");
		
		XWECPLIClient client = XWECPLIClient.createInstance(props);
		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("用户县市");
		lic.setUserMobile("13601400067");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("contextParameter", "12345");
		ic.addContextParameter("testParameter", 54321);
		ic.addContextParameter("login_msisdn", "13913032424");
		ic.addContextParameter("route_type", "2");
		ic.addContextParameter("route_value", "13913032424");
		ic.addContextParameter("ddr_city", "20");
		
		ic.addContextParameter("user_id", "2056200011182291");
		
		lic.setContextParameter(ic);
		
		IQuerydetailByReceptionService co = new QuerydetailByReceptionServiceClientImpl();
		QRY040033Result result = co.queryOperLogDetail("13770472424","12111028928951621", "12");
		List<OperLogDetail> operDetail = result.getOperLogDetail();
		
		for(int i = 0 ; i < operDetail.size() ; i ++)
		{
			//System.out.println(transferDate(operDetail.get(i).getOprTime()) + " " + operDetail.get(i).getOprBiz() + " " + operDetail.get(i).getOprChannel());
		}
		
	}
	
	public static String transferDate(String date)
	{
		
		String result1 = date.substring(0,4);
		String result2 = date.substring(4,6);
		String result3 = date.substring(6,8);
		String result = result1 + "-" + result2 + "-" + result3;
		return result;
	}
	
}
