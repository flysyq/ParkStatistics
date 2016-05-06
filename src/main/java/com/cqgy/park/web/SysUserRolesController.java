package com.cqgy.park.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	@RequestMapping(value="/sysuser/userrolesedit.do",method=RequestMethod.GET)
	public String edit(Long id,Model model){
		SysUser fsysuser = sysUserRepository.findOne(id);
		long roleid=0;
		model.addAttribute("sysuser", fsysuser);
		model.addAttribute("roleid", roleid);
		String forword="/sysuserroles/userrolesedit";
		return forword;
	}
	@RequestMapping(value="/sysuserroles/getroles.do",method=RequestMethod.GET)
	public @ResponseBody List<SysRole> getRoles(){
		String sql="select * from sys_role";
		return sysUserService.getRoles(sql);	
	}
	@RequestMapping(value="/sysuser/sysuserrolessave.do",method=RequestMethod.GET)
	public String save(Long id,Long user_id,Long role_id,Model model,HttpServletRequest request){
		SysUserRoles sysUserRoles=new SysUserRoles();
		HttpSession session = request.getSession();
		sysUserRoles.setCreateTime(new Date());
		sysUserRoles.setCreateUser((Long) session.getAttribute("login_id"));
		sysUserRoles.setUserId(user_id);
		sysUserRoles.setRoleId(role_id);
		sysUserRolesRepository.save(sysUserRoles);
		model.addAttribute("result", "角色分配成功！");
		String aa="aa";
		String forword="/display/result";
		return forword;
	}
}
