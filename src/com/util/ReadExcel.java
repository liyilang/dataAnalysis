 package com.util;

import java.io.File;
import java.io.FileInputStream;    
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.avro.util.Utf8;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.common.Common;
import com.common.DataSchema;
import com.jdbc.JdbcUtils;

import com.service.ParamsService;
import com.sql.SqlConfig;
import com.util.*;

public class ReadExcel {

	
	/*
	 * 
	 * 
	 */
	public boolean readExcel(String path,int timeCol, List<Integer> paramCol, DataSchema data) throws IOException, ParseException{
		
		if(path == null || Common.EMPTY.equals(path)){
			//return null;
			System.out.println("null");
			return false;
		}else {
			String postfix=Util.getPostfix(path);
			if (!Common.EMPTY.equals(postfix)) {
				if (Common.EXCEL_2003.equals(postfix)){
					
						readXls(path,timeCol,paramCol,data);
						System.out.println("date imported done");
						return true;
					
				}else if (Common.EXCEL_2010.equals(postfix)){
				
						readXlsx(path,timeCol,paramCol,data);
						System.out.println("date imported done");
						return true;
				}
				return false;
			}else {
				System.out.println(path+Common.NOT_EXCEL_FILE);
				return false;
			}
		}
		//return null;
	}
	
	
	/**
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws SQLException 
	 * 
	 */
	
