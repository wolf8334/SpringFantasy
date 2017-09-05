package com.xhr.springboot.core.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.xhr.springboot.service.ShiroDbRealm;

@Configuration
public class ShiroConfig {

	private static Logger log = LoggerFactory.getLogger(ShiroConfig.class);

	@ExceptionHandler(AuthorizationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String handleException(AuthorizationException e, Model model) {
		log.info("AuthorizationException was thrown", e);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", HttpStatus.FORBIDDEN.value());
		map.put("message", "No message available");
		model.addAttribute("errors", map);

		return "error";
	}

	/**
	 * 加载Shiro的EhCache管理器
	 */
	public EhCacheManager getCacheManager() {
		log.info("加载EhCacheManager");
		EhCacheManager ecm = new EhCacheManager();
		ecm.setCacheManagerConfigFile("classpath:conf/ehcache-shiro.xml");
		return ecm;

	}

	@Bean
	public Realm realm() {
		log.info("加载realm");
		ShiroDbRealm realm = new ShiroDbRealm();
		realm.setCachingEnabled(true);
		return realm;
	}

	@Bean
	public DefaultWebSecurityManager getSecurityManager() {
		log.info("加载DefaultWebSecurityManager");
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(realm());
		dwsm.setCacheManager(getCacheManager());
		SecurityUtils.setSecurityManager(dwsm);
		return dwsm;
	}

	@Bean
	public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
		log.info("加载Filter");
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		chainDefinition.addPathDefinition("/login", "authc");
		chainDefinition.addPathDefinition("/loginajax/**", "anon");
		chainDefinition.addPathDefinition("/**", "user");
		chainDefinition.addPathDefinition("/logout", "logout");
		return chainDefinition;
	}

	@ModelAttribute(name = "subject")
	public Subject subject() {
		log.info("加载subject");
		return SecurityUtils.getSubject();
	}

	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
		filterFactoryBean.setSecurityManager(SecurityUtils.getSecurityManager());
		filterFactoryBean.setLoginUrl("/login");
		filterFactoryBean.setSuccessUrl("/hello");
		filterFactoryBean.setUnauthorizedUrl("/login");
		filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());
		return filterFactoryBean;
	}

	@Bean
	public FilterRegistrationBean filterRegistration() {
		log.info("加载shiroFilter过滤器");
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new DelegatingFilterProxy());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("targetFilterLifecycle", "true");
		registration.setName("shiroFilter");
		registration.setOrder(1);
		return registration;
	}
}
