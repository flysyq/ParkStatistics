package com.cqgy.park.web;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqgy.park.dao.SysUserRepository;
import com.cqgy.park.dao.SysUserService;
import com.cqgy.park.domain.SysUser;
import com.cqgy.park.form.UserListForm;
import com.google.common.base.Strings;

@Controller
public class SysUserController {
	@Autowired
	SysUserService sysUserService;
	@Autowired
	SysUserRepository sysUserRepository;
	@RequestMapping(value="/sysuser/userlist.do",method=RequestMethod.GET)
	public String list(UserListForm userListForm,Long del_id,HttpServletRequest request,Model model){
		if(!Objects.isNull(del_id)){
			sysUserRepository.delete(del_id);
		}
		String loginCode=userListForm.getLoginCode();
		String name=userListForm.getName();
		String loginPassword=userListForm.getLoginPassword();
		Integer enabled=userListForm.getEnabled();
		String select = "select * from sys_user";
		String where = "";
		if (!Strings.isNullOrEmpty(loginCode)) {
			where+="login_code="+loginCode;
		}
		if (!Strings.isNullOrEmpty(name)) {
			where+=" and name like '%"+name+"%'";
		}
		if (!Strings.isNullOrEmpty(loginPassword)) {
			where+="and login_password";
		}
		if(where.startsWith(" and")){
			where = where.substring(4);
		}
		
		if(where.trim().length()>0){
			where = " where "+where;
		}		
		String sql = select+where;
		List<SysUser> sysUsers=sysUserService.getSysUsers(sql);
		model.addAttribute("sysUsers", sysUsers);
		String foword="/sysuser/userlist";
		return foword;	
	}
	@RequestMapping(value="/sysuser/useredit.do",method=RequestMethod.GET)
	public String edit(Long id,Model model){
		SysUser sysUser=new SysUser(new Integer(0).longValue(), "", "", "", 0);
		if (Objects.isNull(id)) {
			sysUser=sysUserRepository.findOne(id);
		}
		model.addAttribute("sysUser", sysUser);
		String foward="/sysuser/useredit";
		return foward;	
	}
}