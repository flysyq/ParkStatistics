
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

import com.cqgy.park.dao.CardInService;
import com.cqgy.park.domain.InfoCardIn;

@Controller
public class CardInController {
	@Autowired
	CardInService cardInService;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="/cardin/cardinlist.do",method=RequestMethod.GET)
	public String list(Long page,HttpServletRequest request,Model model){
		Long pageSize=(long) 5;	
		String countsql="select count(*) count from sys_user";
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

		String select = "select * from info_card_in limit "+pageStart+","+pageSize;
		//String sql="select * from info_card_in";
		List<InfoCardIn> cardIns = cardInService.getCardIns(select);
		model.addAttribute("cardIns", cardIns);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "记录查询");
		session.setAttribute("childrentitle", "充值延期");
		session.setAttribute("currentpage", page);
		session.setAttribute("prevpage", page-1);
		session.setAttribute("nextpage", page+1);
		session.setAttribute("maxpage", pageMax);
		String forword="/cardin/cardinlist";
		return forword;
	}
}
