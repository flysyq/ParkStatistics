package com.cqgy.park.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.junit.Before;
import org.junit.Test;

import com.cqgy.park.form.upload.UploadCarIo;
import com.cqgy.park.form.upload.UploadCarIoParameter;
import com.cqgy.park.form.upload.UploadHead;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.Stool;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class UploadCarIoTest {
	String host = "localhost";
	String port = "8082";
	String uri = "/park/upload.do";
	String url = null;
	UploadHead head = new UploadHead();
	UploadCarIoParameter parameter=new UploadCarIoParameter();
	UploadCarIo uploadCarIo=new UploadCarIo();
	
	@Before
	public void setUp() throws Exception {
		this.host = "localhost";
		this.port = "8082";
		this.uri = "/park/upload.do";
		this.url = "http://" + host + ":" + port + uri;
		UploadHead head=new UploadHead();
		uploadCarIo=new UploadCarIo(head, parameter);
	}

	@Test
	public void testCarIn() throws IOException {
		String imgFile = "D:/pic/a.jpg";
		String imgB64 = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(imgFile)));
		ObjectMapper mapper = new ObjectMapper();
		uploadCarIo.getHead().setSysId("sjgc");
		uploadCarIo.getHead().setPassword("123456");
		uploadCarIo.getHead().setFunctionId("8005");
		uploadCarIo.getHead().setParkId("0001");
		uploadCarIo.getParameter().setAccType(0);
		uploadCarIo.getParameter().setCardNo("88888888");
		uploadCarIo.getParameter().setCardType(1);
		uploadCarIo.getParameter().setComePic(imgB64);
		uploadCarIo.getParameter().setComeTime(CustomTime.getLocalTime());
		uploadCarIo.getParameter().setEmpNo("10002");
		uploadCarIo.getParameter().setEmpName("梅彩凤");	
		uploadCarIo.getParameter().setPlate("牛B54250");
		String adminString = mapper.writeValueAsString(uploadCarIo);

		String returnString = Stool.postJson(this.url, adminString);

		String code = Stool.getJsonValue(returnString, "head.code");
		Assert.assertEquals("保存成功", "000", code);
	}
	 
	@Test
	public void testCarOut() throws IOException {
		String imgFile = "D:/pic/a.jpg";
		String imgB64 = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(imgFile)));
		ObjectMapper mapper = new ObjectMapper();
		uploadCarIo.getHead().setSysId("sjgc");
		uploadCarIo.getHead().setPassword("123456");
		uploadCarIo.getHead().setFunctionId("8005");
		uploadCarIo.getHead().setParkId("0001");
		uploadCarIo.getParameter().setAccType(1);
		uploadCarIo.getParameter().setCardNo("88888888");
		uploadCarIo.getParameter().setCardType(1);
		uploadCarIo.getParameter().setGoPic(imgB64);
		uploadCarIo.getParameter().setParkSpacePic(imgB64);
		uploadCarIo.getParameter().setGoTime(CustomTime.getLocalTime());
		uploadCarIo.getParameter().setEmpNo("10002");
		uploadCarIo.getParameter().setEmpName("梅彩凤");
		uploadCarIo.getParameter().setPlate("牛B54250");
		String adminString = mapper.writeValueAsString(uploadCarIo);

		String returnString = Stool.postJson(this.url, adminString);

		String code = Stool.getJsonValue(returnString, "head.code");
		Assert.assertEquals("保存成功", "000", code);
	}

}
