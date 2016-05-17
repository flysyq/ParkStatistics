/**
 * @作者 admin
 * @时间 2016年5月13日 上午9:27:58
 * @类名 UploadParkControllerTest1.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月13日 上午9:27:58
 *   修改描述
 */
package com.cqgy.park.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cqgy.park.form.upload.UploadGateOpenHand;
import com.cqgy.park.form.upload.UploadHead;
import com.cqgy.park.form.upload.UploadParkAdmin;
import com.cqgy.park.form.upload.UploadParkAdminParameter;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.Stool;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class UploadParkAdminTest {
	String host = "localhost";
	String port = "8082";
	String uri = "/upload.do";
	String url = null;
	UploadHead head = null;
	UploadParkAdminParameter parameter = null;
	UploadParkAdmin uploadParkAdmin = null;
	UploadGateOpenHand uploadGateOpenHand = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.host = "localhost";
		this.port = "8082";
		this.uri = "/upload.do";
		this.url = "http://" + host + ":" + port + uri;
		String sysId = "error_user";
		String password = "123456";
		String functionId = "8001";
		String parkId = "0001";

		head = new UploadHead(sysId, password, functionId, parkId);
		parameter = new UploadParkAdminParameter("zhaolf", "10001", "赵龙飞", 1, "收费员", 1, CustomTime.getLocalTime(),
				"测试使用");
		uploadParkAdmin = new UploadParkAdmin(head, parameter);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUploadParkAdmin_UserError() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String adminString = mapper.writeValueAsString(uploadParkAdmin);

		String returnString = Stool.postJson(this.url, adminString);

		String code = Stool.getJsonValue(returnString, "code");
		Assert.assertEquals("用户名错误", "001", code);
	}

	@Test
	public void testUploadParkAdmin_PasswordError() throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		uploadParkAdmin.getHead().setSysId("sjgc");
		uploadParkAdmin.getHead().setPassword("error_pass");
		String adminString = mapper.writeValueAsString(uploadParkAdmin);

		String returnString = Stool.postJson(this.url, adminString);

		String code = Stool.getJsonValue(returnString, "code");
		Assert.assertEquals("密码错误", "002", code);
	}

	/*
	 * @Test public void testUploadParkAdmin_Enabled() throws IOException {
	 * 
	 * ObjectMapper mapper = new ObjectMapper();
	 * uploadParkAdmin.getHead().setSysId("sjgc");
	 * uploadParkAdmin.getHead().setPassword("123456"); String adminString =
	 * mapper.writeValueAsString(uploadParkAdmin);
	 * 
	 * String returnString = Stool.postJson(this.url, adminString);
	 * 
	 * String code = Stool.getJsonValue(returnString, "code");
	 * Assert.assertEquals("用户已经禁用", "003", code); }
	 */
	@Test
	public void testUploadParkAdmin_normal() throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		uploadParkAdmin.getHead().setSysId("sjgc");
		uploadParkAdmin.getHead().setPassword("123456");
		uploadParkAdmin.getParameter().setEmpNo("10002");
		uploadParkAdmin.getParameter().setEmpName("梅彩凤");
		uploadParkAdmin.getParameter().setUserCode("meicf");
		String adminString = mapper.writeValueAsString(uploadParkAdmin);

		String returnString = Stool.postJson(this.url, adminString);

		String code = Stool.getJsonValue(returnString, "code");
		Assert.assertEquals("保存成功", "000", code);
	}

	

}
