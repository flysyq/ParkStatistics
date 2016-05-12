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
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
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
		String bath=request.getScheme()+"//"+request.getServerName()+":"+request.getServerPort();
		String uri=request.getRequestURI();
		Long role_id = (Long) session.getAttribute("role_id");
		String sql="SELECT sa.uri,sra.role_id FROM sys_authority sa LEFT JOIN sys_role_authoritys sra ON sa.id=sra.authority_id WHERE sra.role_id="+role_id+" AND uri='"+uri+"'";
		List<Map<String, Object>> list = jdbcTeplate.queryForList(sql);
		if (uri.matches("/login/login.do")) {
			chain.doFilter(req, res);
		}else if (!list.isEmpty()) {
			chain.doFilter(req, res);
		}else{
			response.sendRedirect("/index/index");
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
