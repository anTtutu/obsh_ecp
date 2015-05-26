package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.xwtech.xwecp.service.logic.pojo.TransProPackageBusi;

/**
 * 查询用户新主题产品下增值产品树关系(校园4G专用)
 * @author 张斌
 * 2015-5-4
 */
public class QRY050079Result extends BaseServiceInvocationResult
{
	//必选套餐
	private Map<String,UsrTransSelectBean> bxtcMap = new HashMap<String, UsrTransSelectBean>();
	//增值业务
	private List<TransProPackageBusi> zzyw = new ArrayList<TransProPackageBusi>();
	//删除业务
	private List<TransProPackageBusi> scyw = new ArrayList<TransProPackageBusi>();
	
	public Map<String, UsrTransSelectBean> getBxtcMap() {
		return bxtcMap;
	}

	public void setBxtcMap(Map<String, UsrTransSelectBean> bxtcMap) {
		this.bxtcMap = bxtcMap;
	}

	public List<TransProPackageBusi> getZzyw() {
		return zzyw;
	}

	public void setZzyw(List<TransProPackageBusi> zzyw) {
		this.zzyw = zzyw;
	}

	public List<TransProPackageBusi> getScyw() {
		return scyw;
	}

	public void setScyw(List<TransProPackageBusi> scyw) {
		this.scyw = scyw;
	}

}