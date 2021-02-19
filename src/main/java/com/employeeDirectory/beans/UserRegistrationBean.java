package com.employeeDirectory.beans;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class UserRegistrationBean implements Serializable {
	private static final long serialVersionUID = 413581654624527834L;

	@Size(max = 100)
	private String email;
	@Size(max = 100)
	private String email_conf;
	@Size(min = 8, max = 20)
	private String password;
	@Size(min = 8, max = 20)
	private String password_conf;
	@Size(max = 50)
	private String username;
	@Min(1)
	@Max(6)
	private int department_id;
	@Min(1)
	@Max(5)
	private int project_id;

	@AssertTrue
	public boolean isEmailConfirmed() {
		if (email == null && email_conf == null) {
			return true;
		}
		return email.equals(email_conf);
	}

	@AssertTrue
	public boolean isPasswordConfirmed() {
		if (password == null && password_conf == null) {
			return true;
		}
		return password.equals(password_conf);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail_conf() {
		return email_conf;
	}

	public void setEmail_conf(String email_conf) {
		this.email_conf = email_conf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword_conf() {
		return password_conf;
	}

	public void setPassword_conf(String password_conf) {
		this.password_conf = password_conf;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
}
