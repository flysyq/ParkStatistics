/**
 * @作者 石永强
 * @时间 2016年6月1日 上午11:13:55
 * @类名 ReportTool.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年6月1日 上午11:13:55
 *   修改描述
 */
package com.cqgy.park.tool.task;



import static com.cqgy.park.tool.ExcelUtil.setList;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cqgy.park.domain.InfoDutyShriftReport;
import com.cqgy.park.tool.CustomTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPTable;

import static com.cqgy.park.tool.PdfUtil.*;
public class ReportTool {

	public static String getSearchTime(JdbcTemplate jdbcTemplate,String parkId) {

		String sql = "select date_format(start_time,'%Y-%m-%d %T') start_time from ("
				+ "select max(search_end_time) start_time from info_duty_shrift_report_log where park_id='"+parkId+"') a";

		String start_time = jdbcTemplate.queryForObject(sql, String.class);
		if (Objects.isNull(start_time)) {
			sql = "select date_format(start_time,'%Y-%m-%d %T') start_time from ("
					+ " select date_sub(min(op_time),interval 1 SECOND) start_time from info_user_login_log where user_type=1 and park_id='"+parkId+"') a";
			// System.out.println(sql);
			start_time = jdbcTemplate.queryForObject(sql, String.class);
		}

		return start_time;
	}

	public static Long getComeNum(JdbcTemplate jdbcTemplate, InfoDutyShriftReport report) {
		String sql = "select count(*) cou from info_car_io " + "where park_id='" + report.getParkId()
				+ "' and come_time>'" + CustomTime.parseString(report.getOnTime()) + "' and come_time<='"
				+ CustomTime.parseString(report.getDownTime()) + "'";
		System.out.println(sql);
		Long comeNum = jdbcTemplate.queryForObject(sql, Long.class);
		report.setComeNum(comeNum);
		return comeNum;
	}

	public static Long getGoNum(JdbcTemplate jdbcTemplate, InfoDutyShriftReport report) {
		String sql = "select count(*) cou from info_car_io " + "where park_id='" + report.getParkId()
				+ "' and go_time>'" + CustomTime.parseString(report.getOnTime()) + "' and go_time<='"
				+ CustomTime.parseString(report.getDownTime()) + "'";
		System.out.println(sql);
		Long goNum = jdbcTemplate.queryForObject(sql, Long.class);		
		report.setGoNum(goNum);
		return goNum;
	}

	public static Map<String, Object> getPayMap(JdbcTemplate jdbcTemplate, InfoDutyShriftReport report) {
		String sql = "SELECT SUM(IF(pay_type=2,0,1)) cou_pay, " + " SUM(if(pay_type=2,0,real_pay)) sum_fee, "
				+ " SUM(IF(pay_type=2,1,0)) cou_free_pay, " + " SUM(IF(pay_type=2,fee_free,0)) sum_fee_free,"
				+ " sum(if(pay_type=2 && free_type=1,1,0)) cou_free_card,"
				+ " sum(if(pay_type=2 && free_type=1,fee_free,0)) sum_free_card_pay,"
				+ " sum(if(pay_type=2 && free_type=2,1,0)) cou_free_check,"
				+ " sum(if(pay_type=2 && free_type=2,fee_free,0)) sum_free_check_pay,"
				+ " sum(if(free_type=3,1,0)) cou_free_percent,"
				+ " sum(if(free_type=3,fee_free,0)) sum_free_percent_pay " + " FROM info_car_park_pay a "
				+ " where park_id='" + report.getParkId() + "' " + "and update_time>'"
				+ CustomTime.parseString(report.getOnTime()) + "' " + "and update_time<='"
				+ CustomTime.parseString(report.getDownTime()) + "'";
		System.out.println(sql);
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		if(map.get("sum_fee")!=null){
			report.setPayMoney((Double) map.get("sum_fee"));
			report.setPayCars(((BigDecimal) map.get("cou_pay")).longValue());
			report.setFreeMoney((Double) map.get("sum_fee_free"));
			report.setFreeCars(((BigDecimal) map.get("cou_free_pay")).longValue());
			report.setFreeCardNum(((BigDecimal) map.get("cou_free_card")).longValue());
			report.setFreeCardMoney((Double) map.get("sum_free_card_pay"));
			report.setFreeCheckNum(((BigDecimal) map.get("cou_free_check")).longValue());
			report.setFreeCheckMoney((Double) map.get("sum_free_check_pay"));
			report.setFreePercentNum(((BigDecimal) map.get("cou_free_percent")).longValue());
			report.setFreePercentMoney((Double) map.get("sum_free_percent_pay"));
		}		
		return map;
	}

