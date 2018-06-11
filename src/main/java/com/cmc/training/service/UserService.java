package com.cmc.training.service;

import java.util.List;

import com.cmc.training.dto.LoginParameterObject;
import com.cmc.training.entity.User;
import com.cmc.training.util.ResultUtil;

public interface UserService {
	
	ResultUtil<User> loginUser(LoginParameterObject obj);
	
	List<User> getAllUser();
	
	LoginParameterObject getUserById(Integer id);
	
	User findByUserName(String id);
	
}
