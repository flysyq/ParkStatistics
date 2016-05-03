package com.cqgy.park.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cqgy.park.dao.SysRoleService;
import com.cqgy.park.domain.SysRole;

public class SysRoleServiceImpt implements SysRoleService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public List<SysRole> getRoles(String sql) {
		List<SysRole> sysRoles=jdbcTemplate.query(sql, new RowMapper<SysRole>(){

			@Override
			public SysRole mapRow(ResultSet rs, int Rownum) throws SQLException {
				SysRole sysRole=new SysRole();
				sysRole.setId(rs.getLong("id"));
				sysRole.setCode(rs.getString("code"));
				sysRole.setCreateUser(rs.getLong("create_user"));
				sysRole.setName(rs.getString("name"));
				sysRole.setRemark(rs.getString("remark"));
				sysRole.setUpdateUser(rs.getLong("update_user"));
				return sysRole;
				
			}	
		});
		return sysRoles;
	}

}
