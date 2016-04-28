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

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.form.AuthorityListForm;
import com.google.common.base.Strings;

@Controller
public class SysAuthorityController {
	
	@RequestMapping(value="/authority/list.do",method=RequestMethod.GET)
	public String list(AuthorityListForm authorityListForm,HttpServletRequest request,Model model){
		Integer flag = authorityListForm.getFlag();
		Integer grade = authorityListForm.getGrade();
		String title = authorityListForm.getTitle();
		
		String sql = "select * from sys_authority";
		String where = "";
		if(flag >0){
			where = where+" and flag="+flag;
		}
		if(grade >0){
			where = where+" and grade="+grade;
		}
		
		if(!Strings.isNullOrEmpty(title)){
			where = where + " and ";
		}
		return null;		
	}
}
