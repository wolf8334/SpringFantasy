package com.xhr.springconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringconfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringconfigApplication.class, args);
	}
}