package com.cqgy.park.form.query;

import com.cqgy.park.tool.CustomTime;

public class QueryForm {
	
	private Integer page;
	private Integer page_size;
	private Integer page_count;
	
	private String start_date;
	private String end_date;
	
	private String where;
	private String clause;
	private String orderby;
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
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public String getClause() {
		return clause;
	}
	public void setClause(String clause) {
		this.clause = clause;
	}
	
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public QueryForm() {
		this.page = 1;
		this.page_size = 10;
		this.page_count = 0;
		this.start_date=CustomTime.getLocalTimeMinusMonth(1);
		this.end_date=CustomTime.getLocalTime();
		this.where = null;
		this.clause = null;
		this.orderby = null;
	}
	
}
