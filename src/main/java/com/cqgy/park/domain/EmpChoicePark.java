package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class EmpChoicePark {
	@Id
	@GeneratedValue
	private Long id;
	private Long userId;
	private Long parkId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	private Long createUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getParkId() {
		return parkId;
	}

	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public EmpChoicePark(Long id, Long userId, Long parkId, Date createTime, Long createUser) {
		super();
		this.id = id;
		this.userId = userId;
		this.parkId = parkId;
		this.createTime = createTime;
		this.createUser = createUser;
	}

	public EmpChoicePark() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	



}
