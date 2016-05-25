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

import com.cqgy.park.dao.CarParkPayService;
import com.cqgy.park.domain.InfoCarParkPay;
import com.google.common.base.Strings;

@Controller
public class CarParkPayController {
	@Autowired
	CarParkPayService carParkPayService;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value="/carparkpay/carparkpaylist.do",method=RequestMethod.GET)
	public String list(Long page,Long pageSize,String orderby,HttpServletRequest request,Model model){
		if (pageSize==null) {
			pageSize=(long) 10;
		}
		String countsql="select count(*) count from info_car_park_pay";
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

		String select = "select * from info_car_park_pay";
		String limit=" limit "+pageStart+","+pageSize;
		String where = "";
		String sql;
		if(!Strings.isNullOrEmpty(orderby)){
			where += " order by "+orderby;
			sql=select+where+limit;
		}else{
			sql=select+limit;
		}
		List<InfoCarParkPay> carParkPays = carParkPayService.getCarParkPays(sql);
		model.addAttribute("carParkPays", carParkPays);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "记录查询");
		session.setAttribute("childrentitle", "缴费记录");
		session.setAttribute("currentpage", page);
		session.setAttribute("pagesize", pageSize);
		session.setAttribute("prevpage", prevPage);
		session.setAttribute("nextpage", nextPage);
		session.setAttribute("maxpage", pageMax);
		session.setAttribute("orderby", orderby);
		String forword="carparkpay/carparkpaylist";
		return forword;
	}
}
