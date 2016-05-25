package com.cqgy.park.web;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.dao.GateOpenService;
import com.cqgy.park.domain.InfoGateOpenHand;
import com.google.common.base.Strings;


@Controller
public class GateOpenController {
	@Autowired
	GateOpenService gateOpenService;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="gateopen/gateopenlist",method=RequestMethod.GET)
	public String list(Long page,Long pageSize,String orderby,HttpServletRequest request,Model model){
		if (pageSize==null) {
			pageSize=(long) 10;
		}
		String countsql="select count(*) count from info_gate_open_hand";
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

		String select = "select * from info_gate_open_hand";
		String limit=" limit "+pageStart+","+pageSize;
		String where = "";
		String sql;
		if(!Strings.isNullOrEmpty(orderby)){
			where += " order by "+orderby;
			sql=select+where+limit;
		}else{
			sql=select+limit;
		}
		List<InfoGateOpenHand> gateOpenHands = gateOpenService.getGateOpens(sql);
		model.addAttribute("gateOpenHands", gateOpenHands);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "记录查询");
		session.setAttribute("childrentitle", "场内记录");
		session.setAttribute("currentpage", page);
		session.setAttribute("pagesize", pageSize);
		session.setAttribute("prevpage", prevPage);
		session.setAttribute("nextpage", nextPage);
		session.setAttribute("maxpage", pageMax);
		session.setAttribute("orderby", orderby);
		String forword="gateopen/gateopenlist";
		return forword;
	}

}