package com.cqgy.park.form.upload;

public class UploadCarParkPayParameter {
	private String cardNo;
	private String plate;
	private Integer cardType;
	private Integer payType;
	private String startTime;
	private String endTime;
	private Double fee;
	private Double realPay;
	private Double freeFee;
	private Integer freeType;
	private String empNo;
	private String empName;
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
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Double getRealPay() {
		return realPay;
	}
	public void setRealPay(Double realPay) {
		this.realPay = realPay;
	}
	public Double getFreeFee() {
		return freeFee;
	}
	public void setFreeFee(Double freeFee) {
		this.freeFee = freeFee;
	}
	public Integer getFreeType() {
		return freeType;
	}
	public void setFreeType(Integer freeType) {
		this.freeType = freeType;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public UploadCarParkPayParameter(String cardNo, String plate, Integer cardType, Integer payType, String startTime,
			String endTime, Double fee, Double realPay, Double freeFee, Integer freeType, String empNo,
			String empName) {
		super();
		this.cardNo = cardNo;
		this.plate = plate;
		this.cardType = cardType;
		this.payType = payType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.fee = fee;
		this.realPay = realPay;
		this.freeFee = freeFee;
		this.freeType = freeType;
		this.empNo = empNo;
		this.empName = empName;
	}
	public UploadCarParkPayParameter() {
		super();
	}
	
	
}
