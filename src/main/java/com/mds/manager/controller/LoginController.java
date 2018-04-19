package com.mds.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	private Logger Log = Logger.getLogger(LoginController.class);

	/**
	 * 跳转登录
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Model model) {
		return "redirect:/login.jsp";
	}

	/**
	 * 登录
	 * 
	 * @param model
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/checkLogin")
	public String checkLogin(Model model, String username, String password,HttpServletRequest request) {
		Log.info("登录参数：" + username + "," + password);

		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		Subject subject = SecurityUtils.getSubject();
		
		String msg = null;
		try {
			subject.login(token);// 验证角色和权限
			token.setRememberMe(true);
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			msg = e.getMessage();
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			msg = "密码不匹配";
		} catch (NullPointerException e) {
			e.printStackTrace();
			msg = "用户名或密码不能为空";
		}catch (LockedAccountException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		if(msg == null){
            return "redirect:/index.shtml";
        }
		model.addAttribute("msg",msg);
        return "redirect:/login.jsp";
	}
	
	/**
	 * 退出登录
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(Model model,HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		request.getSession().removeAttribute("user");
		Log.info("退出成功");
		return "redirect:/login.jsp";
	}

}
