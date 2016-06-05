/**
 * 
 */
package com.cqgy.park.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cqgy.park.dao.SysAuthorityService;
import com.cqgy.park.domain.SysAuthority;

/**
 * @author shiyq
 *
 */
@Service
public class SysAuthorityServiceImpl implements SysAuthorityService{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<SysAuthority> getAuthoritys(String sql) {
		List<SysAuthority> sysAuthoritys=jdbcTemplate.query(sql, new RowMapper<SysAuthority>(){

			@Override
			public SysAuthority mapRow(ResultSet rs, int Rownum) throws SQLException {
				SysAuthority sysAuthority = new SysAuthority();
				sysAuthority.setId(rs.getLong("id"));
				sysAuthority.setCode(rs.getString("code"));
				sysAuthority.setCreateTime(rs.getTimestamp("create_time"));
				sysAuthority.setCreateUser(rs.getInt("create_user"));
				sysAuthority.setFatherId(rs.getInt("father_id"));
				sysAuthority.setCreateUser(rs.getLong("create_user"));
				sysAuthority.setFatherId((long) rs.getInt("father_id"));
				sysAuthority.setFlag(rs.getInt("flag"));
				sysAuthority.setGrade(rs.getInt("grade"));
				sysAuthority.setRemark(rs.getString("remark"));
				sysAuthority.setSortLevel(rs.getString("sort_level"));
				sysAuthority.setTitle(rs.getString("title"));
				sysAuthority.setUpdateTime(rs.getTimestamp("update_time"));
				sysAuthority.setUpdateUser(rs.getInt("update_user"));
				sysAuthority.setUpdateUser(rs.getLong("update_user"));
				sysAuthority.setUri(rs.getString("uri"));
				return sysAuthority;
			}
			
		});
		return sysAuthoritys;
	}

}
