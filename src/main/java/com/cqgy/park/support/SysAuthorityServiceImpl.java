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
				sysAuthority.setCode(rs.getString("code"));
				sysAuthority.setCreateTime(rs.getTimestamp("createTime"));
				sysAuthority.setCreateUser(rs.getInt("createUser"));
				sysAuthority.setFatherId(rs.getInt("fatherId"));
				sysAuthority.setFlag(rs.getInt("flag"));
				sysAuthority.setGrade(rs.getInt("grade"));
				sysAuthority.setRemark(rs.getString("remark"));
				sysAuthority.setSortLevel(rs.getString("sortLevel"));
				sysAuthority.setTitle(rs.getString("title"));
				sysAuthority.setUpdateTime(rs.getTimestamp("updateTime"));
				sysAuthority.setUpdateUser(rs.getInt("updateUser"));
				sysAuthority.setUri(rs.getString("uri"));
				return sysAuthority;
			}
			
		});
		return sysAuthoritys;
	}

}
