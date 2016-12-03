package com.iflaunt.backend.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iflaunt.backend.model.Relationship;
import com.iflaunt.backend.model.User;
import com.iflaunt.backend.service.RelationshipService;
import com.iflaunt.backend.service.UserService;

@RestController
@RequestMapping("/relationship/{username:.+}")
public class RelationshipController {

	private final UserService userService;

	private final RelationshipService relationshipService;

	@RequestMapping(value = "/isfollowing/{profileUserName:.+}", method = RequestMethod.GET)
	public HashMap<String, Boolean> isFollowing(@PathVariable String username, @PathVariable String profileUserName) {
		HashMap<String, Boolean> map = new LinkedHashMap<>();
		User followed = userService.findByUserName(username);
		User following = userService.findByUserName(profileUserName);

		Relationship relationship = relationshipService.isFollowingId(followed, following);
		map.put("following", relationship == null);
		return map;
	}

	@RequestMapping(value = "/followAction/{profileUserName:.+}", method = RequestMethod.GET)
	public Map<String, String> followAction(@PathVariable String username, @PathVariable String profileUserName) {
		HashMap<String, String> map = new LinkedHashMap<>();
		User followed = userService.findByUserName(username);
		User following = userService.findByUserName(profileUserName);

		Relationship rel = relationshipService.isFollowingId(followed, following);

		if (rel == null) {
			Relationship relationship = new Relationship();

			User saveUser = userService.findByUserName(username);
			relationship.setFollowed(followed);
			relationship.setFollower(following);
			relationshipService.save(relationship);
			saveUser.getFollowing().add(relationship);
			userService.save(saveUser);
			map.put("label", "UNFOLLOW");
		} else {
			/* userService.removeRelationship(username,profileUserName); */
			map.put("label", "FOLLOW");
		}
		return map;
	}

	@Autowired
	public RelationshipController(UserService userService, RelationshipService relationshipService) {
		this.relationshipService = relationshipService;
		this.userService = userService;
	}

}
