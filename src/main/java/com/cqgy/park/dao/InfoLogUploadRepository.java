/**
 * @作者 admin
 * @时间 2016年5月16日 下午1:57:22
 * @类名 InfoLogUploadRepository.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年5月16日 下午1:57:22
 *   修改描述
 */
package com.cqgy.park.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.InfoLogUpload;

public interface InfoLogUploadRepository extends JpaRepository<InfoLogUpload, Long> {

}
