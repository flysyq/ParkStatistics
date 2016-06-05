
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
import com.cqgy.park.form.stats.CarIoStatsForm;
import com.cqgy.park.form.stats.StatsImageForm;
import com.cqgy.park.qresult.stats.ReturnImage;
import com.cqgy.park.tool.CustomProps;
import com.cqgy.park.tool.CustomTime;
import com.cqgy.park.tool.JFreeChartUtil;
import com.cqgy.park.tool.Stool;
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
		where="where go_time>'"+form.getCome_time()+"' and go_time<'"+form.getGo_time()+"' or go_time is null  and come_time>'"+form.getCome_time()+"' and come_time<'"+form.getGo_time()+"' or come_time is null ";
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
			String describe = "1、创建时间:"+CustomTime.getLocalTime()+"。";
			describe += "2、创建人："+request.getSession().getAttribute("loginCode")+"。";
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
			String describe = "1、创建时间:"+CustomTime.getLocalTime()+"。";
			describe += "2、创建人："+request.getSession().getAttribute("loginCode")+"。";
			describe += "3、时间区间："+form.getCome_time()+"----"+form.getGo_time();
			createPdf(file_name,title,head,code,describe,stats);
			//response.sendRedirect(CustomProps.getProp("file.http.url")+"/"+pdf_name);
			response.sendRedirect("../file/get.do?file_name="+pdf_name);
		}
		model.addAttribute("page", page);
		model.addAttribute("stats", stats);
		model.addAttribute("form", form);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "统计分析");
		session.setAttribute("childrentitle", "车流量统计");
		String forward="cariostats/list";
		return forward;
	}

	@RequestMapping(value = "cariostats/genImage.do", method = RequestMethod.GET)
	public @ResponseBody ReturnImage getImage(StatsImageForm form){
		ReturnImage image = new ReturnImage();
		String fileName = Stool.uuid()+".png";
		String filePath = CustomProps.getProp("file.temp.path")+"/"+fileName;
		String sql = "";
		String sql1="";
		ArrayList<String> rowKeysL = new ArrayList<String>();		
		ArrayList<String> columnKeysL = new ArrayList<String>();
		String xTitle = "";
		String yTitle = "";
		String title = "";
		CategoryDataset dataSet;

		if(form.getFlag()==1){
			sql = " select b.park_name,a.cou_come,a.cou_go from (";
			sql += " SELECT	park_id,count(come_time) cou_come,count(go_time) cou_go ";
			sql += " FROM info_car_io a where come_time>'"+form.getStart_date()+"' and go_time<'"+form.getEnd_date()+"' or come_time is null or go_time is null";
			sql += " GROUP BY park_id) a,info_park b where a.park_id=b.park_code";
			System.out.println(sql);
			String[] rowKeys = {"入场次数","出场次数"};
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			double[][] data = new double[2][list.size()]; 
			String[] columnKeys = new String[list.size()];
			for(int i=0;i<list.size();i++){
				columnKeys[i]=(String)list.get(i).get("park_name");
				data[0][i]=(Long)list.get(i).get("cou_come");
				data[1][i]=(Long)list.get(i).get("cou_go");
			}
			dataSet = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
			xTitle = "停车场";
			yTitle = "出入场情况";
			title = "按停车场统计充值情况";
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

			image.setImage("../file/get.do?file_name="+fileName);
		}
		if(form.getFlag()==2){
			sql = "select b.emp_name,a.cou_come from (";
			sql += " SELECT	park_id,in_emp_no,count(come_time) cou_come ";
			sql += " FROM info_car_io a where come_time>'"+form.getStart_date()+"' and go_time<'"+form.getEnd_date()+"' or go_time is null";
			sql += " GROUP BY park_id,in_emp_no) a,info_park_admin b where a.park_id=b.park_id and a.in_emp_no=b.emp_no";
			System.out.println(sql);

			String[] rowKeys = {"入场次数"};
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			double[][] data = new double[1][list.size()]; 
			String[] columnKeys = new String[list.size()];
			for(int i=0;i<list.size();i++){
				columnKeys[i]=(String)list.get(i).get("emp_name");
				data[0][i]=(Long)list.get(i).get("cou_come");
			}
			dataSet = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
			xTitle = "入场操作员";
			yTitle = "出入场情况";
			title = "按入场操作员统计充值情况";
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

			image.setImage("../file/get.do?file_name="+fileName);
		}
		if(form.getFlag()==3){
			sql = "select b.emp_name,a.cou_go from (";
			sql += " SELECT	park_id,out_emp_no,count(go_time) cou_go ";
			sql += " FROM info_car_io a where come_time>'"+form.getStart_date()+"' and go_time<'"+form.getEnd_date()+"' or come_time is null";
			sql += " GROUP BY park_id,out_emp_no) a,info_park_admin b where a.park_id=b.park_id and a.out_emp_no=b.emp_no";
			System.out.println(sql);

			String[] rowKeys = {"出场次数"};
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			double[][] data = new double[1][list.size()]; 
			String[] columnKeys = new String[list.size()];
			for(int i=0;i<list.size();i++){
				columnKeys[i]=(String)list.get(i).get("emp_name");
				data[0][i]=(Long)list.get(i).get("cou_go");
			}
			dataSet = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
			xTitle = "出场操作员";
			yTitle = "出场情况";
			title = "按出场操作员统计充值情况";
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

			image.setImage("../file/get.do?file_name="+fileName);
		}
		if(form.getFlag()==4){
			sql = " SELECT	plate,count(come_time) cou_come,count(go_time) cou_go";
			sql += " FROM info_car_io a where come_time>'"+form.getStart_date()+"' and go_time<'"+form.getEnd_date()+"' or come_time is null or go_time is null";
			sql += " GROUP BY plate";
			System.out.println(sql);

			String[] rowKeys = {"入场次数","出场次数"};
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			double[][] data = new double[2][list.size()]; 
			String[] columnKeys = new String[list.size()];
			for(int i=0;i<list.size();i++){
				columnKeys[i]=(String)list.get(i).get("plate");
				data[0][i]=(Long)list.get(i).get("cou_come");
				data[1][i]=(Long)list.get(i).get("cou_go");
			}
			dataSet = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
			xTitle = "车牌号";
			yTitle = "出入场情况";
			title = "按车牌号统计充值情况";
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
				System.out.println("percent："+percent.length+"columnKeys:"+columnKeys.length);
				PieDataset pdataSet = jfcu.getDataPieSetByUtil(percent, columnKeys);
				jfcu.createValidityComparePimChar(pdataSet, title, filePath, columnKeys);
			}

			image.setImage("../file/get.do?file_name="+fileName);
		}
		if(form.getFlag()==5){
			sql="SELECT	IFNULL(dfg, dfc) dfg,IFNULL(dfc, dfg) dfc,IFNULL(cou_go, 0) cou_go,IFNULL(cou_come, 0) cou_come FROM" +
					"	(" +
					"		SELECT" +
					"			a.dfg," +
					"			b.dfc," +
					"			a.cou_go," +
					"			b.cou_come" +
					"		FROM" +
					"			(" +
					"				SELECT" +
					"					count(*) cou_go," +
					"					DATE_FORMAT(go_time, '%Y-%m-%d') dfg" +
					"				FROM" +
					"					info_car_io" +
					"				WHERE" +
					"					go_time > '"+form.getStart_date()+"'" +
					"				AND go_time < '"+form.getEnd_date()+"'" +
					"				GROUP BY" +
					"					DATE_FORMAT(go_time, '%Y-%m-%d')" +
					"			) a" +
					"		LEFT JOIN (" +
					"			SELECT" +
					"				count(*) cou_come," +
					"				DATE_FORMAT(come_time, '%Y-%m-%d') dfc" +
					"			FROM" +
					"				info_car_io" +
					"			WHERE" +
					"				come_time > '"+form.getStart_date()+"'" +
					"			AND come_time < '"+form.getEnd_date()+"'" +
					"			GROUP BY" +
					"				DATE_FORMAT(come_time, '%Y-%m-%d')" +
					"		) b ON a.dfg = b.dfc" +
					"		UNION" +
					"			SELECT" +
					"				a.dfg," +
					"				b.dfc," +
					"				a.cou_go," +
					"				b.cou_come" +
					"			FROM" +
					"				(" +
					"					SELECT" +
					"						count(*) cou_go," +
					"						DATE_FORMAT(go_time, '%Y-%m-%d') dfg" +
					"					FROM" +
					"						info_car_io" +
					"					WHERE" +
					"						go_time > '"+form.getStart_date()+"'" +
					"					AND go_time < '"+form.getEnd_date()+"'" +
					"					GROUP BY" +
					"						DATE_FORMAT(go_time, '%Y-%m-%d')" +
					"				) a" +
					"			RIGHT JOIN (" +
					"				SELECT" +
					"					count(*) cou_come," +
					"					DATE_FORMAT(come_time, '%Y-%m-%d') dfc" +
					"				FROM" +
					"					info_car_io" +
					"				WHERE" +
					"					come_time > '"+form.getStart_date()+"' AND come_time < '"+form.getEnd_date()+"' GROUP BY DATE_FORMAT(come_time, '%Y-%m-%d')) b ON a.dfg = b.dfc) c ORDER BY dfc ASC";
			sql1 = " SELECT	DATE_FORMAT(go_time, '%Y-%c-%e') day,count(go_time) cou_go ";
			sql1 += " FROM info_car_io a where go_time>'"+form.getStart_date()+"' and go_time<'"+form.getEnd_date()+"'";
			sql1 += " GROUP BY DATE_FORMAT(go_time, '%Y-%c-%e')";
			System.out.println(sql);
			String[] rowKeys = {"入场次数","出场次数"};
			List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
			double[][] data = new double[2][list.size()]; 
			String[] columnKeys = new String[list.size()];
			for(int i=0;i<list.size();i++){
				columnKeys[i]=(String)list.get(i).get("dfc");
				data[0][i]=(Long)list.get(i).get("cou_come");
				data[1][i]=(Long)list.get(i).get("cou_go");
			}
			dataSet = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
			xTitle = "时间";
			yTitle = "出入场情况";
			title = "按日期统计充值情况";
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
				System.out.println("percent："+percent.length+"columnKeys:"+columnKeys.length);
				PieDataset pdataSet = jfcu.getDataPieSetByUtil(percent, columnKeys);
				jfcu.createValidityComparePimChar(pdataSet, title, filePath, columnKeys);
			}

			image.setImage("../file/get.do?file_name="+fileName);
		}
		return image;
	}
}
