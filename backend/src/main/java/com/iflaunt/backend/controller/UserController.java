package com.iflaunt.backend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.iflaunt.backend.model.Photo;
import com.iflaunt.backend.model.Post;
import com.iflaunt.backend.model.Relationship;
import com.iflaunt.backend.model.User;
import com.iflaunt.backend.service.PhotoService;
import com.iflaunt.backend.service.RelationshipService;
import com.iflaunt.backend.service.UserService;
import com.ocpsoft.pretty.time.PrettyTime;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RelationshipService relationshipService;

	@Autowired
	private PhotoService photoService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String imageName;

    @RequestMapping(method = RequestMethod.GET)
	public String serviceCheck() {
	    return "User Service is Running.";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User registerUser(@RequestBody User user) {
		if ((userService.findByUserName(user.getUserName())) == null) {
			return userService.save(user);
		} else {
			return null;
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(@RequestBody Map<String, String> json) throws ServletException {

		if (json.get("username") == null || json.get("password") == null) {
			throw new ServletException("Please fill in username and password");
		}

		String userName = json.get("username");
		String password = json.get("password");

		User user = userService.findByUserName(userName);

		if (user == null) {
			return null;
		}

		String pwd = user.getPassword();

		if (!password.equals(pwd)) {
			return null;
		} else {
			return user;
		}
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public User upload(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> it = multipartRequest.getFileNames();
		String userDisplaypicName = multipartRequest.getParameter("username");
		MultipartFile multipartFile = multipartRequest.getFile(it.next());
		String fileNameExt = multipartFile.getOriginalFilename();
		String fileExtention = FilenameUtils.getExtension(fileNameExt);
		String newFileName = userDisplaypicName.trim() + "." + fileExtention.trim();
		String path = new File("src/main/resources/static/images").getAbsolutePath() + "/" + newFileName;
		try {
			multipartFile.transferTo(new File(path));
			System.out.println(path);
			User user = userService.findByUserName(multipartRequest.getParameter("username"));
			user.setPhotoName(newFileName);
			userService.save(user);
			imageName = newFileName;
			return user;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public User updateUser(@RequestBody User user) {
		User userTemp = userService.findByUserName(user.getUserName());
		user.setPassword(userTemp.getPassword());
		user.setCreated(userTemp.getCreated());
		user.setPhotoName(imageName);
		return userService.save(user);
	}

	@RequestMapping(value = "/search/{keyword}/{userName:.+}", method = RequestMethod.GET)
	public Object search(@PathVariable String keyword, @PathVariable String userName) {

		User currentUser = userService.findByUserName(userName);
		if (!keyword.isEmpty()) {
			List<User> obj = userService.findUserByFirstNameLike("%" + keyword + "%");

			Iterator<User> iter = obj.iterator();

			while (iter.hasNext()) {
				User user = iter.next();

				if (currentUser.equals(user)) {
					iter.remove();
				}
				Relationship relationship = relationshipService.isFollowingId(currentUser, user);
				if (relationship != null) {
					iter.remove();
					relationship = null;
				}
			}

			/*
			 * for(User user : obj ) { if (currentUser.equals(user)) {
			 * obj.remove(user); }
			 * 
			 * Relationship relationship =
			 * relationShipService.isFollowingId(currentUser, user);
			 * if(relationship != null) { obj.remove(user); } }
			 */

			return obj;
		}
		return null;
	}

	@RequestMapping(value = "/getUserdetails", method = RequestMethod.POST)
	public User getUser(@RequestBody Map<String, String> json) throws ServletException {
		if (json.get("username") == null) {
			throw new ServletException("Please fill in username and password");
		}

		String userName = json.get("username");
		User user = userService.findByUserName(userName);
		return user;
	}
	
	@RequestMapping(value = "/isProfileImageAvailable/{userName:.+}", method = RequestMethod.GET)
	public boolean isProfileImageAvailable(@PathVariable String userName) {
		
		User user = userService.findByUserName(userName);
		String name=user.getPhotoName();
		
		if( name == null || name.equals(""))
		{
			return false;
		}else
		{
			return true;
		}
	}

	@RequestMapping(value = "/getProfilePicture/{userName:.+}", method = RequestMethod.GET)
	public HttpEntity<byte[]> getSingleProfilePicture(@PathVariable String userName, WebRequest request)
			throws IOException {
		User usr = userService.findByUserName(userName);
		String name = null;
		try {
			name = usr.getPhotoName();

		} catch (Exception e) {
		}

		if (name != null) {
			File photo = new File("src/main/resources/static/images", name);
			if (!photo.exists()) {
				System.out.print("Photo not found");
			}

			if (request.checkNotModified(photo.lastModified()))
				return null;

			byte[] photoFile = Files.readAllBytes(Paths.get(photo.getPath()));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentLength(photoFile.length);
			headers.setLastModified(photo.lastModified());
			return new HttpEntity<byte[]>(photoFile, headers);
		} else {
			return null;
		}

	}

	@RequestMapping(value = "/getPosts/{username:.+}", method = RequestMethod.GET)
	public List<Post> getPosts(@PathVariable String username) {
		List<User> followed = userService.getFollowed(username);
		List<Post> posts = new ArrayList<Post>();
		PrettyTime p = new PrettyTime();

		for (User user : followed) {
			List<Photo> photos = photoService.findByUser(user);

			for (Photo photo : photos) {
				Post post = new Post();
				post.setUserFullName(user.getFirstName() + " " + user.getLastName());
				post.setGender(user.getGender());
				post.setBrand(photo.getBrand());
				post.setImageName(trnasformToHttpEntity(photo.getImageName()));
				post.setAccessoryType(photo.getAccessoryType());
				post.setTime(p.format(photo.getCreated()));
				post.setDateCreated(photo.getCreated());
				posts.add(post);

			}
		}

		Collections.sort(posts);
		return posts;
	}
	
	
	
	
	@RequestMapping(value = "/isFollowed/{username:.+}", method = RequestMethod.GET)
	public boolean isFollowed(@PathVariable String username) {
		List<User> followed = userService.getFollowed(username);
		if(followed.size()==0){
			return true;
			}
		else{
			return false;
		}
		
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/getFollowedUser/{username:.+}", method = RequestMethod.GET)
	public List<User> getFollowedUser(@PathVariable String username) {
		List<User> followed = userService.getFollowed(username);
		Collections.sort(followed);
		return followed;
	}
	

	private HttpEntity<byte[]> trnasformToHttpEntity(String imageName2) {
		try {
			File photoImage = new File("src/main/resources/static/images/" + imageName2);
			if (!photoImage.exists()) {
				System.out.print("Photo not found");
			}
			byte[] photoFile = Files.readAllBytes(Paths.get(photoImage.getPath()));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentLength(photoFile.length);
			HttpEntity<byte[]> httpEntity = new HttpEntity<byte[]>(photoFile, headers);
			return httpEntity;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
