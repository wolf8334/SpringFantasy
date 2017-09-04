package com.xhr.springboot.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.xhr.springboot.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Integer> {

	List<User> findByUsername(String username);
}
