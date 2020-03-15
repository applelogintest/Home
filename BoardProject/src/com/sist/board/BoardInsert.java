package com.sist.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;

@WebServlet("/BoardInsert")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>"); //html 5.0 버전
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=\"css/table.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>글쓰기</h1>");
		
		out.println("<form method=post action=BoardInsert>"); //데이터 보낸후 받아서 처리할때 post 화면만 출력할때는 host
		out.println("<table id=\"table_content\" width=500>");
		out.println("<tr>");
		// th => center 정렬  td => left정렬 (default)
		out.println("<th width=15% align=rigth>이름</th>");
		out.println("<td width=85%>");
		out.println("<input type=text name=name size=15 required>"); // required는 5.0버전에서만 사용가능
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th width=15% align=rigth>제목</th>");
		out.println("<td width=85%>");
		out.println("<input type=text name=subject size=50 required>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th width=15% align=rigth valign=top>내용</th>");
		out.println("<td width=85%>");
		out.println("<textarea rows=8 cols=55 name=content required></textarea>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th width=15% align=rigth>비밀번호</th>");
		out.println("<td width=85%>");
		out.println("<input type=password name=pwd size=10 required>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td colspan=2 align=center>");
		out.println("<input type=submit value=글쓰기>");
		out.println("<input type=button value=취소 onclick=\"javascript:history.back()\">");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
				request.setCharacterEncoding("UTF-8"); //한글 변환
		}catch (Exception ex) {}
		
		// 사용자가 보낸 데이터 받기
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		// 오라클 연동 => DAO 메소드를 호출
		BoardDAO dao=new BoardDAO();
		dao.boardInsert(vo);
		// 이동
		response.sendRedirect("BoardListServlet");
	}

}
