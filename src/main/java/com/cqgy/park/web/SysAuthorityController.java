/**
 * @作者 admin
 * @时间 2016年4月20日 下午5:06:58
 * @类名 SysAuthorityController.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月20日 下午5:06:58
 *   修改描述
 */
package com.cqgy.park.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SysAuthorityController {
	
	@RequestMapping(value="/authority/list.do",method=RequestMethod.GET)
	public String list(){
		return null;		
	}
}
