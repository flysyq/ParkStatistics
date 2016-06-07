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
	private Integer page;
	private Integer page_size;
	private Integer page_count;
	
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
	
	public AuthorityListForm() {
		this.flag = 0;
		this.grade = 0;
		this.title = "";
		this.page = 1;
		this.page_size = 10;
		this.page_count = 0;
	}
}
