<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,com.oreilly.servlet.*,com.oreilly.servlet.multipart.*"%>
<%@page import="java.io.*" %>
<%
	try{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception ex){}
	
	// request DefaultFileRenamePolicy() => 같은 파일명이 들어오면 뒤에 첨자번호를 붙여줌 (사용자가 많으면 같은이름으로 파일을 저장할수 있기 때문에 필요하다)
	String path="c:\\upload";
	int maxSize=100*1024*1024;
	String enctype="UTF-8";
	MultipartRequest mr=new MultipartRequest(request,path,maxSize,enctype,
			new DefaultFileRenamePolicy()); 
	
	String name=mr.getParameter("name");
	String subject=mr.getParameter("subject");
	String content=mr.getParameter("content");
	String pwd=mr.getParameter("pwd");
	String filename=mr.getOriginalFileName("upload"); // 오리지날 파일네임은 사용자가 보내준 파일명이 저장됨 (삭제시 문제가 생김)
	String fn=mr.getFilesystemName("upload"); // 파일시스템네임은 사용자가 중복된 이름으로 파일을 보냈을 경우 뒤에 첨자가 붙은 파일이 저장되는데 이때 생긴 파일명을 읽어온다  
	FileBoardVO vo=new FileBoardVO();
	
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	if(filename==null)
	{
		vo.setFilename("");
		vo.setFilesize(0);
	}
	else
	{
		File file=new File(path+"\\"+fn);
		vo.setFilename(fn);
		vo.setFilesize((int)file.length());
	}
	
	FileBoardDAO dao=new FileBoardDAO();
	//insert
	dao.boardInsert(vo);
	//이동
	response.sendRedirect("list.jsp");
	
%>

