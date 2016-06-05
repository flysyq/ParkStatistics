package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class InfoCard {
	@Id
	@GeneratedValue
	private Long Id;
	
	private String parkId;
	private String cardNo;
	private String plate;
	private Integer cardType;
	private String ownerName;
	private Double balance;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	private Double monthMoney;
	@Temporal(TemporalType.TIMESTAMP)
	private Date spreadTime;
	private String spreadEmpNo;
	private String spreadEmpName;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
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

	public Double getMonthMoney() {
		return monthMoney;
	}

	public void setMonthMoney(Double monthMoney) {
		this.monthMoney = monthMoney;
	}

	public Date getSpreadTime() {
		return spreadTime;
	}

	public void setSpreadTime(Date spreadTime) {
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

	public InfoCard(Long id, String parkId, String cardNo, String plate, Integer cardType, String ownerName,
			Double balance, Date startDate, Date endDate, Double monthMoney, Date spreadTime, String spreadEmpNo,
			String spreadEmpName) {
		super();
		Id = id;
		this.parkId = parkId;
		this.cardNo = cardNo;
		this.plate = plate;
		this.cardType = cardType;
		this.ownerName = ownerName;
		this.balance = balance;
		this.startDate = startDate;
		this.endDate = endDate;
		this.monthMoney = monthMoney;
		this.spreadTime = spreadTime;
		this.spreadEmpNo = spreadEmpNo;
		this.spreadEmpName = spreadEmpName;
	}

	public InfoCard() {
		super();
	}
	
	


}
