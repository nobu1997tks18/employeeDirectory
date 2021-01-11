package com.employeeDirectory.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.employeeDirectory.beans.User;

@Mapper
public interface UserRepository {
	public User identifyUser(String email);

	public int registerUser(User user);

	public int registerUserRole(User user);

	public int registerUserDepartment(@Param("User") User user, @Param("department_id") int id);

	public int registerUserProject(@Param("User") User user, @Param("project_id") int id);

}
