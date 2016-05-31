package com.cqgy.park.form.stats;

import com.cqgy.park.tool.CustomTime;

public class CarIoStatsForm {

	private Integer page;
	private Integer page_size;
	private Integer page_count;

	private String come_time;
	private String go_time;

	private String park_id;

	private Integer park_check;
	private Integer plate_check;
	private Integer in_emp_check;
	private Integer out_emp_check;

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
	public String getCome_time() {
		return come_time;
	}
	public void setCome_time(String come_time) {
		this.come_time = come_time;
	}
	public String getGo_time() {
		return go_time;
	}
	public void setGo_time(String go_time) {
		this.go_time = go_time;
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
	public Integer getPlate_check() {
		return plate_check;
	}
	public void setPlate_check(Integer plate_check) {
		this.plate_check = plate_check;
	}
	public Integer getIn_emp_check() {
		return in_emp_check;
	}
	public void setIn_emp_check(Integer in_emp_check) {
		this.in_emp_check = in_emp_check;
	}
	public Integer getOut_emp_check() {
		return out_emp_check;
	}
	public void setOut_emp_check(Integer out_emp_check) {
		this.out_emp_check = out_emp_check;
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
	public CarIoStatsForm() {
		super();
		this.page = 1;
		this.page_size = 10;
		this.page_count = 0;
		this.come_time = CustomTime.getLocalTimeMinusMonth(1);
		this.go_time = CustomTime.getLocalTime();
		this.park_check = 0;
		this.plate_check = 0;
		this.in_emp_check = 0;
		this.out_emp_check = 0;
		this.excel_flag = 0;
		this.pdf_flag = 0;
		this.img_flag = 0;
	}
	
}
