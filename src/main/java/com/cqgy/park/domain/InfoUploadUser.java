/**
 * @作者 admin
 * @时间 2016年5月11日 下午3:44:55
 * @类名 IUploadUser.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月11日 下午3:44:55
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
public class InfoUploadUser {
	@Id
	@GeneratedValue
	private Long Id;
		
	private String loginCode;
	private String loginPassword;
	private Integer enabled;
	private String remark;
	private Long createUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	private Long updateUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public InfoUploadUser(Long id, String loginCode, String loginPassword, Integer enabled, String remark, Long createUser,
			Date createTime, Long updateUser, Date updateTime) {
		Id = id;
		this.loginCode = loginCode;
		this.loginPassword = loginPassword;
		this.enabled = enabled;
		this.remark = remark;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}
	public InfoUploadUser() {
		super();
	}		
	
}
