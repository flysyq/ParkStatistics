package com.cqgy.park.web;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.cqgy.park.form.upload.UploadCarIo;
import com.cqgy.park.form.upload.UploadCarParkPay;
import com.cqgy.park.form.upload.UploadCarParkPayParameter;
import com.cqgy.park.form.upload.UploadHead;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.Stool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class UploadCarParkPayTest {
	String host = "localhost";
	String port = "8082";
	String uri = "/upload.do";
	String url = null;
	UploadHead head = new UploadHead();
	UploadCarParkPayParameter parameter=new UploadCarParkPayParameter();
	UploadCarParkPay uploadCarParkPay=new UploadCarParkPay();
	@Before
	public void setUp() throws Exception {
		this.host = "localhost";
		this.port = "8082";
		this.uri = "/park/upload/upload.do";
		this.url = "http://" + host + ":" + port + uri;
		UploadHead head=new UploadHead();
		uploadCarParkPay=new UploadCarParkPay(head, parameter);
	}

	@Test
	public void test() throws IOException {
		ObjectMapper mapper=new ObjectMapper();
		uploadCarParkPay.getHead().setSysId("sjgc");
		uploadCarParkPay.getHead().setPassword("123456");
		uploadCarParkPay.getHead().setFunctionId("8006");
		uploadCarParkPay.getHead().setParkId("0001");
		uploadCarParkPay.getParameter().setCardNo("123456789");
		uploadCarParkPay.getParameter().setCardType(1);
		uploadCarParkPay.getParameter().setEmpName("飞天鼠");
		uploadCarParkPay.getParameter().setEmpNo("006");
		uploadCarParkPay.getParameter().setEndTime(CustomTime.getLocalTime());
		uploadCarParkPay.getParameter().setFee(23330.);
		uploadCarParkPay.getParameter().setFreeFee(20123.0);
		uploadCarParkPay.getParameter().setFreeType(2);
		uploadCarParkPay.getParameter().setPayType(2);
		uploadCarParkPay.getParameter().setPlate("kj1243");
		uploadCarParkPay.getParameter().setRealPay(22310.0);
		uploadCarParkPay.getParameter().setStartTime(CustomTime.getLocalTime());
		String adminString = mapper.writeValueAsString(uploadCarParkPay);

		String returnString = Stool.postJson(this.url, adminString);

		String code = Stool.getJsonValue(returnString, "head.code");
		Assert.assertEquals("缴费成功", "000", code);
	}

}
