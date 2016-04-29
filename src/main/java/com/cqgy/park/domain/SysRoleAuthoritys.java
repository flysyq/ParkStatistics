/**
 * @作者 admin
 * @时间 2016年4月26日 下午6:02:32
 * @类名 SysRoleAuthoritys.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月26日 下午6:02:32
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
public class SysRoleAuthoritys {

	@Id
	@GeneratedValue
	private Long Id;
	
	private Long UserId;
	private Long authorityId;
	
	private Integer createUser;
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
	public SysRoleAuthoritys(Long id, Long userId, Long authorityId, Integer createUser, Date createTime) {
		Id = id;
		UserId = userId;
		this.authorityId = authorityId;
		this.createUser = createUser;
		this.createTime = createTime;
	}
	public SysRoleAuthoritys() {
		super();
	}	
}
