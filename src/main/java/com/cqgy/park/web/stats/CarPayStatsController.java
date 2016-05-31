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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqgy.park.bo.Page;
import com.cqgy.park.bo.PageUtil;
import com.cqgy.park.form.stats.CarPayStatsForm;
import com.cqgy.park.form.stats.CarPayStatsImageForm;
import com.cqgy.park.qresult.stats.ReturnImage;
import com.cqgy.park.tool.CustomProps;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.JFreeChartUtil;
import com.cqgy.park.tool.Stool;

import static com.cqgy.park.tool.Stool.*;
import static com.cqgy.park.tool.ExcelUtil.*;
import static com.cqgy.park.tool.PdfUtil.*;
@Controller
public class CarPayStatsController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "carpaystats/list.do", method = RequestMethod.GET)
	public String list(CarPayStatsForm form, HttpServletRequest request, Model model,HttpServletResponse response) throws IOException {
		String select = " sum(fee) sum_fee,sum(fee_free) sum_fee_free";
		String from = " from info_car_park_pay ";
		String where = "";
		if (Objects.isNull(form)) {
			form = new CarPayStatsForm();
		}

		where = "where end_time>'" + form.getStart_date() + "' and end_time<'" + form.getEnd_date() + "'";

		String groupby = "";
		
		//Excel &&　PDF
		ArrayList<String> excel_names = new ArrayList<String>();
		ArrayList<String> excel_codes = new ArrayList<String>();

		if (form.getPark_check() == 1) {
			select = " park_id," + select;
			groupby = " park_id," + groupby;
		}
		if (form.getEmp_check() == 1) {
			select = " emp_no,emp_name, " + select;
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
			where_1 = " a.emp_no=c.emp_no and a.emp_name=c.emp_name and "+where_1;
			orderby = orderby + " emp_no desc,";
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
		
		
		excel_names.add("收费金额");
		excel_names.add("免费金额");
		excel_codes.add("sum_fee");
		excel_codes.add("sum_fee_free");

		if (where_1.endsWith(" and ")) {
			where_1 = where_1.substring(0, where_1.length()-4);
		}
		if (where_1.length() > 0) {
			where_1 = " where " + where_1;
		}
		sql = "select " + select_1 + " from " + from_1 + where_1+orderby;
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

		if(form.getExcel_flag()==1){
			String excel_name = uuid()+".xls";
			String file_name = CustomProps.getProp("file.temp.path")+"/"+excel_name;
			String title = "收费信息";
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
			String title = "收费统计信息";
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
		String forward = "carpaystats/list";
		return forward;
	}
	
	@RequestMapping(value = "carpaystats/genImage.do", method = RequestMethod.GET)
	public @ResponseBody ReturnImage getImage(CarPayStatsImageForm form){
		ReturnImage image = new ReturnImage();
		String fileName = Stool.uuid()+".png";
		String filePath = CustomProps.getProp("file.temp.path")+"/"+fileName;
		String sql = "";
		ArrayList<String> rowKeysL = new ArrayList<String>();		
		ArrayList<String> columnKeysL = new ArrayList<String>();
		String xTitle = "";
		String yTitle = "";
		String title = "";
		CategoryDataset dataSet;
		
		if(form.getFlag()==1){
			sql = "select b.park_name,a.sum_fee,a.sum_fee_free from (";
			sql += " SELECT	park_id,sum(fee) sum_fee,sum(fee_free) sum_fee_free";
			sql += " FROM info_car_park_pay a where start_time>'"+form.getStart_date()+"' and end_time<'"+form.getEnd_date()+"'";
			sql += " GROUP BY park_id) a,info_park b where a.park_id=b.park_code";
			
			String[] rowKeys = {"收费","免费"};
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			double[][] data = new double[2][list.size()]; 
			String[] columnKeys = new String[list.size()];
			for(int i=0;i<list.size();i++){
				columnKeys[i]=(String)list.get(i).get("park_name");
				data[0][i]=(Double)list.get(i).get("sum_fee");
				data[1][i]=(Double)list.get(i).get("sum_fee_free");
			}
			dataSet = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
			xTitle = "停车场";
			yTitle = "收费情况";
			title = "按停车场统计收费情况";
			JFreeChartUtil jfcu = new JFreeChartUtil();
			if(form.getImage_flag()==1){
				jfcu.createTimeXYChar(title, xTitle, yTitle, dataSet, filePath);
			}
			if(form.getImage_flag()==2){
				jfcu.createBarChart(dataSet, xTitle, yTitle, title, filePath);
			}
			if(form.getImage_flag()==3){
				double sum = 0;
				for(int i=0;i<list.size();i++){
					sum = data[0][i];
				}
				double[] percent = new double[list.size()];
				
				for(int i=0;i<list.size();i++){
					percent[i]=data[0][i]/sum;
				}
				
				PieDataset pdataSet = jfcu.getDataPieSetByUtil(percent, columnKeys);
				jfcu.createValidityComparePimChar(pdataSet, title, filePath, columnKeys);
			}

			image.setImage(CustomProps.getProp("file.temp.url")+"/"+fileName);
		}
		if(form.getFlag()==2){
			sql = "select b.emp_name,a.sum_fee,a.sum_fee_free from (";
			sql += " SELECT	park_id,emp_no,sum(fee) sum_fee,sum(fee_free) sum_fee_free";
			sql += " FROM info_car_park_pay a where start_time>'"+form.getStart_date()+"' and end_time<'"+form.getEnd_date()+"'";
			sql += " GROUP BY park_id,emp_no) a,info_park_admin b where a.park_id=b.park_id and a.emp_no=b.emp_no";
			System.out.println(sql);
			
			String[] rowKeys = {"收费","免费"};
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			double[][] data = new double[2][list.size()]; 
			String[] columnKeys = new String[list.size()];
			for(int i=0;i<list.size();i++){
				columnKeys[i]=(String)list.get(i).get("emp_name");
				data[0][i]=(Double)list.get(i).get("sum_fee");
				data[1][i]=(Double)list.get(i).get("sum_fee_free");
			}
			dataSet = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
			xTitle = "操作员";
			yTitle = "收费情况";
			title = "按操作员统计收费情况";
			JFreeChartUtil jfcu = new JFreeChartUtil();
			if(form.getImage_flag()==1){
				jfcu.createTimeXYChar(title, xTitle, yTitle, dataSet, filePath);
			}
			if(form.getImage_flag()==2){
				jfcu.createBarChart(dataSet, xTitle, yTitle, title, filePath);
			}
			if(form.getImage_flag()==3){
				double sum = 0;
				for(int i=0;i<list.size();i++){
					sum = data[0][i];
				}
				double[] percent = new double[list.size()];
				
				for(int i=0;i<list.size();i++){
					percent[i]=data[0][i]/sum;
				}
				
				PieDataset pdataSet = jfcu.getDataPieSetByUtil(percent, columnKeys);
				jfcu.createValidityComparePimChar(pdataSet, title, filePath, columnKeys);
			}

			image.setImage(CustomProps.getProp("file.temp.url")+"/"+fileName);
		}
		if(form.getFlag()==3){
			sql = " SELECT	plate,sum(fee) sum_fee,sum(fee_free) sum_fee_free";
			sql += " FROM info_car_park_pay a where start_time>'"+form.getStart_date()+"' and end_time<'"+form.getEnd_date()+"'";
			sql += " GROUP BY plate";
			System.out.println(sql);
			
			String[] rowKeys = {"收费","免费"};
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			double[][] data = new double[2][list.size()]; 
			String[] columnKeys = new String[list.size()];
			for(int i=0;i<list.size();i++){
				columnKeys[i]=(String)list.get(i).get("plate");
				data[0][i]=(Double)list.get(i).get("sum_fee");
				data[1][i]=(Double)list.get(i).get("sum_fee_free");
			}
			dataSet = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
			xTitle = "车牌号";
			yTitle = "收费情况";
			title = "按车牌号统计收费情况";
			JFreeChartUtil jfcu = new JFreeChartUtil();
			if(form.getImage_flag()==1){
				jfcu.createTimeXYChar(title, xTitle, yTitle, dataSet, filePath);
			}
			if(form.getImage_flag()==2){
				jfcu.createBarChart(dataSet, xTitle, yTitle, title, filePath);
			}
			if(form.getImage_flag()==3){
				double sum = 0;
				for(int i=0;i<list.size();i++){
					sum = data[0][i];
				}
				double[] percent = new double[list.size()];
				
				for(int i=0;i<list.size();i++){
					percent[i]=data[0][i]/sum;
				}
				
				PieDataset pdataSet = jfcu.getDataPieSetByUtil(percent, columnKeys);
				jfcu.createValidityComparePimChar(pdataSet, title, filePath, columnKeys);
			}

			image.setImage(CustomProps.getProp("file.temp.url")+"/"+fileName);
		}
		if(form.getFlag()==4){
			sql = " SELECT	DATE_FORMAT(end_time, '%Y-%c-%e') day,sum(fee) sum_fee,sum(fee_free) sum_fee_free";
			sql += " FROM info_car_park_pay a where start_time>'"+form.getStart_date()+"' and end_time<'"+form.getEnd_date()+"'";
			sql += " GROUP BY DATE_FORMAT(end_time, '%Y-%c-%e')";
			
			String[] rowKeys = {"收费","免费"};
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			double[][] data = new double[2][list.size()]; 
			String[] columnKeys = new String[list.size()];
			for(int i=0;i<list.size();i++){
				columnKeys[i]=(String)list.get(i).get("day");
				data[0][i]=(Double)list.get(i).get("sum_fee");
				data[1][i]=(Double)list.get(i).get("sum_fee_free");
			}
			dataSet = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
			xTitle = "时间";
			yTitle = "收费情况";
			title = "按日期统计收费情况";
			JFreeChartUtil jfcu = new JFreeChartUtil();
			if(form.getImage_flag()==1){
				jfcu.createTimeXYChar(title, xTitle, yTitle, dataSet, filePath);
			}
			if(form.getImage_flag()==2){
				jfcu.createBarChart(dataSet, xTitle, yTitle, title, filePath);
			}
			if(form.getImage_flag()==3){
				double sum = 0;
				for(int i=0;i<list.size();i++){
					sum = data[0][i];
				}
				double[] percent = new double[list.size()];
				
				for(int i=0;i<list.size();i++){
					percent[i]=data[0][i]/sum;
				}
				
				PieDataset pdataSet = jfcu.getDataPieSetByUtil(percent, columnKeys);
				jfcu.createValidityComparePimChar(pdataSet, title, filePath, columnKeys);
			}

			image.setImage(CustomProps.getProp("file.temp.url")+"/"+fileName);
		}
		return image;
	}
}
