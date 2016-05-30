package com.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.common.Common;
import com.common.DataSchema;
import com.jdbc.JdbcUtils;
import com.sql.SqlConfig;

public class ReadMapping {
	

	
	//数据库查找给timestamp， paramColummn赋值
	
	public void readMapping(String filename, String filepath ) throws IOException, ParseException{
			
		
		int timeCol;
		//String path=Common.UploadURL+"/"+filename;
		String path;
		String parameter;
		String assetId;
		JdbcUtils jdbcUtils=new JdbcUtils();
		Connection cn=jdbcUtils.getConnection();
		List<String> list=new ArrayList<String>();
		list.add(filename);
		
		try {
			Map map  = jdbcUtils.findSimpleResult(SqlConfig.timeColSql, list);
			timeCol = Integer.parseInt(map.get("timestamp").toString());
			parameter= map.get("parameters").toString();
			assetId=map.get("asset_id").toString();
			List<Integer> paramCol=new ArrayList<Integer>();
			String[] parameters=parameter.split(",");
			
			for (int i = 0; i < parameters.length; i++) {
				paramCol.add(Integer.valueOf(parameters[i]));
			}
			
			DataSchema data=new DataSchema();
			data.setAssetId(assetId);
			
			ReadExcel readExcel=new ReadExcel();
			if(readExcel.readExcel( filepath,timeCol,paramCol,data)){
				List<String> list2=new ArrayList<String>();
				list2.add(filename);
				jdbcUtils.updateByPreparedStatement(SqlConfig.paramsStatus, list2);   
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtils.releaseConn();
		}
		
	
		
	
	}
	



}
