/**
 * 
 */
package com.cqgy.park.dao;

import java.util.List;

/**
 * @author shiyq
 *
 */
import com.cqgy.park.domain.SysAuthority;

public interface SysAuthorityService {
	
	List<SysAuthority> getAuthoritys(String sql);
}
