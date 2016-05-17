package com.cqgy.park.form.upload;

public class UploadGateOpenHand {

	private UploadHead head;
	private InfoGateOpenHandParameter parameter;
	public UploadHead getHead() {
		return head;
	}
	public void setHead(UploadHead head) {
		this.head = head;
	}
	public InfoGateOpenHandParameter getParameter() {
		return parameter;
	}
	public void setParameter(InfoGateOpenHandParameter parameter) {
		this.parameter = parameter;
	}
	public UploadGateOpenHand(UploadHead head, InfoGateOpenHandParameter parameter) {
		this.head = head;
		this.parameter = parameter;
	}
	public UploadGateOpenHand() {
		super();
	}
	
}
