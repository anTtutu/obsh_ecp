package com.xwtech.xwecp.test;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IFamilyProdDelService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.FamilyProdDelServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL610043Result;

/**
 * 家庭V网短号套餐注消
 * @author Administrator
 *
 */
public class DEL610043Test
{
	private static final Logger logger = Logger.getLogger(DEL610043Test.class);
	
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
		lic.setUserBrand("12");
		lic.setUserCity("12");
		lic.setUserMobile("15005232099");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "15005232099");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");
		ic.addContextParameter("ddr_city", "12");
		//ic.addContextParameter("user_id", "1208200008843825");
		
		lic.setContextParameter(ic);
		
		IFamilyProdDelService famDel = new FamilyProdDelServiceClientImpl();
		//主号用户编号,增值产品包编码
		DEL610043Result re = famDel.familyProdDel("1208200011660016", "2012000007");
		if(re != null)
		{
			logger.error("===========错误编号============" + re.getErrorCode());
			logger.error("===========错误信息============" + re.getErrorMessage());
		}
		
	}
}
