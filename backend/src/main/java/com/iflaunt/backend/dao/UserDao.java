package com.iflaunt.backend.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iflaunt.backend.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long>{
	@SuppressWarnings("unchecked")
	User save(User user);
	
	User findByUserName (String userName);

	List<User> findUserByFirstNameLike(String keyword);
	
	List<User> getFollowed(@Param("username") String userName );
}
