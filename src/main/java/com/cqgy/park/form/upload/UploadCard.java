package com.cqgy.park.form.upload;

public class UploadCard {
	private UploadHead head;
	private UploadCardParameter parameter;
	public UploadHead getHead() {
		return head;
	}
	public void setHead(UploadHead head) {
		this.head = head;
	}
	public UploadCardParameter getParameter() {
		return parameter;
	}
	public void setParameter(UploadCardParameter parameter) {
		this.parameter = parameter;
	}
	public UploadCard(UploadHead head, UploadCardParameter parameter) {
		super();
		this.head = head;
		this.parameter = parameter;
	}
	public UploadCard() {
		super();
	}
	
}
