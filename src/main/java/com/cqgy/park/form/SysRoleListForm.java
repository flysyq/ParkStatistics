package com.cqgy.park.form;

public class SysRoleListForm {
	private String code;
	private String name;
	private String remark;
	private Integer page;
	private Integer page_size;
	private Integer page_count;
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
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
	public Integer getPage_count() {
		return page_count;
	}
	public void setPage_count(Integer page_count) {
		this.page_count = page_count;
	}
	public SysRoleListForm() {
		this.code = "";
		this.name = "";
		this.remark = "";
		this.page = 1;
		this.page_size = 10;
		this.page_count = 0;
	}
	
}
