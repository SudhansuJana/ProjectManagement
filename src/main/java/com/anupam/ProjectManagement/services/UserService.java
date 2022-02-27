package com.anupam.ProjectManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anupam.ProjectManagement.demo.User;
import com.anupam.ProjectManagement.exceptions.UsernameAlreadyExistsException;
import com.anupam.ProjectManagement.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User saveUser(User newUser) {

		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

			// Username has to be unique (exception)
			newUser.setUsername(newUser.getUsername());
			// Make sure that password & confirmPassword match

			// We don't persist or show the confirm password
			newUser.setConfirmpassword("");
			return userRepository.save(newUser);
		} catch (Exception e) {
			throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' is already exists ");
		}

	}

}
