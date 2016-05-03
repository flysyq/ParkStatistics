package com.cqgy.park.dao;

import java.util.List;

import com.cqgy.park.domain.SysUser;

public interface SysUserService {
	List<SysUser> getSysUsers(String sql);
}
