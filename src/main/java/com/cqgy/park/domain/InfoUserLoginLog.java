package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class InfoUserLoginLog {

	@Id
	@GeneratedValue
	private Long Id;
	
	private String parkId;
	private String userCode;
	private String userEmpNo;
	private String empName;
	private Integer userType;
	private String userTypeName;
	private Integer opType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date opTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
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
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserEmpNo() {
		return userEmpNo;
	}
	public void setUserEmpNo(String userEmpNo) {
		this.userEmpNo = userEmpNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public Integer getOpType() {
		return opType;
	}
	public void setOpType(Integer opType) {
		this.opType = opType;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public InfoUserLoginLog(Long id, String parkId, String userCode, String userEmpNo, String empName, Integer userType,
			String userTypeName, Integer opType, Date opTime, Date updateTime) {
		super();
		Id = id;
		this.parkId = parkId;
		this.userCode = userCode;
		this.userEmpNo = userEmpNo;
		this.empName = empName;
		this.userType = userType;
		this.userTypeName = userTypeName;
		this.opType = opType;
		this.opTime = opTime;
		this.updateTime = updateTime;
	}
	public InfoUserLoginLog() {
		super();
	}
	
	
}
