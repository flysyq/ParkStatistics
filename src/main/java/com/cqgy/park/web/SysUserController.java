package com.cqgy.park.web;

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

import com.cqgy.park.dao.SysUserRepository;
import com.cqgy.park.dao.SysUserService;
import com.cqgy.park.domain.SysUser;
import com.cqgy.park.form.UserListForm;
import com.cqgy.park.tool.SHAUtil;
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
		String logincode=userListForm.getLoginCode();
		String name=userListForm.getName();
		String loginpassword=userListForm.getLoginPassword();
		Integer enabled=userListForm.getEnabled();
		String select = "select * from sys_user";
		String where = "";
		if (!Strings.isNullOrEmpty(logincode)) {
			where+="login_code="+logincode;
		}
		if (!Strings.isNullOrEmpty(name)) {
			where+=" and name like '%"+name+"%'";
		}
		if (!Strings.isNullOrEmpty(loginpassword)) {
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
		if (!Objects.isNull(id)) {
			sysUser=sysUserRepository.findOne(id);
		}
		model.addAttribute("sysUser", sysUser);
		String foward="/sysuser/useredit";
		return foward;	
	}
	@RequestMapping(value="/sysuser/usersave.do",method=RequestMethod.GET)
	public String sava(Long id,String logincode,String loginpassword,String name,Integer enabled,Model model,HttpServletRequest request){
			SysUser sysUser=new SysUser();
			sysUser.setId(id);
			HttpSession session = request.getSession();
			if (id==0) {
				sysUser.setId(null);
				sysUser.setCreateTime(new Date());
				sysUser.setCreateUser((Long) session.getAttribute("login_id"));
			}
			sysUser.setLoginCode(logincode);
			try {
				sysUser.setLoginPassword(SHAUtil.shaEncode(loginpassword));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sysUser.setName(name);
			sysUser.setEnabled(enabled);
			sysUser.setUpdateTime(new Date());
			sysUser.setUpdateUser((Long) session.getAttribute("login_id"));
			sysUserRepository.save(sysUser);
			model.addAttribute("result", "创建用户成功！");
			String a="lgw";
		String foward="display/result";
		return foward;
	}
}
