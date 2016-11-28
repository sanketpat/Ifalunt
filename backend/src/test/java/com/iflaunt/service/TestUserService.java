package com.iflaunt.service;

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

import com.iflaunt.backend.dao.UserDao;
import com.iflaunt.backend.model.User;
import com.iflaunt.backend.service.impl.UserServiceImpl;

@RunWith(JUnit4.class)
public class TestUserService {
	
	@Mock
	private UserDao userDao;
	
	private UserServiceImpl userService;
	
	private User user;
	
	private List<HashMap<Long,String>> userDetails;;
	
	@Before
	public void setup() throws Exception {
		
		userDetails = new ArrayList<HashMap<Long, String>>();
		user = new User();
		userDao=  mock(UserDao.class);
		user.setUserName("test@test.com");
		userService = new UserServiceImpl();
		userService.setUserDao(userDao);
	}

	@Test
	public void testSave()
	{
		when(userDao.save(user)).thenReturn(user);
		assertEquals(user, userService.save(user));
	}
	
	
	@Test
	public void testFindByUserName()
	{
		when(userDao.findByUserName(user.getUserName())).thenReturn(user);
		assertEquals(user, userService.findByUserName(user.getUserName()));
	}
	
	@Test
	public void testFindUserByFirstNameLike()
	{
		when(userDao.findUserByFirstNameLike("aa")).thenReturn(userDetails);
		assertEquals(user, userService.findUserByFirstNameLike(user.getUserName()));
	}
	
	
}
