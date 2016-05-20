package com.cqgy.park.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.dao.GateOpenService;


@Controller
public class GateOpenController {
	@Autowired
	GateOpenService gateOpenService;
	@RequestMapping(value="gateopen/gateopenlist",method=RequestMethod.GET)
	public String list(Integer openType,HttpServletRequest request,Model model){
		String sql="select * from info_gate_open_hand where open_type="+openType;
		gateOpenService.getGateOpens(sql);
		String forword="gateopen/gateopenlist";
		return forword;
	}

}
