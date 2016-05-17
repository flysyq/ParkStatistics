/**
 * @作者 admin
 * @时间 2016年5月12日 上午10:58:37
 * @类名 CustomTime.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月12日 上午10:58:37
 *   修改描述
 */
package com.cqgy.park.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CustomTime {

	public static String getLocalTimeFormat(String format){
		LocalDateTime lt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return lt.format(dtf);
	}
	
	public static String getLocalTime(){
		return getLocalTimeFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public static Date parseTime(String timeString) throws ParseException{
		String format = "yyyy-MM-dd HH:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(format);  
		
		return dateFormat.parse(timeString);
		
	};
	
}
