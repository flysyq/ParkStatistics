package com.cqgy.park.qresult.upload;

import com.cqgy.park.tool.CustomTime;

public class ReturnHead {
	
	private String code;
	private String serverDate;
	private String describe;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getServerDate() {
		return serverDate;
	}
	public void setServerDate(String serverDate) {
		this.serverDate = serverDate;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public ReturnHead(String code, String describe) {
		super();
		this.code = code;
		this.describe = describe;
		this.serverDate = CustomTime.getLocalTime();
	}
	public ReturnHead() {
		// TODO Auto-generated constructor stub
	}	
	
}
