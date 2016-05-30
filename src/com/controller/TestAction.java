package com.controller;

import java.sql.Connection; 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;

import com.jdbc.JdbcUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;
import com.util.Factory;

public class TestAction extends ActionSupport{	

	public String getType() throws Exception {
		String sql="select asset_id,name from asset where parent_id = ?";
		List<Integer> list=new ArrayList<Integer>();
		list.add(0);
		HttpServletRequest request=ServletActionContext.getRequest();
		String data=request.getParameter("data");
		System.out.println(data);
		JdbcUtils jd=new JdbcUtils();
		Connection cn=jd.getConnection();
		List<Map<String ,Object>> result=(List<Map<String, Object>>) jd.findMoreResult(sql, list);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONArray jsonArray=new JSONArray(result);
	
		response.getWriter().println(jsonArray);
		
		
		
		return null;
		
	}
 


}
