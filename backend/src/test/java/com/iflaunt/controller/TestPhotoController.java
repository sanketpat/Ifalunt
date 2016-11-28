package com.iflaunt.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	private List<Photo> photos;
	@Before
	public void setup() throws Exception {
		
		photos = new ArrayList<Photo>();
		controller = new PhotoController();
		user = mock(User.class);
		when(user.getUserName()).thenReturn("a@a.com");
		
		photo = new Photo();
		photo.setUser(user);
		photo.setPhotoId(5L);
		
		photoservice = mock(PhotoService.class);
		controller.setPhotoService(photoservice);
		when(photoservice.save(photo)).thenReturn(photo);
		when(photoservice.findByUser(user)).thenReturn(new ArrayList<Photo>());
		
		userService = mock(UserService.class);
		controller.setUserService(userService);
		when(userService.findByUserName(user.getUserName())).thenReturn(user);
	}

	@Test
	public void testPhotoAdd() {
		assertEquals(photo, controller.addPhoto(photo));
	}

	@Test
	public void testUpload() {
		@SuppressWarnings("unchecked")
		Iterator<String> it = mock(Iterator.class);
		when(it.next()).thenReturn("");

		MultipartFile file = mock(MultipartFile.class);
		MultipartHttpServletRequest request = mock(MultipartHttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		when(request.getFile(it.next())).thenReturn(file);
		when(request.getFileNames()).thenReturn(it);
		when(file.getOriginalFilename()).thenReturn("");

		try {
			controller.upload(response, request);
		} catch (Exception ex) {
			fail();
		}
	}

	@Test
	public void testGetPhotosByUser() {
		when(photoservice.findByUser(user)).thenReturn(photos);
		assertEquals(photos, controller.getPhotosByUser(user));
	}

	@Test
	public void testGetPhotoByPhotoId() {
		when(photoservice.findByPhotoId(photo.getPhotoId())).thenReturn(photo);
		assertEquals(photo, controller.getPhotoByPhotoId(photo.getPhotoId()));
	}
	
	@Test
	public void testGetBrands() {
		when(photoservice.countsBybrand()).thenReturn(photos);
		assertEquals(photos, controller.getBrands());
	}
	
	@Test
	public void testGetAllPhotos() {
		when(photoservice.findAll()).thenReturn(photos);
		assertEquals(photos, controller.getAllPhotos());
	}
}
