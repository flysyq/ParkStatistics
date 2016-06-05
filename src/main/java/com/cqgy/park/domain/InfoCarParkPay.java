package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class InfoCarParkPay {
	@Id
	@GeneratedValue
	private Long id;

	private String parkId;
	private String cardNo;
	private String plate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	private Double realPay;
	private Double fee;
	private Integer cardType;
	private Integer payType;
	private Double feeFree;
	private Integer freeType;
	private String empNo;
	private String empName;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Double getRealPay() {
		return realPay;
	}
	public void setRealPay(Double realPay) {
		this.realPay = realPay;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
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
	public Double getFeeFree() {
		return feeFree;
	}
	public void setFeeFree(Double feeFree) {
		this.feeFree = feeFree;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public InfoCarParkPay(Long id, String parkId, String cardNo, String plate, Date startTime, Date endTime,
			Double realPay, Double fee, Integer cardType, Integer payType, Double feeFree, Integer freeType,
			String empNo, String empName, Date updateTime) {
		super();
		this.id = id;
		this.parkId = parkId;
		this.cardNo = cardNo;
		this.plate = plate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.realPay = realPay;
		this.fee = fee;
		this.cardType = cardType;
		this.payType = payType;
		this.feeFree = feeFree;
		this.freeType = freeType;
		this.empNo = empNo;
		this.empName = empName;
		this.updateTime = updateTime;
	}
	public InfoCarParkPay() {
		super();
	}
	
	
}
