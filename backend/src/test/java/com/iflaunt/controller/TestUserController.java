package com.iflaunt.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.iflaunt.backend.controller.UserController;
import com.iflaunt.backend.dao.UserDao;
import com.iflaunt.backend.model.User;
import com.iflaunt.backend.service.UserService;

@RunWith(JUnit4.class)
public class TestUserController {
	
	private static final String USERNAME="test@test.com";
	private static final String FIRSTNAME="TestFirst";
	private static final String LASTNAME="TestLast";
	private static final String PASSWORD="test123456";
	private static final String BIO="This is Bio";
	private static final String BIRTHDATE="01/01/1993";
	private static final String GENDER = "male";
	
	@Mock
	private UserService userService;
	
	private User user;
	
	@Mock
	private UserDao userDao;
	
	private UserController controller;
	
	@Before
	public void setup() throws Exception {
		userService = mock(UserService.class);
		userDao= mock(UserDao.class);
		controller = new UserController();
		controller.setUserService(userService);
		user = new User();
		user.setUserName(USERNAME);
		user.setPassword(PASSWORD);
		user.setGender(GENDER);
		user.setBio(BIO);
		user.setBirthDate(BIRTHDATE);
		user.setFirstName(FIRSTNAME);
		user.setLastName(LASTNAME);
		user.setPhotoName(USERNAME+".jpg");
	}
	
	@Test
	public void testRegister()
	{
		when(userService.findByUserName(user.getUserName())).thenReturn(null);
		when(userService.save(user)).thenReturn(user);
		assertEquals(user, controller.registerUser(user));
	}
	
	@Test
	public void testLogin() throws Exception
	{
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("username",USERNAME);
		jsonMap.put("password", PASSWORD);
		
		when(userService.findByUserName(user.getUserName())).thenReturn(user);
		assertEquals(user, controller.login(jsonMap));
	}
	
	@Test
	public void testUpload() 
	{
		when(userService.findByUserName(user.getUserName())).thenReturn(user);
		@SuppressWarnings("unchecked")
		Iterator<String> it = mock(Iterator.class);
		when(it.next()).thenReturn("");
		
		MultipartFile file = mock(MultipartFile.class);
		MultipartHttpServletRequest request = mock(MultipartHttpServletRequest.class);
		when(request.getFile(it.next())).thenReturn(file);
		when(request.getFileNames()).thenReturn(it);
		when(file.getOriginalFilename()).thenReturn("abc.txt");
		when(request.getParameter("username")).thenReturn(USERNAME);
		
		assertEquals(user, controller.upload(request));

	}
	
	@Test
	public void testUpdate(){
		when(userService.save(user)).thenReturn(user);
		when(userService.findByUserName(user.getUserName())).thenReturn(user);
		assertEquals(user, controller.updateUser(user));
	}
	
	@Test
	public void testSearch(){
		List<HashMap<Long,String>> obj = new ArrayList<HashMap<Long,String>>();
		when(userService.findUserByFirstNameLike("ab")).thenReturn(obj);
		assertEquals(obj, controller.search("ab"));
	}
	
	@Test
	public void testGetUser() throws Exception{
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("username",USERNAME);
		when(userService.findByUserName(user.getUserName())).thenReturn(user);
		assertEquals(user, controller.getUser(jsonMap));
	}
	

}
