/**
 * @作者 admin
 * @时间 2016年4月20日 下午5:47:43
 * @类名 SysUserRepository.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月20日 下午5:47:43
 *   修改描述
 */
package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
	
	List<SysUser> findByLoginCode(String loginCode);
	SysUser findByLoginCodeAndLoginPassword(String loginCode,String loginPassword);
	SysUser findOne(Long id);
}
