package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.InfoUserLoginLog;

public interface InfoUserLoginLogRepository extends JpaRepository<InfoUserLoginLog, Long>{
	List<InfoUserLoginLog> findByUserCode(String userCode);
}
