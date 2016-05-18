package com.cqgy.park.form.upload;

public class UploadCardInParameter {
	private String cardNo;
	private String plate;
	private Integer cardType;
	private Double payMoney;
	private String startDate;
	private String endDate;
	private String acceptEmpNo;
	private String acceptEmpName;
	private String remark;
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAcceptEmpNo() {
		return acceptEmpNo;
	}
	public void setAcceptEmpNo(String acceptEmpNo) {
		this.acceptEmpNo = acceptEmpNo;
	}
	public String getAcceptEmpName() {
		return acceptEmpName;
	}
	public void setAcceptEmpName(String acceptEmpName) {
		this.acceptEmpName = acceptEmpName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public UploadCardInParameter(String cardNo, String plate, Integer cardType, Double payMoney, String startDate,
			String endDate, String acceptEmpNo, String acceptEmpName, String remark) {
		super();
		this.cardNo = cardNo;
		this.plate = plate;
		this.cardType = cardType;
		this.payMoney = payMoney;
		this.startDate = startDate;
		this.endDate = endDate;
		this.acceptEmpNo = acceptEmpNo;
		this.acceptEmpName = acceptEmpName;
		this.remark = remark;
	}
	public UploadCardInParameter() {
		super();
	}
	
}
