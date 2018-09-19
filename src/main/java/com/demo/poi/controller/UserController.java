package com.demo.poi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.poi.interfaces.UserInterface;
import com.demo.poi.model.UserEntity;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserInterface userService;

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public UserEntity saveUser(UserEntity entity){
		return userService.save(entity);
	}
}
