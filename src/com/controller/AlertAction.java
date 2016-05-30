package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.mapred.JobHistory.Values;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;

import com.bean.AlertDef;
import com.jdbc.JdbcUtils;
import com.opensymphony.xwork2.ActionSupport;

public class AlertAction extends ActionSupport {
	
	
	JdbcUtils jd=new JdbcUtils();
	Connection cn=jd.getConnection();
	
	
	
	public String updateAlertDef(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String action=request.getParameter("action");
		if (action.equals("edit")) {
			editAlertDef(request);
		}else {
			deleteAlertDef(request);
		}
		
		return null;
	}
	
	public Boolean editAlertDef(HttpServletRequest request){
		
		AlertDef alertDef=new AlertDef();
		String id=request.getParameter("id");
		String asset_id=request.getParameter("asset_id");
		String param_id=request.getParameter("param_id");
		String min=request.getParameter("min");
		String max=request.getParameter("max");
		String duration=request.getParameter("duration");
		alertDef.setAsset_id(asset_id);
		alertDef.setParam_id(param_id);
		alertDef.setMin(min);
		alertDef.setMax(max);
		alertDef.setDuration(duration);
		String saveAlert;
		List<String> list=new ArrayList<String>();
		if (id.equals("")||id.equals(null)) {
			saveAlert="insert into alert_def(asset_id, param_id, min, max, duration) values(?,?,?,?,?)";
			list.add(asset_id);
			list.add(param_id);
			list.add(min);
			list.add(max);
			list.add(duration);
		}else {
			saveAlert="update alert_def set asset_id=?, param_id=?, min=?, max=?, duration=? where id=?";
			list.add(asset_id);
			list.add(param_id);
			list.add(min);
			list.add(max);
			list.add(duration);
			list.add(id);
			}
		
		deleteAlerts(asset_id, param_id);
		//开始查询修改
		try {
			jd.updateByPreparedStatement(saveAlert, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setAlerts(alertDef);
		return true;
	}
	
	public Boolean deleteAlertDef(HttpServletRequest request){
		String id=request.getParameter("id");
		String getAlerts="select asset_id,param_id from alert where id=?";
		List<String > list=new ArrayList<String>();
		list.add(id);
		try {
			Map map=jd.findSimpleResult(getAlerts, list);
			deleteAlerts(map.get("asset_id").toString(), map.get("param_id").toString());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		String deleteAlert="update alert_def set status=0 where id=?";
		
		
		try {
			jd.updateByPreparedStatement(deleteAlert, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//删除相关status
		return true;
	}
	
	public String getAlerts(){
		
		String getAlertDef="select * from alert";
		
		try {
			List<Map<String , String >> maps=jd.findMoreResult(getAlertDef, null);
			HttpServletResponse response = ServletActionContext.getResponse();
			JSONArray jsonArray=new JSONArray(maps);
			response.getWriter().println(jsonArray);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getAlertDefs(){
		String getAlertDef="select * from alert_def where status=?";
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		try {
			List<Map<String , String >> maps=jd.findMoreResult(getAlertDef, list);
			HttpServletResponse response = ServletActionContext.getResponse();
			JSONArray jsonArray=new JSONArray(maps);
			response.getWriter().println(jsonArray);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String deleteAlerts(String asset_id,String param_id) {
		
		String deleteSql="delete from alert where asset_id=? and param_id=? ";
		List<String > list=new ArrayList<String>();
		list.add(asset_id);
		list.add(param_id);
		try {
			jd.updateByPreparedStatement(deleteSql, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String setAlerts(AlertDef alertDef){
		
		String selectexceedSql="select * from data_value where asset_id=? and param_id ";
		
		return null;
	}
	
}
