package com.along.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.along.entity.DbPermission;
import com.along.entity.DbRole;
import com.along.entity.DbUser;
import com.maint.permission.mapper.IDbPermissionMapper;
import com.maint.role.mapper.IDbRoleMapper;
import com.maint.user.mapper.IDbUserMapper;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	IDbUserMapper userMapper;
	@Autowired
	IDbRoleMapper userRoleMapper;
	@Autowired
	IDbPermissionMapper userPermissionMapper;

	/**
	 * 获取用户角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		DbUser user = (DbUser) SecurityUtils.getSubject().getPrincipal();
		String userName = user.getUser_account();
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		// 获取用户角色集
		List<DbRole> roleList = userRoleMapper.findByUserName(userName);
		Set<String> roleSet = new HashSet<String>();
		for (DbRole r : roleList) {
			roleSet.add(r.getRoleDesc());
		}
		simpleAuthorizationInfo.setRoles(roleSet);

		// 获取用户权限集
		List<DbPermission> permissionList = userPermissionMapper.findByUserName(userName);
		Set<String> permissionSet = new HashSet<String>();
		for (DbPermission p : permissionList) {
			if(!"".equals(p.getPerms()) || null!=p.getPerms())
			{
			permissionSet.add(p.getPerms());
			}
		}
		simpleAuthorizationInfo.setStringPermissions(permissionSet);
		return simpleAuthorizationInfo;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());

		DbUser user = userMapper.getUserByName(userName);

		if (user == null) {
			throw new UnknownAccountException("用户名或密码错误！");
		}
		if (!password.equals(user.getUser_password())) {
			throw new IncorrectCredentialsException("用户名或密码错误！");
		}
		if (user.getStatus().equals("0")) {
			throw new LockedAccountException("账号已被锁定,请联系管理员！");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
