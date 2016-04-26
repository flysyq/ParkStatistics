/**
 * @作者 admin
 * @时间 2016年4月21日 上午11:59:41
 * @类名 SysUserAuthoritys.java
 * @类描述 
 * @修改记录
 * 1、修改人 2016年4月21日 上午11:59:41
 *   修改描述
 */
package com.cqgy.park.domain;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
public class SysUserAuthoritys {
	
	@Id
	@GeneratedValue
	private Long Id;
	
	private Long UserId;
	private Long authority_id;
	
	private Integer createUser;
	private Timestamp createTime;
	
}
