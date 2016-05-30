package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import com.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.UserService;
import com.util.Factory;

public class LoginAction extends ActionSupport{
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() throws Exception {
		UserService userService = Factory.getUserService();
		List<Map> users = userService.findUsers();		
		
		HttpServletRequest request = ServletActionContext.getRequest(); 
		request.setAttribute("datas", users);
		
		
		return SUCCESS;
		
//		if (user.getUsername().equals("scott") && user.getPassword().equals("tiger")) {
//			ActionContext.getContext().getSession().put("user", user.getUsername());
//			
//			//
//			UserService userService = Factory.getUserService();
//			List<Map> users = userService.findUsers();
//			
//			
//			HttpServletRequest request = ServletActionContext.getRequest(); 
//			request.setAttribute("datas", users);
//			
//			
//			return SUCCESS;
//		} else {
//			return ERROR;
//		}
	}



}
