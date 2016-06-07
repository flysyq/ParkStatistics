/**
 * @作者 admin
 * @时间 2016年5月16日 下午3:48:30
 * @类名 InfoGateOpenHand.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月16日 下午3:48:30
 *   修改描述
 */
package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class InfoGateOpenHand {

	@Id
	@GeneratedValue
	private Long id;
	private String parkId;
	private String openEmpNo;
	private String openPic;
	private Integer openType;
	private String openEmpName;
	@Temporal(TemporalType.TIMESTAMP)
	private Date openTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getOpenEmpName() {
		return openEmpName;
	}
	public void setOpenEmpName(String openEmpName) {
		this.openEmpName = openEmpName;
	}
	public String getOpenEmpNo() {
		return openEmpNo;
	}
	public void setOpenEmpNo(String openEmpNo) {
		this.openEmpNo = openEmpNo;
	}
	public String getOpenPic() {
		return openPic;
	}
	public void setOpenPic(String openPic) {
		this.openPic = openPic;
	}
	public Integer getOpenType() {
		return openType;
	}
	public void setOpenType(Integer openType) {
		this.openType = openType;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	
	public InfoGateOpenHand(Long id,String parkId, String openEmpNo,String openEmpName, String openPic, Integer openType, Date openTime,
			Date updateTime) {
		this.id = id;
		this.parkId = parkId;
		this.openEmpNo = openEmpNo;
		this.openPic = openPic;
		this.openType = openType;
		this.openTime = openTime;
		this.updateTime = updateTime;
		this.openEmpName = openEmpName;
	}
	public InfoGateOpenHand() {
		super();
	}

}
