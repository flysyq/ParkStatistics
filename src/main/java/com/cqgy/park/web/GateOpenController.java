package com.cqgy.park.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

import com.cqgy.park.dao.GateOpenService;
import com.cqgy.park.domain.InfoGateOpenHand;
import com.cqgy.park.tool.CustomProps;
import com.google.common.base.Strings;


@Controller
public class GateOpenController {
	@Autowired
	GateOpenService gateOpenService;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="gateopen/gateopenlist",method=RequestMethod.GET)
	public String list(Long page,String clause,Long pageSize,String orderby,HttpServletRequest request,Model model){
		String oclause=clause;
		if (pageSize==null) {
			pageSize=(long) 10;
		}
		if (!Strings.isNullOrEmpty(clause)) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(clause.substring(clause.lastIndexOf("=")+1));
			if(clause.contains("open_emp_name")){
				clause="open_emp_name like '"+clause.substring(clause.lastIndexOf("=")+1)+"%'";
			}else if(clause.contains("open_type")){
				String openType=clause.substring(clause.lastIndexOf("=")+1);
				if (openType.equals("手动开闸")) {
					clause="open_type=1";
				}else if(openType.equals("异常开闸")){
					clause="open_type=2";
				}
			}else{
				if (!isNum.matches()) {
					clause=clause.substring(0, clause.lastIndexOf("=")+1)+"null";
				}
			}
		}
		String countselect="select count(*) count from info_gate_open_hand";
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

		String select = "select a.*,b.park_name,concat('"+CustomProps.getProp("file.http.url")+"/open_hand/',date_format(open_time,'%Y-%m-%d'),'/',open_pic) open_pic_path from info_gate_open_hand a,info_park b";
		String limit=" limit "+pageStart+","+pageSize;
		String where = " where a.park_id=b.park_code";
		String sql;
		if(!Strings.isNullOrEmpty(clause)){
			where += " and "+clause;
		}
		if(!Strings.isNullOrEmpty(orderby)){
			where += " order by "+orderby;
		}
		sql=select+where+limit;
		List<Map<String, Object>> gateOpenHands = jdbcTemplate.queryForList(sql);

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
		session.setAttribute("clause", oclause);
		String forword="gateopen/gateopenlist";
		return forword;
	}

}