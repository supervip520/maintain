package com.maint.user.controller;

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
import com.along.entity.DbUser;
import com.common.util.MD5Utils;
import com.maint.user.service.UserService;

@Controller
@RequestMapping(value="user")
public class UserController {
	@Autowired
	UserService userservice;
	
	/**
	 * 用户列表
	 */
	@ResponseBody
	@RequestMapping(value = "listUser", method = RequestMethod.GET)
	public String listUser(HttpServletRequest request, HttpServletResponse response) {
		List<DbUser> list=this.userservice.listUser();
		return JSON.toJSONString(list);
	}
	
	/**
	 * 添加用户
	 */
	@ResponseBody
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public String addUser(@RequestBody DbUser user,HttpServletRequest request, HttpServletResponse response){
		String reslut=null;
		int count = this.userservice.addUser(user);
		if(count>0)
		{
			reslut="添加成功";
		}else {
			reslut= "添加失败";
		}
		
		return reslut;
	}
	
	/**
	 * 修改用户
	 */
	@ResponseBody
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public String updateUser(@RequestBody DbUser user,HttpServletRequest request, HttpServletResponse response){
		String reslut=null;
		String pwd = MD5Utils.encrypt(user.getUser_password());
		user.setUser_password(pwd);
		if(this.userservice.updateUser(user)) {
			reslut="用户修改成功";
		}else {
			reslut="用户修改失败";
		}
		return reslut;
	}
	
	/**
	 * 删除用户
	 */
	@ResponseBody
	@RequestMapping(value = "deleteUser", method = RequestMethod.POST)
	public String deleteUser(@RequestBody DbUser user,HttpServletRequest request, HttpServletResponse response) {
		String reslut=null;
		if(user.getId() == 1)
		{
			return "不能删除管理员";
		}
		int count = this.userservice.deleteUser(user);
	
		if(count>0)
		{
				reslut= "删除成功";
		}else {
			 reslut="删除失败";
		}
		return reslut;
	}
	

	/**
	 * 用户管理页面
	 */
	@RequiresPermissions("user:user")
	@RequestMapping(value = "userPage")
	   public String userPage() throws Exception {
	       return "/user/user";
	   }
	
	
	/**
	 * 修改个人密码页面
	 */
	@RequestMapping(value = "updatePasswdPage")
	   public String updatePasswdPage(){
	       return "/user/update_passwd";
	   }
	
	

	/**
	 * 修改个人密码
	 */
	@ResponseBody
	@RequestMapping(value = "updatePasswd", method = RequestMethod.POST)
	public String updatePasswd(String newPassword,String oldPassword,ServletRequest request) {
		HttpSession session = ((HttpServletRequest)request).getSession();
		DbUser user = (DbUser)session.getAttribute("user");
		Map<String ,String> reslut = new HashMap<>();
		int i = this.userservice.updatePasswd(newPassword,oldPassword,user.getId());
		if(i>0)
		{
			reslut.put("msg", "修改成功");
		}else {
			reslut.put("msg", "旧密码不正确");
		}
		return JSON.toJSONString(reslut);
		
		}

	
	
}
