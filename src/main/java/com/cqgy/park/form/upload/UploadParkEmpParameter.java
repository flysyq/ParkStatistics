package com.cqgy.park.form.upload;

public class UploadParkEmpParameter {
	
	private String userCode;
	private String empNo;
	private String empName;
	private Integer userType;
	private String userTypeName;
	private Integer opType;
	private String opTime;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public Integer getOpType() {
		return opType;
	}
	public void setOpType(Integer opType) {
		this.opType = opType;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public UploadParkEmpParameter(String userCode, String empNo, String empName, Integer userType, String userTypeName,
			Integer opType, String opTime) {
		super();
		this.userCode = userCode;
		this.empNo = empNo;
		this.empName = empName;
		this.userType = userType;
		this.userTypeName = userTypeName;
		this.opType = opType;
		this.opTime = opTime;
	}
	public UploadParkEmpParameter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
