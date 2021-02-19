package com.employeeDirectory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employeeDirectory.beans.User;
import com.employeeDirectory.repositories.UserRepository;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.identifyUser(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return userRepository.identifyUser(username);
	}

}
