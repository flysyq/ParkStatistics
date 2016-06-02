/**
 * @作者 石永强
 * @时间 2016年6月1日 上午10:46:42
 * @类名 InfoDutyShriftReport.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年6月1日 上午10:46:42
 *   修改描述
 */
package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class InfoDutyShriftReport {

	@Id
	@GeneratedValue
	private Long id;
	
	private String parkId;
	private Integer empId;
	private String empNo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date onTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date downTime;
	
	private Integer comeNum;
	private Integer goNum;
	
	private Double payMoney;
	private Integer payCars;
	
	private Double freeMoney;
	private Integer freeCars;
	
	private Integer freeCardNum;
	private Double freeCardMoney;
	
	private Integer freeCheckNum;
	private Double freeCheckMoney;
	
	private Integer freePercentNum;
	private Double freePercentMoney;
	
	private Integer cardNewNum;
	private Double cardNewMoney;
	
	private Integer cardInNum;
	private Double cardInMoney;
	
	private Integer gateOpenHandNum;
	private Integer gateOpenHandExNum;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
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

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public Date getOnTime() {
		return onTime;
	}

	public void setOnTime(Date onTime) {
		this.onTime = onTime;
	}

	public Date getDownTime() {
		return downTime;
	}

	public void setDownTime(Date downTime) {
		this.downTime = downTime;
	}

	public Integer getComeNum() {
		return comeNum;
	}

	public void setComeNum(Integer comeNum) {
		this.comeNum = comeNum;
	}

	public Integer getGoNum() {
		return goNum;
	}

	public void setGoNum(Integer goNum) {
		this.goNum = goNum;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public Integer getPayCars() {
		return payCars;
	}

	public void setPayCars(Integer payCars) {
		this.payCars = payCars;
	}

	public Double getFreeMoney() {
		return freeMoney;
	}

	public void setFreeMoney(Double freeMoney) {
		this.freeMoney = freeMoney;
	}

	public Integer getFreeCars() {
		return freeCars;
	}

	public void setFreeCars(Integer freeCars) {
		this.freeCars = freeCars;
	}

	public Integer getFreeCardNum() {
		return freeCardNum;
	}

	public void setFreeCardNum(Integer freeCardNum) {
		this.freeCardNum = freeCardNum;
	}

	public Double getFreeCardMoney() {
		return freeCardMoney;
	}

	public void setFreeCardMoney(Double freeCardMoney) {
		this.freeCardMoney = freeCardMoney;
	}

	public Integer getFreeCheckNum() {
		return freeCheckNum;
	}

	public void setFreeCheckNum(Integer freeCheckNum) {
		this.freeCheckNum = freeCheckNum;
	}

	public Double getFreeCheckMoney() {
		return freeCheckMoney;
	}

	public void setFreeCheckMoney(Double freeCheckMoney) {
		this.freeCheckMoney = freeCheckMoney;
	}

	public Integer getFreePercentNum() {
		return freePercentNum;
	}

	public void setFreePercentNum(Integer freePercentNum) {
		this.freePercentNum = freePercentNum;
	}

	public Double getFreePercentMoney() {
		return freePercentMoney;
	}

	public void setFreePercentMoney(Double freePercentMoney) {
		this.freePercentMoney = freePercentMoney;
	}

	public Integer getCardNewNum() {
		return cardNewNum;
	}

	public void setCardNewNum(Integer cardNewNum) {
		this.cardNewNum = cardNewNum;
	}

	public Double getCardNewMoney() {
		return cardNewMoney;
	}

	public void setCardNewMoney(Double cardNewMoney) {
		this.cardNewMoney = cardNewMoney;
	}

	public Integer getCardInNum() {
		return cardInNum;
	}

	public void setCardInNum(Integer cardInNum) {
		this.cardInNum = cardInNum;
	}

	public Double getCardInMoney() {
		return cardInMoney;
	}

	public void setCardInMoney(Double cardInMoney) {
		this.cardInMoney = cardInMoney;
	}

	public Integer getGateOpenHandNum() {
		return gateOpenHandNum;
	}

	public void setGateOpenHandNum(Integer gateOpenHandNum) {
		this.gateOpenHandNum = gateOpenHandNum;
	}

	public Integer getGateOpenHandExNum() {
		return gateOpenHandExNum;
	}

	public void setGateOpenHandExNum(Integer gateOpenHandExNum) {
		this.gateOpenHandExNum = gateOpenHandExNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
	
}
