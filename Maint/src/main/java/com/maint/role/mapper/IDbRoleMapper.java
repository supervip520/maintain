package com.maint.role.mapper;

import java.util.List;


import com.along.entity.DbPermission;
import com.along.entity.DbRole;
import com.along.entity.DbUser;
import com.along.entity.DbUserRole;

public interface IDbRoleMapper {
	
	/**
	 * 根据用户获取角色列表
	 * @param userId
	 * @return
	 */
	List<DbRole> getRoleByUser(Integer userId);
	
	
	List<DbRole> findByUserName(String userName);
	
	
	List<DbRole> findRoleAll();

	DbUserRole findAllByUserId(Integer userId);
	
	int addUserRole(DbUserRole userRole);
	
	int editUserRole(DbUserRole userRole);

}
