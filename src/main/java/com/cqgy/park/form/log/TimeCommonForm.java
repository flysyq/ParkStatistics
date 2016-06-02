package com.cqgy.park.form.log;

import com.cqgy.park.tool.CustomTime;

public class TimeCommonForm {
	private Integer page;
	private Integer page_size;
	private Integer page_count;
	
	private String start_date;
	private String end_date;
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
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
	
	public TimeCommonForm() {
		this.start_date=CustomTime.getLocalTimeMinusMonth(1);
		this.end_date=CustomTime.getLocalTime();
		this.page=1;
		this.page_size=10;
		this.page_count=0;
	}
	
	
}
