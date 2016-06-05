package com.cqgy.park.form.upload;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class UploadCardParameter {
	private String cardNo;
	private String plate;
	private Integer cardType;
	private String ownerName;
	private Double cardMoney;
	private String startDate;
	private String endDate;
	private Double umMoney;
	private String spreadTime;
	private String spreadEmpNo;
	private String spreadEmpName;
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
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public Double getCardMoney() {
		return cardMoney;
	}
	public void setCardMoney(Double cardMoney) {
		this.cardMoney = cardMoney;
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
	public Double getUmMoney() {
		return umMoney;
	}
	public void setUmMoney(Double umMoney) {
		this.umMoney = umMoney;
	}
	public String getSpreadTime() {
		return spreadTime;
	}
	public void setSpreadTime(String spreadTime) {
		this.spreadTime = spreadTime;
	}
	public String getSpreadEmpNo() {
		return spreadEmpNo;
	}
	public void setSpreadEmpNo(String spreadEmpNo) {
		this.spreadEmpNo = spreadEmpNo;
	}
	public String getSpreadEmpName() {
		return spreadEmpName;
	}
	public void setSpreadEmpName(String spreadEmpName) {
		this.spreadEmpName = spreadEmpName;
	}
	public UploadCardParameter(String cardNo, String plate, Integer cardType, String ownerName, Double cardMoney,
			String startDate, String endDate, Double umMoney, String spreadTime, String spreadEmpNo,
			String spreadEmpName) {
		super();
		this.cardNo = cardNo;
		this.plate = plate;
		this.cardType = cardType;
		this.ownerName = ownerName;
		this.cardMoney = cardMoney;
		this.startDate = startDate;
		this.endDate = endDate;
		this.umMoney = umMoney;
		this.spreadTime = spreadTime;
		this.spreadEmpNo = spreadEmpNo;
		this.spreadEmpName = spreadEmpName;
	}
	public UploadCardParameter() {
		super();
	}
	
}
