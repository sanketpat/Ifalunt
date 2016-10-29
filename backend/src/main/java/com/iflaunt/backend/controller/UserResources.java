package com.iflaunt.backend.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.iflaunt.backend.model.User;
import com.iflaunt.backend.service.UserService;

@RestController
@RequestMapping("/rest")
public class UserResources {
	
	private String imageName;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/users")
	public String loginSuccess(){
		return "Login Successful!";
	}
	
	@RequestMapping(value="/user/userName", method=RequestMethod.POST)
	public User findByUserName(@RequestBody String userName) {
		return userService.findByUserName(userName);
	}
	
	@RequestMapping(value="/photo/upload", method=RequestMethod.POST)
	public String upload (HttpServletResponse reponse, HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> it = multipartRequest.getFileNames();
		MultipartFile multipartFile = multipartRequest.getFile(it.next());
		String fileName = multipartFile.getOriginalFilename();
		imageName = fileName;
		
		String path= new File("src/main/resources/static/images").getAbsolutePath()+"/"+fileName;
		
		try {
			multipartFile.transferTo(new File(path));
			System.out.println(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "Upload Image Success!";
	}
	
	
	@RequestMapping(value="/user/update", method=RequestMethod.POST)
	public User updateUser(@RequestBody User user) {
		return userService.save(user);
	}
}
