package com.iflaunt.backend.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.iflaunt.backend.model.User;
import com.iflaunt.backend.dao.UserDao;
import com.iflaunt.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User save (User user) {
		return userDao.save(user);
	}
	
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}
	
	public List<User> findUserByFirstNameLike(@Param("keyword") String keyword) {
		return userDao.findUserByFirstNameLike( keyword);
	}


}
