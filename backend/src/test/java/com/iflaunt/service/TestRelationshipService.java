package com.iflaunt.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import com.iflaunt.backend.dao.RelationshipDao;
import com.iflaunt.backend.model.Relationship;
import com.iflaunt.backend.model.User;
import com.iflaunt.backend.service.impl.RelationshipServiceImpl;

@RunWith(JUnit4.class)
public class TestRelationshipService {
	
	@Mock
	private RelationshipDao relationshipDao;
	
	private Relationship relation;
	private User user1, user2;
	
	private RelationshipServiceImpl relationshipService;
	
	
	@Before
	public void setup() throws Exception {
		
		relation = new Relationship();
		user1 = new User();
		user2 = new User();
		relation.setFollowed(user1);
		relation.setFollower(user2);
		relationshipDao = mock(RelationshipDao.class);
		relationshipService = new RelationshipServiceImpl();
		relationshipService.setRelationshipDao(relationshipDao);
		
	}

	@Test
	public void testSave()
	{
		when(relationshipDao.save(relation)).thenReturn(relation);
		assertEquals(relation, relationshipService.save(relation));
	}
	
	@Test
	public void testisFollowingId()
	{
		when(relationshipDao.isFollowingId(user1, user2)).thenReturn(relation);
		
		assertEquals(relation, relationshipService.isFollowingId(user1, user2));
		
	}
	
	

}
