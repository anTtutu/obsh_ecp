package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.msg.RequestData;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.msg.ResponseData;
import com.xwtech.xwecp.msg.ServiceMessage;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.client.BaseClientServiceImpl;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IResidualFlowRelationService;
import com.xwtech.xwecp.service.logic.pojo.DEL040081Result;
/**
 * DEL040081的连接处理类
 * @author 陶刚
 *
 */
public class ResidualFlowRelationServiceClientImpl extends BaseClientServiceImpl implements IResidualFlowRelationService{

	public DEL040081Result residualFlowRelation(String phoneNum, String phone,
			String type, String opertype) throws LIException {
		long __beginTime = System.currentTimeMillis();
		String __cmd = "DEL040081";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_phoneNum = new RequestParameter("msisdn", phoneNum);
		__requestData.getParams().add(__param_phoneNum);
		RequestParameter __param_subMsisdn = new RequestParameter("subMsisdn", phone);
		__requestData.getParams().add(__param_subMsisdn);
		RequestParameter __param_opertype = new RequestParameter("operType", opertype);
		__requestData.getParams().add(__param_opertype);
		RequestParameter __param_type = new RequestParameter("type", type);
		__requestData.getParams().add(__param_type);
		RequestParameter __param_prodid = new RequestParameter("prodid", "2400000287");
		__requestData.getParams().add(__param_prodid);
		__msg.setData(__requestData);
		__msg.getHead().setUser(LIInvocationContext.USER);
		__msg.getHead().setPwd(LIInvocationContext.PWD);
		LIInvocationContext __ic = LIInvocationContext.getContext();
		if(__ic != null)
		{
			__msg.getHead().setOpType(__ic.getOpType());
			__msg.getHead().setUserMobile(__ic.getUserMobile());
			__msg.getHead().setUserCity(__ic.getUserCity());
			__msg.getHead().setBizCode(__ic.getBizCode());
			__msg.getHead().setUserBrand(__ic.getUserBrand());
			__msg.getHead().setOperId(__ic.getOperId());
			((RequestData)(__msg.getData())).setContext(__ic.getContextParameter());
		}
		String __requestXML = __msg.toString();
		DEL040081Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof DEL040081Result)
			{
				__result = (DEL040081Result)__ret;
			}
			else
			{
				__result = new DEL040081Result();
				__result.setResultCode(__ret.getResultCode());
				__result.setErrorCode(__ret.getErrorCode());
				__result.setErrorMessage(__ret.getErrorMessage());
			}
		}
		catch(Exception __e)
		{
			__errorStack = __e;
			throw new LIException(__e);
		}
		finally
		{
			long __endTime = System.currentTimeMillis();
			__client.log(__cmd, __client.getPlatformConnection().getRemoteURL(), __requestXML, __responseXML, __msg, __response, __beginTime, __endTime, __errorStack);
		}
		return __result;
	}
}
