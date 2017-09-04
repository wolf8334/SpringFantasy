package com.xhr.springboot.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xhr.springboot.entity.User;
import com.xhr.springboot.service.UserService;

@RestController
public class HelloController {

	private final Logger logger = Logger.getLogger(HelloController.class);

	@Autowired
	private UserService service;

	@Autowired
	private DiscoveryClient client;

	@RequestMapping("/hello")
	public String index() {
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/hello,host " + instance.getHost() + " ,port " + instance.getPort() + " ,service id " + instance.getServiceId());
		return "Hello";
	}

	@RequestMapping("/login")
	public String login() {
		logger.info("login " + SecurityUtils.getSubject().getPrincipal());
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/login,host " + instance.getHost() + " ,port " + instance.getPort() + " ,service id " + instance.getServiceId());
		return "login";
	}

	@RequestMapping("/loginp")
	public String loginp() {
		logger.info("loginp " + SecurityUtils.getSubject().getPrincipal());
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/loginp,host " + instance.getHost() + " ,port " + instance.getPort() + " ,service id " + instance.getServiceId());
		return "loginp";
	}

	@RequestMapping("/save/{username}/{password}")
	public String save(@PathVariable String username, @PathVariable String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		service.save(user);
		return "已创建用户,序号" + user.getId() + ",用户名" + user.getUsername();
	}

	@RequestMapping("/check/{username}/{password}")
	public String check(@PathVariable String username, @PathVariable String password) {
		String ret = "未查询到相关用户或传入的密码不正确";
		User user = service.findByUsername(username, password);
		if (user != null) {
			ret = "已查询到用户,序号为" + user.getId();
		}
		return ret;
	}
}
