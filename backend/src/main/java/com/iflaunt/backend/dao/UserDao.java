package com.iflaunt.backend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iflaunt.backend.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long>{
	User save(User user);
	
	User findByUserName (String userName);
	

}
