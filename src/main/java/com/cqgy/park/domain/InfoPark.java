/**
 * @作者 admin
 * @时间 2016年5月11日 下午4:26:03
 * @类名 InfoPark.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月11日 下午4:26:03
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
public class InfoPark {
	@Id
	@GeneratedValue
	private Long Id;
	
	private String parkName;//停车场名称
	private String parkCode;//停车场编号
	private String parkDesc;//描述
	private Integer allParkNum;//所有停车位
	private Integer outParkNum;//对外公布的停车位
	private Integer nowParkNum;//现在的停车位
		
	private Long createUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	private Long updateUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	
	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getParkName() {
		return parkName;
	}


	public void setParkName(String parkName) {
		this.parkName = parkName;
	}


	public String getParkCode() {
		return parkCode;
	}


	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}


	public String getParkDesc() {
		return parkDesc;
	}


	public void setParkDesc(String parkDesc) {
		this.parkDesc = parkDesc;
	}


	public Integer getAllParkNum() {
		return allParkNum;
	}


	public void setAllParkNum(Integer allParkNum) {
		this.allParkNum = allParkNum;
	}


	public Integer getOutParkNum() {
		return outParkNum;
	}


	public void setOutParkNum(Integer outParkNum) {
		this.outParkNum = outParkNum;
	}


	public Integer getNowParkNum() {
		return nowParkNum;
	}


	public void setNowParkNum(Integer nowParkNum) {
		this.nowParkNum = nowParkNum;
	}


	public Long getCreateUser() {
		return createUser;
	}


	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Long getUpdateUser() {
		return updateUser;
	}


	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	public InfoPark(Long id, String parkName, String parkCode, String parkDesc, Integer allParkNum, Integer outParkNum,
			Integer nowParkNum, Long createUser, Date createTime, Long updateUser, Date updateTime) {
		Id = id;
		this.parkName = parkName;
		this.parkCode = parkCode;
		this.parkDesc = parkDesc;
		this.allParkNum = allParkNum;
		this.outParkNum = outParkNum;
		this.nowParkNum = nowParkNum;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}
	


	public InfoPark(Long id, String parkName, String parkCode, String parkDesc, Integer allParkNum, Integer outParkNum,
			Integer nowParkNum) {
		super();
		Id = id;
		this.parkName = parkName;
		this.parkCode = parkCode;
		this.parkDesc = parkDesc;
		this.allParkNum = allParkNum;
		this.outParkNum = outParkNum;
		this.nowParkNum = nowParkNum;
	}


	public InfoPark() {
		super();
	}	
	
}
