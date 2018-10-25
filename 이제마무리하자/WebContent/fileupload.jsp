
<%@page import="java.util.Date"%>
<%@page import="javax.xml.crypto.Data"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String file = request.getParameter("fileload");
System.out.println("file:" + file);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pdsupload.jsp</title>
</head>
<body>

	<%!
public String processUploadFile(FileItem fileItem, String dir, JspWriter out) 
											throws IOException{
	String f = fileItem.getName();
	long sizeInBytes = fileItem.getSize();
	
	String fileName = ""; 
	String fpost="";
	
	// 업로드한 파일이 정상일 경우
	if(sizeInBytes > 0){	// d:\\tmp\\abc.jpg    d:/tmp/abc.jpg
		/* int idx = fileName.lastIndexOf("\\");
		if(idx == -1){
			idx = fileName.lastIndexOf("/");
		}
		fileName = fileName.substring(idx + 1);
		 */		
		 
		if(f.indexOf('.')>=0){
			fpost=f.substring(f.indexOf('.'));			
			fileName=new Date().getTime()+fpost;
		}else{
			fileName=new Date().getTime()+".back";
		} 
		 
		try{
			File uploadFile = new File(dir, fileName);
			fileItem.write(uploadFile);	// 실제 업로드하는 부분
		}catch(Exception e){}		
	}	
	
	return fileName;
}
%>

	<%
/*
주의점
	한글파일 : 사용자제
	자료실에 올릴시에는 time에서 숫자를 취득후에 그 숫자로 파일명으로 하는 경우가 많음
	tomcat 서버에 올릴 시에는 서버를 down시키면 파일이 소멸된다. 
*/
// tomcat 배포
// String fupload = application.getRealPath("/upload");

// 지정(개인) 폴더 저장
String fupload = "c:\\tmp";

System.out.println("파일업로드:" + fupload);

String yourTempDirectory = fupload;

int yourMaxRequestSize = 100 * 1024 * 1024;	// 1M
int yourMaxMemorySize = 100 * 1024;

// form field 에 데이터(String)
String id = "";
String title = "";
String content = "";

// file data
String filename = "";

boolean isMultipart = ServletFileUpload.isMultipartContent(request);
if(isMultipart){
	
	////////////////////// file
	
	// FileItem 오브젝트를 생성하는 클래스
	DiskFileItemFactory factory = new DiskFileItemFactory();
	
	factory.setSizeThreshold(yourMaxMemorySize);
	factory.setRepository(new File(yourTempDirectory));
	
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setSizeMax(yourMaxRequestSize);	// 파일 업로드 최대 크기	
	
	///////////////////////////
	
	List<FileItem> items = upload.parseRequest(request);
	Iterator<FileItem> it = items.iterator();

	while(it.hasNext()){	
		FileItem item = it.next();
		if(item.isFormField()){
			if(item.getFieldName().equals("id")){
				id = item.getString("utf-8");
			}else if(item.getFieldName().equals("title")){
				title = item.getString("utf-8");
			}else if(item.getFieldName().equals("content")){
				content = item.getString("utf-8");
			}
		}		
		else{ // fileload
			if(item.getFieldName().equals("fileload")){
				filename = processUploadFile(item, fupload, out);
			}
			System.out.println("fupload:" + fupload);
		}	
	}	
	
}else{
	// multipart type 아님
}



%>
	<script type="text/javascript">

	location.href = "PdsControl?command=ad_pdsWrite&id=<%=id%>&title=<%=title%>&content=<%=content%>&filename=<%=filename%>";	
</script>


</body>
</html>








