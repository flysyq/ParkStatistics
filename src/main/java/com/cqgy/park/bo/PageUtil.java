/**
 * @作者 admin
 * @时间 2016年5月25日 下午1:55:28
 * @类名 PageUtil.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月25日 下午1:55:28
 *   修改描述
 */
package com.cqgy.park.bo;

import java.util.Objects;

public class PageUtil {

	public static Page handle(Page page) {
		Integer page_count_t = (int) Math.ceil((double)page.getCount() / page.getPage_size());
		if(page.getPage_size()==0){
			page.setPage_size(10);
		}
		if(Objects.isNull(page.getPage())){
			page.setPage(1);
		}
			
		if(page.getPage()>page_count_t){
			page.setPage(page_count_t);
		}
		if (page.getPage() < 1) {
			page.setPage(1);
		}	
		page.setPage_count(page_count_t);
		return page;
	}
}
