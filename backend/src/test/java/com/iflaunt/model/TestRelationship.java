package com.iflaunt.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.iflaunt.backend.model.Relationship;
import com.iflaunt.backend.model.User;

@RunWith(JUnit4.class)
public class TestRelationship {

	private User user1, user2;

	private Relationship relationship;
	@Before
	public void setUp() throws Exception {

		relationship = new Relationship();
		
		relationship.setFollowed(user1);
		relationship.setFollower(user2);
	}
	
	@Test
	public void testGetter()
	{
		assertEquals(user1, relationship.getFollowed());
		assertEquals(user2, relationship.getFollower());
		
	}

}
