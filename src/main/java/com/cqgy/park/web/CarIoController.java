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

import com.cqgy.park.dao.CarIoService;
import com.cqgy.park.domain.InfoCarIo;

@Controller
public class CarIoController {
	@Autowired
	CarIoService carIoService;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="/cario/cariolist.do",method=RequestMethod.GET)
	public String list(Long page,String orderby,HttpServletRequest request,Model model){
		Long pageSize=(long) 5;	
		String countsql="select count(*) count from info_car_io";
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
		if(!Objects.isNull(orderby)){
			where += " order by "+orderby;
			sql=select+where+limit;
		}else{
			sql=select+limit;
		}
		List<InfoCarIo> carIos = carIoService.getCarIos(sql);
		model.addAttribute("carIos", carIos);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "记录查询");
		session.setAttribute("childrentitle", "场内记录");
		session.setAttribute("currentpage", page);
		session.setAttribute("prevpage", prevPage);
		session.setAttribute("nextpage", nextPage);
		session.setAttribute("maxpage", pageMax);
		session.setAttribute("orderby", orderby);
		String forword="cario/cariolist";
		return forword;
	}
	
}
