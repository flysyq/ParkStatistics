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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqgy.park.dao.SysAuthorityService;
import com.cqgy.park.dao.SysRoleAuthorityRepository;
import com.cqgy.park.dao.SysRoleAuthorityService;
import com.cqgy.park.domain.SysAuthority;
import com.cqgy.park.domain.SysRoleAuthoritys;

@Controller
public class SysRoleAuthorityController {
	@Autowired
	SysRoleAuthorityService sysRoleAuthorityService;
	@Autowired
	SysRoleAuthorityRepository sysRoleAuthorityRepository;
	@Autowired
	SysAuthorityService sysAuthorityService;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="/sysrole/sysroleauthoritylist.do",method=RequestMethod.GET)
	public String list(Long id,Long del_id,HttpServletRequest request,Model model){
		String sql="SELECT *,IF(father_id=0,sa.id,father_id) fid  FROM sys_authority sa LEFT JOIN sys_role_authoritys sra ON sa.id=sra.authority_id AND sra.role_id="+id+" ORDER BY fid,grade,sort_level";
		List<Map<String, Object>> sysRoleAuthorityList = jdbcTemplate.queryForList(sql);
		//List<SysRoleAuthoritys> roleAuthoritys = sysRoleAuthorityService.getRoleAuthoritys(sql);
		model.addAttribute("sysRoleAuthorityList", sysRoleAuthorityList);
		model.addAttribute("role_id", id);
		String forword="/sysroleauthority/sysroleauthoritylist";
		return forword;	
	};
	@RequestMapping(value="/sysrole/sysroleauthorityedit.do",method=RequestMethod.GET)
	public String edit(Long id,Model model){
		long authorityid=0;
		model.addAttribute("authority_id", authorityid);
		model.addAttribute("role_id", id);
		String forword="/sysroleauthority/sysroleauthorityedit";
		return forword;	
	}
	@RequestMapping(value="/sysroleauthority/getauthority.do",method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getAuthority(@RequestParam(value="role_id")String role_id){
		Long roleid=Long.valueOf(role_id);
		String sql="SELECT * from sys_authority sa WHERE sa.id NOT IN(SELECT authority_id FROM sys_role_authoritys WHERE role_id="+role_id+") AND father_id=0;";
		List<Map<String, Object>> authorityList = jdbcTemplate.queryForList(sql);
		return authorityList;	
	}
	@RequestMapping(value="/sysrole/sysroleauthoritysave.do",method=RequestMethod.GET)
	public String save(Long id,Long role_id,Long authority_id,Model model,HttpServletRequest request){
		SysRoleAuthoritys sysRoleAuthoritys=new SysRoleAuthoritys();
		HttpSession session=request.getSession();
		if (authority_id!=0) {
			sysRoleAuthoritys.setCreateTime(new Date());
			sysRoleAuthoritys.setCreateUser((Long) session.getAttribute("Login_id"));
			sysRoleAuthoritys.setRoleId(role_id);
			sysRoleAuthoritys.setAuthorityId(authority_id);
			sysRoleAuthorityRepository.save(sysRoleAuthoritys);
			model.addAttribute("result", "添加权限成功！");
		}else {
			model.addAttribute("result", "未添加权限");
		}
		String forword="/display/result";
		return forword;	
	}
}
