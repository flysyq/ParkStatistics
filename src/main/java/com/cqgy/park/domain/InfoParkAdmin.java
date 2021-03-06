/**
 * @作者 admin
 * @时间 2016年5月11日 下午3:52:21
 * @类名 IParkAdmin.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月11日 下午3:52:21
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
public class InfoParkAdmin {

	@Id
	@GeneratedValue
	private Long Id;
	
	private String parkId;
	private String userCode;
	private String empNo;
	private String empName;
	private Integer userType;
	private String userTypeName;
	private Integer isEnable;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date opTime;
	private String remark;
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
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
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
	public InfoParkAdmin(Long id, String parkId, String userCode, String empNo, String empName, Integer userType,
			String userTypeName, Integer isEnable, Date opTime, String remark, Date updateTime) {
		Id = id;
		this.parkId = parkId;
		this.userCode = userCode;
		this.empNo = empNo;
		this.empName = empName;
		this.userType = userType;
		this.userTypeName = userTypeName;
		this.isEnable = isEnable;
		this.opTime = opTime;
		this.remark = remark;
		this.updateTime = updateTime;
	}
	public InfoParkAdmin() {
		super();
	}
	
	
}
