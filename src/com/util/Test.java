package com.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */ 
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

		//String path="C:\\Users\\yilangli\\Desktop\\site.xls";
		//String path="C:\\Users\\yilangli\\Desktop\\1.xlsx";
		//new ReadMapping().readMapping(path,"1.xlsx");
		/*String timeString="8/6/2015  2:23:22 PM";
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		Date date=sdf.parse(timeString);
		Timestamp timestamp=new Timestamp(date.getTime());
		System.out.println(date.getTime());*/
		System.out.println(Util.getRandomColor());
		
	}

}
