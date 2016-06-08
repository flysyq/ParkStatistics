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

@Controller
public class CarParkPayController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="carparkpay/list.do",method=RequestMethod.GET)
	public String list(QueryForm form,HttpServletRequest request,Model model){
		if (Objects.isNull(form)) {
			form=new QueryForm();
		}
		String couselect="select count(*) cou from info_car_park_pay ";
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
			if (form.getWhere().equals("pay_type")) {
				if (form.getClause().equals("月卡")) {
					cwhere="where pay_type=1";
				}else if (form.getClause().equals("免费卡")) {
					cwhere="where pay_type=2";
				}else if (form.getClause().equals("储蓄卡")) {
					cwhere="where pay_type=3";
				}else if (form.getClause().equals("现金")) {
					cwhere="where pay_type=4";
				}else if (form.getClause().equals("会员积分")) {
					cwhere="where pay_type=5";
				}else{
					cwhere="where pay_type=0";
				}
			}
			if (form.getWhere().equals("free_type")) {
				if (form.getClause().equals("免费卡")) {
					cwhere="where free_type=1";
				}else if (form.getClause().equals("领导审批")) {
					cwhere="where free_type=2";
				}else if (form.getClause().equals("折扣卡")) {
					cwhere="where free_type=3";
				}else{
					cwhere="where free_type=0";
				}
			}
			if (form.getWhere().equals("card_no")){
				cwhere="where card_no="+form.getClause();
			}
			if (form.getWhere().equals("emp_name")){
				cwhere="where emp_name like '%"+form.getClause()+"%'";
			}
		}
		String cousql=couselect+cwhere;
		Long cou = (Long) jdbcTemplate.queryForList(cousql).get(0).get("cou");
		Page page=new Page();
		page.setPage(form.getPage());
		page.setCount(cou);
		page.setPage_size(form.getPage_size());
		page = PageUtil.handle(page);
		String select = "select a.*,b.park_name ";
		String from="from info_car_park_pay a join info_park b ";
		String on= "on a.park_id=b.park_code ";
		String where="";
		if (form.getWhere()!=null) {
			if (form.getWhere().equals("park_id")) {
				where="where park_id="+form.getClause();
			}
			if (form.getWhere().equals("park_name")) {
				where="and b.park_name like '%"+form.getClause()+"%'";
			}
			if (form.getWhere().equals("plate")) {
				where="where plate like '%"+form.getClause()+"%'";
			}
			if (form.getWhere().equals("card_type")) {
				if (form.getClause().equals("月卡")) {
					where="where card_type=1";
				}else if (form.getClause().equals("免费卡")) {
					where="where card_type=2";
				}else if (form.getClause().equals("储蓄卡")) {
					where="where card_type=3";
				}else if (form.getClause().equals("临时卡")) {
					where="where card_type=4";
				}else{
					where="where card_type=0";
				}
			}
			if (form.getWhere().equals("pay_type")) {
				if (form.getClause().equals("月卡")) {
					where="where pay_type=1";
				}else if (form.getClause().equals("免费卡")) {
					where="where pay_type=2";
				}else if (form.getClause().equals("储蓄卡")) {
					where="where pay_type=3";
				}else if (form.getClause().equals("现金")) {
					where="where pay_type=4";
				}else if (form.getClause().equals("会员积分")) {
					where="where pay_type=5";
				}else{
					cwhere="where pay_type=0";
				}
			}
			if (form.getWhere().equals("free_type")) {
				if (form.getClause().equals("免费卡")) {
					where="where free_type=1";
				}else if (form.getClause().equals("领导审批")) {
					where="where free_type=2";
				}else if (form.getClause().equals("折扣卡")) {
					where="where free_type=3";
				}else{
					where="where free_type=0";
				}
			}
			if (form.getWhere().equals("card_no")){
				where="where card_no="+form.getClause();
			}
			if (form.getWhere().equals("emp_name")){
				where="where emp_name like '%"+form.getClause()+"%'";
			}
		}
		String orderby="";
		if (form.getOrderby()!=null) {
			if (form.getOrderby().equals("plate")) {
				orderby=" order by plate";
			}
			if (form.getOrderby().equals("start_time")) {
				orderby=" order by start_time";
			}
			if (form.getOrderby().equals("end_time")) {
				orderby=" order by end_time";
			}
			if (form.getOrderby().equals("emp_name")) {
				orderby=" order by emp_name";
			}
			if (form.getOrderby().equals("card_type")) {
				orderby=" order by card_type";
			}
			if (form.getOrderby().equals("free_type")) {
				orderby=" order by free_type";
			}
			if (form.getOrderby().equals("pay_type")) {
				orderby=" order by pay_type";
			}
		}
		String limit=" limit " + ((page.getPage() - 1) * page.getPage_size()) + "," + page.getPage_size();
		String sql=select+from+on+where+orderby+limit;
		System.out.println(sql);
		List<Map<String, Object>> carParkPays = jdbcTemplate.queryForList(sql);
		model.addAttribute("carParkPays", carParkPays);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "记录查询");
		session.setAttribute("childrentitle", "缴费记录");
		model.addAttribute("page", page);
		model.addAttribute("form", form);
		String forward="carparkpay/list";
		return forward;	
	}
}
