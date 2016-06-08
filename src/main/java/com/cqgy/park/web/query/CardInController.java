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
public class CardInController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="cardin/list.do",method=RequestMethod.GET)
	public String list(QueryForm form,HttpServletRequest request,Model model){
		if (Objects.isNull(form)) {
			form=new QueryForm();
		}
		String couselect="select count(*) cou from info_card_in ";
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
				String clause = form.getClause();
				if(clause.equals("卡")){

				}else if ("月卡".contains(clause)) {
					cwhere="where card_type=1";
				}else if ("免费卡".contains(clause)) {
					cwhere="where card_type=2";
				}else if ("储蓄卡".contains(clause)) {
					cwhere="where card_type=3";
				}else if ("临时卡".contains(clause)) {
					cwhere="where card_type=4";
				}else {
					cwhere="where card_type=0";
				}
			}
			if (form.getWhere().equals("card_no")){
				cwhere="where card_no="+form.getClause();
			}
			if (form.getWhere().equals("pay_money")){
				cwhere="where pay_money="+form.getClause();
			}
			if (form.getWhere().equals("accept_emp_name")){
				cwhere="where accept_emp_name like '%"+form.getClause()+"%'";
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
		String from="from info_card_in a join info_park b ";
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
				String clause = form.getClause();
				if(clause.equals("卡")){
				}else if ("月卡".contains(clause)) {
					where="where card_type=1";
				}else if ("免费卡".contains(clause)) {
					where="where card_type=2";
				}else if ("储蓄卡".contains(clause)) {
					where="where card_type=3";
				}else if ("临时卡".contains(clause)) {
					where="where card_type=4";
				}else{
					where="where card_type=0";
				}
			}
			if (form.getWhere().equals("card_no")){
				where="and card_no="+form.getClause();
			}
			if (form.getWhere().equals("pay_money")){
				where="and pay_money="+form.getClause();
			}
			if (form.getWhere().equals("accept_emp_name")){
				where="and accept_emp_name like '%"+form.getClause()+"%'";
			}
		}
		String orderby="";
		if (form.getOrderby()!=null) {
			if (form.getOrderby().equals("plate")) {
				orderby=" order by plate";
			}
			if (form.getOrderby().equals("start_date")) {
				orderby=" order by start_date";
			}
			if (form.getOrderby().equals("end_date")) {
				orderby=" order by end_date";
			}
			if (form.getOrderby().equals("accept_emp_name")) {
				orderby=" order by accept_emp_name";
			}
		}
		String limit=" limit " + ((page.getPage() - 1) * page.getPage_size()) + "," + page.getPage_size();
		String sql=select+from+on+where+orderby+limit;
		System.out.println(sql);
		List<Map<String, Object>> cardIns = jdbcTemplate.queryForList(sql);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "记录查询");
		session.setAttribute("childrentitle", "充值延期");
		model.addAttribute("page", page);
		model.addAttribute("form", form);
		model.addAttribute("cardIns", cardIns);
		String forward="cardin/list";
		return forward;	
	}
}
