package com.iflaunt.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.iflaunt.backend.controller.PhotoController;
import com.iflaunt.backend.dao.PhotoDao;
import com.iflaunt.backend.model.Photo;
import com.iflaunt.backend.model.User;
import com.iflaunt.backend.service.PhotoService;
import com.iflaunt.backend.service.UserService;

@RunWith(JUnit4.class)
public class TestPhotoController {
	
	private Photo photo;
	
	@Mock
	private PhotoService photoservice;
	
	@Mock
	@Autowired
	private UserService userService;
	

	private User user;
	
	@Mock
	private PhotoDao photoDao;
	private PhotoController controller;
	
	@Before
    public void setup() throws Exception {
		
		controller = new PhotoController();
		
		 user = mock(User.class);
		 when(user.getUserName()).thenReturn("a@a.com");
        
		 photo= new Photo();
        
        photo.setUser(user);
        photoservice =mock(PhotoService.class);
        userService = mock(UserService.class);
        controller.setPhotoService(photoservice);
		controller.setUserService(userService);
        when(photoservice.save(photo)).thenReturn(photo);
    	when(photoservice.findByUser(user)).thenReturn(new ArrayList<Photo>());
    	when(userService.findByUserName(user.getUserName())).thenReturn(user);
    
    }
	    
	@Test
	public void testPhtoAdd(){
		  	
		assertEquals(photo, controller.addPhoto(photo));
		
	}

}
