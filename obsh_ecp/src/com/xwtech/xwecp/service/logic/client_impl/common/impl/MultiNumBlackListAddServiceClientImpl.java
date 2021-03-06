package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.List;

import com.xwtech.xwecp.msg.RequestData;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.msg.ResponseData;
import com.xwtech.xwecp.msg.ServiceMessage;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.client.BaseClientServiceImpl;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IMultiNumBlackListAddService;
import com.xwtech.xwecp.service.logic.pojo.BlackListInfo;
import com.xwtech.xwecp.service.logic.pojo.DEL040061Result;

public class MultiNumBlackListAddServiceClientImpl extends
		BaseClientServiceImpl implements IMultiNumBlackListAddService {

	public DEL040061Result multiNumBlackListAdd(String userCity,
			String userSid,String vnumSid, List<BlackListInfo> blackListInfo)
			throws LIException {
		long __beginTime = System.currentTimeMillis();
		String __cmd = "DEL040061";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_userCity = new RequestParameter("userCity", userCity);
		__requestData.getParams().add(__param_userCity);
		RequestParameter __param_userSid = new RequestParameter("userSid", userSid);
		__requestData.getParams().add(__param_userSid);
		RequestParameter __param_vnumSid = new RequestParameter("vnumSid", vnumSid);
		__requestData.getParams().add(__param_vnumSid);
		RequestParameter __param_blackListInfo = new RequestParameter("blackListInfo", blackListInfo);
		__requestData.getParams().add(__param_blackListInfo);
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
		DEL040061Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof DEL040061Result)
			{
				__result = (DEL040061Result)__ret;
			}
			else
			{
				__ret = new DEL040061Result();
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
