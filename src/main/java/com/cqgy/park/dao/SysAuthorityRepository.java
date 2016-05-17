/**
 * @作者 admin
 * @时间 2016年4月20日 下午4:53:49
 * @类名 AuthorityRepository.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月20日 下午4:53:49
 *   修改描述
 */
package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.SysAuthority;

public interface SysAuthorityRepository extends JpaRepository<SysAuthority, Long>{
	
	List<SysAuthority> findByGradeOrderBySortLevel(Integer grade);
	List<SysAuthority> findByfatherIdOrderBySortLevel(Long del_id);
	SysAuthority findOne(Long id);
}
