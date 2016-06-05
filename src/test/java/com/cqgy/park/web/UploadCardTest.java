package com.cqgy.park.web;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cqgy.park.form.upload.UploadCard;
import com.cqgy.park.form.upload.UploadCardParameter;
import com.cqgy.park.form.upload.UploadHead;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.Stool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class UploadCardTest {
	String host = "localhost";
	String port = "8082";
	String uri = "/upload.do";
	String url = null;
	UploadHead head = new UploadHead();
	UploadCardParameter parameter = new UploadCardParameter();
	UploadCard uploadCard = new UploadCard();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		this.host = "localhost";
		this.port = "8082";
		this.uri = "/park/upload/upload.do";
		this.url = "http://" + host + ":" + port + uri;
		UploadHead head=new UploadHead();
		uploadCard=new UploadCard(head, parameter);
	}
	@Test
	public void test() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		uploadCard.getHead().setSysId("admin");
		uploadCard.getHead().setPassword("66286027");
		uploadCard.getHead().setFunctionId("8003");
		uploadCard.getHead().setParkId("0001");
		uploadCard.getParameter().setCardMoney(200.0);
		uploadCard.getParameter().setCardNo("595445932");
		uploadCard.getParameter().setCardType(3);
		uploadCard.getParameter().setEndDate(CustomTime.getLocalTime());
		uploadCard.getParameter().setOwnerName("李国伟");
		uploadCard.getParameter().setPlate("HK502");
		uploadCard.getParameter().setSpreadEmpName("梅彩凤");
		uploadCard.getParameter().setSpreadEmpNo("10002");
		uploadCard.getParameter().setSpreadTime(CustomTime.getLocalTime());
		uploadCard.getParameter().setStartDate(CustomTime.getLocalTime());
		uploadCard.getParameter().setUmMoney((double)200);
		String adminString = mapper.writeValueAsString(uploadCard);
		String returnString = Stool.postJson(this.url, adminString);
		System.out.println(adminString);
		String code = Stool.getJsonValue(returnString, "head.code");
		Assert.assertEquals("保存成功", "000", code);
	}

}
