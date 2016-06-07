/**
 * @作者 石永强
 * @时间 2016年6月7日 上午9:41:35
 * @类名 InfoMailLog.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年6月7日 上午9:41:35
 *   修改描述
 */
package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class InfoMailLog {

	@javax.persistence.Id
	@GeneratedValue
	private Long Id;
	
	private Long userId;
	private String userCode;
	private String userMail;
	private Integer mailState;
	private String resultDesc;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Integer getMailState() {
		return mailState;
	}

	public void setMailState(Integer mailState) {
		this.mailState = mailState;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
}
