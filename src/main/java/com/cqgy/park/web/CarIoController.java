package com.cqgy.park.web;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.dao.CarIoService;
import com.cqgy.park.domain.InfoCarIo;
import com.google.common.base.Strings;

@Controller
public class CarIoController {
	@Autowired
	CarIoService carIoService;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="/cario/cariolist.do",method=RequestMethod.GET)
	public String list(Long page,Long pageSize,String clause,String orderby,HttpServletRequest request,Model model){
		String oclause=clause;
		if (pageSize==null) {
			pageSize=(long) 10;
		}
		if (!Strings.isNullOrEmpty(clause)) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(clause.substring(clause.lastIndexOf("=")+1));
			if(clause.contains("in_emp_name")){
				clause="in_emp_name like '"+clause.substring(clause.lastIndexOf("=")+1)+"%'";
			}else if(clause.contains("out_emp_name")){
				clause="out_emp_name like '"+clause.substring(clause.lastIndexOf("=")+1)+"%'";
			}else if(clause.contains("card_type")){
				String cardType=clause.substring(clause.lastIndexOf("=")+1);
				if (cardType.equals("月卡")) {
					clause="card_type=1";
				}else if(cardType.equals("免费卡")){
					clause="card_type=2";
				}else if(cardType.equals("储蓄卡")){
					clause="card_type=3";
				}else if(cardType.equals("临时卡")){
					clause="card_type=4";
				}
			}else if(clause.contains("plate")){
				clause="plate like '%"+clause.substring(clause.lastIndexOf("=")+1)+"%'";
			}else{
				if (!isNum.matches()) {
					clause=clause.substring(0, clause.lastIndexOf("=")+1)+"null";
				}
			}
		}
		String countselect="select count(*) count from info_car_io";
		String countclause="";
		if (!Strings.isNullOrEmpty(clause)) {
			countclause += " where "+clause;
		}
		String countsql=countselect+countclause;
		Long count = (Long)jdbcTemplate.queryForList(countsql).get(0).get("count");
		long pageMax;
		if (count%pageSize==0) {
			pageMax=count/pageSize;
		}else{
			pageMax=count/pageSize+1;
		}
		if (page==0) {
			page=(long) 1;
		}
		if (pageMax==0) {
			pageMax=1;
		}
		Long prevPage=page-1;
		Long nextPage=page+1;
		if (prevPage==0) {
			prevPage=(long) 1;
		}
		if (nextPage>pageMax) {
			nextPage=pageMax;
		}
		Long pageStart=(page-1)*pageSize;

		String select = "select * from info_car_io";
		String limit=" limit "+pageStart+","+pageSize;
		String where = "";
		String sql;
		if(!Strings.isNullOrEmpty(clause)){
			where += " where "+clause;
		}
		if(!Strings.isNullOrEmpty(orderby)){
			where += " order by "+orderby;
		}
		sql=select+where+limit;
		List<InfoCarIo> carIos = carIoService.getCarIos(sql);
		model.addAttribute("carIos", carIos);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "记录查询");
		session.setAttribute("childrentitle", "场内记录");
		session.setAttribute("currentpage", page);
		session.setAttribute("pagesize", pageSize);
		session.setAttribute("prevpage", prevPage);
		session.setAttribute("nextpage", nextPage);
		session.setAttribute("maxpage", pageMax);
		session.setAttribute("orderby", orderby);
		session.setAttribute("clause", oclause);
		String forword="cario/cariolist";
		return forword;
	}
	
}
