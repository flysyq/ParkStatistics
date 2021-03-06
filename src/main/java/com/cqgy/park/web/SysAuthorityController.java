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
import java.util.Iterator;
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
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(value="/authority/list.do",method=RequestMethod.GET)
	public String list(AuthorityListForm authorityListForm,Long page,Long del_id,HttpServletRequest request,Model model){
		if(!Objects.isNull(del_id)){
			String findgrade="select grade from sys_authority where id="+del_id;
			List<Map<String, Object>> foundGrade = jdbcTemplate.queryForList(findgrade);
			String delsql="delete from sys_authority where id="+del_id+" or father_id="+del_id;
			List<SysAuthority> findByfatherIdOrderBySortLevel = sysAuthorityRepository.findByfatherIdOrderBySortLevel(del_id);
			String delRoleAuthority="delete from sys_role_authoritys where authority_id="+del_id;	
			if (!foundGrade.isEmpty()) {
				Integer grade = (Integer) foundGrade.get(0).get("grade");
				if (grade==1) {
					Iterator<SysAuthority> iterator = findByfatherIdOrderBySortLevel.iterator();
					while (iterator.hasNext()) {
						Long authority_id= iterator.next().getId();
						String delgrade2="delete from sys_role_authoritys where authority_id="+authority_id;
						jdbcTemplate.update(delgrade2);
					}
				}
			}
		
			jdbcTemplate.update(delRoleAuthority);
			jdbcTemplate.update(delsql);
		}

		Integer flag = authorityListForm.getFlag();
		Integer grade = authorityListForm.getGrade();
		String title = authorityListForm.getTitle();

		Long pageSize=(long) 10;	
		String countsql="select count(*) count from sys_authority";
		Long count = (Long)jdbcTemplate.queryForList(countsql).get(0).get("count");
		long pageMax;
		if (count%pageSize==0) {
			pageMax=count/pageSize;
		}else{
			pageMax=count/pageSize+1;
		}
		if (page==0) {
			page=(long) 1;
		}
		if (pageMax==0) {
			pageMax=1;
		}
		Long prevPage=page-1;
		Long nextPage=page+1;
		if (prevPage==0) {
			prevPage=(long) 1;
		}
		if (nextPage>pageMax) {
			nextPage=pageMax;
		}
		Long pageStart=(page-1)*pageSize;

		String select = "select *, if(father_id=0,id,father_id) fid from sys_authority order by fid,grade,sort_level limit "+pageStart+","+pageSize;
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
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "系统管理");
		session.setAttribute("childrentitle", "菜单管理");
		session.setAttribute("currentpage", page);
		session.setAttribute("prevpage", prevPage);
		session.setAttribute("nextpage", nextPage);
		session.setAttribute("maxpage", pageMax);
		String forword = "authority/list";
		return forword;		
	}

	@RequestMapping(value="/authority/edit.do",method=RequestMethod.GET)
	public String edit(Long id,Model model){

		SysAuthority sysAuthority = new SysAuthority(new Integer(0).longValue(),"","",1,1,"","",(long) 0,"");
		if(!Objects.isNull(id)){
			sysAuthority = sysAuthorityRepository.findOne(id);
		}
		//List<SysAuthority> fsysAuthoritys=sysAuthorityRepository.findByGradeOrderBySortLevel(1);
		model.addAttribute("sysAuthority", sysAuthority);
		//model.addAttribute("fsysAuthoritys", fsysAuthoritys);
		String forword="authority/edit";
		return forword;
	}
	@RequestMapping(value="authority/father.do",method=RequestMethod.GET)
	public @ResponseBody List<SysAuthority> getByFather(Integer grade,Long father_id,Model model){
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
			Integer flag,Integer grade,Long father_id,String uri,Model model,HttpServletRequest request){

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
		String forword="display/result";
		return forword;
	}
}
