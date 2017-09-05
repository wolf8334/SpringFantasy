package com.xhr.springboot.controller;

import org.apache.log4j.Logger;
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

	@RequestMapping("/save/{username}/{password}/{realname}")
	public String save(@PathVariable String username, @PathVariable String password, @PathVariable String realname) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRealname(realname);
		service.save(user);
		return "已创建用户,序号" + user.getId() + ",用户名" + user.getUsername();
	}

	@RequestMapping("/check/{username}/{password}")
	public String check(@PathVariable String username, @PathVariable String password) {
		String ret = "未查询到相关用户或传入的密码不正确";
		User user = service.findByUsername(username);
		if (user != null) {
			ret = "已查询到用户,序号为" + user.getId();
		}
		return ret;
	}
}
