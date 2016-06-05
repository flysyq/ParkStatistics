package com.cqgy.park.form.upload;

public class UploadUserLoginLog {
	private UploadHead head;
	private UploadUserLoginLogParameter parameter;
	public UploadHead getHead() {
		return head;
	}
	public void setHead(UploadHead head) {
		this.head = head;
	}
	public UploadUserLoginLogParameter getParameter() {
		return parameter;
	}
	public void setParameter(UploadUserLoginLogParameter parameter) {
		this.parameter = parameter;
	}
	public UploadUserLoginLog(UploadHead head, UploadUserLoginLogParameter parameter) {
		super();
		this.head = head;
		this.parameter = parameter;
	}
	public UploadUserLoginLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
