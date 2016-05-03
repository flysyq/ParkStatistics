package com.cqgy.park.dao;

import java.util.List;

import com.cqgy.park.domain.SysRole;

public interface SysRoleService {
	List<SysRole> getRoles(String sql);
}
