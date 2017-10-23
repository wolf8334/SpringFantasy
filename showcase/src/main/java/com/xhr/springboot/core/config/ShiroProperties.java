package com.xhr.springboot.core.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "shiroconfig")
public class ShiroProperties {

	private String ehCacheConfig;
	private String loginUrl;
	private String logout;
	private String unauthorizedUrl;
	private List<String> annos = new ArrayList<String>();
	private List<String> users = new ArrayList<String>();

	public String getEhCacheConfig() {
		return ehCacheConfig;
	}

	public void setEhCacheConfig(String ehCacheConfig) {
		this.ehCacheConfig = ehCacheConfig;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogout() {
		return logout;
	}

	public void setLogout(String logout) {
		this.logout = logout;
	}

	public String getUnauthorizedUrl() {
		return unauthorizedUrl;
	}

	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}

	public List<String> getAnnos() {
		return annos;
	}

	public void setAnnos(List<String> annos) {
		this.annos = annos;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
}
