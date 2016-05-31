
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.bo.Page;
import com.cqgy.park.bo.PageUtil;
import com.cqgy.park.form.stats.CarIoStatsForm;
import com.cqgy.park.tool.CustomProps;
import com.cqgy.park.tool.CustomTime;
@Controller
public class CarIoStatsController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="cariostats/list.do",method=RequestMethod.GET)
	public String list(CarIoStatsForm form,HttpServletRequest request,Model model,HttpServletResponse response) throws IOException{
		String select = " count(come_time) cou_come,count(go_time) cou_go";
		String from=" from info_car_io ";
		String where="";
		if (Objects.isNull(form)) {
			form=new CarIoStatsForm();
		}
		where="where go_time>'"+form.getCome_time()+"' and go_time<'"+form.getGo_time()+"' or go_time is null ";
		String groupby="";
		//Excel　&& PDF
		ArrayList<String> excel_names=new ArrayList<String>();
		ArrayList<String> excel_codes=new ArrayList<String>();

		if (form.getPark_check()==1) {
			select=" park_id,"+select;
			groupby=" park_id,"+groupby;
		}
		if (form.getIn_emp_check()==1) {
			select=" in_emp_no,in_emp_name,"+select;
			groupby=" in_emp_no,"+groupby;
		}
		if (form.getOut_emp_check()==1) {
			select=" out_emp_no,out_emp_name,"+select;
			groupby=" out_emp_no,"+groupby;
		}
		if(form.getPlate_check()==1){
			select=" plate,"+select;
			groupby=" plate,"+groupby;
		}
		if (groupby.length()>0) {
			groupby="group by "+groupby.substring(0, groupby.length()-1);
		}
		String sql_t="select "+select+from+where+groupby;
		String sql="";
		String select_1=" cou_come,cou_go ";
		String from_1="("+sql_t+") a";
		String where_1="";
		String orderby="";

		if (sql_t.contains("park_id")) {
			excel_names.add("停车场名称");
			excel_names.add("停车场编号");
			excel_codes.add("park_name");
			excel_codes.add("park_code");

			select_1="b.park_name,b.id park_id,b.park_code,"+select_1;
			from_1 = "info_park b,"+from_1;
			where_1 = " a.park_id=b.park_code ";
			orderby = " park_name,";
		}
		if (sql_t.contains("in_emp_no")) {
			excel_names.add("入场操作员姓名");
			excel_names.add("入场操作名编码");
			excel_codes.add("in_emp_name");
			excel_codes.add("in_emp_no");
			select_1 = "c.emp_no in_emp_no,c.emp_name in_emp_name," + select_1;
			from_1 = " info_park_admin c," + from_1;
			where_1 = " a.in_emp_no=c.emp_no and a.in_emp_name=c.emp_name and "+where_1;
			orderby = orderby + " c.emp_no desc,";
		}
		if (sql_t.contains("out_emp_no")) {
			excel_names.add("出场操作员姓名");
			excel_names.add("出场操作名编码");
			excel_codes.add("out_emp_name");
			excel_codes.add("out_emp_no");
			select_1 = "d.emp_no out_emp_no,d.emp_name out_emp_name," + select_1;
			from_1 = " info_park_admin d," + from_1;
			where_1 = " a.out_emp_no=d.emp_no and a.out_emp_name=d.emp_name and "+where_1;
			if (!orderby.contains("c.emp_no desc,")) {
				orderby = orderby + " d.emp_no desc,";
			}
			
		}
		if (sql_t.contains("plate")) {
			excel_names.add("车牌号");
			excel_codes.add("plate");
			select_1 = "a.plate," + select_1;
			orderby = orderby + " plate desc,";			
		}

		if(orderby.length()>0){
			orderby = " order by "+orderby.substring(0,orderby.length()-1);

		}
		excel_names.add("入场次数");
		excel_names.add("出场次数");
		excel_codes.add("cou_come");
		excel_codes.add("cou_go");

		if (where_1.endsWith(" and ")) {
			where_1 = where_1.substring(0, where_1.length()-4);
		}
		if (where_1.length() > 0) {
			where_1 = " where " + where_1;
		}
		sql = "select " + select_1 + " from " + from_1 + where_1+orderby;
		String sql1 = "select count(*) cou from (" + sql_t + ") a";	

	//	System.out.println(sql);
	//	System.out.println(sql1);
		Long cou = (Long) jdbcTemplate.queryForList(sql1).get(0).get("cou");
		Page page = new Page();
		page.setPage(form.getPage());
		page.setCount(cou);
		page.setPage_size(form.getPage_size());
		page = PageUtil.handle(page);
		sql = sql + " limit " + ((page.getPage() - 1) * page.getPage_size()) + "," + page.getPage_size();
		System.out.println(sql);
	//	System.out.println(sql1);
		List<Map<String, Object>> stats = jdbcTemplate.queryForList(sql);

		if(form.getExcel_flag()==1){
			String excel_name = uuid()+".xls";
			String file_name = CustomProps.getProp("file.temp.path")+"/"+excel_name;
			String title = "出入信息";
			String[] head = new String[excel_names.size()];
			excel_names.toArray(head);
			String[] code = new String[excel_codes.size()];;
			excel_codes.toArray(code);
			String describe = "1、创建时间:"+CustomTime.getLocalTime()+"。\n";
			describe += "2、创建人："+request.getSession().getAttribute("loginCode")+"。\n";
			describe += "3、时间区间："+form.getCome_time()+"----"+form.getGo_time();
			
			createExcel(file_name,title,head,code,describe,stats);
			//response.sendRedirect(CustomProps.getProp("file.http.url")+"/"+excel_name);
			response.sendRedirect("../file/get.do?file_name="+excel_name);
		}
		if(form.getPdf_flag()==1){
			String pdf_name = uuid()+".pdf";
			String file_name = CustomProps.getProp("file.temp.path")+"/"+pdf_name;
			String title = "出入统计信息";
			String[] head = new String[excel_names.size()];
			excel_names.toArray(head);
			String[] code = new String[excel_codes.size()];;
			excel_codes.toArray(code);
			String describe = "1、创建时间:"+CustomTime.getLocalTime()+"。\n";
			describe += "2、创建人："+request.getSession().getAttribute("loginCode")+"。\n";
			describe += "3、时间区间："+form.getCome_time()+"----"+form.getGo_time();
			createPdf(file_name,title,head,code,describe,stats);
			//response.sendRedirect(CustomProps.getProp("file.http.url")+"/"+pdf_name);
			response.sendRedirect("../file/get.do?file_name="+pdf_name);
		}
		model.addAttribute("page", page);
		model.addAttribute("stats", stats);
		model.addAttribute("form", form);
		String forward="cariostats/list";
		return forward;
	}
}
