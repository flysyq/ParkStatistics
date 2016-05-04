/**
 * @作者 admin
 * @时间 2016年4月21日 上午11:59:41
 * @类名 SysUserAuthoritys.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月21日 上午11:59:41
 *   修改描述
 */
package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
public class SysUserAuthoritys {
	
	@Id
	@GeneratedValue
	private Long Id;
	
	private Long UserId;
	private Long authorityId;
	
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
		return UserId;
	}
	public void setUserId(Long userId) {
		UserId = userId;
	}	
	public Long getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
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
	public SysUserAuthoritys(Long id, Long userId, Long authorityId, Long createUser, Date createTime) {
		super();
		Id = id;
		UserId = userId;
		this.authorityId = authorityId;
		this.createUser = createUser;
		this.createTime = createTime;
	}
	public SysUserAuthoritys() {
		super();
	}
}
