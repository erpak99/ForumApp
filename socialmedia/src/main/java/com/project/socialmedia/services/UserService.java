package com.project.socialmedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.socialmedia.core.results.DataResult;
import com.project.socialmedia.core.results.SuccessDataResult;
import com.project.socialmedia.entities.User;
import com.project.socialmedia.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public DataResult<List<User>> getAllUsers() {
		return new SuccessDataResult<List<User>>(this.userRepository.findAll(),"All users...");
	}

	public DataResult<User> createOneUser(User newUser) {
		this.userRepository.save(newUser);
		return new SuccessDataResult<User>(newUser,"User is created...");
	}

	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public DataResult<User> updateOneUser(Long userId, User newUser) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUserName(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			userRepository.save(foundUser);
			return new SuccessDataResult<User>(foundUser, "User is updated...") ;
		} else
			return null;
	}

	public void deleteOneUser(Long userId) {
		userRepository.deleteById(userId);
	}	
	
	
}
