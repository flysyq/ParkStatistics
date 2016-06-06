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

import java.util.Date;

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
	private String eMmail;
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
	public String geteMmail() {
		return eMmail;
	}
	public void seteMmail(String eMmail) {
		this.eMmail = eMmail;
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
	
	

	
	
	public SysUser(Long id, String loginCode, String name, String loginPassword, Integer enabled, String eMmail,
			Long createUser, Date createTime, Long updateUser, Date updateTime) {
		super();
		Id = id;
		this.loginCode = loginCode;
		this.name = name;
		this.loginPassword = loginPassword;
		this.enabled = enabled;
		this.eMmail = eMmail;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}
	public SysUser(Long id, String loginCode, String name, String loginPassword, Integer enabled,String eMmail) {
		super();
		Id = id;
		this.loginCode = loginCode;
		this.name = name;
		this.loginPassword = loginPassword;
		this.enabled = enabled;		
		this.eMmail = eMmail;
	}
	public SysUser() {
		super();
	}		
}
