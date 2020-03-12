<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,java.net.*"%>
<%
	//GET 방식은 톰캣에서 처리를 해주고 와야해서 따로 인코딩할 필요 x (server.xml 63line:  <Connector URIEncoding="UTF-8" connectionTimeout="20000" port="80" protocol="HTTP/1.1" redirectPort="8443"/>)
	String fn=request.getParameter("fn");
	response.setHeader("Content-Disposition", "attachment;filename="
						+URLEncoder.encode(fn,"UTF-8"));
	File file=new File("c:\\upload\\"+fn); // 파일경로에 있는 파일
	response.setContentLength((int)file.length());
	try{
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
		
		int i=0; 
		byte[] buffer=new byte[1024]; //TCP에서 가장 빠른속도로 보낼수있는 바이트 단위가 1024byte
		while((i=bis.read(buffer,0,1024))!=-1)
		{
			bos.write(buffer,0,i);
		}
		out.clear();
		out=pageContext.pushBody(); //out 객체는 기본객체이므로 위에서 업로드용으로 썼으니 다시 원래 상태로 돌려줘야한다
		
		bis.close();
		bos.close();
		
	}catch(Exception ex){}

%>