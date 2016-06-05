package com.cqgy.park.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cqgy.park.dao.CarIoService;
import com.cqgy.park.domain.InfoCarIo;
import com.cqgy.park.tool.CustomFile;
import com.cqgy.park.tool.CustomProps;
@Service
public class CarIoServiceImpl implements CarIoService{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<InfoCarIo> getCarIos(String sql) {
		List<InfoCarIo> carIos=jdbcTemplate.query(sql, new RowMapper<InfoCarIo>() {

			@Override
			public InfoCarIo mapRow(ResultSet rs, int Rownum) throws SQLException {
				InfoCarIo carIo=new InfoCarIo();
				carIo.setAccType(rs.getInt("acc_type"));
				carIo.setCardNo(rs.getString("card_no"));
				carIo.setCardType(rs.getInt("card_type"));
				carIo.setComePic(CustomProps.getProp("file.http.url")+"/"+CustomFile.getCar_in()+"/"+rs.getDate("come_time")+"/"+rs.getString("come_pic"));
				carIo.setComeTime(rs.getDate("come_time"));
				carIo.setGoPic(CustomProps.getProp("file.http.url")+"/"+CustomFile.getCar_out()+"/"+rs.getDate("go_time")+"/"+rs.getString("go_pic"));
				carIo.setGoTime(rs.getDate("go_time"));
				carIo.setId(rs.getLong("id"));
				carIo.setInEmpName(rs.getString("in_emp_name"));
				carIo.setInEmpNo(rs.getString("in_emp_no"));
				carIo.setOutEmpName(rs.getString("out_emp_name"));
				carIo.setOutEmpNo(rs.getString("out_emp_no"));
				carIo.setParkId(rs.getString("park_id"));
				carIo.setParkSpacePic(CustomProps.getProp("file.http.url")+"/"+CustomFile.getCar_park_space()+"/"+rs.getDate("go_time")+"/"+rs.getString("park_space_pic"));
				carIo.setPlate(rs.getString("plate"));
				carIo.setUpdateTime(rs.getDate("update_time"));
				return carIo;
			}

		});
		return carIos;
	}

}
