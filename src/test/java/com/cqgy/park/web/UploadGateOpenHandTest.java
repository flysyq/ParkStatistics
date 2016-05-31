/**
 * @作者 admin
 * @时间 2016年5月16日 下午5:25:32
 * @类名 UploadGateOpenHandTest.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月16日 下午5:25:32
 *   修改描述
 */
package com.cqgy.park.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.junit.Before;
import org.junit.Test;

import com.cqgy.park.form.upload.UploadGateOpenHandParameter;
import com.cqgy.park.form.upload.UploadGateOpenHand;
import com.cqgy.park.form.upload.UploadHead;
import com.cqgy.park.form.upload.UploadParkAdmin;
import com.cqgy.park.form.upload.UploadParkAdminParameter;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.Stool;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class UploadGateOpenHandTest {
	String host = "localhost";
	String port = "8082";
	String uri = "/upload.do";
	String url = null;
	UploadHead head = null;
	UploadGateOpenHandParameter parameter = null;
	UploadGateOpenHand uploadGateOpenHand = null;
	
	@Before
	public void setUp() throws Exception {
		this.host = "localhost";
		this.port = "8082";
		this.uri = "/park/upload.do";
		this.url = "http://" + host + ":" + port + uri;
		String sysId = "sjgc";
		String password = "123456";
		String functionId = "8007";
		String parkId = "0001";
		String imgFile = "D:/pic/a.jpg";

		String imgB64 = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(imgFile)));
		
		head = new UploadHead(sysId, password, functionId, parkId);
		parameter = new UploadGateOpenHandParameter("1002", "梅彩凤", imgB64, 2,CustomTime.getLocalTime(),CustomTime.getLocalTime());
		uploadGateOpenHand = new UploadGateOpenHand(head, parameter);
	}
	
	// 上传手动开闸信息
		@Test
		public void testUploadGateOpenHand() throws IOException {
			ObjectMapper mapper = new ObjectMapper();
			String uploadString = mapper.writeValueAsString(uploadGateOpenHand);

			String returnString = Stool.postJson(this.url, uploadString);

			String code = Stool.getJsonValue(returnString, "head.code");
			Assert.assertEquals("保存成功", "000", code);
		}
}
