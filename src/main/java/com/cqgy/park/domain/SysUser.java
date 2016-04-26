/**
 * @作者 admin
 * @时间 2016年4月20日 下午2:41:56
 * @类名 SysUser.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月20日 下午2:41:56
 *   修改描述
 */
package com.cqgy.park.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

@Entity
public class SysUser {

	@Id
	@GeneratedValue
	private Long Id;
		
	private String loginCode;
	private String name;
	private String loginPassword;
	private Integer enabled;
	private Long createUser;
	private Timestamp createTime;
	private Long updateUser;
	private Timestamp updateTime;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}	
}
