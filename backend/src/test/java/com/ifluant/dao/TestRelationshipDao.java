package com.ifluant.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.iflaunt.backend.IflauntApplication;
import com.iflaunt.backend.dao.RelationshipDao;
import com.iflaunt.backend.dao.UserDao;
import com.iflaunt.backend.model.Relationship;
import com.iflaunt.backend.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IflauntApplication.class, WebApplicationContext.class })
@ActiveProfiles("test")
public class TestRelationshipDao {

	private User user1, user2;

	private Relationship relationship, dbRelationship;
	@Autowired
	private RelationshipDao relDao;

	@Autowired
	private UserDao userDao;

	@Before
	public void setup() throws Exception {
		user1 = new User();
		user2 = new User();
		user1.setUserName("a@a.com");
		user1.setPassword("aaaaaaa");
		user2.setUserName("b@b.com");
		user2.setPassword("bbbbbbb");

		user1 = userDao.save(user1);
		user2 = userDao.save(user2);

		relationship = new Relationship();
		relationship.setFollowed(user1);
		relationship.setFollower(user2);
		dbRelationship = relDao.save(relationship);
	}

	@Test
	public void testSave() {
		assertEquals(relationship.getFollowed().getUserName(), dbRelationship.getFollowed().getUserName());
		assertEquals(relationship.getFollower().getUserName(), dbRelationship.getFollower().getUserName());
	}

	@Test
	public void testIsFollowing() {
		Relationship isFollowing = relDao.isFollowingId(user2, user1);

		assertEquals(relationship.getFollowed().getUserName(), isFollowing.getFollowed().getUserName());
		assertEquals(relationship.getFollower().getUserName(), isFollowing.getFollower().getUserName());
	}

	@After
	public void cleanUp() {
		relDao.delete(relationship);
		userDao.delete(user1);
		userDao.delete(user2);

	}

}
