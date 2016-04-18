/**
 * @作者 admin
 * @时间 2016年4月15日 下午2:05:56
 * @类名 PersonRepository.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月15日 下午2:05:56
 *   修改描述
 */
package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cqgy.park.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	List<Person> findByAddress(String name);
	Person findByNameAndAddress(String name, String address);
	@Query("select p from Person p where p.name=:name and p.address=:address")
	Person withNameAndAddressQuery(@Param("name")String name,@Param("address") String address);
	
	List<Person> withNameAndAddressNamedQuery(String name,String address);
	
	List<Person> findByNameContainingAndAddressContaining(String name,String address);
}
