package com.cqgy.park.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class InfoCarIo {
	@Id
	@GeneratedValue
	private Long id;
	
	private String parkId;
	private String cardNo;
	private String plate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date comeTime;
	private String comePic;
	private String parkSpacePic;
	@Temporal(TemporalType.TIMESTAMP)
	private Date goTime;
	private String goPic;
	private Integer cardType;
	private Integer accType;
	private String inEmpNo;
	private String inEmpName;
	private String outEmpNo;
	private String outEmpName;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public Date getComeTime() {
		return comeTime;
	}
	public void setComeTime(Date comeTime) {
		this.comeTime = comeTime;
	}
	public String getComePic() {
		return comePic;
	}
	public void setComePic(String comePic) {
		this.comePic = comePic;
	}
	public String getParkSpacePic() {
		return parkSpacePic;
	}
	public void setParkSpacePic(String parkSpacePic) {
		this.parkSpacePic = parkSpacePic;
	}
	public Date getGoTime() {
		return goTime;
	}
	public void setGoTime(Date goTime) {
		this.goTime = goTime;
	}
	public String getGoPic() {
		return goPic;
	}
	public void setGoPic(String goPic) {
		this.goPic = goPic;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public Integer getAccType() {
		return accType;
	}
	public void setAccType(Integer accType) {
		this.accType = accType;
	}
	public String getInEmpNo() {
		return inEmpNo;
	}
	public void setInEmpNo(String inEmpNo) {
		this.inEmpNo = inEmpNo;
	}
	public String getInEmpName() {
		return inEmpName;
	}
	public void setInEmpName(String inEmpName) {
		this.inEmpName = inEmpName;
	}
	public String getOutEmpNo() {
		return outEmpNo;
	}
	public void setOutEmpNo(String outEmpNo) {
		this.outEmpNo = outEmpNo;
	}
	public String getOutEmpName() {
		return outEmpName;
	}
	public void setOutEmpName(String outEmpName) {
		this.outEmpName = outEmpName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public InfoCarIo(Long id, String parkId, String cardNo, String plate, Date comeTime, String comePic,
			String parkSpacePic, Date goTime, String goPic, Integer cardType, Integer accType, String inEmpNo,
			String inEmpName, String outEmpNo, String outEmpName, Date updateTime) {
		super();
		this.id = id;
		this.parkId = parkId;
		this.cardNo = cardNo;
		this.plate = plate;
		this.comeTime = comeTime;
		this.comePic = comePic;
		this.parkSpacePic = parkSpacePic;
		this.goTime = goTime;
		this.goPic = goPic;
		this.cardType = cardType;
		this.accType = accType;
		this.inEmpNo = inEmpNo;
		this.inEmpName = inEmpName;
		this.outEmpNo = outEmpNo;
		this.outEmpName = outEmpName;
		this.updateTime = updateTime;
	}
	public InfoCarIo() {
		super();
	}
	
}
