/**
 * @作者 admin
 * @时间 2016年4月20日 下午2:35:29
 * @类名 SysRole.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月20日 下午2:35:29
 *   修改描述
 */
package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
public class SysRole {

	@Id
	@GeneratedValue
	private Long Id;
	
	private String code;
	private String name;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Long getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	
	
	public SysRole(Long id, String code, String name, String remark) {
		super();
		Id = id;
		this.code = code;
		this.name = name;
		this.remark = remark;
	}
	public SysRole(Long id, String code, String name, String remark, Long createUser, Date createTime, Long updateUser,
			Date updateTime) {
		Id = id;
		this.code = code;
		this.name = name;
		this.remark = remark;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}
	public SysRole() {
		super();
	}	
	
}
