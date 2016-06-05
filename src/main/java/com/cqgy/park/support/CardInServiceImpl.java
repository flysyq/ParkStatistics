package com.cqgy.park.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cqgy.park.dao.CardInService;
import com.cqgy.park.domain.InfoCardIn;
@Service
public class CardInServiceImpl implements CardInService {
@Autowired
JdbcTemplate jdbcTemplate;
	@Override
	public List<InfoCardIn> getCardIns(String sql) {
		List<InfoCardIn> cardIns=jdbcTemplate.query(sql, new RowMapper<InfoCardIn>(){

			@Override
			public InfoCardIn mapRow(ResultSet rs, int Rownum) throws SQLException {
				InfoCardIn cardIn=new InfoCardIn();
				cardIn.setAcceptEmpName(rs.getString("accept_emp_name"));
				cardIn.setAcceptEmpNo(rs.getString("accept_emp_no"));
				cardIn.setCardNo(rs.getString("card_no"));
				cardIn.setCardType(rs.getInt("card_type"));
				cardIn.setEndDate(rs.getDate("end_date"));
				cardIn.setId(rs.getLong("id"));
				cardIn.setParkId(rs.getString("park_id"));
				cardIn.setPayMoney(rs.getDouble("pay_money"));
				cardIn.setPlate(rs.getString("plate"));
				cardIn.setRemark(rs.getString("remark"));
				cardIn.setStartDate(rs.getDate("start_date"));
				cardIn.setUpdateTime(rs.getDate("update_time"));
				return cardIn;
			}
			
		});
				return cardIns;
	}

}
