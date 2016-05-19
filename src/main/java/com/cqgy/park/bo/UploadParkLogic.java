package com.cqgy.park.bo;

import static com.cqgy.park.tool.CustomTime.getLocalTime;
import static com.cqgy.park.tool.SHAUtil.shaEncode;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cqgy.park.dao.InfoCarIoRepository;
import com.cqgy.park.dao.InfoCarParkPayRepository;
import com.cqgy.park.dao.InfoCardInRepository;
import com.cqgy.park.dao.InfoCardRepository;
import com.cqgy.park.dao.InfoGateOpenHandRepository;
import com.cqgy.park.dao.InfoLogUploadRepository;
import com.cqgy.park.dao.InfoParkAdminRepository;
import com.cqgy.park.dao.InfoParkEmpRepository;
import com.cqgy.park.domain.InfoCarIo;
import com.cqgy.park.domain.InfoCarParkPay;
import com.cqgy.park.domain.InfoCard;
import com.cqgy.park.domain.InfoCardIn;
import com.cqgy.park.domain.InfoGateOpenHand;
import com.cqgy.park.domain.InfoLogUpload;
import com.cqgy.park.domain.InfoParkAdmin;
import com.cqgy.park.domain.InfoParkEmp;
import com.cqgy.park.form.upload.InfoGateOpenHandParameter;
import com.cqgy.park.form.upload.UploadCarIo;
import com.cqgy.park.form.upload.UploadCarIoParameter;
import com.cqgy.park.form.upload.UploadCarParkPay;
import com.cqgy.park.form.upload.UploadCarParkPayParameter;
import com.cqgy.park.form.upload.UploadCard;
import com.cqgy.park.form.upload.UploadCardIn;
import com.cqgy.park.form.upload.UploadCardInParameter;
import com.cqgy.park.form.upload.UploadCardParameter;
import com.cqgy.park.form.upload.UploadGateOpenHand;
import com.cqgy.park.form.upload.UploadHead;
import com.cqgy.park.form.upload.UploadParkAdmin;
import com.cqgy.park.form.upload.UploadParkAdminParameter;
import com.cqgy.park.form.upload.UploadParkEmp;
import com.cqgy.park.form.upload.UploadParkEmpParameter;
import com.cqgy.park.qresult.upload.ReturnHead;
import com.cqgy.park.qresult.upload.ReturnResult;
import com.cqgy.park.tool.CustomFile;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.Stool;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UploadParkLogic {


	public static ReturnHead judgeLogin(JdbcTemplate jdbcTemplate, UploadHead head) throws Exception {

		String loginCode = head.getSysId();
		String loginPassword = head.getPassword();
		String parkId = head.getParkId();

		ReturnHead returnHead = new ReturnHead();
		System.out.println(loginCode);
		String sql = "select login_password,enabled from info_upload_user where login_code='" + loginCode + "'";

		System.out.println(sql);
		List infoUser = jdbcTemplate.queryForList(sql);

		if (infoUser.isEmpty()) {
			returnHead.setCode("001");
			returnHead.setServerDate(getLocalTime());
			returnHead.setDescribe("用户名错误，请检查！");
			return returnHead;
		}

		Map<String, Object> map = (Map<String, Object>) infoUser.get(0);

		if (!map.get("login_password").equals(shaEncode(loginPassword))) {
			returnHead.setCode("002");
			returnHead.setServerDate(getLocalTime());
			returnHead.setDescribe("密码错误，请检查！");
			return returnHead;
		}

		if (map.get("enabled").equals(0)) {
			returnHead.setCode("003");
			returnHead.setServerDate(getLocalTime());
			returnHead.setDescribe("用户已禁用，请检查！");
			return returnHead;
		}

		sql = "select * from info_upload_user_link_park where user_code='" + loginCode + "' and park_code='" + parkId
				+ "'";
		List infoUploadUserLinkParks = jdbcTemplate.queryForList(sql);
		if (infoUploadUserLinkParks.isEmpty()) {
			returnHead.setCode("004");
			returnHead.setServerDate(getLocalTime());
			returnHead.setDescribe("此用户无目标停车场的上传权限，请检查！");
			return returnHead;
		} else {
			returnHead.setCode("000");
			returnHead.setServerDate(getLocalTime());
			returnHead.setDescribe("用户权限检查完毕！");
			return returnHead;
		}

	}

	public static ReturnResult saveInfoParkAdmin(InfoParkAdminRepository infoParkAdminRepository,
			InfoLogUploadRepository infoLogUploadRepository, String json)
					throws JsonParseException, JsonMappingException, IOException, ParseException {
		ReturnHead rhead = new ReturnHead();
		ReturnResult result=new ReturnResult();
		result.setHead(rhead);
		ObjectMapper mapper = new ObjectMapper();

		UploadParkAdmin infoAdmin = mapper.readValue(json, UploadParkAdmin.class);

		UploadParkAdminParameter parameter = infoAdmin.getParameter();
		UploadHead head = infoAdmin.getHead();

		String userCode = parameter.getUserCode();

		List<InfoParkAdmin> infoParkAdmins = infoParkAdminRepository.findByUserCode(userCode);
		Integer enabled = 1;
		if (parameter.getOpType() == 3) {
			enabled = 0;
		}
		InfoParkAdmin infoParkAdmin = new InfoParkAdmin();
		infoParkAdmin.setEmpName(parameter.getEmpName());
		infoParkAdmin.setEmpNo(parameter.getEmpNo());
		infoParkAdmin.setIsEnable(enabled);
		infoParkAdmin.setOpTime(CustomTime.parseTime(parameter.getOpTime()));
		infoParkAdmin.setParkId(head.getParkId());
		infoParkAdmin.setRemark(parameter.getRemark());
		infoParkAdmin.setUpdateTime(new Date());
		infoParkAdmin.setUserCode(parameter.getUserCode());
		infoParkAdmin.setUserType(parameter.getUserType());
		infoParkAdmin.setUserTypeName(parameter.getUserTypeName());
		if (infoParkAdmins.isEmpty()) {
			infoParkAdmin.setId(null);
		} else {
			infoParkAdmin.setId((infoParkAdmins.get(0)).getId());
		}

		InfoParkAdmin m = infoParkAdminRepository.save(infoParkAdmin);
		if (Objects.isNull(m)) {
			rhead.setCode("101");
			rhead.setDescribe("保存车场工作人员出现错误");
			rhead.setServerDate(CustomTime.getLocalTime());
		} else {
			rhead.setCode("000");
			rhead.setDescribe("保存成功");
			rhead.setServerDate(CustomTime.getLocalTime());
		}
		saveInfoLogUpload(infoLogUploadRepository, json, rhead);

		return result;
	}

	public static ReturnResult savaInfoParkEmp(InfoParkEmpRepository infoParkEmpRepository,InfoLogUploadRepository infoLogUploadRepository,String json) throws JsonParseException, JsonMappingException, IOException, ParseException{
		ReturnHead rhead=new ReturnHead();
		ReturnResult result= new ReturnResult();
		result.setHead(rhead);
		ObjectMapper mapper=new ObjectMapper();
		UploadParkEmp infoEmp=mapper.readValue(json, UploadParkEmp.class);
		UploadHead head=infoEmp.getHead();
		UploadParkEmpParameter parameter=infoEmp.getParameter();
		String userCode=parameter.getUserCode();
		Integer enabled = 1;
		InfoParkEmp infoParkEmp=new InfoParkEmp();
		infoParkEmp.setEmpName(parameter.getEmpName());
		infoParkEmp.setEmpNo(parameter.getEmpNo());
		infoParkEmp.setId(null);
		infoParkEmp.setIsEnable(enabled);
		infoParkEmp.setOpTime(CustomTime.parseTime(parameter.getOpTime()));
		infoParkEmp.setParkId(head.getParkId());
		infoParkEmp.setUpdateTime(new Date());
		infoParkEmp.setUserCode(parameter.getUserCode());
		infoParkEmp.setUserType(parameter.getUserType());
		infoParkEmp.setUserTypeName(parameter.getUserTypeName());
		InfoParkEmp m = infoParkEmpRepository.save(infoParkEmp);
		if (Objects.isNull(m)) {
			rhead.setCode("101");
			rhead.setDescribe("保存交班记录出现错误");
			rhead.setServerDate(CustomTime.getLocalTime());
		} else {
			rhead.setCode("000");
			rhead.setDescribe("保存成功");
			rhead.setServerDate(CustomTime.getLocalTime());
		}
		saveInfoLogUpload(infoLogUploadRepository, json, rhead);
		return result;

	}

	public static ReturnResult savaInfoCard(InfoCardRepository infoCardRepository,InfoLogUploadRepository infoLogUploadRepository,String json) throws JsonParseException, JsonMappingException, IOException, ParseException{
		ReturnResult result = new ReturnResult();
		ReturnHead rhead = new ReturnHead();
		result.setHead(rhead);
		ObjectMapper mapper = new ObjectMapper();
		UploadCard card = mapper.readValue(json, UploadCard.class);
		UploadHead head = card.getHead();
		UploadCardParameter parameter = card.getParameter();
		String cardNo=parameter.getCardNo();
		List<InfoCard> infoCards = infoCardRepository.findByCardNo(cardNo);
		InfoCard infoCard=new InfoCard();
		infoCard.setBalance(parameter.getCardMoney());
		infoCard.setCardNo(parameter.getCardNo());
		infoCard.setCardType(parameter.getCardType());
		infoCard.setEndDate(CustomTime.parseTime(parameter.getEndDate()));
		if (infoCards.isEmpty()) {
			infoCard.setId(null);
		}else{
			infoCard.setId(infoCards.get(0).getId());
		}
		infoCard.setMonthMoney(parameter.getUmMoney());
		infoCard.setOwnerName(parameter.getOwnerName());
		infoCard.setParkId(head.getParkId());
		infoCard.setPlate(parameter.getPlate());
		infoCard.setSpreadEmpName(parameter.getSpreadEmpName());
		infoCard.setSpreadEmpNo(parameter.getSpreadEmpNo());
		infoCard.setSpreadTime(CustomTime.parseTime(parameter.getSpreadTime()));
		infoCard.setStartDate(CustomTime.parseTime(parameter.getStartDate()));
		InfoCard m = infoCardRepository.save(infoCard);
		if (Objects.isNull(m)) {
			rhead.setCode("101");
			rhead.setDescribe("卡片信息保存出现错误");
			rhead.setServerDate(CustomTime.getLocalTime());
		} else {
			rhead.setCode("000");
			rhead.setDescribe("保存成功");
			rhead.setServerDate(CustomTime.getLocalTime());
		}
		saveInfoLogUpload(infoLogUploadRepository, json, rhead);
		return result;
	}

	public static ReturnResult savaInfoCardIn(InfoCardInRepository infoCardInRepository,InfoLogUploadRepository infoLogUploadRepository,String json) throws JsonParseException, JsonMappingException, IOException, ParseException{
		ReturnHead rhead = new ReturnHead();
		ReturnResult result=new ReturnResult();
		result.setHead(rhead);
		ObjectMapper mapper = new ObjectMapper();
		UploadCardIn cardIn = mapper.readValue(json, UploadCardIn.class);
		UploadHead head = cardIn.getHead();
		UploadCardInParameter parameter = cardIn.getParameter();
		String cardNo=parameter.getCardNo();
		InfoCardIn infoCardIn=new InfoCardIn();
		infoCardIn.setId(null);
		infoCardIn.setParkId(head.getParkId());
		infoCardIn.setCardNo(parameter.getCardNo());
		infoCardIn.setPlate(parameter.getPlate());
		infoCardIn.setCardType(parameter.getCardType());
		infoCardIn.setPayMoney(parameter.getPayMoney());
		infoCardIn.setAcceptEmpNo(parameter.getAcceptEmpNo());
		infoCardIn.setAcceptEmpName(parameter.getAcceptEmpName());
		infoCardIn.setStartDate(CustomTime.parseTime(parameter.getStartDate()));
		infoCardIn.setEndDate(CustomTime.parseTime(parameter.getEndDate()));
		infoCardIn.setUpdateTime(new Date());
		InfoCardIn m = infoCardInRepository.save(infoCardIn);
		if (Objects.isNull(m)) {
			rhead.setCode("101");
			rhead.setDescribe("充值失败");
			rhead.setServerDate(CustomTime.getLocalTime());
		} else {
			rhead.setCode("000");
			rhead.setDescribe("充值成功");
			rhead.setServerDate(CustomTime.getLocalTime());
		}
		saveInfoLogUpload(infoLogUploadRepository, json, rhead);
		return result;	
	}

	public static ReturnResult saveInfoGateOpenHand(InfoGateOpenHandRepository infoGateOpenHandRepository,
			InfoLogUploadRepository infoLogUploadRepository, String json) throws JsonProcessingException, IOException, ParseException {
		ReturnHead rhead = new ReturnHead();
		ReturnResult result=new ReturnResult();
		result.setHead(rhead);
		ObjectMapper mapper = new ObjectMapper();

		UploadGateOpenHand uploadGateOpenHand = mapper.readValue(json, UploadGateOpenHand.class);

		InfoGateOpenHandParameter parameter = uploadGateOpenHand.getParameter();
		UploadHead head = uploadGateOpenHand.getHead();

		String openPic = parameter.getOpenPic();

		InfoGateOpenHand infoGateOpenHand = new InfoGateOpenHand();	
		infoGateOpenHand.setOpenEmpNo(parameter.getOpenEmpNo());
		infoGateOpenHand.setOpenTime(CustomTime.parseTime(parameter.getOpenTime()));
		infoGateOpenHand.setOpenType(parameter.getOpenType());
		infoGateOpenHand.setUpdateTime(new Date());
		infoGateOpenHand.setOpenEmpName(parameter.getOpenEmpName());
		infoGateOpenHand.setOpenPic(CustomFile.saveOpenPic(parameter.getOpenPic(),parameter.getOpenTime()));
		InfoGateOpenHand m = infoGateOpenHandRepository.save(infoGateOpenHand);

		if (Objects.isNull(m)) {
			rhead.setCode("101");
			rhead.setDescribe("保存手动开闸信息出现错误");
			rhead.setServerDate(CustomTime.getLocalTime());
		} else {
			rhead.setCode("000");
			rhead.setDescribe("保存成功");
			rhead.setServerDate(CustomTime.getLocalTime());
		}
		saveInfoLogUpload(infoLogUploadRepository, json, rhead);
		return result;
	}

	public static ReturnResult saveInfoCarIo(InfoCarIoRepository infoCarIoRepository,InfoLogUploadRepository infoLogUploadRepository,String json) throws JsonParseException, JsonMappingException, IOException, ParseException{
		ReturnHead rhead=new ReturnHead();
		ReturnResult result=new ReturnResult();
		result.setHead(rhead);
		ObjectMapper mapper=new ObjectMapper();
		UploadCarIo uploadCarIo = mapper.readValue(json, UploadCarIo.class);
		UploadHead head=uploadCarIo.getHead();
		UploadCarIoParameter parameter=uploadCarIo.getParameter();
		Integer accType=parameter.getAccType();
		InfoCarIo infoCarIo=new InfoCarIo();
		List<InfoCarIo> findByAccTypeOrderByUpdateTime = infoCarIoRepository.findByAccTypeAndCardNoOrderByUpdateTime(0,parameter.getCardNo());
		if (accType==0) {
			infoCarIo.setId(null);
			infoCarIo.setComeTime(CustomTime.parseTime(parameter.getComeTime()));
			infoCarIo.setComePic(parameter.getComePic());
			infoCarIo.setParkSpacePic(parameter.getParkSpacePic());
			infoCarIo.setInEmpNo(parameter.getEmpNo());
			infoCarIo.setInEmpName(parameter.getEmpName());
		}else{
			
			infoCarIo.setGoTime(CustomTime.parseTime(parameter.getGoTime()));
			infoCarIo.setGoPic(parameter.getGoPic());
			infoCarIo.setOutEmpNo(parameter.getEmpNo());
			infoCarIo.setOutEmpName(parameter.getEmpName());
			if (!findByAccTypeOrderByUpdateTime.isEmpty()) {
				infoCarIo.setId(findByAccTypeOrderByUpdateTime.get(0).getId());
				infoCarIo.setComeTime(findByAccTypeOrderByUpdateTime.get(0).getComeTime());
				infoCarIo.setComePic(findByAccTypeOrderByUpdateTime.get(0).getComePic());
				infoCarIo.setParkSpacePic(findByAccTypeOrderByUpdateTime.get(0).getParkSpacePic());
				infoCarIo.setInEmpNo(findByAccTypeOrderByUpdateTime.get(0).getInEmpNo());
				infoCarIo.setInEmpName(findByAccTypeOrderByUpdateTime.get(0).getInEmpName());
			}else{
				infoCarIo.setId(null);
				infoCarIo.setComeTime(null);
				infoCarIo.setComePic(null);
				infoCarIo.setParkSpacePic(null);
				infoCarIo.setInEmpNo(null);
				infoCarIo.setInEmpName(null);
			}
		}
		infoCarIo.setParkId(head.getParkId());
		infoCarIo.setCardNo(parameter.getCardNo());
		infoCarIo.setPlate(parameter.getPlate());
		infoCarIo.setCardType(parameter.getCardType());
		infoCarIo.setAccType(accType);
		infoCarIo.setUpdateTime(new Date());

		saveInfoLogUpload(infoLogUploadRepository, json, rhead);
		InfoCarIo m = infoCarIoRepository.save(infoCarIo);
		if (Objects.isNull(m)) {
			rhead.setCode("101");
			rhead.setDescribe("保存出入场信息出现错误");
			rhead.setServerDate(CustomTime.getLocalTime());
		} else {
			rhead.setCode("000");
			rhead.setDescribe("保存成功");
			rhead.setServerDate(CustomTime.getLocalTime());
		}
		saveInfoLogUpload(infoLogUploadRepository, json, rhead);
		return result;
	}

	public static ReturnResult saveInfoCarParkPay(InfoCarParkPayRepository infoCarParkPayRepository,InfoLogUploadRepository infoLogUploadRepository,String json) throws JsonParseException, JsonMappingException, IOException, ParseException{
		ReturnHead rhead=new ReturnHead();
		ReturnResult result= new ReturnResult();
		result.setHead(rhead);
		ObjectMapper mapper=new ObjectMapper();
		UploadCarParkPay carParkPay=mapper.readValue(json, UploadCarParkPay.class);
		UploadHead head=carParkPay.getHead();
		UploadCarParkPayParameter parameter= carParkPay.getParameter();
		InfoCarParkPay infoCarParkPay=new InfoCarParkPay();
		infoCarParkPay.setId(null);
		infoCarParkPay.setParkId(head.getParkId());
		infoCarParkPay.setCardNo(parameter.getCardNo());
		infoCarParkPay.setPlate(parameter.getPlate());
		infoCarParkPay.setStartTime(CustomTime.parseTime(parameter.getStartTime()));
		infoCarParkPay.setEndTime(CustomTime.parseTime(parameter.getEndTime()));
		infoCarParkPay.setRealPay(parameter.getRealPay());
		infoCarParkPay.setFee(parameter.getFee());
		infoCarParkPay.setCardType(parameter.getCardType());
		infoCarParkPay.setPayType(parameter.getPayType());
		infoCarParkPay.setFeeFree(parameter.getFreeFee());
		infoCarParkPay.setFreeType(parameter.getFreeType());
		infoCarParkPay.setEmpNo(parameter.getEmpNo());
		infoCarParkPay.setEmpName(parameter.getEmpName());
		InfoCarParkPay m = infoCarParkPayRepository.save(infoCarParkPay);
		if (Objects.isNull(m)) {
			rhead.setCode("101");
			rhead.setDescribe("缴费失败");
			rhead.setServerDate(CustomTime.getLocalTime());
		} else {
			rhead.setCode("000");
			rhead.setDescribe("缴费成功");
			rhead.setServerDate(CustomTime.getLocalTime());
		}
		saveInfoLogUpload(infoLogUploadRepository, json, rhead);
		return result;
	}
	
	public static void saveInfoLogUpload(InfoLogUploadRepository infoLogUploadRepository, String json, ReturnHead rhead)
			throws JsonProcessingException, IOException {
		InfoLogUpload infoLogUpload = new InfoLogUpload();

		infoLogUpload.setFunctionId(Stool.getJsonValue(json, "head.functionId"));
		infoLogUpload.setUploadContent(json);
		infoLogUpload.setCode(rhead.getCode());
		infoLogUpload.setMessage(rhead.getDescribe());
		infoLogUpload.setUploadTime(new Date());
		infoLogUploadRepository.save(infoLogUpload);
	}
}
