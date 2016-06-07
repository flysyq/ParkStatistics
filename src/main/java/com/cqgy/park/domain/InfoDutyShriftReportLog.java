/**
 * @作者 石永强
 * @时间 2016年6月1日 上午10:44:09
 * @类名 InfoDutyShriftReportLog.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年6月1日 上午10:44:09
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
public class InfoDutyShriftReportLog {

	@Id
	@GeneratedValue
	private Long id;

	private String parkId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date searchStartTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date searchEndTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date taskStartTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date taskEndTime;

	private Integer flag;// 1 进行中 2 成功结束 3 异常

	public Date getSearchStartTime() {
		return searchStartTime;
	}

	public void setSearchStartTime(Date searchStartTime) {
		this.searchStartTime = searchStartTime;
	}

	public Date getSearchEndTime() {
		return searchEndTime;
	}

	public void setSearchEndTime(Date searchEndTime) {
		this.searchEndTime = searchEndTime;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public Date getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	
	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public InfoDutyShriftReportLog(Long id, Date searchStartTime, Date searchEndTime, Date taskStartTime,
			Date taskEndTime, Integer flag,String parkId) {
		super();
		this.id = id;
		this.searchStartTime = searchStartTime;
		this.searchEndTime = searchEndTime;
		this.taskStartTime = taskStartTime;
		this.taskEndTime = taskEndTime;
		this.flag = flag;
		this.parkId=parkId;
	}

	public InfoDutyShriftReportLog() {
		// TODO Auto-generated constructor stub
	}

	
	

}
