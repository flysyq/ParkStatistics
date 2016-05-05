/**
 * @作者 admin
 * @时间 2016年4月26日 下午6:02:06
 * @类名 SysUserRoles.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月26日 下午6:02:06
 *   修改描述
 */
package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;

@Entity
public class SysUserRoles {

	@Id
	@GeneratedValue
	private Long Id;	

	private Long userId;
	private Long roleId;
	
	private Long createUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
	
	public SysUserRoles(Long id, Long userId, Long roleId) {
		super();
		Id = id;
		this.userId = userId;
		this.roleId = roleId;
	}
	public SysUserRoles(Long id, Long userId, Long roleId, Long createUser, Date createTime) {
		Id = id;
		this.userId = userId;
		this.roleId = roleId;
		this.createUser = createUser;
		this.createTime = createTime;
	}
	public SysUserRoles() {
		super();
	}
	
}
