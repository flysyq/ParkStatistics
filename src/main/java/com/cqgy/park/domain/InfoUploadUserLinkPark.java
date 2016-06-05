/**
 * @作者 admin
 * @时间 2016年5月11日 下午3:47:24
 * @类名 IUploadUserLinkPark.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月11日 下午3:47:24
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
public class InfoUploadUserLinkPark {
	@Id
	@GeneratedValue
	private Long Id;
		
	private String userCode;
	private String parkCode;
	private Integer enabled;
	private Long createUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public InfoUploadUserLinkPark(Long id, String userCode, String parkCode, Integer enabled, Long createUser,
			Date createTime) {
		Id = id;
		this.userCode = userCode;
		this.parkCode = parkCode;
		this.enabled = enabled;
		this.createUser = createUser;
		this.createTime = createTime;
	}
	public InfoUploadUserLinkPark() {
		super();
	}
	
}
