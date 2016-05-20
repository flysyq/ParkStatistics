package com.cqgy.park.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cqgy.park.dao.GateOpenService;
import com.cqgy.park.domain.InfoGateOpenHand;
import com.cqgy.park.tool.CustomFile;
import com.cqgy.park.tool.CustomProps;
@Service
public class GateOpenServiceImpl implements GateOpenService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public List<InfoGateOpenHand> getGateOpens(String sql) {
		List<InfoGateOpenHand> gateOpens=jdbcTemplate.query(sql, new RowMapper<InfoGateOpenHand>(){

			@Override
			public InfoGateOpenHand mapRow(ResultSet rs, int Rownum) throws SQLException {
				InfoGateOpenHand gateOpen=new InfoGateOpenHand();
				gateOpen.setId(rs.getLong("id"));
				gateOpen.setOpenEmpName(rs.getString("open_emp_name"));
				gateOpen.setOpenEmpNo(rs.getString("open_emp_no"));
				gateOpen.setOpenPic(CustomProps.getProp("file.http.url")+"/"+CustomFile.getOpen_hand()+"/"+rs.getDate("open_time")+"/"+rs.getString("open_pic"));
				gateOpen.setOpenTime(rs.getDate("open_time"));
				gateOpen.setOpenType(rs.getInt("open_type"));
				gateOpen.setUpdateTime(rs.getDate("update_time"));
				return gateOpen;
			}

		});
				return gateOpens;
	}

}
