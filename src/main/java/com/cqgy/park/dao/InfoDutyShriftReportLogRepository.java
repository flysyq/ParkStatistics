/**
 * @作者 石永强
 * @时间 2016年6月1日 上午11:52:28
 * @类名 InfoDutyShriftReportLogRepository.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年6月1日 上午11:52:28
 *   修改描述
 */
package com.cqgy.park.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.InfoDutyShriftReportLog;

public interface InfoDutyShriftReportLogRepository extends JpaRepository<InfoDutyShriftReportLog, Long> {

}
