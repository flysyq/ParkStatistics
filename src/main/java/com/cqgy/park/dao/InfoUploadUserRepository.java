/**
 * @作者 admin
 * @时间 2016年5月12日 上午10:00:12
 * @类名 InfoUploadUserRepository.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月12日 上午10:00:12
 *   修改描述
 */
package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.InfoUploadUser;

public interface InfoUploadUserRepository extends JpaRepository<InfoUploadUser, Long> {
	
	List<InfoUploadUser> findByLoginCode(String loginCode);
}
