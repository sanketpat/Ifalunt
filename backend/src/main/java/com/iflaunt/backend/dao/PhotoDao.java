package com.iflaunt.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iflaunt.backend.model.User;
import com.iflaunt.backend.model.Photo;

@Repository
public interface PhotoDao extends CrudRepository<Photo, Long>{
	
	@SuppressWarnings("unchecked")
	Photo save(Photo photo);
	
	List<Photo> findByUser(User user);
	
	Photo findByPhotoId(Long photoId);
	
	List<Photo> findAll();
	@Query("select brand from Photo p group by brand order by count(p) desc")
	List<Photo> countsBybrand();
}