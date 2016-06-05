package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.InfoParkAdmin;

public interface InfoParkAdminRepository extends JpaRepository<InfoParkAdmin, Long> {

	List<InfoParkAdmin> findByUserCode(String userCode); 
}
