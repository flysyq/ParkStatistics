/**
 * @作者 admin
 * @时间 2016年4月26日 下午5:17:41
 * @类名 ActionLog.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月26日 下午5:17:41
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
public class ActionLog {
	@Id
	@GeneratedValue
	private Long id;
	
	private String loginCode;
	
	private int action;//1 登录 2 登出
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}	
	
	public ActionLog(){
		super();
	}
	public ActionLog(Long id, String loginCode, int action, Date date) {
		super();
		this.id = id;
		this.loginCode = loginCode;
		this.action = action;
		this.date = date;
	}
	
}
