/**
 * @作者 admin
 * @时间 2016年4月27日 上午11:14:10
 * @类名 AuthorityListForm.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月27日 上午11:14:10
 *   修改描述
 */
package com.cqgy.park.form;

public class AuthorityListForm {

	private Integer flag;
	private Integer grade;
	private String title;
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public AuthorityListForm(Integer flag, Integer grade, String title) {
		this.flag = flag;
		this.grade = grade;
		this.title = title;
	}
	
}
