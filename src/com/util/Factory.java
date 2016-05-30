package com.util;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.service.UserService;
import com.service.impl.UserServiceImpl;


public class Factory {
	public static UserService getUserService() {
		return new UserServiceImpl();
	}
	
	
   public static UserDao getUserDao() {
	   return new UserDaoImpl();
   }
}
