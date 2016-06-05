/**
 * @作者 admin
 * @时间 2016年5月16日 上午11:54:03
 * @类名 InfoLogUpload.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月16日 上午11:54:03
 *   修改描述
 */
package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class InfoLogUpload {
	@Id
	@GeneratedValue
	private Long id;
	
	private String functionId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadTime;
	
	@Lob
	@Basic
	@Column(length = 16777215) 
	private String uploadContent;
	
	private String code;
	private String message;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getUploadContent() {
		return uploadContent;
	}
	public void setUploadContent(String uploadContent) {
		this.uploadContent = uploadContent;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public InfoLogUpload(Long id, String functionId, Date uploadTime, String uploadContent, String code,
			String message) {
		this.id = id;
		this.functionId = functionId;
		this.uploadTime = uploadTime;
		this.uploadContent = uploadContent;
		this.code = code;
		this.message = message;
	}
	public InfoLogUpload() {
		super();
	}
	
	
	
	
}
