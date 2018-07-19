package com.mazon.spring.rest.webservices.restfulwebservices.post;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mazon.spring.rest.webservices.restfulwebservices.user.User;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	public Post() {}
	
	public Post(Integer id, String title, String content, Date postDate) {
		super();
		this.id = id;
		this.title = title;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + "]";
	}
	
}
