package com.cqgy.park.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqgy.park.domain.SysUserRoles;

public interface SysUserRolesRepository extends JpaRepository<SysUserRoles, Long>{
	List<SysUserRoles> findByUserId(Long userid);
	List<SysUserRoles> findByRoleId(Long roleid);
	SysUserRoles findOne(Long id);
}
