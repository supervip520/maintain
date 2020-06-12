package com.maint.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.along.entity.DbPermission;
import com.along.entity.DbRole;
import com.along.entity.DbUserRole;
import com.maint.role.mapper.IDbRoleMapper;

@Service
public class RoleService {
	@Autowired
	IDbRoleMapper roleMapper;

	public List<DbRole> getRoleByUser(Integer userId){
		return this.roleMapper.getRoleByUser(userId);
	}
	
	public List<DbRole> findRoleAll(){
		return this.roleMapper.findRoleAll();
	}
	
	public 	DbUserRole findAllByUserId(Integer userId){
		
		return this.roleMapper.findAllByUserId(userId);
	}
	
	public	int addUserRole(DbUserRole userRole) {
		return this.roleMapper.addUserRole(userRole);
	}
	
	public	int editUserRole(DbUserRole userRole) 
	{
		return this.roleMapper.editUserRole(userRole);
		
	}
}
