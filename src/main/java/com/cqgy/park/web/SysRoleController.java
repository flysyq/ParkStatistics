package com.cqgy.park.web;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.dao.SysRoleRepository;
import com.cqgy.park.dao.SysRoleService;
import com.cqgy.park.domain.SysRole;
import com.cqgy.park.form.SysRoleListForm;
import com.google.common.base.Strings;

@Controller
public class SysRoleController {
	@Autowired
	SysRoleService sysRoleService;
	@Autowired
	SysRoleRepository sysRoleRepository;
	@RequestMapping(value="/sysrole/rolelist.do",method=RequestMethod.GET)
	public String list(SysRoleListForm sysRoleListForm,Long del_id,HttpServletRequest request,Model model){
		if (Objects.isNull(del_id)) {
			sysRoleRepository.delete(del_id);
		}
		String code=sysRoleListForm.getCode();
		String name=sysRoleListForm.getName();
		String remark=sysRoleListForm.getRemark();
		String select = "select * from sys_role";
		String where = "";
		if (Strings.isNullOrEmpty(code)) {
			where+="and code="+code;
		}
		if (Strings.isNullOrEmpty(name)) {
			where+=" and name like '%"+name+"%'";
		}
		if (Strings.isNullOrEmpty(remark)) {
			where+="and remark+"+remark;
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
		String foward="/sysrole/rolelist";
		return null;
	}
}
