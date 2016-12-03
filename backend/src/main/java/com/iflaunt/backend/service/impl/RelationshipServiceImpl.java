package com.iflaunt.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iflaunt.backend.dao.RelationshipDao;
import com.iflaunt.backend.model.Relationship;
import com.iflaunt.backend.model.User;
import com.iflaunt.backend.service.RelationshipService;

@Service
public class RelationshipServiceImpl implements RelationshipService {

	@Autowired
	private RelationshipDao relationshipDao;

	@Override
	public Relationship isFollowingId(User userName, User profileUserName) {
		return relationshipDao.isFollowingId(userName, profileUserName);
	}

	@Override
	public Relationship save(Relationship relationship) {
		return relationshipDao.save(relationship);
	}

}
