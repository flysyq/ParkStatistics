package com.cqgy.park.form.upload;

public class UploadParkEmp {
	private UploadHead head;
	private UploadParkEmpParameter parameter;
	public UploadHead getHead() {
		return head;
	}
	public void setHead(UploadHead head) {
		this.head = head;
	}
	public UploadParkEmpParameter getParameter() {
		return parameter;
	}
	public void setParameter(UploadParkEmpParameter parameter) {
		this.parameter = parameter;
	}
	public UploadParkEmp(UploadHead head, UploadParkEmpParameter parameter) {
		super();
		this.head = head;
		this.parameter = parameter;
	}
	public UploadParkEmp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
