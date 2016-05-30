package com.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.common.DataSchema;
import com.jdbc.JdbcUtils;
import com.sql.SqlConfig;

public class ParamsService {
	
	public void saveParams(DataSchema data,JdbcUtils jd) throws SQLException{
		
		
		String sql=SqlConfig.params_sql;
		List<String > list=new ArrayList<String>();
		list.add(data.getParamId());
		list.add(data.getAssetId());
		list.add(data.getValue());
		list.add(data.getTimeStamp());
		jd.updateByPreparedStatement(sql, list);
	
	}
	
	public static void main(String[] args) {
		/*DataSchema jsonSchema=new DataSchema();
		jsonSchema.setAssetId("11");
		jsonSchema.setParameter("alt");
		jsonSchema.setTimeStamp("123123");
		jsonSchema.setRecord("ssedf");
		JdbcUtils jd=new JdbcUtils();
		Connection cn=jd.getConnection();
		try {
			new ParamsService().saveParams(jsonSchema,jd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
