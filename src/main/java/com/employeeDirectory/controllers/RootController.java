package com.employeeDirectory.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employeeDirectory.beans.User;
import com.employeeDirectory.repositories.UserRepository;
import com.employeeDirectory.service.UserRegistrationService;

@Controller
public class RootController {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRegistrationService userRegistrationService;

	@RequestMapping("/")
	public String root() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String loginUser() {
		return "index";
	}

	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}

	@PostMapping("/registration")
	public String registrationUser(@RequestParam("email") String email,
			@RequestParam("email_conf") String email_conf,
			@RequestParam("password") String password,
			@RequestParam("password_conf") String password_conf,
			@RequestParam("username") String username,
			@RequestParam("department_id") int department_id,
			@RequestParam("project_id") int project_id) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String entrance_date = sdf.format(calendar.getTime());
		System.out.println(department_id);
		System.out.println(project_id);
		User user = new User();
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setUsername(username);
		user.setEntrance_date(entrance_date);
		userRegistrationService.registerUser(user, department_id, project_id);

		return "login";
	}

}
