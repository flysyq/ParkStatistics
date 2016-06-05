package com.cqgy.park.tool;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class PageUtil {
	static Long pageSize=(long) 5;
	static JdbcTemplate jdbcTemplate;
	public static List<Map<String, Object>>  paging(Long pageNum,String tableName){
		String countsql="select count(*) count from "+tableName;
		Long count = (Long)jdbcTemplate.queryForList(countsql).get(0).get("count");
		long pageMax;
		if (count%pageSize==0) {
			pageMax=count/pageSize;
		}else{
			pageMax=count/pageSize+1;
		}
		if (pageNum<1) {
			pageNum=(long) 1;
		}else if (pageNum>pageMax) {
			pageNum=pageMax;
		}
		Long pageStart=(pageNum-1)*pageSize;
		String select = "select * from "+tableName+" limit "+pageStart+","+pageSize;
		List<Map<String,Object>> list = jdbcTemplate.queryForList(select);
		return list;	
	}
}
