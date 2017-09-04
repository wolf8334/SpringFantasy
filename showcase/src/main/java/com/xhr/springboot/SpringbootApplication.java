package com.xhr.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

//@EnableDiscoveryClient
// @EnableEurekaServer
@Configuration
@ControllerAdvice
@SpringBootApplication
public class SpringbootApplication {

	private static Logger log = LoggerFactory.getLogger(SpringbootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}