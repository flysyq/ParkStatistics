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

import com.cqgy.park.bo.Page;
import com.cqgy.park.bo.PageUtil;
import com.cqgy.park.dao.EmpChoiceParkRepository;
import com.cqgy.park.dao.ParkRepository;
import com.cqgy.park.dao.ParkService;
import com.cqgy.park.dao.SysUserService;
import com.cqgy.park.domain.EmpChoicePark;
import com.cqgy.park.domain.InfoPark;
import com.cqgy.park.domain.SysUser;
import com.cqgy.park.form.ParkListForm;
import com.google.common.base.Strings;

@Controller
public class ParkController {

	@Autowired
	ParkService parkService;
	@Autowired
	ParkRepository parkRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	SysUserService sysUserService;
	@Autowired
	EmpChoiceParkRepository choiceParkRepository;
	@RequestMapping(value="park/parklist.do",method=RequestMethod.GET)
	public String list(Long del_id,ParkListForm form,HttpServletRequest request,Model model){
		if (Objects.isNull(form)) {
			form=new ParkListForm();
		}
		if (!Objects.isNull(del_id)) {
			String delsql="delete from info_park where id="+del_id;
			jdbcTemplate.update(delsql);
		}
		String countsql="select count(*) count from info_park";
		Long count = (Long)jdbcTemplate.queryForList(countsql).get(0).get("count");
		Page page=new Page();
		page.setPage(form.getPage());
		page.setCount(count);
		page.setPage_size(form.getPage_size());
		page = PageUtil.handle(page);
		String select = "select * from info_park limit "+((page.getPage() - 1) * page.getPage_size()) + "," + page.getPage_size();
		String where = "";
		String sql=select+where;
		List<InfoPark> parks = parkService.getParks(sql);
		model.addAttribute("parks",parks);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "车库管理");
		session.setAttribute("childrentitle", "车库管理");
		model.addAttribute("page", page);
		model.addAttribute("form", form);
		String forword="park/parklist";
		return forword;
	}

	@RequestMapping(value="park/parkedit.do",method=RequestMethod.GET)
	public String edit(Long id,Model model){
		InfoPark infoPark=new InfoPark(new Integer(0).longValue(), "", "", "", null, null, null);
		if (!Objects.isNull(id)) {
			infoPark=parkRepository.findOne(id);
		}
		model.addAttribute("infoPark", infoPark);
		String forword="park/parkedit";
		return forword;		
	}

	@RequestMapping(value="park/parksave.do",method=RequestMethod.GET)
	public String save(Long id,String parkName,String parkCode,String parkDesc,Integer allParkNum,Integer outParkNum,Integer nowParkNum,HttpServletRequest request,Model model){
		InfoPark infoPark=new InfoPark();
		infoPark.setId(id);
		HttpSession session=request.getSession();
		if (id==0) {
			infoPark.setId(null);
			infoPark.setCreateTime(new Date());
			infoPark.setCreateUser((Long) session.getAttribute("login_id"));
		}
		infoPark.setParkName(parkName);
		infoPark.setParkCode(parkCode);
		infoPark.setParkDesc(parkDesc);
		infoPark.setAllParkNum(allParkNum);
		infoPark.setOutParkNum(outParkNum);
		infoPark.setNowParkNum(nowParkNum);
		infoPark.setUpdateTime(new Date());
		infoPark.setUpdateUser((Long) session.getAttribute("login_id"));
		parkRepository.save(infoPark);
		model.addAttribute("result", "创建车库成功");
		String forword="display/result";
		return forword;

	}

	@RequestMapping(value="park/choiceparklist.do",method=RequestMethod.GET)
	public String choiceParkList(Long page,HttpServletRequest request,Model model){
		Long pageSize=(long) 5;	
		String countsql="select count(*) count from sys_user";
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

		String select = "select * from sys_user limit "+pageStart+","+pageSize;
		String where = "";
		String sql = select+where;
		System.out.println(sql);
		List<SysUser> sysUsers=sysUserService.getSysUsers(sql);
		model.addAttribute("sysUsers", sysUsers);
		HttpSession session = request.getSession();
		session.setAttribute("fathertitle", "车库管理");
		session.setAttribute("childrentitle", "车库选择");
		session.setAttribute("currentpage", page);
		session.setAttribute("prevpage", prevPage);
		session.setAttribute("nextpage", nextPage);
		session.setAttribute("maxpage", pageMax);
		String forword="park/choiceparklist";
		return forword;
	}

	@RequestMapping(value="park/choiceparkedit.do",method=RequestMethod.GET)
	public String choiceParkEdit(Long id,Model model){
		String select = "SELECT ip.*,ecp.user_id FROM info_park ip LEFT JOIN emp_choice_park ecp ON ip.id=ecp.park_id AND user_id="+id;
		String where = "";
		String sql=select+where;
		List<Map<String, Object>> parks = jdbcTemplate.queryForList(sql);
		model.addAttribute("userid", id);
		model.addAttribute("parks",parks);
		String forword="park/choiceparkedit";
		return forword;

	}

	@RequestMapping(value="park/choiceparksave.do",method=RequestMethod.GET)
	public String choiceParkSave(Long userId,Long[] parkId,Model model,HttpServletRequest request){
		String sql="delete from emp_choice_park where user_id="+userId;
		jdbcTemplate.update(sql);
		HttpSession session = request.getSession();
		if (parkId!=null) {
			for (int i = 0; i < parkId.length; i++) {
				EmpChoicePark choicePark=new EmpChoicePark();
				choicePark.setUserId(userId);
				choicePark.setParkId(parkId[i]);
				choicePark.setCreateTime(new Date());
				choicePark.setCreateUser((Long) session.getAttribute("login_code"));
				choiceParkRepository.save(choicePark);
			}
			model.addAttribute("result", "保存成功！");
		}else{
			model.addAttribute("result", "已取消！");
		}
		String forword="display/result";
		return forword;
	};
}
