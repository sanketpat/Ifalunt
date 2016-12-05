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
import com.iflaunt.backend.dao.PhotoDao;
import com.iflaunt.backend.dao.UserDao;
import com.iflaunt.backend.model.Photo;
import com.iflaunt.backend.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IflauntApplication.class, WebApplicationContext.class })
@ActiveProfiles("test")
public class TestPhotoDao {
	
	
	private static final String ACCESSORYTYPE = "shirt";
	private static final String BRAND = "Levis";
	private static final String IMAGE_NAME="abc.jpg";
	
	private User user;
	private Photo photo, dbPhoto;
	
	@Autowired
	private PhotoDao photoDao;
	
	@Autowired
	private UserDao userDao;

	@Before
	public void setup() throws Exception {
		
		user= new User();
		user.setUserName("testphotouser@uncc.edu");
		user.setPassword("testphotouser");
		user = userDao.save(user);
		photo = new Photo();
		
		photo.setUser(user);
		photo.setAccessoryType(ACCESSORYTYPE);
		photo.setBrand(BRAND);
		photo.setImageName(IMAGE_NAME);

		dbPhoto= photoDao.save(photo);
		
	}
	
	@Test
	public void testSave(){
		assertEquals(photo.getUser().getUserName(), dbPhoto.getUser().getUserName());
		assertEquals(photo.getAccessoryType(), dbPhoto.getAccessoryType());
		assertEquals(photo.getBrand(), dbPhoto.getBrand());
	}
	
	@Test
	public void testFindByUser()
	{
		Photo photo2= new Photo();
		photo2.setUser(user);
		photo2.setAccessoryType(ACCESSORYTYPE);
		photo2.setBrand(BRAND);
		photo2=photoDao.save(photo2);
		List<Photo> photoByUser=photoDao.findByUser(user);
		assertEquals(2, photoByUser.size());
		photoDao.delete(photo2);
	}
	
	@Test
	public void testFindByPhotoId()
	{
		Photo photoById= photoDao.findByPhotoId(dbPhoto.getPhotoId());
		
		assertEquals(dbPhoto.getUser().getUserName(), photoById.getUser().getUserName());
		assertEquals(dbPhoto.getAccessoryType(), photoById.getAccessoryType());
		assertEquals(dbPhoto.getBrand(), photoById.getBrand());
	}
	
	@Test
	public void testFindAll()
	{
		assertEquals(1, photoDao.findAll().size());
	}
	
	@Test
	public void testCounteByBrand()
	{
		assertEquals(0, photoDao.countsBybrand(user.getUserName()).size());
	}
	
	
	@After
	public void cleanUp(){
		photoDao. delete(dbPhoto);
		userDao.delete(user);
	}
	

}
