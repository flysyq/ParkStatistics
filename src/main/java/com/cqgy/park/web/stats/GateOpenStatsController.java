package com.cqgy.park.web.stats;

import static com.cqgy.park.tool.ExcelUtil.createExcel;
import static com.cqgy.park.tool.PdfUtil.createPdf;
import static com.cqgy.park.tool.Stool.uuid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.bo.Page;
import com.cqgy.park.bo.PageUtil;
import com.cqgy.park.form.stats.GateOpenStatsForm;
import com.cqgy.park.tool.CustomProps;
import com.cqgy.park.tool.CustomTime;

@Controller
public class GateOpenStatsController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="gateopenstats/list.do",method=RequestMethod.GET)
	public String list(GateOpenStatsForm form,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		String select= " count(*) cou_open";
		String from=" from info_gate_open_hand ";
		String where="";
		if (Objects.isNull(form)) {
			form=new GateOpenStatsForm();
		}
		
		where="where open_time>'"+form.getStart_date()+"' and open_time<'"+form.getEnd_date()+"'";
		
		String groupby="";
		
		ArrayList<String> excel_names = new ArrayList<String>();
		ArrayList<String> excel_codes = new ArrayList<String>();

		if (form.getPark_check() == 1) {
			select = " park_id," + select;
			groupby = " park_id," + groupby;
		}
		if (form.getEmp_check() == 1) {
			select = " open_emp_no,open_emp_name, " + select;
			groupby = " open_emp_no," + groupby;
		}
		//
		System.out.println(form.getOpen_type_check());
		if (form.getOpen_type_check()==1) {
			select=" if(open_type=1,'非法开闸','手动开闸') open_type, "+select;
			groupby=" open_type,"+groupby;
		}
		
		if (groupby.length() > 0) {
			groupby = "group by " + groupby.substring(0, groupby.length() - 1);
		}

		String sql_t = "select " + select + from + where + groupby;
		System.out.println(sql_t);
		String sql = "";
		String select_1 = " cou_open ";
		String from_1 = " (" + sql_t + ") a";
		String where_1 = "";
		String orderby = "";
		
		if (sql_t.contains("park_id")) {
			excel_names.add("停车场名称");
			excel_names.add("停车场编号");
			excel_codes.add("park_name");
			excel_codes.add("park_code");
			
			select_1 = "b.park_name,b.id park_id,b.park_code," + select_1;
			from_1 = " info_park b," + from_1;
			where_1 = " a.park_id=b.park_code ";
			orderby = " park_name,";
		}

		if (sql_t.contains("emp_no")) {
			excel_names.add("操作员姓名");
			excel_names.add("操作名编码");
			excel_codes.add("emp_name");
			excel_codes.add("emp_no");
			select_1 = "c.emp_no,c.emp_name," + select_1;
			from_1 = " info_park_admin c," + from_1;
			where_1 = " a.open_emp_no=c.emp_no and a.open_emp_name=c.emp_name and "+where_1;
			orderby = orderby + " emp_no desc,";
		}
		if (sql_t.contains("open_type")) {
			excel_names.add("开闸类型");
			excel_codes.add("open_type");
			select_1 = "a.open_type," + select_1;
			orderby = orderby + " open_type desc,";			
		}
		
		if(orderby.length()>0){
			orderby = " order by "+orderby.substring(0,orderby.length()-1);
			
		}	
		
		excel_names.add("开闸次数");
		excel_codes.add("cou_open");
		
		if (where_1.endsWith(" and ")) {
			where_1 = where_1.substring(0, where_1.length()-4);
		}
		if (where_1.length() > 0) {
			where_1 = " where " + where_1;
		}
		sql = "select " + select_1 + " from " + from_1 + where_1+orderby;
		String sql1 = "select count(*) cou from (" + sql_t + ") a";
		
		Long cou = (Long) jdbcTemplate.queryForList(sql1).get(0).get("cou");
		Page page = new Page();
		page.setPage(form.getPage());
		page.setCount(cou);
		page.setPage_size(form.getPage_size());
		page = PageUtil.handle(page);
		sql = sql + " limit " + ((page.getPage() - 1) * page.getPage_size()) + "," + page.getPage_size();
		System.out.println(sql);
		List<Map<String, Object>> stats = jdbcTemplate.queryForList(sql);
		System.out.println(sql);
		if(form.getExcel_flag()==1){
			String excel_name = uuid()+".xls";
			String file_name = CustomProps.getProp("file.temp.path")+"/"+excel_name;
			String title = "开闸信息";
			String[] head = new String[excel_names.size()];
			excel_names.toArray(head);
			String[] code = new String[excel_codes.size()];;
			excel_codes.toArray(code);
			String describe = "1、创建时间:"+CustomTime.getLocalTime()+"。\n";
			describe += "2、创建人："+request.getSession().getAttribute("loginCode")+"。\n";
			describe += "3、时间区间："+form.getStart_date()+"----"+form.getEnd_date();
			
			createExcel(file_name,title,head,code,describe,stats);
			//response.sendRedirect(CustomProps.getProp("file.http.url")+"/"+excel_name);
			response.sendRedirect("../file/get.do?file_name="+excel_name);
		}
		if(form.getPdf_flag()==1){
			String pdf_name = uuid()+".pdf";
			String file_name = CustomProps.getProp("file.temp.path")+"/"+pdf_name;
			String title = "开闸统计信息";
			String[] head = new String[excel_names.size()];
			excel_names.toArray(head);
			String[] code = new String[excel_codes.size()];;
			excel_codes.toArray(code);
			String describe = "1、创建时间:"+CustomTime.getLocalTime()+"。\n";
			describe += "2、创建人："+request.getSession().getAttribute("loginCode")+"。\n";
			describe += "3、时间区间："+form.getStart_date()+"----"+form.getEnd_date();
			createPdf(file_name,title,head,code,describe,stats);
			//response.sendRedirect(CustomProps.getProp("file.http.url")+"/"+pdf_name);
			response.sendRedirect("../file/get.do?file_name="+pdf_name);
		}
		model.addAttribute("page", page);
		model.addAttribute("stats", stats);
		model.addAttribute("form", form);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "统计分析");
		session.setAttribute("childrentitle", "开闸统计");
		String forward="gateopenstats/list";
		return forward;
	}
}
