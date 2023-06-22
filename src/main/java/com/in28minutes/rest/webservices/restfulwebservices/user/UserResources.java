package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResources {

	private UserDaoService service;
 
	public UserResources(UserDaoService service) {
		this.service = service;
	}

	// GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {

		return service.findAll();

	 }

	 /*
	 what we are doing as part of HATEOAS is that we are wrapping this user and adding links to it.
	 for that we need EntityModel and WebMVC link builder.
	 what we are we actually doing ?
	 	from getting one particular user/resource how can we get link to all users/or multiple resources.
	 */

	 // GET /users
	 @GetMapping("/users/{id}") 
	 public EntityModel<User> retrieveUser(@PathVariable int id) {
		 
		 User user = service.findOne(id);
		 if(user == null) {
			 throw new UserNotFoundException("id:"+id);
		 }

		 EntityModel<User> entityModel = EntityModel.of(user);

		 WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		 entityModel.add(link.withRel("all_users"));

		 return entityModel;
	 
	 }
	 
	 @DeleteMapping("/users/{id}") 
	 public void deleteUser(@PathVariable int id) {
		 
		 service.deleteById(id);
		 
	 
	 }
	 
	 
	 // POST /users
	 @PostMapping("/users")
	 public ResponseEntity<User> creteUser(@Valid @RequestBody User user) {
		 
		 User savedUser = service.save(user);
		 
		 // location - /users/4 => /users/user.getID - we have to send location of newly created user.
		 
		 //we are using present request address and then appending the user id to it so 
		 //once user created we can have look at details of user by returning location from this post.
		 
		 URI location = ServletUriComponentsBuilder
				 .fromCurrentRequest()
				 .path("/{id}")
				 .buildAndExpand(savedUser.getId())
				 .toUri();
		 
		 return ResponseEntity.created(location).build();
		 
	 } 
	 
	
}
