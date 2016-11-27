package com.iflaunt.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iflaunt.backend.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
public class TestUser {

	private User user;
    
    @Before
	public void setUp() throws Exception{
		user = new User();
		user.setFirstName("test");
		user.setLastName("test");
		user.setUserName("a@ucn.com");
		user.setPassword("test12");
		user.setBio("i am test");
		user.setBirthDate("11/20/2012");
		user.setGender("M");
		user.setPhotoName("1.jpg");
	}
 
    @Test
    public void testGetterMethods() throws Exception{
    	boolean exceptionCaught = false;
    	try{
    		
    		assertEquals(user.getFirstName(), "test");
    		assertEquals(user.getLastName(), "test");
    		assertEquals(user.getUserName(), "a@ucn.com");
    		assertEquals(user.getPassword(), "test12");
    		assertEquals(user.getBio(), "i am test");
    		assertEquals(user.getBirthDate(), "11/20/2012");
    		assertEquals(user.getGender(), "M");
    		assertEquals(user.getPhotoName(), "1.jpg");

    	}catch(Exception e){
    		exceptionCaught = true;
    	}
    	assertFalse(exceptionCaught);
    }
  
}