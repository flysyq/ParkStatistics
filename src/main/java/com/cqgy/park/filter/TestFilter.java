/**
 * @作者 admin
 * @时间 2016年4月20日 上午11:43:02
 * @类名 TestFilter.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月20日 上午11:43:02
 *   修改描述
 */
package com.cqgy.park.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;	
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component

public class TestFilter implements Filter {
	@Autowired
	JdbcTemplate jdbcTeplate;
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		HttpSession session = request.getSession();
		String uri=request.getRequestURI();
		int index=uri.indexOf("/");
		index=uri.indexOf("/",index+1);
		uri=uri.substring(index+1);
		System.out.println(uri);
		ArrayList<String> allow_urls = new ArrayList<String>();

		allow_urls.add("login/login.do");
		allow_urls.add("file/get.do");
		allow_urls.add("upload.do");
		allow_urls.add("sysuserroles/getroles.do");
		allow_urls.add("authority/father.do");
		allow_urls.add("sysrole/sysroleauthoritylist.do");
		allow_urls.add("carpaystats/genImage.do");


		if(allow_urls.contains(uri)){
			chain.doFilter(request, response);
		}else{
			Long role_id = (Long) session.getAttribute("role_id");
			String sql = "";
			sql="SELECT sa.uri,sra.role_id FROM sys_authority sa LEFT JOIN sys_role_authoritys sra ON sa.id=sra.authority_id WHERE sra.role_id="+role_id+" AND uri='"+uri+"'";
			System.out.println(sql);
			List<Map<String, Object>> list = jdbcTeplate.queryForList(sql);
			if(!list.isEmpty()){
				chain.doFilter(request, response);
			}else if(uri.endsWith("edit.do")){
				chain.doFilter(request, response);
			}else if(uri.endsWith("save.do")){
				chain.doFilter(request, response);
			}else if(uri.endsWith("changepasswordedit.do")){
				chain.doFilter(request, response);
			}else if(uri.endsWith("exit.do")){
				chain.doFilter(request, response);
			}else if(uri.endsWith("noauthority.do")){
				chain.doFilter(request, response);
			}else{
				response.sendRedirect("noauthority.do");
			}

		}



	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("init");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}


}
