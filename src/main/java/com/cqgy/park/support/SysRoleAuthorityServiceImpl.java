package com.cqgy.park.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cqgy.park.dao.SysRoleAuthorityService;
import com.cqgy.park.domain.SysRoleAuthoritys;
@Service
public class SysRoleAuthorityServiceImpl implements SysRoleAuthorityService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public List<SysRoleAuthoritys> getRoleAuthoritys(String sql) {
		List<SysRoleAuthoritys> sysRoleAuthoritys=jdbcTemplate.query(sql, new RowMapper<SysRoleAuthoritys>(){

			@Override
			public SysRoleAuthoritys mapRow(ResultSet rs, int Rownum) throws SQLException {
				SysRoleAuthoritys sysRoleAuthority=new SysRoleAuthoritys();
					sysRoleAuthority.setId(rs.getLong("id"));
					sysRoleAuthority.setRoleId(rs.getLong("role_id"));
					sysRoleAuthority.setAuthorityId(rs.getLong("authority_id"));
					sysRoleAuthority.setCreateTime(rs.getDate("create_time"));
					sysRoleAuthority.setCreateUser(rs.getLong("create_user"));
				return sysRoleAuthority;
			}
			
		});
		return sysRoleAuthoritys;
	}

}
