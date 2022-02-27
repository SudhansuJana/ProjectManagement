package com.anupam.ProjectManagement.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anupam.ProjectManagement.demo.User;
import com.anupam.ProjectManagement.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		if(user == null)  throw new UsernameNotFoundException("User not Found");
		return user;
	}
	
	@Transactional
	public User loadUserById(Long id) {
		User user=userRepository.findById(id).get();
		if(user==null) throw new UsernameNotFoundException("User not Found");
		return user;
	}
	
}
