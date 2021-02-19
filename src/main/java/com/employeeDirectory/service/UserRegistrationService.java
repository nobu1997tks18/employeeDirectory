package com.employeeDirectory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeeDirectory.beans.User;
import com.employeeDirectory.repositories.UserRepository;

@Service
public class UserRegistrationService {
	@Autowired
	UserRepository userRepository;

	@Transactional
	public void registerUser(User user, int department_id, int project_id) {
		int result = 0;
		result += userRepository.registerUser(user);
		result += userRepository.registerUserRole(user);
		result += userRepository.registerUserDepartment(user, department_id);
		result += userRepository.registerUserProject(user, project_id);
		if (result != 4) {
			throw new RuntimeException();
		}
	}

	public boolean isDuplicateUser(String email) {
		User user = userRepository.identifyUser(email);
		return user != null;
	}
}
