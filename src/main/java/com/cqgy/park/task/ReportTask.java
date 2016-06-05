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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import com.cqgy.park.tool.CustomProps;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.Stool;
import com.cqgy.park.tool.task.ReportTool;
import static com.cqgy.park.tool.PdfUtil.*;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfWriter;

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
			String searchStartTime = ReportTool.getSearchTime(jdbcTemplate, parkId);
			if(searchStartTime==null){
				continue;
			}
			String searchEndTime = searchStartTime;
			InfoDutyShriftReportLog log = new InfoDutyShriftReportLog();
			log.setParkId(parkId);
			try {
				log.setFlag(1);
				log.setSearchStartTime(CustomTime.parseTime(searchStartTime));
				log.setTaskStartTime(new Date());
				String sql = "select park_id,user_emp_no,date_format(op_time,'%Y-%m-%d %T') op_time from info_user_login_log a"
						+ " where user_type=1 and park_id='"+parkId+"' "
						+ " and a.op_time>'" + searchStartTime + "' order by park_id,op_time asc";
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

						searchEndTime = (String) list.get(i - 1).get("op_time");
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
						//文件名规则： 车库名_操作员工号_交班时间_唯一编号.pdf
						String pdfName = report.getParkId()+"_"+report.getEmpNo()+"_"+CustomTime.parseDate(report.getOnTime())+"_"+Stool.uuid()+".pdf";
						report.setPdfName(pdfName);
						infoDutyShriftReportRepository.save(report);
						
						//保存日志信息
						log.setTaskEndTime(new Date());
						log.setSearchEndTime(CustomTime.parseTime(searchEndTime));
						log.setFlag(2);
						infoDutyShriftReportLogRepository.save(log);
						
						//生成pdf报表
						String file_dir = CustomProps.getProp("file.temp.path")+"/duty_shift/";
						Files.createDirectories(Paths.get(file_dir));
						
						String file_name = file_dir+"/"+pdfName;
						
						Document document = new Document();
						PdfWriter.getInstance(document, new FileOutputStream(file_name));
						document.open();
						
						String title = "交接班报表";
						//正文字体大小
				        int fontsize = 10;
				        FontSelector selector = getSelector(fontsize);
				        //标题字体大小
				        int titleFontSize = 20;
				        //创建标题
				        addTile(document, title, titleFontSize);
				        String parkNameSql = "select park_name from info_park where park_code='"+report.getParkId()+"'";
				        String parkName = jdbcTemplate.queryForObject(parkNameSql, String.class);
				        String empNameSql = " select emp_name from info_park_admin where emp_no='"+report.getEmpNo()+"' and park_id='"+report.getParkId()+"'";
				        System.out.println(empNameSql);
				        String empName = jdbcTemplate.queryForObject(empNameSql, String.class);
				        
				        String describe = "--创建时间："+CustomTime.parseString(new Date())+";\r";
				        describe += "--停车场："+parkName+"\r";
				        describe += "--操作员工号："+report.getEmpNo()+",操作员姓名:"+empName+"\r";
				        describe += "--在班时间："+CustomTime.parseString(log.getSearchStartTime())+" 至 ";
				        describe += CustomTime.parseString(log.getSearchEndTime())+"\r";
				        
				        //创建副标题，说明
				        addDescribe(document, describe, selector);
				        
				        //创建正文
				        int s = 1;
				        //车辆进出信息
				        String subTitle = s+"、车辆进出场信息";
				        int subTitleFontSize = 14;
				        addSubTitle(document,subTitle,subTitleFontSize);
				        document.add(Chunk.NEWLINE);
				        
				        ReportTool.createCarIoPDF(jdbcTemplate,document,report.getParkId(),log.getSearchStartTime(),log.getSearchEndTime(),selector);
						//创建收费信息
				        document.add(Chunk.NEWLINE);
				        s++;
				        subTitle = s+"、车辆收费信息";
				        addSubTitle(document,subTitle,subTitleFontSize);
				        document.add(Chunk.NEWLINE);
				        ReportTool.createCarPayPDF(jdbcTemplate,document,report.getParkId(),log.getSearchStartTime(),log.getSearchEndTime(),selector);
				        //创建新充值卡信息
				        document.add(Chunk.NEWLINE);
				        s++;
				        subTitle = s+"、新卡信息";
				        addSubTitle(document,subTitle,subTitleFontSize);
				        document.add(Chunk.NEWLINE);
				        ReportTool.createCardNewPDF(jdbcTemplate,document,report.getParkId(),log.getSearchStartTime(),log.getSearchEndTime(),selector);
				        //创建充值信息				
				        document.add(Chunk.NEWLINE);
				        s++;
				        subTitle = s+"、充值信息";
				        addSubTitle(document,subTitle,subTitleFontSize);
				        document.add(Chunk.NEWLINE);
				        ReportTool.createCardInPDF(jdbcTemplate,document,report.getParkId(),log.getSearchStartTime(),log.getSearchEndTime(),selector);
				        
				        //创建手动开闸信息
				        document.add(Chunk.NEWLINE);
				        s++;
				        subTitle = s+"、手动开闸信息";
				        addSubTitle(document,subTitle,subTitleFontSize);
				        document.add(Chunk.NEWLINE);
				        ReportTool.createOpenHandPDF(jdbcTemplate,document,report.getParkId(),log.getSearchStartTime(),log.getSearchEndTime(),selector);
				        
				        document.close();
				        report = new InfoDutyShriftReport();
						report.setEmpNo((String) list.get(i).get("user_emp_no"));
						report.setParkId((String) list.get(i).get("park_id"));
						report.setOnTime(CustomTime.parseTime((String) list.get(i).get("op_time")));
						report.setDownTime(CustomTime.parseTime((String) list.get(i).get("op_time")));					
						
					}
				}

				

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
