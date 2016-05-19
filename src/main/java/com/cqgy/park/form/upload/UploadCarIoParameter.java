package com.cqgy.park.form.upload;

public class UploadCarIoParameter {
	private String cardNo;
	private String plate;
	private Integer cardType;
	private String comeTime;
	private String comePic;
	private String parkSpacePic;
	private String goTime;
	private String goPic;
	private String empNo;
	private String empName;
	private Integer accType;
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
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getComeTime() {
		return comeTime;
	}
	public void setComeTime(String comeTime) {
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
	public String getGoTime() {
		return goTime;
	}
	public void setGoTime(String goTime) {
		this.goTime = goTime;
	}
	public String getGoPic() {
		return goPic;
	}
	public void setGoPic(String goPic) {
		this.goPic = goPic;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getAccType() {
		return accType;
	}
	public void setAccType(Integer accType) {
		this.accType = accType;
	}
	public UploadCarIoParameter(String cardNo, String plate, Integer cardType, String comeTime, String comePic,
			String parkSpacePic, String goTime, String goPic, String empNo, String empName, Integer accType) {
		super();
		this.cardNo = cardNo;
		this.plate = plate;
		this.cardType = cardType;
		this.comeTime = comeTime;
		this.comePic = comePic;
		this.parkSpacePic = parkSpacePic;
		this.goTime = goTime;
		this.goPic = goPic;
		this.empNo = empNo;
		this.empName = empName;
		this.accType = accType;
	}
	public UploadCarIoParameter() {
		super();
	}
	
	
}
