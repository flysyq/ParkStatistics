package com.cqgy.park.form;

public class UserListForm {
	private String loginCode;
	private String name;
	private String loginPassword;
	private Integer enabled;
	private Integer page;
	private Integer page_size;
	private Integer page_count;
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
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
	public UserListForm() {
		this.loginCode = "";
		this.name = "";
		this.loginPassword = "";
		this.enabled = 0;
		this.page = 1;
		this.page_size = 10;
		this.page_count = 0;
	}
	

	
}
