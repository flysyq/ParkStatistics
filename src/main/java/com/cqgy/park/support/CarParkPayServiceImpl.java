package com.cqgy.park.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cqgy.park.dao.CarParkPayService;
import com.cqgy.park.domain.InfoCarParkPay;
@Service
public class CarParkPayServiceImpl implements CarParkPayService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public List<InfoCarParkPay> getCarParkPays(String sql) {
		List<InfoCarParkPay> carParkPays=jdbcTemplate.query(sql, new RowMapper<InfoCarParkPay>(){

			@Override
			public InfoCarParkPay mapRow(ResultSet rs, int Rownum) throws SQLException {
				InfoCarParkPay carParkPay=new InfoCarParkPay();
				carParkPay.setCardNo(rs.getString("card_no"));
				carParkPay.setCardType(rs.getInt("card_type"));
				carParkPay.setEmpName(rs.getString("emp_name"));
				carParkPay.setEmpNo(rs.getString("emp_no"));
				carParkPay.setEndTime(rs.getDate("end_time"));
				carParkPay.setFee(rs.getDouble("fee"));
				carParkPay.setFeeFree(rs.getDouble("fee_free"));
				carParkPay.setFreeType(rs.getInt("free_type"));
				carParkPay.setId(rs.getLong("id"));
				carParkPay.setParkId(rs.getString("park_id"));
				carParkPay.setPayType(rs.getInt("pay_type"));
				carParkPay.setRealPay(rs.getDouble("real_pay"));
				carParkPay.setStartTime(rs.getDate("start_time"));
				carParkPay.setUpdateTime(rs.getDate("update_time"));
				return carParkPay;
			}

		});
		return carParkPays;
	}

}
