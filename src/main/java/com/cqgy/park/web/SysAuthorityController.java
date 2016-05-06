/**
 * @作者 admin
 * @时间 2016年4月20日 下午5:06:58
 * @类名 SysAuthorityController.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月20日 下午5:06:58
 *   修改描述
 */
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqgy.park.dao.SysAuthorityRepository;
import com.cqgy.park.dao.SysAuthorityService;
import com.cqgy.park.domain.SysAuthority;
import com.cqgy.park.form.AuthorityListForm;
import com.google.common.base.Strings;;

@Controller
public class SysAuthorityController {
	
	@Autowired
	SysAuthorityService sysAuthorityService;
	
	@Autowired
	SysAuthorityRepository sysAuthorityRepository;
	@RequestMapping(value="/authority/list.do",method=RequestMethod.GET)
	public String list(AuthorityListForm authorityListForm,Long del_id,HttpServletRequest request,Model model){
		if(!Objects.isNull(del_id)){
			sysAuthorityRepository.delete(del_id);
		}
		
		Integer flag = authorityListForm.getFlag();
		Integer grade = authorityListForm.getGrade();
		String title = authorityListForm.getTitle();
		
		String select = "select * from sys_authority";
		String where = "";
		if(!Objects.isNull(flag)){
			where += " flag = "+flag;
		}
		if(!Objects.isNull(grade)){
			where += " and grade="+grade;
		}
		if(!Strings.isNullOrEmpty(title)){
			where += " and title like '%"+title+"%'";
		}
		if(where.startsWith(" and")){
			where = where.substring(4);
		}
		
		if(where.trim().length()>0){
			where = " where "+where;
		}		
		String sql = select + where;
		List<SysAuthority> sysAuthoritys=sysAuthorityService.getAuthoritys(sql);
		List<SysAuthority> fsysAuthoritys=sysAuthorityRepository.findByGradeOrderBySortLevel(1);
		model.addAttribute("sysAuthoritys", sysAuthoritys);
		model.addAttribute("fsysAuthoritys", fsysAuthoritys);
		String forword = "/authority/list";
		return forword;		
	}
	
	@RequestMapping(value="/authority/edit.do",method=RequestMethod.GET)
	public String edit(Long id,Model model){
		
		SysAuthority sysAuthority = new SysAuthority(new Integer(0).longValue(),"","",1,1,"","",0,"");
		if(!Objects.isNull(id)){
			sysAuthority = sysAuthorityRepository.findOne(id);
		}
		//List<SysAuthority> fsysAuthoritys=sysAuthorityRepository.findByGradeOrderBySortLevel(1);
		model.addAttribute("sysAuthority", sysAuthority);
		//model.addAttribute("fsysAuthoritys", fsysAuthoritys);
		String forword="/authority/edit";
		return forword;
	}
	@RequestMapping(value="/authority/father.do",method=RequestMethod.GET)
	public @ResponseBody List<SysAuthority> getByFather(Integer grade,Integer father_id,Model model){
		List<SysAuthority> fsysAuthoritys = null;
		
		if(Objects.isNull(grade) || grade==1){
			fsysAuthoritys=sysAuthorityRepository.findByGradeOrderBySortLevel(1);
		}		
		if(!Objects.isNull(father_id)){
			fsysAuthoritys=sysAuthorityRepository.findByfatherIdOrderBySortLevel(father_id);
		}
		return fsysAuthoritys;
	}
	
	@RequestMapping(value="/authority/save.do",method=RequestMethod.GET)
	public String save(Long id,String title,String remark,String sort_level,
			Integer flag,Integer grade,Integer father_id,String uri,Model model,HttpServletRequest request){
		
		SysAuthority sysAuthority = new SysAuthority();
		sysAuthority.setId(id);
		HttpSession session = request.getSession();
		if(id == 0){
			sysAuthority.setId(null);
			sysAuthority.setCreateTime(new Date());
			sysAuthority.setCreateUser((Long)session.getAttribute("login_id"));
		}
		sysAuthority.setFatherId(father_id);
		sysAuthority.setFlag(flag);
		sysAuthority.setGrade(grade);
		sysAuthority.setSortLevel(sort_level);
		sysAuthority.setTitle(title);
		sysAuthority.setRemark(remark);
		sysAuthority.setUri(uri);
		sysAuthority.setUpdateTime(new Date());
		sysAuthority.setUpdateUser((Long)session.getAttribute("login_id"));
		sysAuthorityRepository.save(sysAuthority);
		model.addAttribute("result", "创建菜单成功！");
		String forword="/display/result";
		String a="lgw11";
		return forword;
	}
}
