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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	void index(HttpServletResponse res) throws IOException, ServletException{
		res.sendRedirect("login/login.html");
	}
	@RequestMapping("login/login.html")
	String index() throws IOException, ServletException{
		return "login/login";
	}
	
}
