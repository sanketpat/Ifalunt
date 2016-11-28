package com.iflaunt.backend.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.iflaunt.backend.model.Photo;
import com.iflaunt.backend.service.PhotoService;
import com.iflaunt.backend.service.UserService;
import com.iflaunt.backend.model.User;

@RestController
@RequestMapping("/photo")
public class PhotoController {

	private String imageName;

	@Autowired
	private PhotoService photoService;

	@Autowired
	private UserService userService;

	@RequestMapping("/allPhotos")
	public List<Photo> getAllPhotos() {
		return photoService.findAll();
	}


	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(HttpServletResponse reponse, HttpServletRequest request) {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> it = multipartRequest.getFileNames();
		MultipartFile multipartFile = multipartRequest.getFile(it.next());
		String fileName = multipartFile.getOriginalFilename();
		imageName = fileName;
		String path = new File("src/main/resources/static/images").getAbsolutePath() + "/" + fileName;

		try {
			multipartFile.transferTo(new File(path));
			System.out.println(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Photo addPhoto(@RequestBody Photo photo) {
		photo.setUser(userService.findByUserName(photo.getUser().getUserName()));
		photo.setImageName(imageName);
		return photoService.save(photo);
	}

	public PhotoService getPhotoService() {
		return photoService;
	}

	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/userPhotos", method = RequestMethod.POST)
	public List<Photo> getPhotosByUser(@RequestBody User user) {

		return photoService.findByUser(user);
	}

	@RequestMapping(value = "/photo/photoId", method = RequestMethod.POST)
	public Photo getPhotoByPhotoId(@RequestBody Long photoId) {
		return photoService.findByPhotoId(photoId);
	}

	@RequestMapping(value = "/getBrands", method = RequestMethod.POST)
	public List<Photo> getBrands() {
		return photoService.countsBybrand();
	}

	@RequestMapping(value = "/photo/update", method = RequestMethod.POST)
	public void updatePhoto(@RequestBody Photo photo) {
		Photo currentPhoto = photoService.findByPhotoId(photo.getPhotoId());
		photoService.save(currentPhoto);
	}
}
