/**
 * @作者 admin
 * @时间 2016年5月19日 上午9:47:50
 * @类名 ExcelUtil.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月19日 上午9:47:50
 *   修改描述
 */
package com.cqgy.park.tool;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import static com.cqgy.park.tool.Stool.*;
public class ExcelUtil {

	public static void main(String[] args) {
		String excelName = "d:/temp/发卡信息_2016-5-4_"+uuid()+".xls";

		String SheetName = "收费信息";
		String[] head = { "卡号", "卡类型", "发卡时间", "发卡人工号", "发卡人姓名", "持卡人姓名", "车牌号", "卡上余额", "开始时间", "截止时间", "月卡延期金额" };
		String[] code = { "card_no", "card_type", "spread_time", "spread_emp_no", "spread_emp_name", "owner_name",
				"plate", "blance", "start_time", "end_time", "month_money" };
		List<Map<String, Object>> list = setList();
		String describe = "1、创建时间：" + (new Date().toString()) + "。\n";
		describe += "2.创建人：石永强。\n";
		describe += "3.查询条件：日期在2016-4-4至2016-5-4之间";
		
		boolean created = createExcel(excelName,SheetName,head,code,describe,list);
		if (created == true) {
			System.out.println("创建Excel文件：" + excelName + " 成功");
		} else {
			System.out.println("创建Excel文件：" + excelName + " 失败");
		}
	}

	public static boolean createExcel(String excelName,String SheetName,String[] head, String[] code,String describe,List list){
		boolean created = false;
		Workbook wb = new HSSFWorkbook();	
		Sheet sheet = wb.createSheet(SheetName);
		//创建表
		setSheet(sheet, list, head, code);	
		//创建说明
		setSheet(sheet, head, describe);

		
		try {
			try (FileOutputStream fileOut = new FileOutputStream(excelName)) {
				wb.write(fileOut);
				created = true;
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(ExcelUtil.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ExcelUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return created;
	}
	
	public static boolean createExcel(String excelName) {
		boolean created = false;
		Workbook wb = new HSSFWorkbook();	
		Sheet sheet = wb.createSheet("2016年5月发卡信息");
		String[] head = { "卡号", "卡类型", "发卡时间", "发卡人工号", "发卡人姓名", "持卡人姓名", "车牌号", "卡上余额", "开始时间", "截止时间", "月卡延期金额" };
		String[] code = { "card_no", "card_type", "spread_time", "spread_emp_no", "spread_emp_name", "owner_name",
				"plate", "blance", "start_time", "end_time", "month_money" };
		List<Map<String, Object>> list = setList();
		String describe = "1、创建时间：" + (new Date().toString()) + "。\n";
		describe += "2.创建人：石永强。\n";
		describe += "3.查询条件：日期在2016-4-4至2016-5-4之间";
		//创建表
		setSheet(sheet, list, head, code);	
		//创建说明
		setSheet(sheet, head, describe);

		Sheet sheet1 = wb.createSheet("2016年6月发卡信息");
		//创建表
		setSheet(sheet1, list, head, code);
		//创建说明
		setSheet(sheet1, head, describe);
		try {
			try (FileOutputStream fileOut = new FileOutputStream(excelName)) {
				wb.write(fileOut);
				created = true;
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(ExcelUtil.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ExcelUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return created;
	}

	public static void setHeadRow(Row row, String[] cells, int startColumn, CellStyle style) {
		for (int i = 0; i < cells.length; i++) {
			row.createCell(i + startColumn).setCellValue(cells[i]);
			row.getCell(i + startColumn).setCellStyle(style);
		}
	}

	public static void setHeadRow(Row row, String[] cells, CellStyle style) {
		setHeadRow(row, cells, 0, style);
	}

	public static List setList() {

		List<Map<String, Object>> list = new LinkedList<>();

		for (int i = 0; i < 100; i++) {
			Map<String, Object> map = new HashMap();
			map.put("card_no", "1001" + i);
			String card_type = "月卡";
			if (i % 2 == 0) {
				card_type = "充值卡";
			}
			map.put("card_type", card_type);
			map.put("spread_time", "2016-4-3 13:23:21");
			map.put("spread_emp_no", "0001");
			map.put("spread_emp_name", "飞红巾");
			map.put("owner_name", "杨云宗");
			map.put("plate", "渝B-1001-1");
			map.put("blance", "1000.00");
			map.put("start_time", "2016-4-3");
			map.put("end_time", "2016-5-3");
			map.put("month_money", "200.00");
			list.add(map);
		}
		return list;
	}

	private static void setSheet(Sheet sheet, int startRow, int startColumn, List<Map<String, Object>> list,
			String[] head,String[] code) {
		Font font = sheet.getWorkbook().createFont();
		font.setBold(true);
		CellStyle headStyle = sheet.getWorkbook().createCellStyle();
		headStyle.setFont(font);
		Row row = sheet.createRow(startRow);
		setHeadRow(row, head, startColumn, headStyle);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			row = sheet.createRow(i + startRow + 1);
			setRow(row, startColumn, map, code);
		}

	}

	private static void setSheet(Sheet sheet, List<Map<String, Object>> list, String[] head, 
			String[] code) {
		setSheet(sheet, 0, 0, list, head,code);
	}

	private static void setSheet(Sheet sheet, String[] head, String describe) {
		// 说明位于左上
		CellStyle descStyle = sheet.getWorkbook().createCellStyle();
		descStyle.setAlignment(CellStyle.ALIGN_LEFT);
		descStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		// 创建说明
		int startRow = 4;
		int endRow = 8;
		int startColumn = head.length + 4;
		int endColumn = head.length + 8;
		Row row = sheet.getRow(startRow);
		if (Objects.isNull(row)) {
			row = sheet.createRow(startRow);
		}
		Cell cell = row.createCell(startColumn);
		cell.setCellStyle(descStyle);
		cell.setCellValue(describe);
		sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, startColumn, endColumn));
	}

	private static void setRow(Row row, int startColumn, Map<String, Object> map, String[] code) {
		for (int i = 0; i < code.length; i++) {
			row.createCell(i + startColumn).setCellValue(String.valueOf(map.get(code[i])));
		}
	}

	private static void setRow(Row row, Map<String, Object> map, String[] code) {
		setRow(row, 0, map, code);
	}

}
