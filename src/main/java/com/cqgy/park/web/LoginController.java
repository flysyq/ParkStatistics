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
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.dao.ActionLogRepository;
import com.cqgy.park.dao.SysUserRepository;
import com.cqgy.park.dao.SysUserRolesRepository;
import com.cqgy.park.domain.ActionLog;
import com.cqgy.park.domain.SysUser;
import com.cqgy.park.domain.SysUserRoles;

import static com.cqgy.park.tool.SHAUtil.shaEncode;
@Controller
public class LoginController {

	@Autowired
	SysUserRepository sysUserRepository;

	@Autowired
	ActionLogRepository actionLogRepository;
	@Autowired
	SysUserRolesRepository sysUserRolesRepository;

	@RequestMapping(value="/login/login.do", method=RequestMethod.POST)
	public String login(String login_code,String login_password,HttpServletRequest req) throws Exception{

		SysUser user = sysUserRepository.findByLoginCodeAndLoginPassword(login_code,shaEncode(login_password));

		if(Objects.isNull(user)){
			return "login/login";
		}else{	
			List<SysUserRoles> findByUserId = sysUserRolesRepository.findByUserId(user.getId());
			Long role_id;
			if (!findByUserId.isEmpty()) {
				role_id=findByUserId.get(0).getRoleId();
			}else{
				role_id=(long)0;
			}
			Date date = new Date();
			ActionLog actionLog = new ActionLog(null,login_code,1,date);
			actionLogRepository.save(actionLog);
			HttpSession session = req.getSession();
			session.setAttribute("login_id", user.getId());
			session.setAttribute("loginCode", login_code);
			session.setAttribute("loginTime", date);
			session.setAttribute("role_id", role_id);
			return "index/index";
		}		
	}
	@RequestMapping(value="/login/changepasswordedit",method=RequestMethod.GET)
	public String changePasswordEdit(Long id,Model model){
		model.addAttribute("login_id",id);
		String forword="index/changepasswordedit";
		return forword;
	}
	@RequestMapping(value="/login/newpasswordsave.do",method=RequestMethod.POST)
	public String changePasswordSave(Long login_id,String login_password,String new_password,Model model) throws Exception{
		SysUser user = sysUserRepository.findOne(login_id);
		String loginPassword=user.getLoginPassword();
		if (login_password!=null&&!login_password.equals("")) {
			if (shaEncode(login_password).equals(loginPassword)) {
				if (new_password!=null&&!new_password.equals("")) {
					user.setLoginPassword(shaEncode(new_password));
					sysUserRepository.save(user);
					model.addAttribute("result", "密码修改成功");
					String forword="/display/result";
					return forword;
				}else{
					model.addAttribute("result", "新密码不能为空");
					String forword="/display/result";
					return forword;
				}
			
			}else{
				model.addAttribute("result", "原密码错误");
				String forword="/display/result";
				return forword;
			}
		}else{
			model.addAttribute("result", "原密码不能为空");
			String forword="/display/result";
			return forword;
		}
	
	
	}
	@RequestMapping(value="/login/noauturity.do")
	public String goToDisplay(Model model){
		model.addAttribute("result", "你没有权限！");
		String forword="/display/result";
		return forword;
		
	}
	@RequestMapping(value="/login/exit.do")
	public String exitLogin(HttpServletRequest req){
		HttpSession session = req.getSession();
		session.invalidate();
		String forword="/login/login";
		return forword;	
	}
}
