package com.iflaunt.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import com.iflaunt.backend.controller.RelationshipController;
import com.iflaunt.backend.model.Relationship;
import com.iflaunt.backend.model.User;
import com.iflaunt.backend.service.RelationshipService;
import com.iflaunt.backend.service.UserService;



@RunWith(JUnit4.class)
public class TestRelationshipController {
	
	@Mock
	private UserService userService;
	
	private User user1, user2, user3;
	private Relationship rel;
	@Mock
	private RelationshipService relationshipService;
	
	private RelationshipController controller;
	
	@Before
	public void setup() throws Exception {
	
		List<Relationship> relList=  new ArrayList<Relationship>();
		user1 = new User();
		user1.setUserName("a@a.com");
		user1.setFollowing(relList);
		user2= new User();
		user2.setUserName("b@b.com");
		
		user3= new User();
		user3.setUserName("c@c.com");
		
		rel = new Relationship();
		rel.setFollowed(user1);
		rel.setFollower(user2);
		
		
		userService= mock(UserService.class);
		relationshipService = mock(RelationshipService.class);
		controller= new RelationshipController(userService, relationshipService);
		when(userService.findByUserName("a@a.com")).thenReturn(user1);
		when(userService.findByUserName("b@b.com")).thenReturn(user2);
		when(userService.findByUserName("c@c.com")).thenReturn(user3);
		
		
		
	}
	
	
	
	@Test
	public void testIsFollowingNegative()
	{
		HashMap<String, Boolean> hashmap= new HashMap<String, Boolean>();
		hashmap.put("following", true);
		assertEquals(hashmap, controller.isFollowing(user1.getUserName(), user2.getUserName()));
	}
	
	@Test
	public void testFollowAction()
	{
		
		HashMap<String, String> hashmap= new HashMap<String, String>();
		hashmap.put("label", "UNFOLLOW");
		when(relationshipService.isFollowingId(user1, user2)).thenReturn(rel);
		when(relationshipService.save(rel)).thenReturn(rel);
		when(userService.save(user1)).thenReturn(user1);
		assertEquals(hashmap, controller.followAction(user1.getUserName(), user2.getUserName()));
	}
	
	
	@Test
	public void testIsFollowingPositive()
	{
		HashMap<String, Boolean> hashmap= new HashMap<String, Boolean>();
		hashmap.put("following", false);
		when(relationshipService.isFollowingId(user1, user2)).thenReturn(rel);
		assertEquals(hashmap, controller.isFollowing(user1.getUserName(), user2.getUserName()));
		
	}
	
	@Test
	public void testUnfollow()
	{
		HashMap<String, Boolean> hashmap= new HashMap<String, Boolean>();
		hashmap.put("following", false);
		when(relationshipService.isFollowingId(user1, user2)).thenReturn(rel);
		
		assertEquals(true, controller.unfollowAction(user1.getUserName(), user2.getUserName()));
	}

}
