package com.cqgy.park.form.stats;

import com.cqgy.park.tool.CustomTime;

public class GateOpenStatsForm {
	private Integer page;
	private Integer page_size;
	private Integer page_count;

	private String start_date;
	private String end_date;

	private String park_id;

	private Integer park_check;
	private Integer open_type_check;
	private Integer emp_check;

	private Integer excel_flag;
	private Integer pdf_flag;
	private Integer img_flag;
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
	public String getPark_id() {
		return park_id;
	}
	public void setPark_id(String park_id) {
		this.park_id = park_id;
	}
	public Integer getPark_check() {
		return park_check;
	}
	public void setPark_check(Integer park_check) {
		this.park_check = park_check;
	}
	public Integer getOpen_type_check() {
		return open_type_check;
	}
	public void setOpen_type_check(Integer open_type_check) {
		this.open_type_check = open_type_check;
	}
	public Integer getEmp_check() {
		return emp_check;
	}
	public void setEmp_check(Integer emp_check) {
		this.emp_check = emp_check;
	}
	public Integer getExcel_flag() {
		return excel_flag;
	}
	public void setExcel_flag(Integer excel_flag) {
		this.excel_flag = excel_flag;
	}
	public Integer getPdf_flag() {
		return pdf_flag;
	}
	public void setPdf_flag(Integer pdf_flag) {
		this.pdf_flag = pdf_flag;
	}
	public Integer getImg_flag() {
		return img_flag;
	}
	public void setImg_flag(Integer img_flag) {
		this.img_flag = img_flag;
	}
	public GateOpenStatsForm() {
		this.page=1;
		this.page_size=10;
		this.park_check=0;
		this.page_count=0;
		this.emp_check=0;
		this.open_type_check=0;
		this.start_date=CustomTime.getLocalTimeMinusMonth(1);
		this.end_date=CustomTime.getLocalTime();
		this.excel_flag=0;
		this.pdf_flag=0;
		this.img_flag=0;
	}
	
}
