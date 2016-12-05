package com.iflaunt.backend.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@NamedNativeQuery(name = "User.getFollowed", resultClass = User.class, query = "SELECT * FROM user LEFT JOIN relationship on relationship.follower = :username WHERE relationship.followed = user.user_name")
public class User implements Serializable, Comparable<User>{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	private String userName;

	@Column(nullable = false)
	private String password;

	@CreationTimestamp
	private Date created;

	private String firstName;

	private String lastName;

	private String photoName;

	private String birthDate;

	private String gender;
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private String bio;

	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<Photo> photoList;

	@OneToMany(mappedBy = "follower")
	@JsonManagedReference(value = "user-follower")
	private Collection<Relationship> following;

	@OneToMany(mappedBy = "followed")
	@JsonManagedReference(value = "user-follwed")
	private Collection<Relationship> followed;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<Photo> photoList) {
		this.photoList = photoList;
	}

	public Collection<Relationship> getFollowing() {
		return following;
	}

	public void setFollowing(List<Relationship> following) {
		this.following = following;
	}

	@Override
	public int compareTo(User o) {
		return getFirstName().compareTo(o.getFirstName());
	}
}
