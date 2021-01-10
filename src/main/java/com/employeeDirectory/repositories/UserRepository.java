package com.employeeDirectory.repositories;

import org.apache.ibatis.annotations.Mapper;

import com.employeeDirectory.beans.User;

@Mapper
public interface UserRepository {
	public User identifyUser(String email);
}
