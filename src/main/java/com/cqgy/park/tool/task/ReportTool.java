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

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cqgy.park.domain.InfoDutyShriftReport;
import com.cqgy.park.tool.CustomTime;

public class ReportTool {

	public static String getSearchTime(JdbcTemplate jdbcTemplate) {

		String sql = "select date_format(start_time,'%Y-%m-%d %T') start_time from ("
				+ "select max(task_start_time) start_time from info_duty_shrift_report_log) a";

		String start_time = jdbcTemplate.queryForObject(sql, String.class);
		if (Objects.isNull(start_time)) {
			sql = "select date_format(start_time,'%Y-%m-%d %T') start_time from ("
					+ " select min(update_time) start_time from info_user_login_log where user_type=1) a";
			// System.out.println(sql);
			start_time = jdbcTemplate.queryForObject(sql, String.class);
		}

		return start_time;
	}

	public static Integer getComeNum(JdbcTemplate jdbcTemplate, InfoDutyShriftReport report) {
		String sql = "select count(*) cou from info_car_io " + "where park_id='" + report.getParkId()
				+ "' and come_time>'" + CustomTime.parseString(report.getOnTime()) + "' and come_time<'"
				+ CustomTime.parseString(report.getDownTime()) + "'";
		Integer comeNum = jdbcTemplate.queryForObject(sql, Integer.class);
		report.setComeNum(comeNum);
		return comeNum;
	}

	public static Integer getGoNum(JdbcTemplate jdbcTemplate, InfoDutyShriftReport report) {
		String sql = "select count(*) cou from info_car_io " + "where park_id='" + report.getParkId()
				+ "' and come_time>'" + CustomTime.parseString(report.getOnTime()) + "' and come_time<'"
				+ CustomTime.parseString(report.getDownTime()) + "'";
		Integer goNum = jdbcTemplate.queryForObject(sql, Integer.class);
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
				+ CustomTime.parseString(report.getOnTime()) + "' " + "and update_time<'"
				+ CustomTime.parseString(report.getDownTime()) + "'";

		Map<String, Object> map = jdbcTemplate.queryForMap(sql);

		report.setPayMoney((Double) map.get("sum_fee"));
		report.setPayCars((Integer) map.get("cou_pay"));
		report.setFreeMoney((Double) map.get("sum_fee_free"));
		report.setFreeCars((Integer) map.get("cou_free_pay"));
		report.setFreeCardNum((Integer) map.get("cou_free_card"));
		report.setFreeCardMoney((Double) map.get("sum_free_card_pay"));
		report.setFreeCheckNum((Integer) map.get("cou_fee_check"));
		report.setFreeCheckMoney((Double) map.get("sum_free_check_pay"));
		report.setFreePercentNum((Integer) map.get("cou_free_percent"));
		report.setFreePercentMoney((Double) map.get("sum_free_percent_pay"));
		return map;
	}

	public static Map<String, Object> getNewCard(JdbcTemplate jdbcTemplate, InfoDutyShriftReport report) {
		String sql = "select count(*) cou,sum(balance) sum_balance from info_card where park_id='" + report.getParkId()
				+ "' " + "and spread_time>'" + CustomTime.parseString(report.getOnTime()) + "' " + "and spread_time<'"
				+ CustomTime.parseString(report.getDownTime()) + "'";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		report.setCardNewNum((Integer) map.get("cou"));
		report.setCardNewMoney((Double) map.get("sum_balance"));
		return map;

	}

	public static Map<String, Object> getCardIn(JdbcTemplate jdbcTemplate, InfoDutyShriftReport report) {
		String sql = "select count(*) cou,sum(pay_money) sum_pay from info_card_in where park_id='" + report.getParkId()
				+ "' " + "and update_time>'" + CustomTime.parseString(report.getOnTime()) + "' " + "and update_time<'"
				+ CustomTime.parseString(report.getDownTime()) + "'";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		report.setCardInNum((Integer) map.get("cou"));
		report.setCardInMoney((Double) map.get("sum_pay"));
		return map;
	}

	public static Map<String, Object> getOpenHand(JdbcTemplate jdbcTemplate, InfoDutyShriftReport report) {
		String sql = "select sum(if(open_type=1,1,0)) sum_hand,sum(if(open_type=2,1,0)) sum_ex from info_gate_open_hand"
				+ " where park_id='" + report.getParkId()
				+ "' " + "and update_time>'" + CustomTime.parseString(report.getOnTime()) + "' " + "and update_time<'"
				+ CustomTime.parseString(report.getDownTime()) + "'";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		report.setGateOpenHandNum((Integer) map.get("sum_hand"));
		report.setGateOpenHandExNum((Integer) map.get("sum_ex"));
		return map;
	}
}
