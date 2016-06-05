package com.cqgy.park.form.upload;

public class UploadGateOpenHand {

	private UploadHead head;
	private UploadGateOpenHandParameter parameter;
	public UploadHead getHead() {
		return head;
	}
	public void setHead(UploadHead head) {
		this.head = head;
	}
	public UploadGateOpenHandParameter getParameter() {
		return parameter;
	}
	public void setParameter(UploadGateOpenHandParameter parameter) {
		this.parameter = parameter;
	}
	public UploadGateOpenHand(UploadHead head, UploadGateOpenHandParameter parameter) {
		this.head = head;
		this.parameter = parameter;
	}
	public UploadGateOpenHand() {
		super();
	}
	
}
