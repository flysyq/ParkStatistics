package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.SysRoleAuthoritys;

public interface SysRoleAuthorityRepository extends JpaRepository<SysRoleAuthoritys, Long>{
	List<SysRoleAuthoritys> findByAuthorityId(Long authorityId);
	List<SysRoleAuthoritys> findByRoleId(Long roleId);
	SysRoleAuthoritys findOne(Long id);
}
