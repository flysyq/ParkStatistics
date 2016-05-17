/**
 * @作者 admin
 * @时间 2016年5月11日 下午4:42:28
 * @类名 UploadHead.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月11日 下午4:42:28
 *   修改描述
 */
package com.cqgy.park.form.upload;

public class UploadHead {

	private String sysId;
	private String password;
	private String functionId;
	private String parkId;
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public UploadHead(String sysId, String password, String functionId, String parkId) {
		this.sysId = sysId;
		this.password = password;
		this.functionId = functionId;
		this.parkId = parkId;
	}
	public UploadHead() {
		super();
	}
	
}
