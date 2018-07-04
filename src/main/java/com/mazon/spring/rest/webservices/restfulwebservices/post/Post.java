package com.mazon.spring.rest.webservices.restfulwebservices.post;

import java.util.Date;

public class Post {
	
	private Integer id;
	private String title;
	private String content;
	private Date postDate;
	
	public Post() {}
	
	public Post(Integer id, String title, String content, Date postDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.postDate = postDate;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", postDate=" + postDate + "]";
	}
	
}
