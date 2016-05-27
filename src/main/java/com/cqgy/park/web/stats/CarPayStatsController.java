/**
 * @作者 admin
 * @时间 2016年5月25日 下午1:37:24
 * @类名 CarPayStatsController.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月25日 下午1:37:24
 *   修改描述
 */
package com.cqgy.park.web.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.bo.Page;
import com.cqgy.park.bo.PageUtil;
import com.cqgy.park.form.stats.CarPayStatsForm;

@Controller
public class CarPayStatsController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "carpaystats/list.do", method = RequestMethod.GET)
	public String list(CarPayStatsForm form, HttpServletRequest request, Model model) {
		String select = " sum(fee) sum_fee,sum(fee_free) sum_fee_free";
		String from = " from info_car_park_pay ";
		String where = "";
		if (Objects.isNull(form)) {
			form = new CarPayStatsForm();
		}

		where = "where end_time>'" + form.getStart_date() + "' and end_time<'" + form.getEnd_date() + "'";

		String groupby = "";
		
		//Excel
		ArrayList<String> excel_names = new ArrayList<String>();
		ArrayList<String> excel_codes = new ArrayList<String>();

		if (form.getPark_check() == 1) {
			select = " park_id," + select;
			groupby = " park_id," + groupby;
		}
		if (form.getEmp_check() == 1) {
			select = " emp_no," + select;
			groupby = " emp_no," + groupby;
		}
		if (form.getPlate_check() == 1) {
			select = " plate," + select;
			groupby = " plate," + groupby;
		}

		if (groupby.length() > 0) {
			groupby = "group by " + groupby.substring(0, groupby.length() - 1);
		}

		String sql_t = "select " + select + from + where + groupby;
		String sql = "";
		String select_1 = " sum_fee,sum_fee_free ";
		String from_1 = " (" + sql_t + ") a";
		String where_1 = "";

		if (sql_t.contains("park_id")) {
			excel_names.add("停车场名称");
			excel_names.add("停车场编号");
			excel_codes.add("park_name");
			excel_codes.add("park_code");
			
			select_1 = "b.park_name,b.id park_id,b.park_code," + select_1;
			from_1 = " info_park b," + from_1;
			where_1 = " a.park_id=b.park_code ";
		}

		if (sql_t.contains("emp_no")) {
			excel_names.add("操作员姓名");
			excel_names.add("操作名编码");
			excel_codes.add("emp_name");
			excel_codes.add("emp_no");
			select_1 = "c.emp_no,c.emp_name," + select_1;
			from_1 = " info_park_admin c," + from_1;
			where_1 = " and a.emp_no=c.emp_no";
		}
		if (sql_t.contains("plate")) {
			excel_names.add("车牌号");
			excel_codes.add("plate");
			select_1 = "a.plate," + select_1;
		}
		
		excel_names.add("收费金额");
		excel_names.add("免费金额");
		excel_codes.add("sum_fee");
		excel_codes.add("sum_fee_free");

		if (where_1.startsWith(" and ")) {
			where_1 = where_1.substring(4);
		}
		if (where_1.length() > 0) {
			where_1 = " where " + where_1;
		}
		sql = "select " + select_1 + " from " + from_1 + where_1;
		String sql1 = "select count(*) cou from (" + sql_t + ") a";

		System.out.println(sql);
		System.out.println(sql1);
		Long cou = (Long) jdbcTemplate.queryForList(sql1).get(0).get("cou");
		Page page = new Page();
		page.setPage(form.getPage());
		page.setCount(cou);
		page.setPage_size(form.getPage_size());
		page = PageUtil.handle(page);
		sql = sql + " limit " + ((page.getPage() - 1) * page.getPage_size()) + "," + page.getPage_size();

		List<Map<String, Object>> stats = jdbcTemplate.queryForList(sql);

		model.addAttribute("page", page);
		model.addAttribute("stats", stats);
		model.addAttribute("form", form);
		String forward = "carpaystats/list";
		return forward;
	}
}
