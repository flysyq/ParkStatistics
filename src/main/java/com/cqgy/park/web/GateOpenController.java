package com.cqgy.park.web;

import java.util.List;

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


@Controller
public class GateOpenController {
	@Autowired
	GateOpenService gateOpenService;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="gateopen/gateopenlist",method=RequestMethod.GET)
	public String list(Long page,HttpServletRequest request,Model model){
		Long pageSize=(long) 5;	
		String countsql="select count(*) count from info_gate_open_hand";
		Long count = (Long)jdbcTemplate.queryForList(countsql).get(0).get("count");
		long pageMax;
		if (count%pageSize==0) {
			pageMax=count/pageSize;
		}else{
			pageMax=count/pageSize+1;
		}
		if (page<1) {
			page=(long) 1;
		}else if (page>pageMax) {
			page=pageMax;
		}
		Long pageStart=(page-1)*pageSize;

		String select = "select * from info_gate_open_hand limit "+pageStart+","+pageSize;
		List<InfoGateOpenHand> gateOpenHands = gateOpenService.getGateOpens(select);
		model.addAttribute("gateOpenHands", gateOpenHands);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "记录查询");
		session.setAttribute("childrentitle", "场内记录");
		session.setAttribute("currentpage", page);
		session.setAttribute("prevpage", page-1);
		session.setAttribute("nextpage", page+1);
		session.setAttribute("maxpage", pageMax);
		String forword="gateopen/gateopenlist";
		return forword;
	}

}