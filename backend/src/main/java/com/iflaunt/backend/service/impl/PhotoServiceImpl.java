package com.iflaunt.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iflaunt.backend.model.User;
import com.iflaunt.backend.dao.PhotoDao;
import com.iflaunt.backend.model.Photo;
import com.iflaunt.backend.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService{
	
	@Autowired
	private PhotoDao photoDao;
	
	public Photo save(Photo photo) {
		return photoDao.save(photo);
	}
	
	public List<Photo> findByUser(User user) {
		return photoDao.findByUser(user);
	}
	
	public Photo findByPhotoId(Long photoId) {
		return photoDao.findByPhotoId(photoId);
	}
	
	public List<Photo> findAll() {
		return photoDao.findAll();
	}
}
