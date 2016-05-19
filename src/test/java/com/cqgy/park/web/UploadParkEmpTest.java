package com.cqgy.park.web;

import java.io.IOException;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cqgy.park.form.upload.UploadGateOpenHand;
import com.cqgy.park.form.upload.UploadHead;
import com.cqgy.park.form.upload.UploadParkEmp;
import com.cqgy.park.form.upload.UploadParkEmpParameter;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.Stool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.fabric.xmlrpc.base.Data;

import junit.framework.Assert;

public class UploadParkEmpTest {
	String host = "localhost";
	String port = "8082";
	String uri = "/upload.do";
	String url = null;
	UploadHead head = new UploadHead();
	UploadParkEmpParameter parameter = new UploadParkEmpParameter();
	UploadParkEmp uploadParkEmp = new UploadParkEmp();
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
		this.uri = "/upload.do";
		this.url = "http://" + host + ":" + port + uri;
		UploadHead head=new UploadHead();
		uploadParkEmp=new UploadParkEmp(head, parameter);
	}
	@Test
	public void testUploadParkEmp_normal() throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		uploadParkEmp.getHead().setSysId("admin");
		uploadParkEmp.getHead().setPassword("66286027");
		uploadParkEmp.getHead().setFunctionId("8002");
		uploadParkEmp.getHead().setParkId("0001");
		uploadParkEmp.getParameter().setEmpNo("10002");
		uploadParkEmp.getParameter().setEmpName("梅彩凤");
		uploadParkEmp.getParameter().setUserCode("meicf");
		uploadParkEmp.getParameter().setOpTime(CustomTime.getLocalTime());
		uploadParkEmp.getParameter().setOpType(1);
		uploadParkEmp.getParameter().setUserType(1);
		uploadParkEmp.getParameter().setUserTypeName("收费员");
		String adminString = mapper.writeValueAsString(uploadParkEmp);

		String returnString = Stool.postJson(this.url, adminString);

		String code = Stool.getJsonValue(returnString, "code");
		Assert.assertEquals("保存成功", "000", code);
	}
}
