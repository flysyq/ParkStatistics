/**
 * @作者 admin
 * @时间 2016年5月30日 下午3:10:21
 * @类名 CarPayStatsImageForm.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月30日 下午3:10:21
 *   修改描述
 */
package com.cqgy.park.form.stats;

public class StatsImageForm {

	private String start_date;
	private String end_date;
	private Integer flag;
	private Integer image_flag;	
	
	public Integer getImage_flag() {
		return image_flag;
	}
	public void setImage_flag(Integer image_flag) {
		this.image_flag = image_flag;
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
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}	
	
}
