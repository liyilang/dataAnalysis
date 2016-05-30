package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.common.Common;



public class Util {

	public static String getPostfix(String path) {
		if (path==null || Common.EMPTY.equals(path.trim())){
			return Common.EMPTY;
		}
		if (path.contains(Common.POINT)) {
			return path.substring(path.lastIndexOf(Common.POINT)+1,path.length());
		}
		return Common.EMPTY;
	}
	
	public static String  getTimestamp(String time) {
			
			//System.out.println(time);
			if (time==null) {
				return "Unparsed Time";
			}
			Date date = DateUtils.parseDate(time);
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time=df.format(date);
			//System.out.println(date.getTime());
			if (time==null) {
				return "Unparsed Time";
			}
			return time;
	}	
	
	public static String getRandomColor(){
		
		 String r,g,b;  
		  Random random = new Random();  
		  r = Integer.toHexString(random.nextInt(256)).toUpperCase();  
		  g = Integer.toHexString(random.nextInt(256)).toUpperCase();  
		  b = Integer.toHexString(random.nextInt(256)).toUpperCase();  
		    
		  r = r.length()==1 ? "0" + r : r ;  
		  g = g.length()==1 ? "0" + g : g ;  
		  b = b.length()==1 ? "0" + b : b ;  
		    
		  return r+g+b;  
	}
}
