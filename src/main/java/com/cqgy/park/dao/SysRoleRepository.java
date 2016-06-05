package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.SysRole;

public interface SysRoleRepository extends JpaRepository<SysRole, Long>{
	List<SysRole> findByCode(String code);
	List<SysRole> findByName(String name);
	List<SysRole> findByCodeAndName(String code,String name);
	SysRole findOne(Long id);
}
