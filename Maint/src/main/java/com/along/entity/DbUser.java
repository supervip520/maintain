package com.along.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class DbUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String user_account;
	private String user_password;
	private String creat_time;
	private String isdelete;
	private String fullName;
	private int roleId;
	private String enable;
	private Integer status;
	private String salt;
	private String code;
	
    private List<DbRole> roles;
	
    /** 角色组 */
    private Long[] roleIds;
    
    
    
	
	
	
}
