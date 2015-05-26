package com.xwtech.xwecp.test;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQuerySimNetHallCustService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QuerySimNetHallCustServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.OrderInfo;
import com.xwtech.xwecp.service.logic.pojo.QRY090015Result;

public class QRY090015Test {
private static final Logger logger = Logger.getLogger(QRY090015Test.class);
	
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "obsh_channel");
		props.put("platform.url", "http://127.0.0.1:8080/obsh_ecp/xwecp.do");
		props.put("platform.user", "jhr");
		props.put("platform.password", "jhr");
		
		XWECPLIClient client = XWECPLIClient.createInstance(props);
		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		 lic.setBizCode("biz_code_19234");
		 lic.setOpType("开通/关闭/查询/变更");
		 lic.setUserBrand("动感地带");
		 lic.setUserCity("12");
		 lic.setUserMobile("13952395959");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "13952395959");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");
		ic.addContextParameter("request_seq", "1_1");
		ic.addContextParameter("request_time", "20090728024911");
		ic.addContextParameter("ddr_city", "12");
		
		ic.addContextParameter("user_id", "1208200000060545");
		
		lic.setContextParameter(ic);
		
		IQuerySimNetHallCustService co = new QuerySimNetHallCustServiceClientImpl();
		QRY090015Result re = co.querySimNethallCust("1", "", "", "15250893979", "order_status_1");
		if(null != re){
			for(OrderInfo oi : re.getOrderInfos()){
			System.out.println("订单号："+oi.getOrderId());
			}
		}
	}
	
}
