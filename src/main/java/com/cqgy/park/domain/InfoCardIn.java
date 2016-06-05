package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class InfoCardIn {
	@Id
	@GeneratedValue
	private Long id;
	
	private String parkId;
	private String cardNo;
	private String plate;
	private Integer cardType;
	private Double payMoney;
	private String acceptEmpNo;
	private String acceptEmpName;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	private String remark;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public InfoCardIn(Long id, String parkId, String cardNo, String plate, Integer cardType, Double payMoney,
			String acceptEmpNo, String acceptEmpName, Date startDate, Date endDate, String remark, Date updateTime) {
		super();
		this.id = id;
		this.parkId = parkId;
		this.cardNo = cardNo;
		this.plate = plate;
		this.cardType = cardType;
		this.payMoney = payMoney;
		this.acceptEmpNo = acceptEmpNo;
		this.acceptEmpName = acceptEmpName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remark = remark;
		this.updateTime = updateTime;
	}
	public InfoCardIn() {
		super();
	}
	
}
