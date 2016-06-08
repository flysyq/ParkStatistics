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
public class CarIoController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="cario/list.do",method=RequestMethod.GET)
	public String list(QueryForm form,HttpServletRequest request,Model model){
		if (Objects.isNull(form)) {
			form=new QueryForm();
		}
		String couselect="select count(*) cou from info_car_io ";
		String cwhere="";
		if (form.getWhere()!=null) {
			if (form.getWhere().equals("park_name")) {
				cwhere="a join info_park b on a.park_id=b.park_code and b.park_name like '%"+form.getClause()+"%'";
			}
			if (form.getWhere().equals("park_id")) {
				cwhere="where park_id="+form.getClause();
			}
			if (form.getWhere().equals("plate")) {
				cwhere="where plate like '%"+form.getClause()+"%'";
			}
			if (form.getWhere().equals("card_type")) {
				if (form.getClause().equals("月卡")) {
					cwhere="where card_type=1";
				}else if (form.getClause().equals("免费卡")) {
					cwhere="where card_type=2";
				}else if (form.getClause().equals("储蓄卡")) {
					cwhere="where card_type=3";
				}else if (form.getClause().equals("临时卡")) {
					cwhere="where card_type=4";
				}else{
					cwhere="where card_type=0";
				}
			}
			if (form.getWhere().equals("card_no")){
				cwhere="where card_no="+form.getClause();
			}
			if (form.getWhere().equals("in_emp_name")){
				cwhere="where in_emp_name like '%"+form.getClause()+"%'";
			}
			if (form.getWhere().equals("out_emp_name")){
				cwhere="where out_emp_name like '%"+form.getClause()+"%'";
			}
		}
		String cousql=couselect+cwhere;
		Long cou = (Long) jdbcTemplate.queryForList(cousql).get(0).get("cou");
		Page page=new Page();
		page.setPage(form.getPage());
		page.setCount(cou);
		page.setPage_size(form.getPage_size());
		page = PageUtil.handle(page);
		String select = "select a.*,b.park_name,concat('"+CustomProps.getProp("file.http.url")+"/car_in/',date_format(come_time,'%Y-%m-%d'),'/',come_pic) come_pic_path,concat('"+CustomProps.getProp("file.http.url")+"/car_out/',date_format(go_time,'%Y-%m-%d'),'/',go_pic) go_pic_path,concat('"+CustomProps.getProp("file.http.url")+"/car_park_space/',date_format(go_time,'%Y-%m-%d'),'/',park_space_pic) park_space_pic_path ";
		String from="from info_car_io a join info_park b ";
		String on= "on a.park_id=b.park_code ";
		String where="";
		if (form.getWhere()!=null) {
			if (form.getWhere().equals("park_id")) {
				where="and park_id="+form.getClause();
			}
			if (form.getWhere().equals("park_name")) {
				where="and b.park_name like '%"+form.getClause()+"%'";
			}
			if (form.getWhere().equals("plate")) {
				where="where plate like '%"+form.getClause()+"%'";
			}
			if (form.getWhere().equals("card_type")) {
				if (form.getClause().equals("月卡")) {
					where="and card_type=1";
				}else if (form.getClause().equals("免费卡")) {
					where="and card_type=2";
				}else if (form.getClause().equals("储蓄卡")) {
					where="and card_type=3";
				}else if (form.getClause().equals("临时卡")) {
					where="and card_type=4";
				}else{
					where="and card_type=0";
				}
			}
			if (form.getWhere().equals("card_no")){
				where="and card_no="+form.getClause();
			}
			if (form.getWhere().equals("in_emp_name")){
				where="where in_emp_name like '%"+form.getClause()+"%'";
			}
			if (form.getWhere().equals("out_emp_name")){
				where="where out_emp_name like '%"+form.getClause()+"%'";
			}
		}
		String orderby="";
		if (form.getOrderby()!=null) {
			if (form.getOrderby().equals("plate")) {
				orderby=" order by plate";
			}
			if (form.getOrderby().equals("come_time")) {
				orderby=" order by come_time";
			}
			if (form.getOrderby().equals("go_time")) {
				orderby=" order by go_time";
			}
			if (form.getOrderby().equals("in_emp_name")) {
				orderby=" order by in_emp_name";
			}
			if (form.getOrderby().equals("out_emp_name")) {
				orderby=" order by out_emp_name";
			}
		}
		String limit=" limit " + ((page.getPage() - 1) * page.getPage_size()) + "," + page.getPage_size();
		String sql=select+from+on+where+orderby+limit;
		System.out.println(sql);
		List<Map<String, Object>> carIos = jdbcTemplate.queryForList(sql);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "记录查询");
		session.setAttribute("childrentitle", "场内记录");
		model.addAttribute("carIos", carIos);
		model.addAttribute("page", page);
		model.addAttribute("form", form);
		String forward="cario/list";
		return forward;	
	}
}
