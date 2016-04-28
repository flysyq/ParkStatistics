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

import java.util.Date;

import javax.persistence.*;
@Entity
public class SysAuthority {
	@Id
	@GeneratedValue
	private Long Id;
	
	private String title;
	private String code;
	private Integer flag;// 1 菜单， 2 非菜单
	private Integer grade; // 1 一级 ，2 二级
	private String uri;
	private String remark;
	private Integer fatherId;
	private String sortLevel;

	private Integer createUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	private Integer updateUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
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
	public Integer getUpdateUser() {
		return updateUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	public SysAuthority(Long id, String title, String code, Integer flag, Integer grade, String uri, String remark,
			Integer fatherId, String sortLevel, Integer createUser, Date createTime, Integer updateUser,
			Date updateTime) {
		Id = id;
		this.title = title;
		this.code = code;
		this.flag = flag;
		this.grade = grade;
		this.uri = uri;
		this.remark = remark;
		this.fatherId = fatherId;
		this.sortLevel = sortLevel;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}
	public SysAuthority() {
		super();
	}
	
}
