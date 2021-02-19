package com.employeeDirectory.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employeeDirectory.beans.User;
import com.employeeDirectory.beans.UserRegistrationBean;
import com.employeeDirectory.repositories.UserRepository;
import com.employeeDirectory.service.GetUserDataService;
import com.employeeDirectory.service.UserRegistrationService;

@Controller
public class RootController {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRegistrationService userRegistrationService;

	@Autowired
	GetUserDataService getUserDataService;

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

	@PostMapping("/login-error")
	public String loginError(@RequestAttribute("SPRING_SECURITY_LAST_EXCEPTION") AuthenticationException ex,
			Model model) {
		model.addAttribute("authenticationException", ex);
		return "login";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userRegistrationBean", new UserRegistrationBean());
		return "registration";
	}

	@PostMapping("/registration")
	public String registrationUser(@Valid @ModelAttribute UserRegistrationBean userRegistrationBean,
			BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		if (userRegistrationService.isDuplicateUser(userRegistrationBean.getEmail())) {
			model.addAttribute("duplicateError", "すでに使われているEmailです");
			return "registration";
		}

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String entrance_date = sdf.format(calendar.getTime());

		User user = new User();
		user.setEmail(userRegistrationBean.getEmail());
		user.setPassword(passwordEncoder.encode(userRegistrationBean.getPassword()));
		user.setUsername(userRegistrationBean.getUsername());
		user.setEntrance_date(entrance_date);
		userRegistrationService.registerUser(user, userRegistrationBean.getDepartment_id(),
				userRegistrationBean.getProject_id());

		return "login";
	}

	@GetMapping("/employeesAll")
	public String employeeAll(Model model) {
		List<User> allUsers = getUserDataService.getAllUser();
		model.addAttribute("allUsers", allUsers);
		for (User user : allUsers) {
			System.out.print(user.getUsername());
		}
		return "employeeAll";
	}

	@GetMapping("/employeeData/{id}")
	public String employeeData(@PathVariable("id") int id, Model model) {
		User user = getUserDataService.getUser(id);
		model.addAttribute("user", user);
		return "employeeData";
	}

}
