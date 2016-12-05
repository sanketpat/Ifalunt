package com.ifluant.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
import com.iflaunt.backend.dao.UserDao;
import com.iflaunt.backend.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IflauntApplication.class, WebApplicationContext.class })
@ActiveProfiles("test")
public class TestUserDao {

	private static final String USERNAME = "test@test.com";
	private static final String FIRSTNAME = "TestFirst";
	private static final String LASTNAME = "TestLast";
	private static final String PASSWORD = "test123456";
	private static final String BIO = "This is Bio";
	private static final String BIRTHDATE = "01/01/1993";
	private static final String GENDER = "male";
	private static final String CITY = "Test";

	@Autowired
	private UserDao userDao;

	private User user, dbUser;

	@Before
	public void setup() throws Exception {
		user = new User();
		user.setUserName(USERNAME);
		user.setPassword(PASSWORD);
		user.setGender(GENDER);
		user.setBio(BIO);
		user.setBirthDate(BIRTHDATE);
		user.setFirstName(FIRSTNAME);
		user.setLastName(LASTNAME);
		user.setPhotoName(USERNAME + ".jpg");
		user.setCity(CITY);
		dbUser = userDao.save(user);
	}

	@Test
	public void testSave() {

		assertEquals(user.getUserName(), dbUser.getUserName());
		assertEquals(user.getFirstName(), dbUser.getFirstName());
		assertEquals(user.getLastName(), dbUser.getLastName());
		assertEquals(user.getCity(), dbUser.getCity());
		assertEquals(user.getBio(), dbUser.getBio());
		assertEquals(user.getGender(), dbUser.getGender());
		assertEquals(user.getPassword(), dbUser.getPassword());
		assertEquals(user.getPhotoName(), dbUser.getPhotoName());
		assertEquals(user.getBirthDate(), dbUser.getBirthDate());
	}

	@Test
	public void testFindByUserName() {
		assertEquals(dbUser.getUserName(), userDao.findByUserName(user.getUserName()).getUserName());
	}

	@Test
	public void testFindUserByFirstNameLike() {
		User user2 = new User();
		user2.setUserName("2" + USERNAME);
		user2.setFirstName(FIRSTNAME);
		user2.setPassword(PASSWORD);
		userDao.save(user2);

		List<User> users = userDao.findUserByFirstNameLike("%Test%");
		assertEquals(2, users.size());

		userDao.delete(user2);
	}
	
	@Test
	public void testGetFollowed(){
		List<User> users = userDao.getFollowed(user.getUserName());
		assertEquals(0, users.size());
	}

	@After
	public void cleanUp() {
		userDao.delete(dbUser);
	}

}