	public static Map<String, Object> getNewCard(JdbcTemplate jdbcTemplate, InfoDutyShriftReport report) {
		String sql = "select count(*) cou,sum(balance) sum_balance from info_card where park_id='" + report.getParkId()
				+ "' " + "and spread_time>'" + CustomTime.parseString(report.getOnTime()) + "' " + "and spread_time<='"
				+ CustomTime.parseString(report.getDownTime()) + "'";
		System.out.println(sql);
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		if(map.get("sum_balance")==null){
			
			report.setCardNewNum((long)0);
			report.setCardNewMoney((double)0);
		}else{
			report.setCardNewNum((Long) map.get("cou"));
			report.setCardNewMoney((Double) map.get("sum_balance"));
		}
		
		return map;

	}

	public static Map<String, Object> getCardIn(JdbcTemplate jdbcTemplate, InfoDutyShriftReport report) {
		String sql = "select count(*) cou,sum(pay_money) sum_pay from info_card_in where park_id='" + report.getParkId()
				+ "' " + "and update_time>'" + CustomTime.parseString(report.getOnTime()) + "' " + "and update_time<='"
				+ CustomTime.parseString(report.getDownTime()) + "'";
		System.out.println(sql);
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		if(map.get("sum_pay")==null){
			report.setCardInNum((long)0);
			report.setCardInMoney((double)0);
			
		}else{
			report.setCardInNum((Long) map.get("cou"));
			report.setCardInMoney((Double) map.get("sum_pay"));
				
		}
		return map;
	}

	public static Map<String, Object> getOpenHand(JdbcTemplate jdbcTemplate, InfoDutyShriftReport report) {
		String sql = "select sum(if(open_type=1,1,0)) sum_hand,sum(if(open_type=2,1,0)) sum_ex from info_gate_open_hand"
				+ " where park_id='" + report.getParkId() + "' " + "and update_time>'"
				+ CustomTime.parseString(report.getOnTime()) + "' " + "and update_time<='"
				+ CustomTime.parseString(report.getDownTime()) + "'";
		System.out.println(sql);
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		System.out.println(Objects.isNull(map.get("sum_hand")));
		if(map.get("sum_hand")==null){
			report.setGateOpenHandNum((long)0);
			report.setGateOpenHandExNum((long)0);
		}else{
			BigDecimal b_sum_hand = (BigDecimal) map.get("sum_hand");
			BigDecimal b_sum_ex = (BigDecimal) map.get("sum_ex");
			report.setGateOpenHandNum(b_sum_hand.longValue());
			report.setGateOpenHandExNum(b_sum_ex.longValue());
		}		

		return null;
	}

