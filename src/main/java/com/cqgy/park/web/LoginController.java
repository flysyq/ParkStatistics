/**
 * @作者 admin
 * @时间 2016年4月20日 下午5:34:44
 * @类名 LoginController.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月20日 下午5:34:44
 *   修改描述
 */
package com.cqgy.park.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.dao.SysUserRepository;
import com.cqgy.park.domain.SysUser;

@Controller
public class LoginController {
	
	@Autowired
	SysUserRepository sysUserRepository;
	
	@RequestMapping(value="/login/login.do", method=RequestMethod.POST)
	public String login(String login_code,String login_password,HttpServletRequest req){
		
		List<SysUser> users = sysUserRepository.findByLoginCodeAndLoginPassword(login_code,login_password);
		
		if(users.isEmpty()){
			return "login/login";
		}else{
			
			return "index/index";
		}
		
	}
}
