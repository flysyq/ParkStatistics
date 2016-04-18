/**
 * @作者 admin
 * @时间 2016年4月15日 下午2:50:19
 * @类名 ClassStudent.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月15日 下午2:50:19
 *   修改描述
 */
package com.cqgy.park.dao;

import java.util.List;

import com.cqgy.park.qresult.ClassStudent;

public interface ClassStudentService {
	List<ClassStudent> getStudentByClass(Integer classId);
}
