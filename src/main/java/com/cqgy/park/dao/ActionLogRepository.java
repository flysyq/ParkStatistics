/**
 * @作者 admin
 * @时间 2016年4月26日 下午5:34:46
 * @类名 ActionLogRepository.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月26日 下午5:34:46
 *   修改描述
 */
package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.ActionLog;

public interface ActionLogRepository extends JpaRepository<ActionLog, Long>{
	
}
