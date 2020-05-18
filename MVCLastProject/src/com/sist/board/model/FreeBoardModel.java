package com.sist.board.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

/*							Model => DAO,Service,VO (중복이되어서 따로 클래스로 나눠서 작업한것 결론은 Model하나는 DAO,Service,VO들을 합친것과 같음)
 * 	사용자 요청 (페이지) =======> Model(DAO) ============================> JSP출력
 * 							=======
 * 							@RequestMapping(사용자가 요청한 URI주소)
 */

@Controller
public class FreeBoardModel {
	@RequestMapping("freeboard/list.do")
	public String freeboard_list(HttpServletRequest request,HttpServletResponse response)
	{
		// page
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		FreeBoardDAO dao=new FreeBoardDAO();
		List<BoardVO> list=dao.freeboardListData(curpage);
		int totalpage=dao.freeboardTotalPage();
		
		
		request.setAttribute("today", new SimpleDateFormat("YYYY-MM-DD").format(new Date()));
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("freeboard/insert.do")
	public String freeboard_insert(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../freeboard/insert.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request, HttpServletResponse response)
	{
		// 메소드 => 매개변수가 3개 이상이면 클래스로 묶어서 전송
		//				   ============== ~VO (구조체(여러변수를 묶어준다) => 클래스)
		// 사용자 보낸 데이터를 받는다
		try{
			request.setCharacterEncoding("UTF-8"); // 디코딩( 송신: 인코딩, 수신: 디코딩)
			// ASC (1byte) => Unicode (2byte)
		}catch (Exception ex) {}
		
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setContent(content);
		vo.setSubject(subject);
		vo.setPwd(pwd);
		
		// DAO로 전송 => DAO에서 오라클로 보내준다
		FreeBoardDAO dao=new FreeBoardDAO();
		dao.freeboardInsert(vo);
		return "redirect:../freeboard/list.do";
	}
	
	@RequestMapping("freeboard/detail.do")
	public String freeboard_detail(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		String type=request.getParameter("type");
		String page=request.getParameter("page");
		System.out.println("no : " +no);
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=10;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		
		map.put("pStart", start);
		map.put("pEnd", end);
		map.put("pBno", Integer.parseInt(no));
		
		List<BoardReplyVO> list=FreeBoardReplyDAO.replyListData(map);
		
		map=new HashMap();
		map.put("pBno", Integer.parseInt(no));
		int totalpage=FreeBoardReplyDAO.replyTotalPage(map);
		
		// no 주고 => vo 를 받는다.
		FreeBoardDAO dao=new FreeBoardDAO();
		//vo를 받아온다
		BoardVO vo=dao.freeboardInfoData(Integer.parseInt(no), Integer.parseInt(type));
		//jsp로 보내준다
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/update.do")
	public String freeboard_update(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		String type=request.getParameter("type");
		
		// no 주고 => vo 를 받는다.
		FreeBoardDAO dao=new FreeBoardDAO();
		//vo를 받아온다
		BoardVO vo=dao.freeboardInfoData(Integer.parseInt(no), Integer.parseInt(type));
		//jsp로 보내준다
		System.out.println(vo.getNo());
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/update.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/update_ok.do")
	public String freeboard_update_ok(HttpServletRequest request, HttpServletResponse response)
	{
		// 메소드 => 매개변수가 3개 이상이면 클래스로 묶어서 전송
		//				   ============== ~VO (구조체(여러변수를 묶어준다) => 클래스)
		// 사용자 보낸 데이터를 받는다
		try{
			request.setCharacterEncoding("UTF-8"); // 디코딩( 송신: 인코딩, 수신: 디코딩)
			// ASC (1byte) => Unicode (2byte)
		}catch (Exception ex) {}
		
		String no=request.getParameter("no");
		
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setContent(content);
		vo.setSubject(subject);
		vo.setPwd(pwd);
		vo.setNo(Integer.parseInt(no));
		// DAO로 전송 => DAO에서 오라클로 보내준다
		FreeBoardDAO dao=new FreeBoardDAO();
		// update 요청
		Boolean bCheck=dao.freeboardUpdate(vo);
		System.out.println(bCheck);
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("no", no);
		return "../freeboard/update_ok.jsp";
	}
	
	/*
	 *  사용자 
	 *  	=> delete.do?no=10 ==> Model ==> 요청한 JSP로 값을 전송(화면에 출력)
	 *  						  ======= 
	 *  						  사용자가 보내준 값을 받는다
	 */
	@RequestMapping("freeboard/delete.do")
	public String freeboard_delete(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		request.setAttribute("no",no);
		request.setAttribute("main_jsp", "../freeboard/delete.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/delete_ok.do")
	public String freeboard_delete_ok(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		
		FreeBoardDAO dao=new FreeBoardDAO();
		Boolean bCheck=dao.freeboardDelete(Integer.parseInt(no), pwd);
		request.setAttribute("bCheck",bCheck);
		return "../freeboard/delete_ok.jsp";
	}
	
	@RequestMapping("freeboard/reply_insert.do")
	public String freeboard_reply_insert(HttpServletRequest request,HttpServletResponse response)
	{
		try{
			request.setCharacterEncoding("UTF-8");
		}catch (Exception ex) {}
		String bno=request.getParameter("bno");
		String msg=request.getParameter("msg");
		HttpSession session=request.getSession(); //request => Session, Cookie 얻어올수있음
		
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("pBno", Integer.parseInt(bno));
		map.put("pId", id);
		map.put("pName", name);
		map.put("pMsg", msg);
		// insert 처리
		FreeBoardReplyDAO.replyInsert(map);
		return "redirect:../freeboard/detail.do?no="+bno+"&type=2";
	}
	
	@RequestMapping("freeboard/reply_update.do")
	public String freeboard_reply_update(HttpServletRequest request,HttpServletResponse response)
	{
		try{
			request.setCharacterEncoding("UTF-8");
		}catch (Exception ex) {}
		String bno=request.getParameter("bno");
		String no=request.getParameter("no");
		String msg=request.getParameter("msg");
		
		
		Map map=new HashMap();
		
		map.put("pNo", Integer.parseInt(no));
		map.put("pMsg", msg);
		
		FreeBoardReplyDAO.replyUpdate(map);
		
		return "redirect:../freeboard/detail.do?no="+bno+"&type=2";
	}
	
	@RequestMapping("freeboard/reply_reply_insert.do")
	public String freeboard_reply_reply_insert(HttpServletRequest request,HttpServletResponse response)
	{
		try{
			request.setCharacterEncoding("UTF-8");
		}catch (Exception ex) {}
		
		String bno=request.getParameter("bno");
		String pno=request.getParameter("pno");
		String msg=request.getParameter("msg");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		
		Map map=new HashMap();
		
		map.put("pBno", Integer.parseInt(bno));
		map.put("pPno", Integer.parseInt(pno));
		map.put("pId",id);
		map.put("pName", name);
		map.put("pMsg", msg);
		
		//DAO
		FreeBoardReplyDAO.replyReplyInsert(map);
		return "redirect:../freeboard/detail.do?no="+bno+"&type=2";
	}
	@RequestMapping("freeboard/reply_delete.do")
	public String freeboard_reply_delete(HttpServletRequest request,HttpServletResponse response)
	{
		String no=request.getParameter("no");
		String bno=request.getParameter("bno");
		Map map=new HashMap();
		
		map.put("pNo", Integer.parseInt(no));
		
		// DAO
		FreeBoardReplyDAO.replyDelete(map);
		return "redirect:../freeboard/detail.do?no="+bno+"&type=2";
	}
}











