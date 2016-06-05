package com.cqgy.park.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cqgy.park.dao.ParkService;
import com.cqgy.park.domain.InfoPark;

@Service
public class ParkServiceImpl implements ParkService{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<InfoPark> getParks(String sql) {
		List<InfoPark> infoParks=jdbcTemplate.query(sql, new RowMapper<InfoPark>(){

			@Override
			public InfoPark mapRow(ResultSet rs, int Rownum) throws SQLException {
				InfoPark infoPark=new InfoPark();
				infoPark.setId(rs.getLong("id"));
				infoPark.setParkName(rs.getString("park_name"));
				infoPark.setParkCode(rs.getString("park_code"));
				infoPark.setParkDesc(rs.getString("park_desc"));
				infoPark.setAllParkNum(rs.getInt("all_park_num"));
				infoPark.setOutParkNum(rs.getInt("out_park_num"));
				infoPark.setNowParkNum(rs.getInt("now_park_num"));
				infoPark.setCreateUser(rs.getLong("create_user"));
				infoPark.setCreateTime(rs.getDate("create_time"));
				infoPark.setUpdateUser(rs.getLong("update_user"));
				infoPark.setUpdateTime(rs.getDate("update_time"));
				return infoPark;
			}
			
		});
		return infoParks;
	}
	
}
