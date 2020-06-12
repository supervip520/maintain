package com.maint.user.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.along.entity.DbUser;
import com.common.util.MD5Utils;
import com.maint.user.mapper.IDbUserMapper;

@Service
public class UserService {
	@Autowired
	private IDbUserMapper userMapper;
	
	public List<DbUser> listUser()
	{
		return this.userMapper.listUser();
	}

	public int addUser(DbUser user)
	{	
		String pwd = MD5Utils.encrypt(user.getUser_password());
		user.setUser_password(pwd);
		Date date =new Date();
		String creat_time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(date).toString();
		user.setCreat_time(creat_time);
		user.setIsdelete("0");
		user.setStatus(1);
		
		return this.userMapper.addUser(user);
	}
	
	public boolean updateUser(DbUser user)
	{
		return this.userMapper.updateUser(user)>0;
	}
	
	public int deleteUser(DbUser user)
	{
		return this.userMapper.deleteUser(user);
	}
	
	public DbUser loginByUserNameAndPassword(DbUser user)
	{
		return this.userMapper.loginByUserNameAndPassword(user);
	}
	
	public DbUser getUserByName(String userName) {
		return this.userMapper.getUserByName(userName);
	}
	
	
	
	    public List<DbUser> queryAllUser() {
	        List<DbUser> musers=null;
	        try {
	            //查数据库
	            musers=userMapper.queryAllUser();
	        }catch (Exception e){
	            e.printStackTrace();
	        }

	        return musers;
	    }

	    public DbUser queryIdUser(Integer id) {
	    	DbUser musers=null;
	        try {
	            musers=userMapper.queryIdUser(id);

	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return musers;
	    }
	    public 	int updatePasswd(String newPassword,String oldPassword,Integer id) {
	    	DbUser user=null;
	    	int i=0;
	    	if(StringUtils.isNotEmpty(oldPassword))
	    	{
	    			String oldPwd = MD5Utils.encrypt(oldPassword);
	    			String newPwd=MD5Utils.encrypt(newPassword);
	    			user = userMapper.queryIdUser(id);
	    			String user_password = user.getUser_password();
	    			if(oldPwd.equals(user_password))
	    			{
	    				user.setId(id);
	    				user.setUser_password(newPwd);
	    				i=userMapper.updatePasswd(user);
	    				
	    			}
	    		 
	    	}
	    	return i;
	    	
	    	
	    }
	
	
	
	
}
