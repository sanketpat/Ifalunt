package com.iflaunt.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iflaunt.backend.model.User;
import com.iflaunt.backend.dao.UserDao;
import com.iflaunt.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	public User save (User user) {
		return userDao.save(user);
	}
	
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}
	

}
