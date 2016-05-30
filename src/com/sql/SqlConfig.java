package com.sql;

public class SqlConfig {
	
	public static String params_sql = "insert into data_value(param_id,asset_id,value,last_update,updated_time) values(?,?,?,?,SYSDATE())";

	public static final String mapingsql = " insert into mapping(asset_id,timestamp,parameters,filename,updatedtime) values(?,?,?,?,SYSDATE())";

	public static final String timeColSql = "select timestamp,parameters,asset_id from mapping where filename=?";

	public static final String paramsStatus = "update mapping set status = 1 where filename=?";
	
	public static final String paramIdSql="select param_id from parameter where param_name=?";
	
	public static final String DATASQL="select value from data_value where asset_id=? and param_id=? and last_update between ? and ?";

}
