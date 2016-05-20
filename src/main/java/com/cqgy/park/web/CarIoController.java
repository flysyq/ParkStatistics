package com.cqgy.park.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.dao.CarIoService;
import com.cqgy.park.domain.InfoCarIo;

@Controller
public class CarIoController {
	@Autowired
	CarIoService carIoService;
	@RequestMapping(value="/cario/cariolist.do",method=RequestMethod.GET)
	public String list(HttpServletRequest request,Model model){
		String sql="select * from info_car_io";
		List<InfoCarIo> carIos = carIoService.getCarIos(sql);
		model.addAttribute("carIos", carIos);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitile", "记录查询");
		session.setAttribute("childrentitle", "场内记录");
		String forword="cario/cariolist";
		return forword;
	}
	
}
