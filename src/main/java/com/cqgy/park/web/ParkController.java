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

import com.cqgy.park.dao.ParkRepository;
import com.cqgy.park.dao.ParkService;
import com.cqgy.park.domain.InfoPark;

@Controller
public class ParkController {
	
	@Autowired
	ParkService parkService;
	@Autowired
	ParkRepository parkRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="/park/parklist.do",method=RequestMethod.GET)
	public String list(Long del_id,Long page,HttpServletRequest request,Model model){
		if (!Objects.isNull(del_id)) {
			String delsql="delete from info_park where id="+del_id;
			jdbcTemplate.update(delsql);
		}
		Long pageSize=(long) 5;	
		String countsql="select count(*) count from info_park";
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
		if (pageStart<0) {
			pageStart=(long) 0;
		}
		
		String select = "select * from info_park limit "+pageStart+","+pageSize;
		String where = "";
		String sql=select+where;
		List<InfoPark> parks = parkService.getParks(sql);
		model.addAttribute("parks",parks);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "车库管理");
		session.setAttribute("childrentitle", "车库管理");
		session.setAttribute("currentpage", page);
		session.setAttribute("prevpage", prevPage);
		session.setAttribute("nextpage", nextPage);
		session.setAttribute("maxpage", pageMax);
		String forword="park/parklist";
		return forword;
	}
}
