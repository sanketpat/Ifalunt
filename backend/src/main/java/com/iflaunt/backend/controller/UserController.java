package com.iflaunt.backend.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.iflaunt.backend.model.User;
import com.iflaunt.backend.service.UserService;

@RestController
@RequestMapping ("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User registerUser(@RequestBody User user) {
		if ((userService.findByUserName(user.getUserName())) == null) {
			return userService.save(user);
		}
		else{
			return null;
		}
		
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public User login(@RequestBody Map<String, String> json) throws ServletException {

		if (json.get("username") == null || json.get("password") == null) {
			throw new ServletException("Please fill in username and password");
		}

		String userName = json.get("username");
		String password = json.get("password");

		User user = userService.findByUserName(userName);

		if (user == null) {
			throw new ServletException("User name not found.");
		}

		String pwd = user.getPassword();

		if (!password.equals(pwd)) {
			return null;
		} else {
			return user;
		}
	}

	@RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile) {
		try {
			String filename = uploadfile.getOriginalFilename();
			String imageName = filename;

			String path = new File("src/main/resources/static/images").getAbsolutePath() + "/" + filename;

			uploadfile.transferTo(new File(path));
			System.out.println(path);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
