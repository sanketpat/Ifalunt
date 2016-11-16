package com.iflaunt.backend.service;

import java.util.List;

import com.iflaunt.backend.model.User;
import com.iflaunt.backend.model.Photo;

public interface PhotoService {
	Photo save(Photo photo);
	
	List<Photo> findByUser(User user);
	
	Photo findByPhotoId(Long photoId);
	
	List<Photo> findAll();
}
