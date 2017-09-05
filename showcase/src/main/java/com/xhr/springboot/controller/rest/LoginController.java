package com.xhr.springboot.controller.rest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	private final Logger log = Logger.getLogger(LoginController.class);

	@RequestMapping("/loginajax/{username}/{password}")
	public String loginajax(@PathVariable String username, @PathVariable String password) {
		log.info("loginajax " + SecurityUtils.getSubject().getPrincipal());
		String retMsg = "";
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				currentUser.login(token);
				retMsg = "用户登录成功 " + token.getUsername();
			} catch (UnknownAccountException ex) {
				retMsg = "账号错误";
			} catch (IncorrectCredentialsException ex) {
				retMsg = "密码错误";
			} catch (LockedAccountException ex) {
				retMsg = "账号已被锁定，请与系统管理员联系";
			} catch (AuthenticationException ex) {
				retMsg = "您没有授权!";
			}
			log.info(retMsg);
		}
		return retMsg;
	}

	@RequestMapping("/login")
	public String login() {
		log.info("login " + SecurityUtils.getSubject().getPrincipal());
		return "请登录";
	}
}
