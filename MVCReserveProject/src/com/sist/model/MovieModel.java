package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MovieModel {
	@RequestMapping("movie/reserve.do")
	public String movie_reserve(HttpServletRequest request, HttpServletResponse response)
	{
		return "reserve.jsp";
	}
	@RequestMapping("movie/movie.do")
	public String movie_movie(HttpServletRequest request,HttpServletResponse response)
	{
		List<MovieVO> list=MovieDAO.movieListData();
		request.setAttribute("mList", list);
		
		return "movie.jsp";
	}
	@RequestMapping("movie/theater.do")
	public String movie_theater(HttpServletRequest request,HttpServletResponse response)
	{
		String tno=request.getParameter("tno");
		System.out.println(tno);
		// DAO
		
		// 결과값 theater.jsp
		StringTokenizer st=new StringTokenizer(tno,",");
		List<ReserveTheaterVO> list=new ArrayList<ReserveTheaterVO>();
		
		while(st.hasMoreTokens())
		{
			ReserveTheaterVO vo=MovieDAO.movieTheaterData(Integer.parseInt(st.nextToken()));
			list.add(vo);
		}
		
		request.setAttribute("tList",list);
		return "theater.jsp";
	}
	
	@RequestMapping("movie/date.do")
	public String movie_date(HttpServletRequest request,HttpServletResponse response)
	{
		String strYear=request.getParameter("year");
		String strMonth=request.getParameter("month");
		
		String reserve_date=request.getParameter("rdate");
		
		String today=new SimpleDateFormat("yyyy-M-d").format(new Date());
		StringTokenizer st=new StringTokenizer(today,"-");
		
		String sy=st.nextToken();
		String sm=st.nextToken();
		String sd=st.nextToken();
		
		if(strYear==null)
			strYear=sy;
		if(strMonth==null)
			strMonth=sm;
		
		int year=Integer.parseInt(strYear);
		int month=Integer.parseInt(strMonth);
		int day=Integer.parseInt(sd);
		
		String[] strWeek={"일","월","화","수","목","금","토"};
		
		int total=(year-1)*365
				+(year-1)/4
				-(year-1)/100
				+(year-1)/400;
		
		int[] lastDay={31,28,31,30,31,30,
					   31,31,30,31,30,31};
		if((year%4==0 && year%100!=0)||(year%400==0))
			lastDay[1]=29;
		else
			lastDay[1]=28;
		
		for(int i=0; i<month-1;i++)
		{
			total+=lastDay[i];
		}
		total++;
		
		int week=total%7;
		
		int[] days=new int[31];
		
		if(reserve_date!=null)
		{
			StringTokenizer st1=new StringTokenizer(reserve_date,",");
			while(st1.hasMoreTokens())
			{
				int p=Integer.parseInt(st1.nextToken());
				if(p>=day)
				{
					days[p-1]=p;
				}
				
			}
		}
		
		request.setAttribute("days", days);
		request.setAttribute("lastDay", lastDay[month-1]);
		request.setAttribute("week", week);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("strWeek", strWeek);
		
		return "date.jsp";
	}
	
	@RequestMapping("movie/time.do")
	public String movie_time(HttpServletRequest request,HttpServletResponse response)
	{
		String tno=request.getParameter("tno");
		String times=MovieDAO.movieTimeData(Integer.parseInt(tno));
		// 1 6 7 8 9...
		StringTokenizer st=new StringTokenizer(times,",");
		List<String> list=new ArrayList<String>();
		
		while(st.hasMoreTokens())
		{
			String time=MovieDAO.movieTimeData2(Integer.parseInt(st.nextToken()));
			list.add(time);
		}
		
		request.setAttribute("tList", list);
		return "time.jsp";
	}
	
	@RequestMapping("movie/inwon.do")
	public String movie_inwon(HttpServletRequest request,HttpServletResponse response)
	{
		return "inwon.jsp";
	}
	
	@RequestMapping("movie/login.do")
	public String movie_login(HttpServletRequest request,HttpServletResponse response)
	{
		return "login.jsp";
	}
	/*
	 	MVC ==>
	 		Model : ~VO,~DAO,Model
	 		View : 화면에 출력 (JSP)
	 						 request	      request => 처리 내용을 첨부 (addAttribute())
	 		Controller : 사용자 요청을 받아서 => 요청처리 내용을 전송
	 					 ================================
	 					 			 	배달
	 */
	@RequestMapping("movie/login_ok.do")
	public String movie_login_ok(HttpServletRequest request,HttpServletResponse response)
	{
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		MemberVO vo=MovieDAO.movieLogin(id, pwd);
		
		if(vo.getMsg().equals("OK"))
		{
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", vo.getName());
			session.setAttribute("admin", vo.getAdmin());
		}
		request.setAttribute("vo", vo);
		// DAO
		return "login_ok.jsp";
	}
	
	@RequestMapping("movie/admin.do")
	public String movie_admin(HttpServletRequest request, HttpServletResponse response)
	{
		List<ReserveVO> list=MovieDAO.movieAdmin();
		
		request.setAttribute("list", list);
		return "admin.jsp";
	}
	
	@RequestMapping("movie/mypage.do")
	public String movie_mypage(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		List<ReserveVO> list=MovieDAO.movieMyPage(id);
		
		request.setAttribute("list", list);
		return "mypage.jsp";
	}
	
	@RequestMapping("movie/reserve_ok.do")
	public String movie_reserve_ok(HttpServletRequest request, HttpServletResponse response)
	{
		ReserveVO vo=new ReserveVO();
		
		try{
			request.setCharacterEncoding("UTF-8");
		}catch (Exception ex) {}
		
		String mno=request.getParameter("mno");
		String tname=request.getParameter("tname");
		String rdate=request.getParameter("rdate");
		String rtime=request.getParameter("rtime");
		String rinwon=request.getParameter("rinwon");
		String rprice=request.getParameter("rprice");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		vo.setMno(Integer.parseInt(mno));
		vo.setTname(tname);
		vo.setRdate(rdate);
		vo.setRtime(rtime);
		vo.setRinwon(rinwon);
		vo.setRprice(rprice);
		vo.setId(id);
		
		// DAO => INSERT
		MovieDAO.movieReserveOk(vo);
		
		return "redirect:mypage.do";
	}
	
	@RequestMapping("movie/admin_update.do")
	public String movie_admin_update(HttpServletRequest request,HttpServletResponse response)
	{
		String rno=request.getParameter("rno");
		// DAO => UPDATE
		MovieDAO.adminUpdate(Integer.parseInt(rno));
		return "redirect:admin.do";
	}
	
	@RequestMapping("movie/reserve_result.do")
	public String movie_reserve_result(HttpServletRequest request,HttpServletResponse response)
	{
		String mno=request.getParameter("mno");
		MovieVO vo=MovieDAO.reserveResultData(Integer.parseInt(mno));
		request.setAttribute("vo", vo);
		return "reserve_result.jsp";
	}
}
