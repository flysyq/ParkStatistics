package com.cqgy.park.web.report;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.bo.Page;
import com.cqgy.park.bo.PageUtil;
import com.cqgy.park.form.report.InfoDutyShriftReportForm;

@Controller
public class InfoDutyShriftReportController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="infodutyshriftreport/list.do",method=RequestMethod.GET)
	public String list(InfoDutyShriftReportForm form,Model model,HttpServletRequest request){
		if (Objects.isNull(form)) {
			form=new InfoDutyShriftReportForm();
		}
		String cousql="select count(*) cou from info_duty_shrift_report where on_time>'"+form.getStart_date()+"' and on_time<'"+form.getEnd_date()+"' and down_time>'"+form.getStart_date()+"' and down_time<'"+form.getEnd_date();
		Long cou = (Long) jdbcTemplate.queryForList(cousql).get(0).get("cou");
		Page page=new Page();
		page.setPage(form.getPage());
		page.setCount(cou);
		page.setPage_size(form.getPage_size());
		page = PageUtil.handle(page);
		String select="select a.*,b.park_name ";
		String from="from info_duty_shrift_report a join info_park b on a.park_id=b.park_code and a.on_time>'"+form.getStart_date()+"' and a.on_time<'"+form.getEnd_date()+"' and a.down_time>'"+form.getStart_date()+"' and a.down_time<'"+form.getEnd_date();
		String where="";
		String limit=" limit " + ((page.getPage() - 1) * page.getPage_size()) + "," + page.getPage_size();
		
		if (form.getWhere()!=null) {
			if (form.getWhere().equals("park_id")) {
				where+=" and a.park_id="+form.getClause();
			}
			if (form.getWhere().equals("park_name")) {
				where+=" and b.park_name ='"+form.getClause()+"'";
			}
			if (form.getWhere().equals("emp_no")) {
				where+=" and emp_no ='"+form.getClause()+"'";
			}
		}
		String sql=select+from+where+limit;
		List<Map<String, Object>> report = jdbcTemplate.queryForList(sql);
		model.addAttribute("page", page);
		model.addAttribute("form", form);
		model.addAttribute("report", report);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "交接班报表");
		session.setAttribute("childrentitle", "交班记录");	
		String forward="infodutyshriftreport/list";
		return forward;
	}
}