	public void readXlsx(String path,int timeCol,List<Integer> paramCol,DataSchema data) throws IOException, ParseException{
		
		System.out.println(Common.PROCESSING+path);
		InputStream is=new FileInputStream(path);
		XSSFWorkbook xssfWorkbook=new XSSFWorkbook(is);
		List<String > columnHeader=new ArrayList<String>();
		//read sheets
		int NumberOfSheet=xssfWorkbook.getNumberOfSheets();
		for (int numSheet = 0; numSheet < NumberOfSheet; numSheet++) {
			XSSFSheet xssfSheet=xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet==null){
				continue;
			}
			JdbcUtils jd=new JdbcUtils();
			Connection cn=jd.getConnection();
			int rowNum=xssfSheet.getLastRowNum()+1;
			ParamsService ps=new ParamsService();
			//read the row
			try {
			for (int i =0 ; i<rowNum ; i++) {
					XSSFRow xssfRow=xssfSheet.getRow(i); 
					if (xssfRow!=null) {
					int cellNum=xssfRow.getLastCellNum();
					if (i==0) {
						for (int j = 0; j < cellNum; j++) {
							XSSFCell cell=xssfRow.getCell(j);
							
							if (j==(timeCol-1)) {
								columnHeader.add(getValue(cell));
							}else {
								List<String> list=new ArrayList<String>();
								list.add(getValue(cell));
								Map<String , Object> map=jd.findSimpleResult(SqlConfig.paramIdSql,list );
								if (!map.isEmpty()) {
									columnHeader.add(map.get("param_id").toString());
								} else{
									columnHeader.add("N/A");
								}
								
								
							}
							
							//System.out.println(columnHeader.get(j));
						}
					}else {
						//System.out.println(getValue(xssfRow.getCell(timeCol-1)));
						String timestamp=Util.getTimestamp(getValue(xssfRow.getCell(timeCol-1)));
						
						//String timestamp=Util.getTimestamp("8/6/2015  2:23:22 PM");
						for (Iterator<Integer> iterator = paramCol.iterator(); iterator
								.hasNext();) {
							
								Integer col = (Integer) iterator.next();
								String paramId=columnHeader.get(col-1);
								XSSFCell cell=xssfRow.getCell(col-1);
								String record=getValue(cell);
								data.setTimeStamp(timestamp);
								data.setParamId(paramId);
								data.setValue(record);
								//System.out.println("time: "+timestamp+" param: "+parameter+" value: "+record);
								ps.saveParams(data,jd);
								
							
					 	
							
						}
					}
					/*for (int j = 0; j < cellNum; j++) {   
						XSSFCell cell=xssfRow.getCell(j);
						if (i==0) {
							columnHeader.add(getValue(cell));
						}
						else{
							//dataMap.put(columnHeader.get(j), getValue(cell));
							//System.out.println(columnHeader.get(j)+":"+getValue(cell));
							for (Iterator iterator = paramCol.iterator(); iterator
									.hasNext();) {
								if (((Integer)iterator.next()-1)==j )
								{	
									Integer col = (Integer) iterator.next();
									String parameter=columnHeader.get(col-1);
									String record = getValue(cell);
									System.out.println("time:"+timestamp+" param:"+parameter+" value:"+record);
								}
							}
						}
					}*/
					
					}		
		}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jd.releaseConn();
			
		}
		//return dataMap;
	}
	}
	
	/**
	 * @throws IOException 
	 * @throws SQLException 
	 * 
	 * 
	 */
	public void readXls(String path,int timeCol,List<Integer> paramCol,DataSchema data) throws IOException{
		System.out.println(Common.PROCESSING+path);
		InputStream is=new FileInputStream(path);
		HSSFWorkbook hssfWorkbook=new HSSFWorkbook(is);
		
		List<String > columnHeader=new ArrayList<String>();
		
		
		//read sheets
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet=hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet==null){
				continue;
			}
			JdbcUtils jd=new JdbcUtils();
			Connection cn=jd.getConnection();
			int rowNum=hssfSheet.getLastRowNum()+1;
			ParamsService ps=new ParamsService();
			//read the row
			try {
			for (int i =0 ; i<rowNum ; i++) {
					HSSFRow hssfRow=hssfSheet.getRow(i);
					if (hssfRow!=null) {
					int cellNum=hssfRow.getLastCellNum();
					if (i==0) {
						for (int j = 0; j < cellNum; j++) {
							HSSFCell cell=hssfRow.getCell(j);
							
							if (j==(timeCol-1)) {
								columnHeader.add(getValue(cell));
							}else {
								List<String> list=new ArrayList<String>();
								list.add(getValue(cell));
								Map<String , Object> map=jd.findSimpleResult(SqlConfig.paramIdSql,list );
								if (!map.isEmpty()) {
									columnHeader.add(map.get("param_id").toString());
								} else{
									columnHeader.add("N/A");
								}
								
								
							}
							
							//System.out.println(columnHeader.get(j));
						}
					}else {
						//System.out.println(getValue(xssfRow.getCell(timeCol-1)));
						String timestamp=Util.getTimestamp(getValue(hssfRow.getCell(timeCol-1)));
						
						//String timestamp=Util.getTimestamp("8/6/2015  2:23:22 PM");
						for (Iterator<Integer> iterator = paramCol.iterator(); iterator
								.hasNext();) {
							
								Integer col = (Integer) iterator.next();
								String paramId=columnHeader.get(col-1);
								HSSFCell cell=hssfRow.getCell(col-1);
								String record=getValue(cell);
								data.setTimeStamp(timestamp);
								data.setParamId(paramId);
								data.setValue(record);
								//System.out.println("time: "+timestamp+" param: "+parameter+" value: "+record);
								ps.saveParams(data,jd);
							
						}
					}
					}
			}} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jd.releaseConn();
				
			}
				
		}
		//return dataMap;
	}
	
	private String getValue(XSSFCell xssfCell){
		
		
		if (xssfCell.getCellType()==xssfCell.CELL_TYPE_NUMERIC){
			if (DateUtil.isCellDateFormatted(xssfCell)) {
				SimpleDateFormat sdfDateFormat=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				//System.out.println(DateUtil.getJavaDate(xssfRow.getNumericCellValue()).toString());
				return sdfDateFormat.format(DateUtil.getJavaDate(xssfCell.getNumericCellValue())).toString();
				
			}
			return String.valueOf(xssfCell.getNumericCellValue());
		}else /*if (xssfRow.getCellType()==xssfRow.CELL_TYPE_STRING)*/
			{
			return String.valueOf(xssfCell.getStringCellValue());
		}
		
	}
	
	private String getValue(HSSFCell hssfCell){
		
		if (hssfCell.getCellType()==hssfCell.CELL_TYPE_NUMERIC){
			if (DateUtil.isCellDateFormatted(hssfCell)) {
				SimpleDateFormat sdfDateFormat=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				//System.out.println(DateUtil.getJavaDate(xssfRow.getNumericCellValue()).toString());
				return sdfDateFormat.format(DateUtil.getJavaDate(hssfCell.getNumericCellValue())).toString();
				
			}
			return String.valueOf(hssfCell.getNumericCellValue());
		}else /*if (xssfRow.getCellType()==xssfRow.CELL_TYPE_STRING)*/
			{
			return String.valueOf(hssfCell.getStringCellValue());
		}
		
	}
	
	 
	
}
