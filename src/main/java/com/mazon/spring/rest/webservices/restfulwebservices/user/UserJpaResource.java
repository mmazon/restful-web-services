package com.mazon.spring.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mazon.spring.rest.webservices.restfulwebservices.post.Post;
import com.mazon.spring.rest.webservices.restfulwebservices.post.PostRepository;

@RestController
public class UserJpaResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@GetMapping(path="/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	/**
	 * retornar um Resource de User, com um link para todos os usuarios
	 * @param id
	 * @return Resource<User>
	 */
	@GetMapping(path="/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-" + id);
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@PostMapping(path="/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
	}
	
	@GetMapping(path="/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-" + id);
		return user.get().getPosts();
	}
	
	@PostMapping(path="/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-" + id);
		User userEntity = user.get();
		post.setUser(userEntity);
		postRepository.save(post);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
