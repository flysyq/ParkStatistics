package com.cqgy.park.bo;

public class Page {

	private Integer page=1;
	private Integer page_size=10;
	private Integer page_count;
	private Long count;
	
	
	public Page(Integer page, Integer page_size, Integer page_count, Long count) {
		super();
		this.page = page;
		this.page_size = page_size;
		this.page_count = page_count;
		this.count = count;
	}
	public Page() {
		// TODO Auto-generated constructor stub
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