	public static void createCarIoPDF(JdbcTemplate jdbcTemplate,Document document, String parkId, Date searchStartTime, Date searchEndTime,FontSelector selector){
	       
		String start_time = CustomTime.parseString(searchStartTime);
		String end_time = CustomTime.parseString(searchEndTime);
		
		String sql = "SELECT " +
				" IF (" +
				"	acc_type = 1," +
				"	'已出场'," +
				"	'未出场'" +
				") acc_state," +
				" card_no," +
				" CASE card_type" +
				" WHEN 1 THEN" +
				"	'月租卡'" +
				" WHEN 2 THEN" +
				"	'免费卡'" +
				" WHEN 3 THEN" +
				"	'充值卡'" +
				" WHEN 4 THEN" +
				"	'临时卡'" +
				" ELSE" +
				"	'不明卡'" +
				" END card_type," +
				" plate," +
				" date_format(come_time,'%Y-%m-%d %T') come_time," +
				" date_format(go_time,'%Y-%m-%d %T') go_time," +
				" in_emp_name," +
				" in_emp_no," +
				" out_emp_name," +
				" out_emp_no" +
				" FROM" +
				"	info_car_io" +
				" where park_id='"+parkId+"' and (come_time>'"+start_time+"' and come_time<='"+end_time+"')" +
				" or (go_time>'"+start_time+"' and (go_time<'"+end_time+"' or go_time is null)) "+ 
				" ORDER BY" +
				"	come_time ASC," +
				"	go_time ASC";
		
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		String[] head = {"状态", "卡号", "卡类型","车牌号", "进场时间", "出场时间", "进场操作员姓名", "进场操作人工号", "出厂操作人姓名", "出厂操作人工号"};
        String[] code = {"acc_state", "card_no","card_type", "plate", "come_time", "go_time", "in_emp_name", "in_emp_no", "out_emp_name", "out_emp_no"};
       
        PdfPTable table = new PdfPTable(head.length + 1);
        //创建表头
        createTableHead(table, head, selector);
        //创建表内容
        try {
			createTableContent(table, list, code, selector);
			document.add(table);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
	}

	public static void createCarPayPDF(JdbcTemplate jdbcTemplate, Document document, String parkId,
			Date searchStartTime, Date searchEndTime, FontSelector selector) {
		String start_time = CustomTime.parseString(searchStartTime);
		String end_time = CustomTime.parseString(searchEndTime);
		String sql ="SELECT" +
				"	plate," +
				"	date_format(start_time, '%Y-%m-%d %T') start_time," +
				"	date_format(end_time, '%Y-%m-%d %T') end_time," +
				"	fee," +
				"	real_pay," +
				"	card_no," +
				"	CASE card_type" +
				" WHEN 1 THEN" +
				"	'月租卡'" +
				" WHEN 2 THEN" +
				"	'免费卡'" +
				" WHEN 3 THEN" +
				"	'充值卡'" +
				" WHEN 4 THEN" +
				"	'临时卡'" +
				" ELSE" +
				"	'其他'" +
				" END card_type," +
				" CASE pay_type" +
				" WHEN 1 THEN" +
				"	'月租卡'" +
				" WHEN 2 THEN" +
				"	'免费卡'" +
				" WHEN 3 THEN" +
				"	'充值卡'" +
				" WHEN 4 THEN" +
				"	'现金'" +
				" WHEN 5 THEN" +
				"	'会员积分'" +
				" ELSE" +
				"	'其他'" +
				" END pay_type," +
				" case free_type " +
				" when 1 then '免费卡'" +
				" when 2 then '领导审批'" +
				" when 3 then '折扣卡'" +
				" else '其他'" +
				" end" +
				" free_type," +
				" emp_name," +
				" emp_no" +
				" FROM" +
				"	info_car_park_pay" +
				" where park_id='"+parkId+"' and update_time>'"+start_time+"' and update_time<='"+end_time+"' " + 
				" ORDER BY" +
				"	end_time ASC";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		String[] head = {"车牌号","进场时间", "出场时间","费用", "实际缴费", "卡号",  "卡类型", "缴费类型", "免费类型", "操作员姓名","操作员工号"};
        String[] code = {"plate", "start_time", "end_time","fee", "real_pay","card_no",  "card_type", "pay_type", "free_type","emp_name", "emp_no"};
       
        PdfPTable table = new PdfPTable(head.length + 1);
        //创建表头
        createTableHead(table, head, selector);
        //创建表内容
        try {
			createTableContent(table, list, code, selector);
			document.add(table);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void createCardNewPDF(JdbcTemplate jdbcTemplate, Document document, String parkId,
			Date searchStartTime, Date searchEndTime, FontSelector selector) {
		String start_time = CustomTime.parseString(searchStartTime);
		String end_time = CustomTime.parseString(searchEndTime);
		String sql ="SELECT" +
				"	card_no," +
				" plate," +
				"	CASE card_type" +
				" WHEN 1 THEN" +
				"	'月租卡'" +
				" WHEN 2 THEN" +
				"	'免费卡'" +
				" WHEN 3 THEN" +
				"	'充值卡'" +
				" ELSE" +
				"	'其他'" +
				" END card_type," +
				" balance," +
				" owner_name," +
				" date_format(start_date, '%Y-%m-%d') start_date," +
				" date_format(end_date, '%Y-%m-%d') end_date," +
				" month_money," +
				" date_format(spread_time, '%Y-%m-%d %T') spread_time," +
				" spread_emp_name," +
				" spread_emp_no" +
				" FROM" +
				"	info_card" +
				" where park_id='"+parkId+"' and spread_time>'"+start_time+"' and spread_time<='"+end_time+"' " + 
				" ORDER BY" +
				"	spread_time ASC";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		String[] head = {"卡号","车牌号", "卡类型","余额", "持卡人", "开始时间",  "结束时间", "续费金额", "发卡人姓名", "发卡人工号"};
        String[] code = {"card_no", "plate", "card_type","balance", "owner_name","start_date",  "end_date", "month_money", "spread_emp_name","spread_emp_no"};
       
        PdfPTable table = new PdfPTable(head.length + 1);
        //创建表头
        createTableHead(table, head, selector);
        //创建表内容
        try {
			createTableContent(table, list, code, selector);
			document.add(table);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// TODO Auto-generated method stub
		
	}

	public static void createCardInPDF(JdbcTemplate jdbcTemplate, Document document, String parkId,
			Date searchStartTime, Date searchEndTime, FontSelector selector) {
		String start_time = CustomTime.parseString(searchStartTime);
		String end_time = CustomTime.parseString(searchEndTime);
		String sql ="SELECT" +
				"	card_no," +
				"	plate," +
				"	CASE card_type" +
				" WHEN 1 THEN" +
				"	'月租卡'" +
				" WHEN 2 THEN" +
				"	'充值卡'" +
				" ELSE" +
				"	'其他'" +
				" END card_type," +
				" pay_money," +
				" date_format(start_date, '%Y-%m-%d') start_date," +
				" date_format(end_date, '%Y-%m-%d') end_date," +
				" accept_emp_name," +
				" accept_emp_no" +
				" FROM" +
				"	info_card_in" +
				" where park_id='"+parkId+"' and update_time>'"+start_time+"' and update_time<='"+end_time+"' " + 
				" ORDER BY" +
				"	update_time ASC";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		String[] head = {"卡号","车牌号", "卡类型","充值",  "开始时间",  "结束时间", "充值人姓名", "充值人工号"};
        String[] code = {"card_no", "plate", "card_type","pay_money", "start_date",  "end_date", "accept_emp_name","accept_emp_no"};
       
        PdfPTable table = new PdfPTable(head.length + 1);
        //创建表头
        createTableHead(table, head, selector);
        //创建表内容
        try {
			createTableContent(table, list, code, selector);
			document.add(table);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// TODO Auto-generated method stub
		
	}

	public static void createOpenHandPDF(JdbcTemplate jdbcTemplate, Document document, String parkId,
			Date searchStartTime, Date searchEndTime, FontSelector selector) {
		String start_time = CustomTime.parseString(searchStartTime);
		String end_time = CustomTime.parseString(searchEndTime);
		String sql ="SELECT" +
				"	date_format(open_time, '%Y-%m-%d %T') open_time," +
				"	CASE open_type" +
				" WHEN 1 THEN" +
				"	'手动开闸'" +
				" WHEN 2 THEN" +
				"	'异常开闸'" +
				" ELSE" +
				"	'其他'" +
				" END open_type," +
				" open_emp_name," +
				" open_emp_no" +
				" FROM" +
				"	info_gate_open_hand" +
				" ORDER BY" +
				"	open_time ASC";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		String[] head = {"操作时间","操作类型", "操作员姓名","操作员工号"};
        String[] code = {"open_time", "open_type", "open_emp_name","open_emp_no"};
       
        PdfPTable table = new PdfPTable(head.length + 1);
        //创建表头
        createTableHead(table, head, selector);
        //创建表内容
        try {
			createTableContent(table, list, code, selector);
			document.add(table);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// TODO Auto-generated method stub
		
	}
}
