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
import java.util.List;

import javax.persistence.*;

@Entity
public class SysUser {

	@Id
	@GeneratedValue
	private Long Id;
	
	@ManyToMany(cascade = {CascadeType.REFRESH}, fetch=FetchType.EAGER)
	private List<SysRole> roles;
	
	@ManyToMany(cascade = {CascadeType.REFRESH}, fetch=FetchType.EAGER)
	private List<SysAuthority> authoritys;
	
	private String loginCode;
	private String name;
	private String loginPassword;
	private Integer enabled;
	private Integer createUser;
	private Date createTime;
	private Integer updateUser;
	private Date updateTime;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public List<SysRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}
	public List<SysAuthority> getAuthoritys() {
		return authoritys;
	}
	public void setAuthoritys(List<SysAuthority> authoritys) {
		this.authoritys = authoritys;
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
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
}
