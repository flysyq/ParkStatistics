/**
 * @作者 admin
 * @时间 2016年4月20日 下午2:30:37
 * @类名 SysAuthority.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月20日 下午2:30:37
 *   修改描述
 */
package com.cqgy.park.domain;

import javax.persistence.Entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;
@Entity
public class SysAuthority {
	@Id
	@GeneratedValue
	private Long Id;
	
	private String title;
	private String code;
	private Integer flag;
	private Integer grade;
	private String uri;
	private String remark;
	private Integer fatherId;
	private String sortLevel;

	private Integer createUser;
	private Timestamp createTime;
	private Integer updateUser;
	private Timestamp updateTime;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getFatherId() {
		return fatherId;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	public String getSortLevel() {
		return sortLevel;
	}
	public void setSortLevel(String sortLevel) {
		this.sortLevel = sortLevel;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}	
	
}
