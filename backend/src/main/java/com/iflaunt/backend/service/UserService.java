package com.iflaunt.backend.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.iflaunt.backend.model.User;

public interface UserService {
	User save(User user);
	
	User findByUserName(String userName);
	
	List<User> findUserByFirstNameLike(@Param("keyword") String keyword);


}
