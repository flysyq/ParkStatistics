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
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date taskStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date searchStartTime;

	private Integer flag;//1 进行中 2 成功结束 3 异常	
	
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

	public Date getSearchStartTime() {
		return searchStartTime;
	}

	public void setSearchStartTime(Date searchStartTime) {
		this.searchStartTime = searchStartTime;
	}

	public InfoDutyShriftReportLog(Long id, Date taskStartTime, Date searchStartTime,Integer flag) {
		super();
		this.id = id;
		this.taskStartTime = taskStartTime;
		this.searchStartTime = searchStartTime;
		this.flag=flag;
	}

	
	
}
