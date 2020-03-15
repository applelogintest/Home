package com.sist.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;


@WebServlet("/BoardFind")
public class BoardFind extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); // xml,html 어디로 보낼건지 선택하는 메소드
		PrintWriter out=response.getWriter(); //브라우저에서 읽어가는 위치를 잡아줌
		BoardDAO dao=new BoardDAO();
		String fs=request.getParameter("fs");
		String ss=request.getParameter("ss");
		ArrayList<BoardVO> list=dao.boardFindData(fs, ss);
		
		try {
			request.setCharacterEncoding("UTF-8");
		}catch (Exception ex) {}
		// 사용자 요청한 페이지를 받는다
		String strPage=request.getParameter("page");
		if(strPage==null)
			strPage="1";
		int curPage=Integer.parseInt(strPage);
		ArrayList<BoardVO> list2=dao.boardListData(curPage);
		int totalPage=dao.boardTotalPage();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=\"css/table.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>자유게시판</h1>");
		
		out.println("<table id=\"table_content\" width=700>");
		out.println("<tr>");
		out.println("<td align=left>");
		out.println("<a href=\"BoardInsert\">새글</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		
		out.println("<table id=\"table_content\" width=700>");
		out.println("<tr>");
		out.println("<th width=10%>번호</th>");
		out.println("<th width=45%>제목</th>");
		out.println("<th width=15%>이름</th>");
		out.println("<th width=20%>작성일</th>");
		out.println("<th width=10%>조회수</th>");
		out.println("</tr>");
		
		for(BoardVO vo:list)
		{
			
			out.println("<tr class=dataTr>");
			out.println("<td width=10% align=center>"+vo.getNo()+"</td>");
			out.println("<td width=45% align=left>");
			out.println("<a href=BoardDetailServlet?no="+vo.getNo()+">");
			out.println(vo.getSubject()+"</a>");
			out.println("<td width=15% align=center>"+vo.getName()+"</td>");
			out.println("<td width=20% align=center>"+vo.getRegdate()+"</td>");
			out.println("<td width=10% align=center>"+vo.getHit()+"</td>");
			out.println("</tr>");
			
		}
		out.println("</table>");
		
		out.println("<table id=\"table_content\" width=700>");
		out.println("<tr>");
		out.println("<td align=left>");
		out.println("Search:");
		out.println("<select>");
		out.println("<option>이름</option>");
		out.println("<option>제목</option>");
		out.println("<option>내용</option>");
		out.println("</select>");
		out.println("<input type=text size=15>");
		out.println("<input type=submit value=찾기 name=find>");
		out.println("</td>");
		out.println("<td align=right>");
		out.println("<a href=\"BoardListServlet?page="+(curPage>1?curPage-1:curPage)+"\">&lt;이전&gt;</a>");
		/*
		 *  특수문자
		 *  	&nbsp; " "
		 		&lt; <
		 		&gt; >
		 */
		out.println(curPage+" page / "+totalPage+" page");
		out.println("<a href=\"BoardListServlet?page="+(curPage<totalPage?curPage+1:curPage)+"\">&lt;다음&gt;</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

}
