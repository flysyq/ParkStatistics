/**
 * @作者 石永强
 * @时间 2016年6月7日 上午10:40:56
 * @类名 MailTask.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年6月7日 上午10:40:56
 *   修改描述
 */
package com.cqgy.park.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cqgy.park.dao.InfoMailLogRepository;
import com.cqgy.park.domain.InfoMailLog;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.MailUtil;
import com.google.common.base.Strings;

import java.util.*;

import javax.mail.MessagingException;

import java.math.BigDecimal;
@Component
@Configurable
@EnableScheduling
public class MailTask {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	InfoMailLogRepository infoMailLogRepository;
	
	
	@Scheduled(cron="0 0 0 1 * * ")
	public void mailTo(){
		
		//获取接收邮件的用户的邮件地址
		String mailSql = "select * from sys_user where mail_flag=1";
		
		List<Map<String,Object>> mailList = jdbcTemplate.queryForList(mailSql);
		
		for(int i=0;i<mailList.size();i++){
			
			InfoMailLog mailLog = new InfoMailLog();
			mailLog.setName((String)mailList.get(i).get("name"));
			mailLog.setUserCode((String)mailList.get(i).get("login_code"));
			mailLog.setUserId((Long)mailList.get(i).get("id"));
			mailLog.setUserMail((String)mailList.get(i).get("e_mail"));
			mailLog.setStartTime(new Date());
			mailLog.setSearchEndTime(new Date());
			mailLog.setMailState(1);
			mailLog.setResultDesc("发送成功");
			System.out.println("mail="+mailLog.getUserMail());
			
			if(mailLog.getUserMail()==null){
				System.out.println("没有邮件地址");
				mailLog.setMailState(0);
				mailLog.setResultDesc("没有邮件地址");			
				infoMailLogRepository.save(mailLog);						

				continue;
			}
			//发送之前一个月的报表，以及本月的报表
			String html = "<html><head><meta http-equiv='keywords' content='keyword1,keyword2,keyword3'>" +
			          "<meta http-equiv='description' content='this is my page'><meta http-equiv='content-type' content='text/html; charset=utf-8'>" ;
			html += "<style>table{border-collapse:collapse;border-spacing:0} td{border:1px solid #000000}</style>";
			html += "</head><body>";
			String preMonthCarInSql = "SELECT" +
					"	count(*) cou " +
					"FROM" +
					"	info_car_io" +
					" WHERE" +
					"	come_time > concat(" +
					"		YEAR (now())," +
					"		\"-\"," +
					"		MONTH (now()) - 1," +
					"		'-'," +
					"		1" +
					"	)" +
					" AND come_time < concat(" +
					"	YEAR (now())," +
					"	\"-\"," +
					"	MONTH (now())," +
					"	'-'," +
					"	1" +
					")";
			Long pre_car_in = jdbcTemplate.queryForObject(preMonthCarInSql, Long.class);
			String nowMonthCarInSql = "SELECT" +
					"	count(*) cou" +
					" FROM" +
					"	info_car_io" +
					" WHERE" +
					"	come_time > concat(" +
					"		YEAR (now())," +
					"		\"-\"," +
					"		MONTH (now())," +
					"		'-'," +
					"		1" +
					"	)";
			
			Long now_car_in = jdbcTemplate.queryForObject(nowMonthCarInSql, Long.class);
			String preMonthCarOutSql = "SELECT" +
					"	count(*) cou" +
					" FROM" +
					"	info_car_io" +
					" WHERE" +
					"	go_time > concat(" +
					"		YEAR (now())," +
					"		\"-\"," +
					"		MONTH (now()) - 1," +
					"		'-'," +
					"		1" +
					"	)" +
					" AND go_time < concat(" +
					"	YEAR (now())," +
					"	\"-\"," +
					"	MONTH (now())," +
					"	'-'," +
					"	1" +
					")";
			Long pre_car_out = jdbcTemplate.queryForObject(preMonthCarOutSql, Long.class);
			String nowMonthCarOutSql = "SELECT" +
					"	count(*) cou" +
					" FROM" +
					"	info_car_io" +
					" WHERE" +
					"	come_time > concat(" +
					"		YEAR (now())," +
					"		\"-\"," +
					"		MONTH (now())," +
					"		'-'," +
					"		1" +
					"	)";
			
			Long now_car_out = jdbcTemplate.queryForObject(nowMonthCarOutSql, Long.class);
			
			
			String preCarPaySql = "select ifnull(sum(real_pay),0) sum_pay,ifnull(sum(fee_free),0) sum_fee_free from info_car_park_pay "
					+ "where update_time>concat(year(now()),'-',month(now())-1,'-',1) "
					+ "and update_time<concat(year(now()),'-',month(now()),'-',1)";
			
			Map<String,Object> preCarPayMap = jdbcTemplate.queryForMap(preCarPaySql);
			Double pre_sum_pay = (Double)(preCarPayMap.get("sum_pay"));
			Double pre_sum_fee_free = (Double)(preCarPayMap.get("sum_fee_free"));
			String nowCarPaySql = "select ifnull(sum(real_pay),0) sum_pay,ifnull(sum(fee_free),0) sum_fee_free from info_car_park_pay "
					+ "where update_time>concat(year(now()),'-',month(now()),'-',1) ";
			Map<String,Object> nowCarPayMap = jdbcTemplate.queryForMap(nowCarPaySql);
			Double now_sum_pay = (Double)(nowCarPayMap.get("sum_pay"));
			Double now_sum_fee_free = (Double)(nowCarPayMap.get("sum_fee_free"));
			
			String preCardNewSql = "select count(*) cou from info_card "
					+ " where spread_time>concat(year(now()),'-',month(now())-1,'-',1) "
					+ " and spread_time<concat(year(now()),'-',month(now()),'-',1)";
			Long pre_card_new = jdbcTemplate.queryForObject(preCardNewSql, Long.class);
			String nowCardNewSql = "select count(*) cou from info_card "
					+ " where spread_time>concat(year(now()),'-',month(now()),'-',1) ";
			Long now_card_new = jdbcTemplate.queryForObject(nowCardNewSql, Long.class);
			
			String preCardInSql = "select ifnull(sum(pay_money),0) sum_pay from info_card_in"
					+ " where update_time>concat(year(now()),'-',month(now())-1,'-',1)"
					+ " and update_time<concat(year(now()),'-',month(now()),'-',1)";
			Double pre_card_in = (Double)jdbcTemplate.queryForObject(preCardInSql,Double.class);
			String nowCardInSql = "select ifnull(sum(pay_money),0) sum_pay from info_card_in"
					+ " where update_time>concat(year(now()),'-',month(now()),'-',1)";
			Double now_card_in = (Double)jdbcTemplate.queryForObject(nowCardInSql,Double.class);
			
			String preOpenHandSql = "select open_type,count(*) cou "
					+ " from info_gate_open_hand "
					+ " where open_time>concat(year(now()),'-',month(now())-1,'-',1) "
					+ " and open_time<concat(year(now()),'-',month(now()),'-',1) "
					+ " group by open_type "
					+ " order by open_type asc";
			List<Map<String,Object>> preOpenHandList = jdbcTemplate.queryForList(preOpenHandSql);
			
			Long pre_open_hand = 0L;
			Long pre_open_ex = 0L;
			
			for(int j=0;j<preOpenHandList.size();j++){
				if(preOpenHandList.get(j).get("open_type").equals(1)){
					pre_open_hand =(Long) preOpenHandList.get(j).get("cou");
				}
				if(preOpenHandList.get(j).get("open_type").equals(2)){
					pre_open_ex =(Long) preOpenHandList.get(j).get("cou");
				}
			}
			
			String nowOpenHandSql = "select open_type,count(*) cou "
					+ " from info_gate_open_hand "
					+ " where open_time>concat(year(now()),'-',month(now()),'-',1) "
					+ " group by open_type "
					+ " order by open_type asc";
			List<Map<String,Object>> nowOpenHandList = jdbcTemplate.queryForList(nowOpenHandSql);
			
			Long now_open_hand = 0L;
			Long now_open_ex = 0L;
			
			for(int j=0;j<nowOpenHandList.size();j++){
				if(nowOpenHandList.get(j).get("open_type").equals(1)){
					now_open_hand =(Long) nowOpenHandList.get(j).get("cou");
				}
				if(nowOpenHandList.get(j).get("open_type").equals(2)){
					now_open_ex =(Long) nowOpenHandList.get(j).get("cou");
				}
			}
			String preMonth = CustomTime.getLocalTimeFormatMinusMonths("yyyy-MM",1);
			String nowMonth = CustomTime.getLocalTimeFormat("yyyy-MM");
			
			html += "<table width='80%' align='center'><tr><td>月份</td><td>进场车次</td><td>出厂车次</td><td>收费金额</td><td>免费金额</td><td>充值金额</td><td>新开卡数量</td>";
			html += "<td>手动开闸数量</td><td>异常开闸数量</td></tr>";
			html +="<tr><td>"+preMonth+"</td><td>"+pre_car_in+"</td><td>"+pre_car_out+"</td><td>"+pre_sum_pay+"</td>";
			html +="<td>"+pre_sum_fee_free+"</td><td>"+pre_card_in+"</td><td>"+pre_card_new+"</td>";
			html +="<td>"+pre_open_hand+"</td><td>"+pre_open_ex+"</td></tr>";
			html +="<tr><td>"+nowMonth+"</td><td>"+now_car_in+"</td><td>"+now_car_out+"</td><td>"+now_sum_pay+"</td>";
			html +="<td>"+now_sum_fee_free+"</td><td>"+now_card_in+"</td><td>"+now_card_new+"</td>";
			html +="<td>"+now_open_hand+"</td><td>"+now_open_ex+"</td></tr>";		
			html +=" </table>";
			html +="</body></html>";
			System.out.println(html);
			
			String title = nowMonth+" 停车场统计报表";
			try {
				System.out.println("mail="+mailLog.getUserMail());
				System.out.println("mail="+mailList.get(i).get("email"));
				MailUtil.mailHtmlTo(mailLog.getUserMail(),title,html);

			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mailLog.setMailState(0);
				mailLog.setResultDesc(e.getMessage());
			}finally{
				infoMailLogRepository.save(mailLog);
			}			
			
		}

		
		
	}
}
