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
public class InfoUserLoginLogController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="infouserloginlog/list.do",method=RequestMethod.GET)
	public String list(TimeCommonForm form,Model model,HttpServletRequest request){
		String cousql="select count(*) cou from info_user_login_log where op_time>'"+form.getStart_date()+"' and op_time<'"+form.getEnd_date()+"'";
		if (Objects.isNull(form)) {
			form = new TimeCommonForm();
		}
		Long cou = (Long) jdbcTemplate.queryForList(cousql).get(0).get("cou");
		Page page=new Page();
		page.setPage(form.getPage());
		page.setCount(cou);
		page.setPage_size(form.getPage_size());
		page = PageUtil.handle(page);
		String sql="select a.*,b.park_name from info_user_login_log a left join info_park b on a.op_time>'"+form.getStart_date()+"' and a.op_time<'"+form.getEnd_date()+"' and a.park_id=b.park_code limit " + ((page.getPage() - 1) * page.getPage_size()) + "," + page.getPage_size();;
		List<Map<String, Object>> log = jdbcTemplate.queryForList(sql);
		model.addAttribute("page", page);
		model.addAttribute("log", log);
		model.addAttribute("form", form);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "系统日志");
		session.setAttribute("childrentitle", "交班日志");
		String forward="infouserloginlog/list";
		return forward;
	}
}
