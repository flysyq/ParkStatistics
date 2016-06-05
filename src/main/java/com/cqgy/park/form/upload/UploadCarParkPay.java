package com.cqgy.park.form.upload;

public class UploadCarParkPay {
	private UploadHead head;
	private UploadCarParkPayParameter parameter;
	public UploadHead getHead() {
		return head;
	}
	public void setHead(UploadHead head) {
		this.head = head;
	}
	public UploadCarParkPayParameter getParameter() {
		return parameter;
	}
	public void setParameter(UploadCarParkPayParameter parameter) {
		this.parameter = parameter;
	}
	public UploadCarParkPay(UploadHead head, UploadCarParkPayParameter parameter) {
		super();
		this.head = head;
		this.parameter = parameter;
	}
	public UploadCarParkPay() {
		super();
	}
	
	
}
