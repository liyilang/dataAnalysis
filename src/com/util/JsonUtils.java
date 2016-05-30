/*package com.util;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.common.Common;
import com.common.DataSchema;
import java.io.*;

public class JsonUtils {

	*//**
	 * @param args
	 *//*
	public static void main(String[] args) {
		
		DataSchema json1=new DataSchema();
		DataSchema json2=new DataSchema();
		json1.setAssetId("ITU1006");
		json1.setTimeStamp("1442562525000");
		json1.setParameter("WATT_T");
		json1.setRecord("124.12");
		json2.setAssetId("ITU1006");
		json2.setTimeStamp("1442562525000");
		json2.setParameter("VAR_T");
		json2.setRecord("125.4");
		JSONArray array=new JSONArray();
		array.put(generateData(json1));
		array.put(generateData(json2));
		String string=generateJsonFile(array,"data.json");
		System.out.println(string);
	}
	
	public static JSONObject generateData (DataSchema jsonSchema) {
		JSONObject jsonObject=new JSONObject();
		try {
		jsonObject.put("assetid", jsonSchema.getAssetId());
		JSONObject parentid=new JSONObject();
		parentid.put ("null", jsonObject.NULL);
		jsonObject.put("parentid", parentid);
		jsonObject.put("timestamp", Long.valueOf(jsonSchema.getTimeStamp()));
		jsonObject.put("parameter", jsonSchema.getParameter());
		jsonObject.put("mode", "All phases");
		JSONObject record=new JSONObject();
		record.put("double", Double.valueOf(jsonSchema.getRecord()));
		jsonObject.put("record", record);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public static String generateJsonFile (JSONArray array, String filename) {

		JSONObject jsonObject=new JSONObject();
		JSONObject string=new JSONObject();
		
		try {
			string.put("string", "Original Information");
			jsonObject.put("source", string);
			jsonObject.put("data", array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String servletURL=Common.servletURL;
		String fileURL=servletURL+"\\"+filename;
		
		try {
		File myfile=new File(fileURL);
		if (!myfile.exists()) {
			
				myfile.createNewFile();
			
		}else {
			throw new Exception(filename+" already exist");
		}
		FileWriter fileWriter=new FileWriter(myfile);
		PrintWriter myNewFile=new PrintWriter(fileWriter);
		myNewFile.println(jsonObject.toString());
		fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileURL;
	}
	
	
	
	

}*/
