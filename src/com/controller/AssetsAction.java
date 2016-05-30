package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jdbc.JdbcUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.sql.SqlConfig;
import com.util.Util;

public class AssetsAction extends ActionSupport {
	
	JdbcUtils jd=new JdbcUtils();
	Connection cn=jd.getConnection();

	public String getType() throws Exception {
		String sql="select asset_id,name from asset where parent_id = ?";
		List<Integer> list=new ArrayList<Integer>();
		list.add(0);
		HttpServletRequest request=ServletActionContext.getRequest();
		String data=request.getParameter("data");
		//System.out.println(data);
		
		List<Map<String ,Object>> result=(List<Map<String, Object>>) jd.findMoreResult(sql, list);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONArray jsonArray=new JSONArray(result);
		response.getWriter().println(jsonArray);
		return null;
		
	}
	
	public String getModel() throws IOException, SQLException{
		String sql="select asset_id,name from asset where parent_id = ?";
		HttpServletRequest request=ServletActionContext.getRequest();
		String data=request.getParameter("type_id");
		
		List<Integer> list=new ArrayList<Integer>();
		list.add(Integer.valueOf(data));
		
		//System.out.println(data);
		
		List<Map<String ,Object>> result=(List<Map<String, Object>>) jd.findMoreResult(sql, list);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONArray jsonArray=new JSONArray(result);
		response.getWriter().println(jsonArray);
		
		jd.releaseConn();
		return null;
	}
	
	public String getAsset() throws SQLException, IOException{
		
		String sql="select asset_id,name from asset where parent_id = ?";
		HttpServletRequest request=ServletActionContext.getRequest();
		String data=request.getParameter("model_id");
		
		List<Integer> list=new ArrayList<Integer>();
		list.add(Integer.valueOf(data));
		
		//System.out.println(data);
		
		List<Map<String ,Object>> result=(List<Map<String, Object>>) jd.findMoreResult(sql, list);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONArray jsonArray=new JSONArray(result);
		response.getWriter().println(jsonArray);
		jd.releaseConn();
		return null;
	}
	
	public String getParam() throws SQLException, IOException{
		
		String sql="select param_id,param_name from parameter where asset_id = ?";
		HttpServletRequest request=ServletActionContext.getRequest();
		String data=request.getParameter("asset_id");
		
		List<Integer> list=new ArrayList<Integer>();
		list.add(Integer.valueOf(data));
		
		//System.out.println(data);
		
		List<Map<String ,Object>> result=(List<Map<String, Object>>) jd.findMoreResult(sql, list);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONArray jsonArray=new JSONArray(result);
		response.getWriter().println(jsonArray);
		jd.releaseConn();
		return null;
	}
	
	public String getData() throws SQLException, JSONException, IOException {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String asset_id=request.getParameter("asset_id");
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		String params=request.getParameter("params");
		String[] temp=params.split(",");
		int temp_length=temp.length;
		int[] params_id=new int[temp_length];
		for (int i = 0; i < temp_length; i++) {
			params_id[i]=Integer.parseInt(temp[i]);
		}
		List<Object> list=new ArrayList<Object>();
		List<JSONArray> data=new ArrayList<JSONArray>();
		for (int i = 0; i < temp_length; i++) {
			list.add(asset_id);
			list.add(params_id[i]);
			list.add(start);
			list.add(end);
			List<Double> result=jd.findDataResult(SqlConfig.DATASQL, list);
		
			data.add(new JSONArray(result));
			list.clear();
			}
		System.out.println(data.get(0).toString()+"aaa");
		
		
		
		JSONObject chartData=new JSONObject();
		JSONArray lables=new JSONArray();
		int length=data.get(0).length();
		for (int j = 0; j <length ; j++) {
			lables.put("");
		}
		chartData.put("labels", lables);
		JSONArray datasets=new JSONArray();
		for (int i = 0; i < temp_length; i++) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("label", asset_id+"_"+temp[i]);
			String color="#"+Util.getRandomColor();
			jsonObject.put("fillColor", color);
			jsonObject.put("strokeColor", color);
			jsonObject.put("pointColor", color);
			jsonObject.put("pointStrokeColor", "#fff");
			jsonObject.put("pointHighlightFill", "#fff");
			jsonObject.put("pointHighlightStroke", "#fff");
			JSONArray data_val=data.get(i);
			
			jsonObject.put("data", data_val);
			datasets.put(jsonObject);
		}
		chartData.put("datasets", datasets);
		
		
		System.out.println(chartData);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.getWriter().println(chartData);
		
		
		
		jd.releaseConn();
		return null;
	}
	
	
	
}
