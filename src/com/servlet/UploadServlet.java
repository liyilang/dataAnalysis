package com.servlet;

import java.io.File; 
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.common.Common;
import com.jdbc.JdbcUtils;
import com.sql.SqlConfig;
import com.util.ReadMapping;


/**
 * 
 *  æ•°æ�®æº�æ–‡ä»¶ä¸Šä¼ 
 *  
 */
public class UploadServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
      
    private String filePath;       
    private String tempPath;      
    private String initPath;   
    private String cachePath;
        
    public void init(ServletConfig config) throws ServletException    
    {    
        super.init(config);    
        filePath = config.getInitParameter("filepath");    
        tempPath = config.getInitParameter("temppath"); 
        initPath = config.getInitParameter("initPath"); 
        cachePath = config.getInitParameter("cachePath"); 
   
        ServletContext context = getServletContext();    
   
        filePath = context.getRealPath(filePath);    
        tempPath = context.getRealPath(tempPath); 
        initPath = context.getRealPath(initPath); 
        cachePath = context.getRealPath(cachePath);
          
        File pathFile = new File(filePath);  
        File pathTemp = new File(tempPath);  
        if(!pathFile.exists()){  
            pathFile.mkdirs();  
        }  
        if(!pathTemp.exists()){  
            pathTemp.mkdirs();  
        }  
    }    
        
    // doPost    
    public void doPost(HttpServletRequest req, HttpServletResponse res)    
        throws IOException, ServletException    
    { 	
    	long start=System.currentTimeMillis();
    	req.setCharacterEncoding("UTF-8");
        res.setContentType("text/plain;charset=UTF-8");    
        PrintWriter pw = res.getWriter();    
        try{    
            DiskFileItemFactory diskFactory = new DiskFileItemFactory();    
            diskFactory.setSizeThreshold(1000 * 1024 * 1024);    
            diskFactory.setRepository(new File(tempPath));    
            
            ServletFileUpload upload = new ServletFileUpload(diskFactory);    
            upload.setSizeMax(1000 * 1024 * 1024);    
            List<FileItem> fileItems = upload.parseRequest(new ServletRequestContext(req));    
            Iterator<FileItem> iter = fileItems.iterator();    
            while(iter.hasNext())    
            {    
                FileItem item = (FileItem)iter.next();    
                if(item.isFormField())    
                {    
                    processFormField(item, pw);    
                }else{    
                    processUploadFile(item, pw,req);    
                }    
            }
            pw.close();    
            
           System.out.println(System.currentTimeMillis()-start);
        }catch(Exception e){    
            e.printStackTrace();    
        }    
    }
   
   
    private void processFormField(FileItem item, PrintWriter pw)    
        throws Exception    
    {    
        String name = item.getFieldName();    
        String value = item.getString();            
    }    
        
    private void processUploadFile(FileItem item, PrintWriter pw,HttpServletRequest req)    
        throws Exception    
    {    
        String filename = item.getName();           
        int index = filename.lastIndexOf("\\");    
        filename = filename.substring(index + 1, filename.length());    
   
        long fileSize = item.getSize();    
   
        if("".equals(filename) && fileSize == 0)    
        {               
            return;    
        }    
   
        File uploadFile = new File(filePath + "/" + filename);    
        if(!uploadFile.exists()){  
            uploadFile.createNewFile();  
        }  
        item.write(uploadFile); 
        
        
      
        JdbcUtils ju = new JdbcUtils();
		ju.getConnection();
        String time = req.getParameter("time");
        String parameters = req.getParameter("parameters");
         
        
        String asset_id = req.getParameter("assetId");
        //String[] paramsArray = parameters.split(",");
        System.out.println(asset_id);
                 
       // for (String param : paramsArray) {
            List<String> params = new ArrayList<String>();   
            params.add(asset_id);
        	params.add(time); 
        	params.add(parameters);
        	params.add(filename);
        	
        	ju.updateByPreparedStatement(SqlConfig.mapingsql, params);
       // }
        
        
       
        String filePath = getServletContext().getRealPath("/")+"initUpload\\"+filename;
        
        Common.servletURL=getServletContext().getRealPath("/");
        
        
        new ReadMapping().readMapping(filename,filePath);
			
		
        
        
        
        pw.println("data collected done");    
    }    
        
    public void doGet(HttpServletRequest req, HttpServletResponse res)    
        throws IOException, ServletException    
    {    
        doPost(req, res);    
    }

}  
