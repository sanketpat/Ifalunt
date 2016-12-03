package com.iflaunt.backend.model;

import java.util.Date;

import org.springframework.http.HttpEntity;

public class Post implements Comparable<Post> {

	private String userFullName;
	private String gender;
	private String accessoryType;
	private String brand;
	private HttpEntity<byte[]> image;
	private String time;
	private Date dateCreated;

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public HttpEntity<byte[]> getImage() {
		return image;
	}

	public void setImageName(HttpEntity<byte[]> image) {
		this.image = image;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAccessoryType() {
		return accessoryType;
	}

	public void setAccessoryType(String accessoryType) {
		this.accessoryType = accessoryType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public int compareTo(Post o) {
		return o.getDateCreated().compareTo(getDateCreated());
	}

}
