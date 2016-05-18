package com.cqgy.park.web;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.dao.SysRoleRepository;
import com.cqgy.park.dao.SysRoleService;
import com.cqgy.park.dao.SysUserRolesRepository;
import com.cqgy.park.domain.SysRole;
import com.cqgy.park.domain.SysUserRoles;
import com.cqgy.park.form.SysRoleListForm;
import com.google.common.base.Strings;

@Controller
public class SysRoleController {
	@Autowired
	SysRoleService sysRoleService;
	@Autowired
	SysRoleRepository sysRoleRepository;
	@Autowired
	SysUserRolesRepository sysUserRolesRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="/sysrole/rolelist.do",method=RequestMethod.GET)
	public String list(SysRoleListForm sysRoleListForm,Long del_id,HttpServletRequest request,Model model){
		if (!Objects.isNull(del_id)) {
			//sysRoleRepository.delete(del_id);
			String delRoleAuthority="delete from sys_role_authoritys where role_id="+del_id;
			jdbcTemplate.update(delRoleAuthority);
			String delRole="delete from sys_role where id="+del_id;
			jdbcTemplate.update(delRole);
			List<SysUserRoles> findByRoleId = sysUserRolesRepository.findByRoleId(del_id);
			if (!findByRoleId.isEmpty()) {
				SysUserRoles sysUserRoles = findByRoleId.get(0);
				Long user_role_id=sysUserRoles.getId();
				String delUserRole="delete from sys_user_roles where id="+user_role_id;
				jdbcTemplate.update(delUserRole);
				
			}
			
		}
		String code=sysRoleListForm.getCode();
		String name=sysRoleListForm.getName();
		String remark=sysRoleListForm.getRemark();
		String select = "select * from sys_role";
		String where = "";
		if (!Strings.isNullOrEmpty(code)) {
			where+="and code="+code;
		}
		if (!Strings.isNullOrEmpty(name)) {
			where+=" and name like '%"+name+"%'";
		}
		if (!Strings.isNullOrEmpty(remark)) {
			where+=" and remark+"+remark;
		}
		if(where.startsWith(" and")){
			where = where.substring(4);
		}
		
		if(where.trim().length()>0){
			where = " where "+where;
		}		
		String sql = select+where;
		List<SysRole> sysRoles = sysRoleService.getRoles(sql);
		model.addAttribute("sysRoles", sysRoles);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitile", "系统管理");
		session.setAttribute("childrentitle", "角色管理");
		String forword="/sysrole/rolelist";
		return forword;
	}
	@RequestMapping(value="/sysrole/roleedit.do",method=RequestMethod.GET)
	public String edit(Long id,Model model){
		SysRole sysRole=new SysRole(new Integer(0).longValue(), "", "", "");
		if (!Objects.isNull(id)) {
			sysRole=sysRoleRepository.findOne(id);
		}
		model.addAttribute("sysRole", sysRole);
		String forword="/sysrole/roleedit";
		return forword;
		}
	@RequestMapping(value="/sysrole/rolesave.do",method=RequestMethod.GET)
	public String save(Long id,String code,String name,String remark,Model model,HttpServletRequest request){
		SysRole sysRole=new SysRole();
		sysRole.setId(id);
		HttpSession session = request.getSession();
		if (id==0) {
			sysRole.setId(null);
			sysRole.setCreateUser((Long) request.getAttribute("login_code"));
		}
		sysRole.setCode(code);
		sysRole.setName(name);
		sysRole.setRemark(remark);
		sysRole.setUpdateUser((Long) request.getAttribute("login_code"));
		sysRoleRepository.save(sysRole);
		model.addAttribute("result", "创建角色成功！");
		String forword="/display/result";
		String a="lgw111";
		return forword;
	}
}
