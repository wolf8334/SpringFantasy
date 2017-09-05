package com.xhr.springconfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ParamController {

	@Autowired
	@Value("${appName}")
	private String appName;

	@RequestMapping("/appName")
	public String appName() {
		return this.appName;
	}

}
