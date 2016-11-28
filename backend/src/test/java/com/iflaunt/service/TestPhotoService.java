package com.iflaunt.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import com.iflaunt.backend.dao.PhotoDao;
import com.iflaunt.backend.model.Photo;
import com.iflaunt.backend.model.User;
import com.iflaunt.backend.service.impl.PhotoServiceImpl;

@RunWith(JUnit4.class)
public class TestPhotoService {
	
	@Mock
	private PhotoDao photoDao;
	
	private PhotoServiceImpl photoService;
	private Photo photo;
	private List<Photo> photos;
	private User user;
	
	@Before
	public void setup() throws Exception {
		photoDao = mock(PhotoDao.class);
		photo= new Photo();
		photo.setPhotoId(1L);
		user = new User();
		photoService = new PhotoServiceImpl();
		photoService.setPhotoDao(photoDao);
	}
	
	@Test
	public void testSave()
	{
		when(photoDao.save(photo)).thenReturn(photo);
		assertEquals(photo, photoService.save(photo));
	}
	
	@Test
	public void testFindByUser()
	{
		when(photoDao.findByUser(user)).thenReturn(photos);
		assertEquals(photos, photoService.findByUser(user));
	}

	@Test
	public void testFindByPhotoId()
	{
		when(photoDao.findByPhotoId(photo.getPhotoId())).thenReturn(photo);
		assertEquals(photo, photoService.findByPhotoId(photo.getPhotoId()));
	}

	
	@Test
	public void testFindAll()
	{
		when(photoDao.findAll()).thenReturn(photos);
		assertEquals(photos, photoService.findAll());
	}
	
	
	@Test
	public void testCountsBybrand()
	{
		when(photoDao.countsBybrand()).thenReturn(photos);
		assertEquals(photos, photoService.countsBybrand());
	}
	
	
	
	
}
