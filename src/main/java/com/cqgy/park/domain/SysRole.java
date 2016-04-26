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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

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
	private Timestamp createTime;
	private Long updateUser;
	private Timestamp updateTime;
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
