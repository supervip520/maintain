package com.maint.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.along.entity.DbPermission;
import com.along.entity.DbUser;

public interface IDbUserMapper {
	List<DbUser> listUser();
	DbUser getUserByID(String id);
	int addUser(DbUser user);
	int updateUser(DbUser user);
	int deleteUser(DbUser user);
	DbUser loginByUserNameAndPassword(DbUser user);
	DbUser getUserByName(String userName);
	
	int updatePasswd(DbUser user);
	
	
	List<DbUser> queryAllUser();
	DbUser queryIdUser(Integer id);
	
    @Select("select * from db_permission")
    List<DbPermission> queryMenu();

}
