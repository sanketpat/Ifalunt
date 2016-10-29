package com.iflaunt.backend.service;

import com.iflaunt.backend.model.User;

public interface UserService {
	User save(User user);
	
	User findByUserName(String userName);
	


}
