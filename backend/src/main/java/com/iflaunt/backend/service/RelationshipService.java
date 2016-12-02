package com.iflaunt.backend.service;

import com.iflaunt.backend.model.Relationship;
import com.iflaunt.backend.model.User;

public interface RelationshipService {
	
	Relationship isFollowingId(User followed, User following);
	Relationship save(Relationship relationship);

}
