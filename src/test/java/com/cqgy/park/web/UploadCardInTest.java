package com.cqgy.park.web;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.cqgy.park.form.upload.UploadCardIn;
import com.cqgy.park.form.upload.UploadCardInParameter;
import com.cqgy.park.form.upload.UploadHead;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.Stool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class UploadCardInTest {
	String host = "localhost";
	String port = "8082";
	String uri = "/upload.do";
	String url = null;
	UploadHead head = new UploadHead();
	UploadCardInParameter parameter=new UploadCardInParameter();
	UploadCardIn uploadCardIn=new UploadCardIn();
	@Before
	public void setUp() throws Exception {
		this.host = "localhost";
		this.port = "8082";
		this.uri = "/park/upload/upload.do";
		this.url = "http://" + host + ":" + port + uri;
		UploadHead head=new UploadHead();
		uploadCardIn=new UploadCardIn(head, parameter);
	}

	@Test
	public void test() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		uploadCardIn.getHead().setSysId("admin");
		uploadCardIn.getHead().setPassword("66286027");
		uploadCardIn.getHead().setFunctionId("8004");
		uploadCardIn.getHead().setParkId("0001");
		uploadCardIn.getParameter().setCardNo("123456");
		uploadCardIn.getParameter().setCardType(1);
		uploadCardIn.getParameter().setPayMoney((double) 2000);
		uploadCardIn.getParameter().setPlate("hk111");
		uploadCardIn.getParameter().setStartDate(CustomTime.getLocalTime());
		uploadCardIn.getParameter().setEndDate(CustomTime.getLocalTime());
		uploadCardIn.getParameter().setRemark("土豪");
		uploadCardIn.getParameter().setAcceptEmpNo("10002");
		uploadCardIn.getParameter().setAcceptEmpName("梅彩凤");
		String adminString = mapper.writeValueAsString(uploadCardIn);
		String returnString = Stool.postJson(this.url, adminString);
		System.out.println(adminString);
		String code = Stool.getJsonValue(returnString, "head.code");
		Assert.assertEquals("充值成功", "000", code);
	}

}
