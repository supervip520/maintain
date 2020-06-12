package com.maint.login;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.along.entity.DbUser;
import com.common.util.MD5Utils;
import com.common.util.ResponseBo;
import com.maint.permission.service.PermissionService;
import com.maint.user.mapper.IDbUserMapper;
import com.maint.user.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userservice;
	
	@Autowired
	IDbUserMapper userMapper;
	
	@Autowired
	PermissionService menuService;

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	/**
	 * 用户登陆
	 */
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseBo loginPage(String username, String password, Boolean rememberMe, HttpServletRequest request, HttpServletResponse response) {
			password = MD5Utils.encrypt(password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		if(rememberMe)
		{
			token.setRememberMe(true);
		}
		
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return ResponseBo.ok();
		} catch (UnknownAccountException e) {
			return ResponseBo.error(e.getMessage());
		} catch (IncorrectCredentialsException e) {
			return ResponseBo.error(e.getMessage());
		} catch (LockedAccountException e) {
			return ResponseBo.error(e.getMessage());
		} catch (AuthenticationException e) {
			return ResponseBo.error("认证失败！");
		}
	}

	/**
	 * 首页
	 */
	@RequestMapping(value = "index")
	public String index(HttpSession session) {
		DbUser user = (DbUser) SecurityUtils.getSubject().getPrincipal();
		session.setAttribute("user", user);
		return "index";
	}

	/**
	 * 用户登出
	 */
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) throws Exception {
		session.removeAttribute("user_account");
		session.invalidate();
		return "redirect:/login";
	}
	
	/**
	 * 菜单
	 */
	@RequestMapping(value = "/menu",method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Map<Object,Object>> getMenu(){
         ArrayList<Map<Object, Object>> menus = menuService.queryAll();
        return menus;
    }
	
	
	@GetMapping("/403")
	public String forbid() {
		return "403";
	}
	
	

}
