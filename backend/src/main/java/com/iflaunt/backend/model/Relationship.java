package com.iflaunt.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Relationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long Id;

	@ManyToOne
	@JsonBackReference(value = "user-follower")
	@JoinColumn(name = "follower")
	private User follower;

	@ManyToOne
	@JsonBackReference(value = "user-follwed")
	@JoinColumn(name = "followed")
	private User followed;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public User getFollowed() {
		return followed;
	}

	public void setFollowed(User followed) {
		this.followed = followed;
	}

	public Relationship() {
	}

}
