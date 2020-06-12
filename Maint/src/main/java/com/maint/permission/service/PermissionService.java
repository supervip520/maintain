package com.maint.permission.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.along.entity.DbPermission;
import com.maint.permission.mapper.IDbPermissionMapper;

@Service
public class PermissionService{
	
	@Autowired
	public IDbPermissionMapper permissionMapper;

    public ArrayList<Map<Object,Object>> queryAll(){
    	  List<HashMap<Object,Object>> resultMap =  this.permissionMapper.queryAll();
         //定义一个Map集合  存储按指定顺序排列好的菜单
          Map<String,List<Map<Object,Object>>> temp = new HashMap<String,List<Map<Object, Object>>>();
          for (HashMap<Object,Object> map : resultMap) {
              //如果temp的键包含这个parentid
              if(temp.containsKey(map.get("parentid").toString())) {
                  //么把所有相同parentid的数据全部添加到该parentid键下
                  temp.get(map.get("parentid").toString()).add(map);
              }else {
                  //初始化temp
                  List<Map<Object,Object>> list = new ArrayList<Map<Object, Object>>();
                  list.add(map);
                  temp.put(map.get("parentid").toString(),list);
              }
          }
   
          //定义一个完整菜单列表
          ArrayList<Map<Object,Object>> array = new ArrayList<Map<Object,Object>>();
   
          for (HashMap<Object,Object> hashMap : resultMap) {
              //如果temp中的键与当前id一致
              if (temp.containsKey(hashMap.get("id").toString())){
                  //说明temp是当前id菜单的子菜单
                  hashMap.put("nodes",temp.get(hashMap.get("id").toString()));
              }
              //遇到顶级菜单就添加进完整菜单
              if (hashMap.get("parentid").toString().equals("0")){
                  array.add(hashMap);
              }
          }
          return array;
    }

    public List<DbPermission> queryList(){
    	
    	return this.permissionMapper.queryList();
    }

    public List<DbPermission> queryResourcesListWithSelected(Integer rid){
    	return permissionMapper.queryResourcesListWithSelected(rid);
    }
    
    
	/**
	 * 根据角色id获取权限数据
	 * @param id
	 * @return
	 */
	 public List<DbPermission> findPermsByRoleId(Integer id){
		return this.permissionMapper.findPermsByRoleId(id);
		
	}
}