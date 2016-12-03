package com.iflaunt.backend.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iflaunt.backend.model.Relationship;
import com.iflaunt.backend.model.User;

@Repository
public interface RelationshipDao extends CrudRepository<Relationship, Long> {

	@Query("SELECT DISTINCT r FROM Relationship r WHERE follower = :username AND followed = :profile")
	Relationship isFollowingId(@Param("username") User userName, @Param("profile") User profileUserName);

}
