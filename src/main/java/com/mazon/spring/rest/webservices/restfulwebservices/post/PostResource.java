package com.mazon.spring.rest.webservices.restfulwebservices.post;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PostResource {
	
	@Autowired
	private PostDaoService postService;
	
	
	@GetMapping(path="/posts")
	public List<Post> retrieveAllUsers(){
		return postService.findAll();
	}
	
	@GetMapping(path="/posts/{id}")
	public Post retrieveUser(@PathVariable int id){
		Post post = postService.findOne(id);
		if(post == null)
			throw new PostNotFoundException("id-" + id);
		return post;
	}
	
	@PostMapping(path="/posts")
	public ResponseEntity<Object> createUser(@RequestBody Post post){
		Post savedUser = postService.save(post);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/posts/{id}")
	public void remove(@PathVariable int id){
		Post post = postService.findOne(id);
		if(post == null)
			throw new PostNotFoundException("id-" + id);
		else
			postService.remove(post);
	}

}
