package com.iflaunt.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.HttpEntity;

import com.iflaunt.backend.model.Post;

@RunWith(JUnit4.class)
public class TestPost {

	private Post post;
	private String userFullName = "A B";
	private String gender = "M";
	private String accessoryType = "AT";
	private String brand = "BD";
	private String time = "5 minutes ago";
	private HttpEntity<byte[]> image;
	private Date date;

	@Before
	public void setUp() throws Exception {
		
		post= new Post();
		post.setUserFullName(userFullName);
		post.setGender(gender);
		post.setAccessoryType(accessoryType);
		post.setBrand(brand);
		post.setTime(time);
		post.setImageName(image);
		post.setDateCreated(date);
		
	}
	
	@Test
	public void testGetter()
	{
		assertEquals(userFullName, post.getUserFullName());
		assertEquals(gender, post.getGender());
		assertEquals(accessoryType, post.getAccessoryType());
		assertEquals(brand, post.getBrand());
		assertEquals(time, post.getTime());
		assertEquals(image, post.getImage());
		assertEquals(date, post.getDateCreated());
		
		
	}

}
