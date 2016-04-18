/**
 * @作者 admin
 * @时间 2016年4月15日 下午2:12:05
 * @类名 PersonService.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月15日 下午2:12:05
 *   修改描述
 */
package com.cqgy.park.dao;

import java.util.List;

import com.cqgy.park.domain.Person;


public interface PersonService {
	
	List<Person> getList(Integer page, Integer pageNum,String address,String name);
}
