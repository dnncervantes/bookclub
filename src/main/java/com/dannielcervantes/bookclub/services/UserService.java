package com.dannielcervantes.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.dannielcervantes.bookclub.models.LoginUser;
import com.dannielcervantes.bookclub.models.User;
import com.dannielcervantes.bookclub.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
		//To-do Reject values or register if no errors
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		
		//1. check email(present - reject
		if(potentialUser.isPresent()) {
			result.rejectValue("email", "unique", "Email already in use!");
		}
		//2. check pw != confirm - reject
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("password", "matches", "Passwords do not match!");
		}
		//3. if result has errors
		if(result.hasErrors()) {
			return null;
		}
		//4. Hash and set pw
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepo.save(newUser);
		
	}
	public User login(LoginUser newLogin, BindingResult result) {
		
		//1. Find user in the db by email
		Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
		
		//2. if email is not present, reject
		if(!potentialUser.isPresent()) {
			result.rejectValue("email", "unque", "Email does not exist!");
			return null;
		}
		//3. Get user from db
		User user = potentialUser.get();
		//Checking password
		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "matches", "Invalid Password!");
			}
		if(result.hasErrors()) {
			return null;
		}
		return user;
	}
	public User getUser(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}
}
