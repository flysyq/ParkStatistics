package com.cqgy.park.form.upload;

public class UploadCardIn {
	private UploadHead head;
	private UploadCardInParameter parameter;
	public UploadHead getHead() {
		return head;
	}
	public void setHead(UploadHead head) {
		this.head = head;
	}
	public UploadCardInParameter getParameter() {
		return parameter;
	}
	public void setParameter(UploadCardInParameter parameter) {
		this.parameter = parameter;
	}
	public UploadCardIn(UploadHead head, UploadCardInParameter parameter) {
		super();
		this.head = head;
		this.parameter = parameter;
	}
	public UploadCardIn() {
		super();
	}
	
}
