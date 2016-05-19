package com.cqgy.park.form.upload;

public class UploadCarIo {
 private UploadHead head;
 private UploadCarIoParameter parameter;
public UploadHead getHead() {
	return head;
}
public void setHead(UploadHead head) {
	this.head = head;
}
public UploadCarIoParameter getParameter() {
	return parameter;
}
public void setParameter(UploadCarIoParameter parameter) {
	this.parameter = parameter;
}
public UploadCarIo(UploadHead head, UploadCarIoParameter parameter) {
	super();
	this.head = head;
	this.parameter = parameter;
}
public UploadCarIo() {
	super();
}
 

}
