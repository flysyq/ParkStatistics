/**
 * @作者 admin
 * @时间 2016年5月11日 下午5:04:26
 * @类名 UploadParkAdminController.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月11日 下午5:04:26
 *   修改描述
 */
package com.cqgy.park.web.upload;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cqgy.park.bo.UploadParkLogic;
import com.cqgy.park.dao.InfoParkAdminRepository;
import com.cqgy.park.dao.InfoUploadUserRepository;
import com.cqgy.park.domain.InfoUploadUserLinkParkRepository;
import com.cqgy.park.form.upload.UploadHead;
import com.cqgy.park.qresult.upload.ReturnHead;
import com.cqgy.park.tool.Stool;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UploadParkController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	InfoUploadUserRepository infoUploadUserRepository;

	@Autowired
	InfoUploadUserLinkParkRepository infoUploadUserLinkParkRepository;

	@Autowired
	InfoParkAdminRepository infoParkAdminRepository;
	
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public ReturnHead upload(HttpServletRequest request) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String json = Stool.readJSONString(request);
		System.out.println(json);
		System.out.println(Stool.getJsonValue(json, "head.sysId"));
		String sysId = Stool.getJsonValue(json, "head.sysId");
		String password = Stool.getJsonValue(json, "head.password");
		String functionId = Stool.getJsonValue(json, "head.functionId");
		String parkId = Stool.getJsonValue(json, "head.parkId");
		UploadHead head = new UploadHead(sysId,password,functionId,parkId);
		
		ReturnHead rhead = UploadParkLogic.judgeLogin(jdbcTemplate, head);
		if(!rhead.getCode().equals("000")){
			return rhead;
		}
		
		switch (functionId) {
		case "8001":
			UploadParkLogic.saveInfoParkAdmin(infoParkAdminRepository, json);
		}
		return new ReturnHead("000", "正确");

	}
}
