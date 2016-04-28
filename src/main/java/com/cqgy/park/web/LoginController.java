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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.dao.ActionLogRepository;
import com.cqgy.park.dao.SysUserRepository;
import com.cqgy.park.domain.ActionLog;
import com.cqgy.park.domain.SysUser;
import static com.cqgy.park.tool.SHAUtil.shaEncode;
@Controller
public class LoginController {
	
	@Autowired
	SysUserRepository sysUserRepository;
	
	@Autowired
	ActionLogRepository actionLogRepository;
	
	@RequestMapping(value="/login/login.do", method=RequestMethod.POST)
	public String login(String login_code,String login_password,HttpServletRequest req) throws Exception{
		
		List<SysUser> users = sysUserRepository.findByLoginCodeAndLoginPassword(login_code,shaEncode(login_password));
		
		Date date = new Date();
		ActionLog actionLog = new ActionLog(null,login_code,1,date);
		
		actionLogRepository.save(actionLog);
		if(users.isEmpty()){
			return "login/login";
		}else{			
			HttpSession session = req.getSession();
			session.setAttribute("loginCode", login_code);
			session.setAttribute("loginTime", date);
			return "index/index";
		}
		
	}
}
