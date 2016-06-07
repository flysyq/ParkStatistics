package com.cqgy.park.web;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqgy.park.dao.SysRoleService;
import com.cqgy.park.dao.SysUserRepository;
import com.cqgy.park.dao.SysUserRolesRepository;
import com.cqgy.park.domain.SysRole;
import com.cqgy.park.domain.SysUser;
import com.cqgy.park.domain.SysUserRoles;

@Controller
public class SysUserRolesController {
	@Autowired
	SysUserRepository sysUserRepository;
	@Autowired
	SysRoleService sysUserService; 
	@Autowired
	SysUserRolesRepository sysUserRolesRepository;
	@Autowired
	JdbcTemplate jdbcTmplate;
	@RequestMapping(value="/sysuser/userrolesedit.do",method=RequestMethod.GET)
	public String edit(Long id,Model model){
		SysUser fsysuser = sysUserRepository.findOne(id);
		String sql="select * from sys_user_roles where user_id="+id;
		List<Map<String, Object>> list = jdbcTmplate.queryForList(sql);
		Long roleid;
		if (list.isEmpty()) {
			roleid=(long) 0;
		}else{
			roleid=(Long) list.get(0).get("role_id");
			String sql_1="select * from sys_role where id="+roleid;
			String name=(String) jdbcTmplate.queryForList(sql_1).get(0).get("name");
			model.addAttribute("name", name);
		}
		
		
		
		model.addAttribute("sysuser", fsysuser);
		model.addAttribute("roleid", roleid);
		
		String forword="sysuserroles/userrolesedit";
		return forword;
	}
	@RequestMapping(value="sysuserroles/getroles.do",method=RequestMethod.GET)
	public @ResponseBody List<SysRole> getRoles(){
		String sql="select * from sys_role";
		return sysUserService.getRoles(sql);	
	}
	@RequestMapping(value="/sysuser/sysuserrolessave.do",method=RequestMethod.GET)
	public String save(Long id,Long user_id,Long role_id,Model model,HttpServletRequest request){
		String sql="select * from sys_user_roles where user_id="+user_id+" and role_id="+role_id;
		List<Map<String, Object>> list = jdbcTmplate.queryForList(sql);
		if (list.isEmpty()) {
			String sql1="select * from sys_user_roles where user_id="+user_id;
			List<Map<String, Object>> list2 = jdbcTmplate.queryForList(sql1);
			if (list2.isEmpty()) {
				SysUserRoles sysUserRoles=new SysUserRoles();
				HttpSession session = request.getSession();
				if (role_id!=0) {
					sysUserRoles.setCreateTime(new Date());
					sysUserRoles.setCreateUser((Long) session.getAttribute("login_id"));
					sysUserRoles.setUserId(user_id);
					sysUserRoles.setRoleId(role_id);
					sysUserRolesRepository.save(sysUserRoles);
					model.addAttribute("result", "用户角色分配成功！");
				}else {
					List<SysUserRoles> findByUserId = sysUserRolesRepository.findByUserId(user_id);
					if (!findByUserId.isEmpty()) {
						sysUserRoles = findByUserId.get(0);
						sysUserRolesRepository.delete(sysUserRoles.getId());
						model.addAttribute("result", "取消用户角色成功！");
					}else {
						model.addAttribute("result", "未分配用户角色！");
					}
				}
			}else{
				String sql2="delete from sys_user_roles where user_id="+user_id;
				jdbcTmplate.update(sql2);
				SysUserRoles sysUserRoles=new SysUserRoles();
				HttpSession session = request.getSession();
				if (role_id!=0) {
					sysUserRoles.setCreateTime(new Date());
					sysUserRoles.setCreateUser((Long) session.getAttribute("login_id"));
					sysUserRoles.setUserId(user_id);
					sysUserRoles.setRoleId(role_id);
					sysUserRolesRepository.save(sysUserRoles);
					model.addAttribute("result", "用户角色分配成功！");
				}else {
					List<SysUserRoles> findByUserId = sysUserRolesRepository.findByUserId(user_id);
					if (!findByUserId.isEmpty()) {
						sysUserRoles = findByUserId.get(0);
						sysUserRolesRepository.delete(sysUserRoles.getId());
						model.addAttribute("result", "取消用户角色成功！");
					}else {
						model.addAttribute("result", "未分配用户角色！");
					}
				}
			}
		
		}else{
			model.addAttribute("result", "用户角色成功已存在！");
		}
		
	
		
		
		String forword="display/result";
		return forword;
	}
}
