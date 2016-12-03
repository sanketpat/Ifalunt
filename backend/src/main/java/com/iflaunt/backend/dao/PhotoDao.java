package com.iflaunt.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iflaunt.backend.model.User;
import com.iflaunt.backend.model.Photo;

@Repository
public interface PhotoDao extends CrudRepository<Photo, Long> {

	@SuppressWarnings("unchecked")
	Photo save(Photo photo);

	List<Photo> findByUser(User user);

	Photo findByPhotoId(Long photoId);

	List<Photo> findAll();

	@Query("select brand from Photo p where p.user in(select u.userName from User u where u.city in(select uu.city from User uu where uu.userName=:Userparam)) group by p.brand order by count(p) desc")
	List<Photo> countsBybrand(@Param("Userparam") String User);
}