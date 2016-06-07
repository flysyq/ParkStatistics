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
	
	private Long comeNum;
	private Long goNum;
	
	private Double payMoney;
	private Long payCars;
	
	private Double freeMoney;
	private Long freeCars;
	
	private Long freeCardNum;
	private Double freeCardMoney;
	
	private Long freeCheckNum;
	private Double freeCheckMoney;
	
	private Long freePercentNum;
	private Double freePercentMoney;
	
	private Long cardNewNum;
	private Double cardNewMoney;
	
	private Long cardInNum;
	private Double cardInMoney;
	
	private Long gateOpenHandNum;
	private Long gateOpenHandExNum;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	private String pdfName;
	
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

	public Long getComeNum() {
		return comeNum;
	}

	public void setComeNum(Long comeNum) {
		this.comeNum = comeNum;
	}

	public Long getGoNum() {
		return goNum;
	}

	public void setGoNum(Long goNum) {
		this.goNum = goNum;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public Long getPayCars() {
		return payCars;
	}

	public void setPayCars(Long payCars) {
		this.payCars = payCars;
	}

	public Double getFreeMoney() {
		return freeMoney;
	}

	public void setFreeMoney(Double freeMoney) {
		this.freeMoney = freeMoney;
	}

	public Long getFreeCars() {
		return freeCars;
	}

	public void setFreeCars(Long freeCars) {
		this.freeCars = freeCars;
	}

	public Long getFreeCardNum() {
		return freeCardNum;
	}

	public void setFreeCardNum(Long freeCardNum) {
		this.freeCardNum = freeCardNum;
	}

	public Double getFreeCardMoney() {
		return freeCardMoney;
	}

	public void setFreeCardMoney(Double freeCardMoney) {
		this.freeCardMoney = freeCardMoney;
	}

	public Long getFreeCheckNum() {
		return freeCheckNum;
	}

	public void setFreeCheckNum(Long freeCheckNum) {
		this.freeCheckNum = freeCheckNum;
	}

	public Double getFreeCheckMoney() {
		return freeCheckMoney;
	}

	public void setFreeCheckMoney(Double freeCheckMoney) {
		this.freeCheckMoney = freeCheckMoney;
	}

	public Long getFreePercentNum() {
		return freePercentNum;
	}

	public void setFreePercentNum(Long freePercentNum) {
		this.freePercentNum = freePercentNum;
	}

	public Double getFreePercentMoney() {
		return freePercentMoney;
	}

	public void setFreePercentMoney(Double freePercentMoney) {
		this.freePercentMoney = freePercentMoney;
	}

	public Long getCardNewNum() {
		return cardNewNum;
	}

	public void setCardNewNum(Long cardNewNum) {
		this.cardNewNum = cardNewNum;
	}

	public Double getCardNewMoney() {
		return cardNewMoney;
	}

	public void setCardNewMoney(Double cardNewMoney) {
		this.cardNewMoney = cardNewMoney;
	}

	public Long getCardInNum() {
		return cardInNum;
	}

	public void setCardInNum(Long cardInNum) {
		this.cardInNum = cardInNum;
	}

	public Double getCardInMoney() {
		return cardInMoney;
	}

	public void setCardInMoney(Double cardInMoney) {
		this.cardInMoney = cardInMoney;
	}

	public Long getGateOpenHandNum() {
		return gateOpenHandNum;
	}

	public void setGateOpenHandNum(Long gateOpenHandNum) {
		this.gateOpenHandNum = gateOpenHandNum;
	}

	public Long getGateOpenHandExNum() {
		return gateOpenHandExNum;
	}

	public void setGateOpenHandExNum(Long gateOpenHandExNum) {
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

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public InfoDutyShriftReport() {
		this.comeNum = (long)0;
		this.goNum = (long)0;
		
		this.payMoney=(double)0;
		this.payCars=0L;
		
		this.freeMoney=0.0;
		this.freeCars=0L;
		
		this.freeCardNum=0L;
		this.freeCardMoney=0.0;
		
		this.freeCheckNum=0L;
		this.freeCheckMoney=0.0;
		
		this.freePercentNum=0L;
		this.freePercentMoney=0.0;
		
		this.cardNewNum=0L;
		this.cardNewMoney=0.0;
		
		this.cardInNum=0L;
		this.cardInMoney=0.0;
		
		this.gateOpenHandNum=0L;
		this.gateOpenHandExNum=0L;
	}

	
	
}
