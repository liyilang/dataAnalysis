package com.service.impl;

import java.util.List;
import java.util.Map;

import com.dao.UserDao;
import com.service.UserService;
import com.util.Factory;



public class UserServiceImpl implements UserService {
    
	public List<Map> findUsers() {
	    UserDao userDao = Factory.getUserDao();
		
		return userDao.findUsers();
	}
}
