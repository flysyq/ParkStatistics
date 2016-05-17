/**
 * @作者 admin
 * @时间 2016年5月11日 下午4:53:29
 * @类名 UploadParkAdminParameter.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月11日 下午4:53:29
 *   修改描述
 */
package com.cqgy.park.form.upload;

public class UploadParkAdminParameter {
	
	private String userCode;
	private String empNo;
	private String empName;
	private Integer userType;
	private String userTypeName;
	private Integer opType;
	private String opTime;
	private String remark;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public UploadParkAdminParameter() {
		super();
	}
	public UploadParkAdminParameter(String userCode, String empNo, String empName, Integer userType,
			String userTypeName, Integer opType, String opTime, String remark) {
		super();
		this.userCode = userCode;
		this.empNo = empNo;
		this.empName = empName;
		this.userType = userType;
		this.userTypeName = userTypeName;
		this.opType = opType;
		this.opTime = opTime;
		this.remark = remark;
	}	
	
	
}
