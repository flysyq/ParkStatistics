package com.cqgy.park.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value="/cardin/cardinlist.do",method=RequestMethod.GET)
	public String list(HttpServletRequest request,Model model){
		String sql="select * from info_card_in";
		List<InfoCardIn> cardIns = cardInService.getCardIns(sql);
		model.addAttribute("cardIns", cardIns);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "记录查询");
		session.setAttribute("childrentitle", "充值延期");
		String forword="/cardin/cardinlist";
		return forword;
	}
}
