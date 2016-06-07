package com.cqgy.park.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cqgy.park.dao.SysUserService;
import com.cqgy.park.domain.SysUser;
@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<SysUser> getSysUsers(String sql) {
		List<SysUser> sysUsers=jdbcTemplate.query(sql, new RowMapper<SysUser>(){

			@Override
			public SysUser mapRow(ResultSet rs, int Rownum) throws SQLException {
				SysUser sysUser=new SysUser();
				sysUser.setId(rs.getLong("id"));
				sysUser.setCreateTime(rs.getTimestamp("create_time"));
				sysUser.setCreateUser(rs.getLong("create_user"));
				sysUser.setEnabled(rs.getInt("enabled"));
				sysUser.seteMail(rs.getString("e_mail"));
				sysUser.setLoginCode(rs.getString("login_code"));
				sysUser.setLoginPassword(rs.getString("login_password"));
				sysUser.setName(rs.getString("name"));
				sysUser.setUpdateTime(rs.getTimestamp("update_time"));
				sysUser.setUpdateUser(rs.getLong("update_user"));
				sysUser.setMailFlag(rs.getInt("mail_flag"));

				return sysUser;
			}

		});
		return sysUsers;
	}

}
