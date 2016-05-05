package com.cqgy.park.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.dao.SysUserRepository;
import com.cqgy.park.domain.SysUser;

@Controller
public class SysUserRolesController {
	@Autowired
	SysUserRepository sysUserRepository;
	@RequestMapping(value="/sysuser/userrolesedit.do",method=RequestMethod.GET)
	public String edit(Long id,Model model){
		SysUser fsysuser = sysUserRepository.findOne(id);
		model.addAttribute("sysuser", fsysuser);
		String foward="/sysuserroles/userrolesedit";
		return foward;
	}
}
