package com.cqgy.park.web.log;

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
import com.cqgy.park.form.log.TimeCommonForm;

@Controller
public class InfoDutyShriftReportLog {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="infodutyshriftreportlog/list",method=RequestMethod.GET)
	public String list(TimeCommonForm form,Model model,HttpServletRequest request){
		String cousql="select count(*) cou from info_duty_shrift_report_log where task_start_time>'"+form.getStart_date()+"' and task_end_time<'"+form.getEnd_date()+"'";
		if (Objects.isNull(form)) {
			form=new TimeCommonForm();
		}
		Long cou=(Long) jdbcTemplate.queryForList(cousql).get(0).get("cou");
		Page page=new Page();
		page.setPage(form.getPage());
		page.setCount(cou);
		page.setPage_size(form.getPage_size());
		page = PageUtil.handle(page);
		String sql="select a.*,b.park_name from info_duty_shrift_report_log a left join info_park b on a.park_id=b.park_code and task_start_time>'"+form.getStart_date()+"' and task_end_time<'"+form.getEnd_date()+"' limit "+((page.getPage() - 1) * page.getPage_size()) + "," + page.getPage_size();
		List<Map<String, Object>> log = jdbcTemplate.queryForList(sql);
		model.addAttribute("page", page);
		model.addAttribute("log", log);
		model.addAttribute("form", form);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "系统日志");
		session.setAttribute("childrentitle", "报表生成日志");
		String forward="infodutyshriftreportlog/list";
		return forward;
	}
}
