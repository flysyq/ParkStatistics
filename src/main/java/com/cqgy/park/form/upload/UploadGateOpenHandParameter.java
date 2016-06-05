/**
 * @作者 admin
 * @时间 2016年5月16日 下午4:13:43
 * @类名 InfoGateOpenHandParameter.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月16日 下午4:13:43
 *   修改描述
 */
package com.cqgy.park.form.upload;

public class UploadGateOpenHandParameter {
	private String openEmpNo;
	private String openEmpName;
	private String openPic;
	private Integer openType;
	private String openTime;
	private String updateTime;

	public String getOpenEmpNo() {
		return openEmpNo;
	}

	public void setOpenEmpNo(String openEmpNo) {
		this.openEmpNo = openEmpNo;
	}

	public String getOpenEmpName() {
		return openEmpName;
	}

	public void setOpenEmpName(String openEmpName) {
		this.openEmpName = openEmpName;
	}

	public String getOpenPic() {
		return openPic;
	}

	public void setOpenPic(String openPic) {
		this.openPic = openPic;
	}

	public Integer getOpenType() {
		return openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public UploadGateOpenHandParameter(String openEmpNo, String openEmpName, String openPic, Integer openType,
			String openTime, String updateTime) {
		this.openEmpNo = openEmpNo;
		this.openPic = openPic;
		this.openType = openType;
		this.openTime = openTime;
		this.updateTime = updateTime;
		this.openEmpName = openEmpName;
	}

	public UploadGateOpenHandParameter() {
		super();
	}

}
