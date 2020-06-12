package com.along.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class DbPermission {

	 	private Integer id;
	    private String text; // 菜单名称
	    private String url; // 访问路径
	    /** 父菜单名称 */
	    private String parentName;
	    /** 父菜单ID */
	    private Long parentId;
	    /** 显示顺序 */
	    private String plevel;
	    /** 类型:0目录,1菜单,2按钮 */
	    private String menuType;
	    /** 菜单状态:0显示,1隐藏 */
	    private String visible;
	    /** 权限字符串 */
	    private String perms;
	    /** 菜单图标 */
	    private String icon;
	    
	    private String code;
	    
	    /** 子菜单 */
	    private List<DbPermission> children = new ArrayList<DbPermission>();
	    
	    
		
		
    
}
