package com.maint.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.along.entity.DbRole;
import com.along.entity.DbUser;
import com.along.entity.DbUserRole;
import com.maint.role.service.RoleService;
import com.maint.user.service.UserService;

@Controller
@RequestMapping(value="role")
public class RoleController {
	@Autowired
	RoleService roleService;
	
	
	
	

	/**
	 * 角色管理页面
	 */
	@RequiresPermissions("role:role")
	@RequestMapping(value = "rolePage")
	   public String userPage() throws Exception {
	       return "/role/role";
	   }
	
	
	
	
	/**
	 * 用户列表
	 */
	@ResponseBody
	@RequestMapping(value = "listRole", method = RequestMethod.GET)
	public String listUser(HttpServletRequest request, HttpServletResponse response) {
		List<DbRole> list=this.roleService.findRoleAll();
		return JSON.toJSONString(list);
	}
	
	/**
	 * 角色分配页面
	 */
	@ResponseBody
	@RequestMapping(value = "roleApp", method = RequestMethod.GET)
	public String roleApp(Integer id) {
		DbUserRole userRole = this.roleService.findAllByUserId(id);
		return JSON.toJSONString(userRole);
	}
	
	/**
	 * 角色分配
	 */
	@ResponseBody
	@RequestMapping(value = "addUserRole", method = RequestMethod.POST)
	public String addUser(@RequestBody DbUserRole ur,HttpServletRequest request, HttpServletResponse response){
		String reslut=null;
		
		DbUserRole userRole = this.roleService.findAllByUserId(ur.getUserId());
		if(null == userRole)
		{
			int i = this.roleService.addUserRole(ur);
			if(i>0)
			{
				reslut="角色分配成功";
			}else {
				reslut= "角色分配失败";
			}
		}else {
			
			int j = this.roleService.editUserRole(ur);
			if(j>0)
			{
				reslut="角色分配成功";
			}else {
				reslut= "角色分配失败";
			}
		}
		
		
		return reslut;
	}

	
	
}
