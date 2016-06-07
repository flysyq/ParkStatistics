/**
 * @作者 石永强
 * @时间 2016年6月7日 上午10:51:26
 * @类名 InfoMailLogRepository.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年6月7日 上午10:51:26
 *   修改描述
 */
package com.cqgy.park.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.InfoMailLog;

public interface InfoMailLogRepository extends JpaRepository<InfoMailLog, Long> {

}
