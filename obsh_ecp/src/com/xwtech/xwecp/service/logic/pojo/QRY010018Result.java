package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import java.util.List;
import java.util.ArrayList;
import com.xwtech.xwecp.service.logic.pojo.FeeDetail;

public class QRY010018Result extends BaseServiceInvocationResult
{
	private Long mainprodId;

	private String mainprodName;

	private String accTid;

	private String subSid;

	private Integer cycle;

	private Long totalFee;

	private Long otherPay;

	private Long groupPay;

	private Long rentFee;

	private List<FeeDetail> feeDetailList = new ArrayList<FeeDetail>();
	
	
	private List<QRY010018Result> qry010018List = new ArrayList<QRY010018Result>();
	// 新增字段
	private String userName;		// 用户姓名
	private String userMobile;		// 用户手机号码
	private String isHouse;			// 是否是家庭用户

	public String getIsHouse() {
		return isHouse;
	}

	public void setIsHouse(String isHouse) {
		this.isHouse = isHouse;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public void setMainprodId(Long mainprodId)
	{
		this.mainprodId = mainprodId;
	}

	public Long getMainprodId()
	{
		return this.mainprodId;
	}

	public void setMainprodName(String mainprodName)
	{
		this.mainprodName = mainprodName;
	}

	public String getMainprodName()
	{
		return this.mainprodName;
	}

	public void setAccTid(String accTid)
	{
		this.accTid = accTid;
	}

	public String getAccTid()
	{
		return this.accTid;
	}

	public void setSubSid(String subSid)
	{
		this.subSid = subSid;
	}

	public String getSubSid()
	{
		return this.subSid;
	}

	public void setCycle(Integer cycle)
	{
		this.cycle = cycle;
	}

	public Integer getCycle()
	{
		return this.cycle;
	}

	public void setTotalFee(Long totalFee)
	{
		this.totalFee = totalFee;
	}

	public Long getTotalFee()
	{
		return this.totalFee;
	}

	public void setOtherPay(Long otherPay)
	{
		this.otherPay = otherPay;
	}

	public Long getOtherPay()
	{
		return this.otherPay;
	}

	public void setGroupPay(Long groupPay)
	{
		this.groupPay = groupPay;
	}

	public Long getGroupPay()
	{
		return this.groupPay;
	}

	public void setRentFee(Long rentFee)
	{
		this.rentFee = rentFee;
	}

	public Long getRentFee()
	{
		return this.rentFee;
	}

	public void setFeeDetailList(List<FeeDetail> feeDetailList)
	{
		this.feeDetailList = feeDetailList;
	}

	public List<FeeDetail> getFeeDetailList()
	{
		return this.feeDetailList;
	}

	public List<QRY010018Result> getQry010018List() {
		return qry010018List;
	}

	public void setQry010018List(List<QRY010018Result> qry010018List) {
		this.qry010018List = qry010018List;
	}

}