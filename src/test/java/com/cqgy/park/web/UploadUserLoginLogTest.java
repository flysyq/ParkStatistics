package com.cqgy.park.web;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cqgy.park.form.upload.UploadGateOpenHand;
import com.cqgy.park.form.upload.UploadHead;
import com.cqgy.park.form.upload.UploadUserLoginLog;
import com.cqgy.park.form.upload.UploadUserLoginLogParameter;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.Stool;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class UploadUserLoginLogTest {
	String host = "localhost";
	String port = "8082";
	String uri = "/upload.do";
	String url = null;
	UploadHead head = new UploadHead();
	UploadUserLoginLogParameter parameter = new UploadUserLoginLogParameter();
	UploadUserLoginLog uploadUserLoginLog = new UploadUserLoginLog();
	UploadGateOpenHand uploadGateOpenHand = null;
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
		uploadUserLoginLog=new UploadUserLoginLog(head, parameter);
	}
	@Test
	public void testUploadUserLoginLog_normal() throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		uploadUserLoginLog.getHead().setSysId("sjgc");
		uploadUserLoginLog.getHead().setPassword("123456");
		uploadUserLoginLog.getHead().setFunctionId("8002");
		uploadUserLoginLog.getHead().setParkId("0001");
		uploadUserLoginLog.getParameter().setEmpNo("10003");
		uploadUserLoginLog.getParameter().setEmpName("梅");
		uploadUserLoginLog.getParameter().setUserCode("m");
		uploadUserLoginLog.getParameter().setOpTime(CustomTime.getLocalTime());
		uploadUserLoginLog.getParameter().setOpType(1);
		uploadUserLoginLog.getParameter().setUserType(1);
		uploadUserLoginLog.getParameter().setUserTypeName("收费员");
		String adminString = mapper.writeValueAsString(uploadUserLoginLog);

		String returnString = Stool.postJson(this.url, adminString);

		String code = Stool.getJsonValue(returnString, "head.code");
		Assert.assertEquals("保存成功", "000", code);
	}
}
