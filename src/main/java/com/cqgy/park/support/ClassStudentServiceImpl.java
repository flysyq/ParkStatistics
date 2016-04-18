/**
 * @作者 admin
 * @时间 2016年4月15日 下午2:52:38
 * @类名 ClassStudentServiceImpl.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月15日 下午2:52:38
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

import com.cqgy.park.dao.ClassStudentService;
import com.cqgy.park.domain.Person;
import com.cqgy.park.qresult.ClassStudent;

@Service
public class ClassStudentServiceImpl implements ClassStudentService {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<ClassStudent> getStudentByClass(Integer classId){
		String sql = "select a.id class_id,a.name class_name,b.* from class a,person b,person_l_class c "
				+ "where a.id=c.class_id and b.id=c.person_id and a.id="+classId;
		System.out.println(sql);
		System.out.println(jdbcTemplate);
		List<ClassStudent> students = jdbcTemplate.query(sql, new RowMapper<ClassStudent>() {
			@Override
			public ClassStudent mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClassStudent student = new ClassStudent();
				student.setAddress(rs.getString("address"));
				student.setAge(rs.getInt("age"));
				student.setId(rs.getInt("Id"));
				student.setName(rs.getString("name"));
				student.setClassId(rs.getInt("class_id"));
				student.setClassName(rs.getString("class_name"));
				return student;
			}
		});
		return students;
	};
	
}
