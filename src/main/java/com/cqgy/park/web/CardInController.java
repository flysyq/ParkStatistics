
package com.cqgy.park.web;

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

import com.cqgy.park.dao.CardInService;
import com.cqgy.park.domain.InfoCardIn;
import com.google.common.base.Strings;

@Controller
public class CardInController {
	@Autowired
	CardInService cardInService;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="/cardin/cardinlist.do",method=RequestMethod.GET)
	public String list(Long page,Long pageSize,String clause, String orderby,HttpServletRequest request,Model model){
		System.out.println(clause);
		String oclause=clause;
		if (pageSize==null) {
			pageSize=(long) 10;
		}
		if (!Strings.isNullOrEmpty(clause)) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(clause.substring(clause.lastIndexOf("=")+1));
			if(clause.contains("emp_name")){
				clause="emp_name like '"+clause.substring(clause.lastIndexOf("=")+1)+"%'";
			}else if(clause.contains("card_type")){
				String cardType=clause.substring(clause.lastIndexOf("=")+1);
				if (cardType.equals("月卡")) {
					clause="card_type=1";
				}else if(cardType.equals("免费卡")){
					clause="card_type=2";
				}else if(cardType.equals("储蓄卡")){
					clause="card_type=3";
				}else if(cardType.equals("临时卡")){
					clause="card_type=4";
				}
			}else if(clause.contains("pay_type")){
				String payType=clause.substring(clause.lastIndexOf("=")+1);
				if (payType.equals("月卡")) {
					clause="pay_type=1";
				}else if(payType.equals("免费卡")){
					clause="pay_type=2";
				}else if(payType.equals("储蓄卡")){
					clause="pay_type=3";
				}else if(payType.equals("现金")){
					clause="pay_type=4";
				}else if(payType.equals("会员积分")){
					clause="pay_type=5";
				}
			}else if(clause.contains("pay_type")){
				String payType=clause.substring(clause.lastIndexOf("=")+1);
				if (payType.equals("月卡")) {
					clause="pay_type=1";
				}else if(payType.equals("免费卡")){
					clause="pay_type=2";
				}else if(payType.equals("储蓄卡")){
					clause="pay_type=3";
				}else if(payType.equals("现金")){
					clause="pay_type=4";
				}else if(payType.equals("会员积分")){
					clause="pay_type=5";
				}
			}else if(clause.contains("free_type")){
				String freeType=clause.substring(clause.lastIndexOf("=")+1);
				if (freeType.equals("免费卡")) {
					clause="free_type=1";
				}else if(freeType.equals("领导审批")){
					clause="free_type=2";
				}else if(freeType.equals("折扣卡")){
					clause="free_type=3";
				}
			}else if(clause.contains("plate")){
				clause="plate like '%"+clause.substring(clause.lastIndexOf("=")+1)+"%'";
			}else{
				if (!isNum.matches()) {
					clause=clause.substring(0, clause.lastIndexOf("=")+1)+"null";
				}
			}
		}
		
		String countselect="select count(*) count from info_card_in";
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

		String select = "select b.park_name,a.* from info_card_in a,info_park b";
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
		System.out.println(sql);
		//String sql="select * from info_card_in";
		List<Map<String, Object>> cardIns = jdbcTemplate.queryForList(sql);
		model.addAttribute("cardIns", cardIns);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "记录查询");
		session.setAttribute("childrentitle", "充值延期");
		session.setAttribute("currentpage", page);
		session.setAttribute("pagesize", pageSize);
		session.setAttribute("prevpage", prevPage);
		session.setAttribute("nextpage", nextPage);
		session.setAttribute("maxpage", pageMax);
		session.setAttribute("orderby", orderby);
		session.setAttribute("clause", oclause);
		String forword="cardin/cardinlist";
		return forword;
	}
}
