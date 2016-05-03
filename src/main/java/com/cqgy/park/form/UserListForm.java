package com.cqgy.park.form;

public class UserListForm {
	private String loginCode;
	private String name;
	private String loginPassword;
	private Integer enabled;
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
	public UserListForm(String loginCode, String name, String loginPassword, Integer enabled) {
		super();
		this.loginCode = loginCode;
		this.name = name;
		this.loginPassword = loginPassword;
		this.enabled = enabled;
	}
	public UserListForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
