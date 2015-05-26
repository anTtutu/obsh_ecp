package com.xwtech.xwecp.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryYxfaCheckTransService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryYxfaCheckTransServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY090005Result;
import com.xwtech.xwecp.service.logic.pojo.YxfaCheckInfo;
import com.xwtech.xwecp.service.logic.pojo.YxfaIdInfo;

public class QRY090005Test {
	private static final Logger logger = Logger.getLogger(QRY070005Test.class);
	
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "test_channel");
		props.put("platform.url", "http://127.0.0.1/js_ecp/xwecp.do");
		props.put("platform.user", "zlbbq");
		props.put("platform.password", "zlbbq99");
		
		XWECPLIClient client = XWECPLIClient.createInstance(props);
		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		 lic.setBizCode("biz_code_19234");
		 lic.setOpType("开通/关闭/查询/变更");
		 lic.setUserBrand("动感地带");
		 lic.setUserCity("12");
		 lic.setUserMobile("13952395959");
		InvocationContext ic = new InvocationContext();
		//ic.addContextParameter("login_msisdn", "13913032424");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");
		//ic.addContextParameter("request_seq", "1_1");
		//ic.addContextParameter("request_time", "20090728024911");
		ic.addContextParameter("ddr_city", "12");
		
		//ic.addContextParameter("user_id", "2056200011182291");
		
		lic.setContextParameter(ic);
		 
		IQueryYxfaCheckTransService co = new QueryYxfaCheckTransServiceClientImpl();
		List<YxfaIdInfo> list =new ArrayList<YxfaIdInfo>();
		YxfaIdInfo s1=new YxfaIdInfo();
		s1.setMarketingbusiinfo_plan_id("2512102689");
		list.add(s1);
		s1=new YxfaIdInfo();
		s1.setMarketingbusiinfo_plan_id("2512102689");
		list.add(s1);
		QRY090005Result re = co.queryYxfaCheckTrans("12", "1210300002055004", list,"1");
		 
		for (YxfaCheckInfo p : re.getYxfaCheckInfo())
		{
			System.out.println("记录数：" +p.getCheck_flag());
			System.out.println("记录数：" +p.getPlan_id());
		}
		logger.info(" ====== 开始返回参数 ======");
	}
}
