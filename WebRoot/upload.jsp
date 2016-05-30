<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>网关云端登录</title>
<script type="text/javascript">
   function upload() {       
       var time = document.getElementById("time").value;
       if (time==null || ""==time) {
          alert("请填写time在excel中对应的列数!");
          return;
       }
       var parameters = document.getElementById("parameters").value;
       if (parameters==null || ""==parameters) {
         alert("请填写parameters在excel中对应的列数");
         return;
       }
       document.uploadForm.action="uploadServlet?time="+time+"&parameters="+parameters;
       document.uploadForm.submit();
   }  
</script>
</head>
	<body>  
	      <form name="uploadForm" method="post" enctype="multipart/form-data" action="uploadServlet">  
	      <table align="center">	       
	        <tr height="40px;">
	           <td>请上传文件:</td>
	           <td colspan="2"><input type="file" name="file1"/></td>
	        </tr>
	         <tr>
	          <td colspan="3">--------------------------------------------------------------------------------------------------------------------</td>
	        </tr>	       
	        <tr  align="center">
	           <td colspan="3">请列出右边值在excel中对应的列数</td>	           
	        </tr>
	        <tr>
	           <td>
	               <input type="text"  name="time" id="time"/>
	           </td>
	           <td>-------></td>
	           <td>
	              <input readonly="readonly"  value="time"/>
	           </td>
	        </tr>
	        <tr>
	           <td>
	               <input type="text"  name="parameters" id="parameters"/>
	           </td>
	           <td>--------></td>
	           <td>
	              <input readonly="readonly" value="parameters" />
	           </td>
	        </tr>	       
	        <tr height="40px;">
	           <td colspan="3" align="right"><input type="button"  value="upload" onclick="upload();">&nbsp;&nbsp;<input type="reset" name="reset" value="reset"> </td>
	        </tr>
	      </table>
	    </form> 
	</body>  
</html>
