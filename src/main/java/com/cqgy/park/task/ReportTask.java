/**
 * @作者 石永强
 * @时间 2016年6月1日 上午10:38:30
 * @类名 ReportTask.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年6月1日 上午10:38:30
 *   修改描述
 */
package com.cqgy.park.task;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cqgy.park.dao.InfoDutyShriftReportLogRepository;
import com.cqgy.park.dao.InfoDutyShriftReportRepository;
import com.cqgy.park.domain.InfoDutyShriftReport;
import com.cqgy.park.domain.InfoDutyShriftReportLog;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.task.ReportTool;

import java.util.*;

@Component
@Configurable
@EnableScheduling
public class ReportTask {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	InfoDutyShriftReportRepository infoDutyShriftReportRepository;
	@Autowired
	InfoDutyShriftReportLogRepository infoDutyShriftReportLogRepository;

	@Scheduled(fixedRate = 1000 * 500)
	public void genReport() {
		//遍历info_park表，分park进行操作
		String parkSql = "select park_code from info_park";
		List<Map<String, Object>> listPark = jdbcTemplate.queryForList(parkSql);
		for (int k = 0; k < listPark.size(); k++) {
			String parkId = (String) listPark.get(k).get("park_code");
			
			// 查询Log表，如果是第一次操作，查找info_user_login_log，查找最早的时间，收费员类型
			String taskStartTime = ReportTool.getSearchTime(jdbcTemplate, parkId);
			if(taskStartTime==null){
				continue;
			}
			String taskEndTime = taskStartTime;
			InfoDutyShriftReportLog log = new InfoDutyShriftReportLog();
			log.setParkId(parkId);
			try {
				log.setFlag(1);
				log.setSearchStartTime(new Date());
				log.setTaskStartTime(CustomTime.parseTime(taskStartTime));
				String sql = "select park_id,user_emp_no,date_format(op_time,'%Y-%m-%d %T') op_time from info_user_login_log a"
						+ " where user_type=1 and park_id='"+parkId+"' "
						+ " and a.op_time>='" + taskStartTime + "' order by park_id,op_time asc";
				System.out.println(sql);

				List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

				if (list.isEmpty()) {
					continue;
				}

				InfoDutyShriftReport report = new InfoDutyShriftReport();
				report.setEmpNo((String) list.get(0).get("user_emp_no"));
				report.setParkId((String) list.get(0).get("park_id"));
				report.setOnTime(CustomTime.parseTime((String) list.get(0).get("op_time")));
				report.setDownTime(CustomTime.parseTime((String) list.get(0).get("op_time")));

				for (int i = 1; i < list.size(); i++) {
					String tempEmpNo = (String) list.get(i).get("user_emp_no");
					String tempParkId = (String) list.get(i).get("park_id");

					if (!(report.getEmpNo().equals(tempEmpNo) && report.getParkId().equals(tempParkId))) {
						report.setDownTime(CustomTime.parseTime((String) list.get(i - 1).get("op_time")));

						taskEndTime = (String) list.get(i - 1).get("op_time");
						// 获取进入车库的车辆数量。
						ReportTool.getComeNum(jdbcTemplate, report);
						ReportTool.getGoNum(jdbcTemplate, report);

						// 获取车辆收费、免费信息
						ReportTool.getPayMap(jdbcTemplate, report);

						// 获取发卡信息
						ReportTool.getNewCard(jdbcTemplate, report);

						// 获取充值信息
						ReportTool.getCardIn(jdbcTemplate, report);

						// 手动开闸信息
						ReportTool.getOpenHand(jdbcTemplate, report);
						report.setCreateTime(new Date());
						report.setUpdateTime(new Date());
						infoDutyShriftReportRepository.save(report);
						report = new InfoDutyShriftReport();
						report.setEmpNo((String) list.get(i).get("user_emp_no"));
						report.setParkId((String) list.get(i).get("park_id"));
						report.setOnTime(CustomTime.parseTime((String) list.get(i).get("op_time")));
						report.setDownTime(CustomTime.parseTime((String) list.get(i).get("op_time")));
					}
				}

				log.setTaskEndTime(CustomTime.parseTime(taskEndTime));
				log.setSearchEndTime(new Date());
				log.setFlag(2);
				infoDutyShriftReportLogRepository.save(log);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
