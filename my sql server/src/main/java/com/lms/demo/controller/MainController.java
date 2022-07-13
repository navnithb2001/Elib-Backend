package com.lms.demo.controller;

import java.util.Map;

import com.lms.demo.data.model.Roles;
import com.lms.demo.data.model.User;
import com.lms.demo.data.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MainController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	public User login(@RequestBody Map<String,String> json) {
		String username = json.get("username");
        String password = json.get("password");

		User user = userRepository.findByUsername(username);

		System.out.println(user.getName());
		if(user!= null && password.equals(user.getPassword())){
			System.out.println("returning user");
			return user;
		}
		return null;
	}

	@PostMapping("/register")
	public void registerUser(@RequestBody User user) throws Exception {
		User user1 = userRepository.findByUsername(user.getUsername());

		user.setFine((long) 0);

		if(user1 != null){
			throw new Exception("username taken");
		}
		else{
			userRepository.save(user);
		}
	}
}
