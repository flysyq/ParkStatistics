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

import static com.cqgy.park.tool.SHAUtil.shaEncode;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqgy.park.dao.ActionLogRepository;
import com.cqgy.park.dao.SysUserRepository;
import com.cqgy.park.dao.SysUserRolesRepository;
import com.cqgy.park.domain.ActionLog;
import com.cqgy.park.domain.SysRoleAuthoritys;
import com.cqgy.park.domain.SysUser;
import com.cqgy.park.domain.SysUserRoles;
@Controller
public class LoginController {

	@Autowired
	SysUserRepository sysUserRepository;

	@Autowired
	ActionLogRepository actionLogRepository;
	@Autowired
	SysUserRolesRepository sysUserRolesRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="login/login.do", method=RequestMethod.POST)
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
			String sql="SELECT sa.*,sra.role_id,father_id,IF(father_id=0,sa.id,father_id)fid  FROM sys_authority sa JOIN sys_role_authoritys sra ON sa.id=sra.authority_id AND sra.role_id="+role_id+" ORDER BY fid,grade,sort_level";
			List<Map<String, Object>> menu = jdbcTemplate.queryForList(sql);
			Date date = new Date();
			ActionLog actionLog = new ActionLog(null,login_code,1,date);
			actionLogRepository.save(actionLog);
			String  li = "<div class='left_menu' th:fragment='left_menu'><ul id='nav_dot'>";
			for (int i = 0; i < menu.size(); i++) {
				if (menu.get(i).get("grade").equals("1")) {
					li += "<li><h4 class='M1'><span></span>"
							+ menu.get(i).get("title")
							+ "</h4><div class='list-item none'><ul>";
				}
				if (menu.get(i).get("grade").equals("2")) {
					li += "<li><a href='../"+menu.get(i).get("uri")+"'>"
							+ menu.get(i).get("title") + "</a></li>";
				}
				if (i < menu.size() - 1 && menu.get(i+1).get("grade").equals("1")) {
					li += "</ul></div></li>";
				}
			}
			li += "</ul></div>";
			HttpSession session = req.getSession();
			session.setAttribute("login_id", user.getId());
			session.setAttribute("loginCode", login_code);
			session.setAttribute("loginTime", date);
			session.setAttribute("role_id", role_id);
			session.setAttribute("li", li);
			session.setAttribute("fathertitle", "系统公告");
			session.setAttribute("childrentitle", "最新公告");
			return "index/index";
		}		
	}
	@RequestMapping(value="*/changepasswordedit",method=RequestMethod.GET)
	public String changePasswordEdit(Long id,Model model){
		model.addAttribute("login_id",id);
		String forword="index/changepasswordedit";
		return forword;
	}
	@RequestMapping(value="*/newpasswordsave.do",method=RequestMethod.POST)
	public String changePasswordSave(Long login_id,String login_password,String new_password,Model model) throws Exception{
		SysUser user = sysUserRepository.findOne(login_id);
		String loginPassword=user.getLoginPassword();
		if (login_password!=null&&!login_password.equals("")) {
			if (shaEncode(login_password).equals(loginPassword)) {
				if (new_password!=null&&!new_password.equals("")) {
					user.setLoginPassword(shaEncode(new_password));
					sysUserRepository.save(user);
					model.addAttribute("result", "密码修改成功");
					String forword="display/result";
					return forword;
				}else{
					model.addAttribute("result", "新密码不能为空");
					String forword="display/result";
					return forword;
				}

			}else{
				model.addAttribute("result", "原密码错误");
				String forword="display/result";
				return forword;
			}
		}else{
			model.addAttribute("result", "原密码不能为空");
			String forword="display/result";
			return forword;
		}


	}
	@RequestMapping(value="*/noauthority.do")
	public String goToDisplay(Model model){
		System.out.println("进入了");
		model.addAttribute("result", "你没有权限！");
		String forword="display/result";
		return forword;

	}
	@RequestMapping(value="*/exit.do")
	public void exitLogin(HttpServletRequest req,HttpServletResponse res) throws IOException{
		HttpSession session = req.getSession();
		session.invalidate();
		res.sendRedirect("../login/login.html");	
	}
	@RequestMapping(value="hello/hello.do")
	public String hello(HttpServletRequest req,HttpServletResponse res) throws IOException{
			return "theme/hello";
	}

}
