package com.cqgy.park.form.upload;

public class UploadParkAdmin {

	private UploadHead head;
	private UploadParkAdminParameter parameter;
	public UploadHead getHead() {
		return head;
	}
	public void setHead(UploadHead head) {
		this.head = head;
	}
	public UploadParkAdminParameter getParameter() {
		return parameter;
	}
	public void setParameter(UploadParkAdminParameter parameter) {
		this.parameter = parameter;
	}
	public UploadParkAdmin(UploadHead head, UploadParkAdminParameter parameter) {
		this.head = head;
		this.parameter = parameter;
	}
	public UploadParkAdmin() {
		super();
	}
	
}
