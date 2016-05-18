package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.InfoParkEmp;

public interface InfoParkEmpRepository extends JpaRepository<InfoParkEmp, Long>{
	List<InfoParkEmp> findByUserCode(String userCode);
}
