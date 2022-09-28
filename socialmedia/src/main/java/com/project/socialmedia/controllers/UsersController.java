package com.project.socialmedia.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.socialmedia.core.results.DataResult;
import com.project.socialmedia.entities.User;
import com.project.socialmedia.services.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

	private UserService userService;
	
	public UsersController(UserService userService) {
		 this.userService = userService;
	}
	
	@GetMapping
	public DataResult<List<User>> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping
	public DataResult<User> createOneUser(@RequestBody User newUser) {
		return userService.createOneUser(newUser);
	}
	
	@GetMapping("/{userId}")
	public User getOneUserById(@PathVariable Long userId) {
		return userService.getOneUserById(userId);
	}
	
	@PutMapping("/{userId}")
	public DataResult<User> updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
		return userService.updateOneUser(userId, newUser);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable Long userId) {
		userService.deleteOneUser(userId);
	}
	
	
	
	
}
