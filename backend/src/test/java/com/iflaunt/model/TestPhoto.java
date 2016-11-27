package com.iflaunt.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iflaunt.backend.model.Photo;
import com.iflaunt.backend.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestPhoto {
	
	private Photo photo;

	@Mock
	private User user;

	
	
	@Before
	public void setUp() throws Exception{
		user= Mockito.mock(User.class);
		photo = new Photo();
		photo.setAccessoryType("Shirt");
		photo.setBrand("Tommy");
		photo.setDescription("New Shirt");
		photo.setPhotoName("abc.jpg");
		photo.setUser(user);
	}
	
	 @Test
	    public void testGetterMethods() throws Exception{
	    	boolean exceptionCaught = false;
	    	try{
	    		
	    		assertEquals(photo.getAccessoryType(), "Shirt");
	    		assertEquals(photo.getBrand(), "Tommy");
	    		assertEquals(photo.getDescription(), "New Shirt");
	    		assertEquals(photo.getPhotoName(), "abc.jpg");
	    		assertEquals(photo.getUser(), user);
	    	}catch(Exception e){
	    		exceptionCaught = true;
	    	}
	    	assertFalse(exceptionCaught);
	    }
	  

}
