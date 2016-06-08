package com.cqgy.park.web.query;

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
import com.cqgy.park.form.query.QueryForm;
import com.cqgy.park.tool.CustomProps;

@Controller
public class GateOpenController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="gateopen/list.do",method=RequestMethod.GET)
	public String list(QueryForm form,HttpServletRequest request,Model model){
		if (Objects.isNull(form)) {
			form=new QueryForm();
		}
		String couselect="select count(*) cou from info_gate_open_hand ";
		String cwhere="";
		if (form.getWhere()!=null) {
			if (form.getWhere().equals("park_name")) {
				cwhere="a join info_park b on a.park_id=b.park_code and b.park_name like '%"+form.getClause()+"%'";
			}
			if (form.getWhere().equals("park_id")) {
				cwhere="where park_id="+form.getClause();
			}
			if (form.getWhere().equals("open_type")) {
				String clause=form.getClause();
				if(clause.equals("开闸")){

				}else if ("手动开闸".contains(clause)) {
					cwhere="where open_type=1";
				}else if ("非法开闸".contains(clause)) {
					cwhere="where open_type=2";
				}else{
					cwhere="where open_type=0";
				}
			}
			if (form.getWhere().equals("open_emp_name")){
				cwhere="where open_emp_name like '%"+form.getClause()+"%'";
			}
		}
		String cousql=couselect+cwhere;
		Long cou = (Long) jdbcTemplate.queryForList(cousql).get(0).get("cou");
		Page page=new Page();
		page.setPage(form.getPage());
		page.setCount(cou);
		page.setPage_size(form.getPage_size());
		page = PageUtil.handle(page);
		String select = "select a.*,b.park_name,concat('"+CustomProps.getProp("file.http.url")+"/open_hand/',date_format(open_time,'%Y-%m-%d'),'/',open_pic) open_pic_path";
		String from=" from info_gate_open_hand a join info_park b ";
		String on= "on a.park_id=b.park_code ";
		String where="";
		if (form.getWhere()!=null) {
			if (form.getWhere().equals("park_id")) {
				where="and park_id="+form.getClause();
			}
			if (form.getWhere().equals("park_name")) {
				where="and b.park_name like '%"+form.getClause()+"%'";
			}
			if (form.getWhere().equals("open_type")) {
				String clause=form.getClause();
				if(clause.equals("开闸")){

				}else if ("手动开闸".contains(clause)) {
					where="where open_type=1";
				}else if ("非法开闸".contains(clause)) {
					where="where open_type=2";
				}else{
					where="where open_type=0";
				}
			}
			if (form.getWhere().equals("open_emp_name")){
				where="where open_emp_name like '%"+form.getClause()+"%'";
			}
		}
		String orderby="";
		if (form.getOrderby()!=null) {
			if (form.getOrderby().equals("open_type")) {
				orderby=" order by open_type";
			}
			if (form.getOrderby().equals("open_time")) {
				orderby=" order by open_time";
			}
			if (form.getOrderby().equals("open_emp_name")) {
				orderby=" order by open_emp_name";
			}
		}
		String limit=" limit " + ((page.getPage() - 1) * page.getPage_size()) + "," + page.getPage_size();
		String sql=select+from+on+where+orderby+limit;
		System.out.println(sql);
		List<Map<String, Object>> gateOpenHands = jdbcTemplate.queryForList(sql);
		model.addAttribute("gateOpenHands", gateOpenHands);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "记录查询");
		session.setAttribute("childrentitle", "开闸记录");
		model.addAttribute("page", page);
		model.addAttribute("form", form);
		String forward="gateopen/list";
		return forward;	
	}
}
