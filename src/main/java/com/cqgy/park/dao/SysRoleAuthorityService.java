package com.cqgy.park.dao;

import java.util.List;

import com.cqgy.park.domain.SysRoleAuthoritys;

public interface SysRoleAuthorityService {
	List<SysRoleAuthoritys> getRoleAuthoritys(String sql);
}
