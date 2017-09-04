package com.xhr.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.xhr.springboot.dao.UserDao;
import com.xhr.springboot.entity.User;
import com.xhr.springboot.util.EncryptUtil;

@Component
public class UserService {

	@Autowired
	private UserDao dao;

	public User findByUsername(String username, String password) {
		List<User> list = dao.findByUsername(username);
		if (list != null && list.size() > 0) {
			User user = list.get(0);
			if (user.getPassword().equals(EncryptUtil.string2MD5(password))) {
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public <S extends User> S save(S entity) {
		String password = entity.getPassword();
		password = EncryptUtil.string2MD5(password);
		entity.setPassword(password);
		return dao.save(entity);
	}

	public Iterable<User> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	public <S extends User> Iterable<S> save(Iterable<S> entities) {
		return dao.save(entities);
	}

	public Page<User> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	public User findOne(Integer id) {
		return dao.findOne(id);
	}

	public boolean exists(Integer id) {
		return dao.exists(id);
	}

	public Iterable<User> findAll() {
		return dao.findAll();
	}

	public Iterable<User> findAll(Iterable<Integer> ids) {
		return dao.findAll(ids);
	}

	public long count() {
		return dao.count();
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public void delete(User entity) {
		dao.delete(entity);
	}

	public void delete(Iterable<? extends User> entities) {
		dao.delete(entities);
	}

	public void deleteAll() {
		dao.deleteAll();
	}
}
