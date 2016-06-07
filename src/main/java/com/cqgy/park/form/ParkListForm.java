package com.cqgy.park.form;

public class ParkListForm {
	private Integer page;
	private Integer page_size;
	private Integer page_count;
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
	public ParkListForm() {
		this.page = 1;
		this.page_size = 10;
		this.page_count = 0;
	}
	
}
