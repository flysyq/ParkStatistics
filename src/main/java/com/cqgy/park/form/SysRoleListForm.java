package com.cqgy.park.form;

public class SysRoleListForm {
	private String code;
	private String name;
	private String remark;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public SysRoleListForm(String code, String name, String remark) {
		super();
		this.code = code;
		this.name = name;
		this.remark = remark;
	}
	public SysRoleListForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
