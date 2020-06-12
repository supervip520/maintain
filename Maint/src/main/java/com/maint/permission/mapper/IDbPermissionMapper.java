package com.maint.permission.mapper;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.along.entity.DbPermission;

public interface IDbPermissionMapper{

    public List<HashMap<Object,Object>> queryAll();

    public List<DbPermission> queryList();

    public List<DbPermission> queryResourcesListWithSelected(Integer rid);
    
    /**
	 * 根据角色id获取权限数据
	 * @param roleId
	 * @return
	 */
	List<DbPermission> findPermsByRoleId(Integer id);
	
	
	
	List<DbPermission> findByUserName(String userName);
}