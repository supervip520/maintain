package com.along.entity;


import lombok.Data;

@Data
public class DbRole {
	private Integer id;
	
	private String roleDesc;//角色名称
	
    private String roleKey;//角色权限
    
    private String roleSort;//角色排序
    
    private String status;//角色状态
    
    
	private String code;
    
    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag; 
    
    /** 用户是否存在此角色标识 默认不存在 */
    private boolean flag = false;
    
    /** 菜单组 */
    private Long[] menuIds;

    /** 部门组（数据权限） */
    private Long[] deptIds;
    
    
	
	
	
}
