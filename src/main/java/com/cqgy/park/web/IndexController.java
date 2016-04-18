/**
 * @作者 admin
 * @时间 2016年4月15日 下午4:03:43
 * @类名 IndexController.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月15日 下午4:03:43
 *   修改描述
 */
package com.cqgy.park.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	String index(){
		return "index";
	}
	
}
