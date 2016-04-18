/**
 * @作者 admin
 * @时间 2016年4月15日 下午2:13:24
 * @类名 PersonServiceImpl.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月15日 下午2:13:24
 *   修改描述
 */
package com.cqgy.park.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cqgy.park.dao.PersonService;
import com.cqgy.park.domain.Person;


@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Person> getList(Integer page, Integer pageNum, String address, String name) {

		String sql = "select id,name,age,address from person where address like '%" + address + "' and name like '%"
				+ name + "%' limit " + ((page - 1) * pageNum) + "," + pageNum;
		System.out.println(sql);
		System.out.println(jdbcTemplate);
		List<Person> persons = jdbcTemplate.query(sql, new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person person = new Person();
				person.setAddress(rs.getString("address"));
				person.setAge(rs.getInt("age"));
				person.setId(rs.getLong("Id"));
				person.setName(rs.getString("name"));
				return person;
			}
		});
		return persons;
	}
}

