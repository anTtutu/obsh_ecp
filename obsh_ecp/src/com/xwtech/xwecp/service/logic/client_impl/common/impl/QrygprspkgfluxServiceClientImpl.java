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
import com.xwtech.xwecp.service.logic.client_impl.common.IQrygprspkgfluxService;
import com.xwtech.xwecp.service.logic.pojo.QRY040089Result;
import com.xwtech.xwecp.service.logic.pojo.QRY040089Result;

/**
 * GPRS套餐流量查询
 * @author YangXQ
 * 2014-11-04
 */
public class QrygprspkgfluxServiceClientImpl extends BaseClientServiceImpl implements IQrygprspkgfluxService
{
	public QRY040089Result queryOrderStatus(String subsid,String cycle,String cdrtype,String interfacetype) throws LIException
	{
		long __beginTime = System.currentTimeMillis();
		String __cmd = "QRY040089";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_subsid = new RequestParameter("subsid", subsid);
		__requestData.getParams().add(__param_subsid);
		RequestParameter __param_cycle = new RequestParameter("cycle", cycle);
		__requestData.getParams().add(__param_cycle);
		RequestParameter __param_cdrtype = new RequestParameter("cdrtype", cdrtype);
		__requestData.getParams().add(__param_cdrtype);
		RequestParameter __param_interfacetype = new RequestParameter("interfacetype", interfacetype);
		__requestData.getParams().add(__param_interfacetype);
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
		QRY040089Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof QRY040089Result)
			{
				__result = (QRY040089Result)__ret;
			}
			else
			{
				__result = new QRY040089Result();
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