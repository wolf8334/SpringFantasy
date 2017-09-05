package com.xhr.springboot.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.xhr.springboot.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Integer> {

	User findByUsername(String username);
}
