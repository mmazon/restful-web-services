package com.mazon.spring.rest.webservices.restfulwebservices.post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PostDaoService {
	public static List<Post> posts = new ArrayList<>();
	private static int postsCount = 4;
	
	static {
		posts.add(new Post(1, "How to Programme in Java", "Não da", new Date()));
		posts.add(new Post(2, "How to Programme in PHP", "ad Noã ", new Date()));
		posts.add(new Post(3, "How to Programme in Python", "[Não_da]", new Date()));
		posts.add(new Post(4, "How to Programme in Node", "Não da.js", new Date()));
	}
	
	public List<Post> findAll(){
		return posts;
	}
	
	public Post save(Post post){
		if(post.getId() == null){
			post.setId(++postsCount);
		}
		posts.add(post);
		return post;
	}
	
	public Post findOne(int id){
		for (Post r : posts){
			if(r.getId() == id)
				return r;
		}
		return null;
	}
	
	public void remove(Post post){
		posts.remove(post);
	}
}
