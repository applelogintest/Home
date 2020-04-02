package com.sist.board.model;

import java.util.*;
import com.sist.dao.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.common.model.CommonData;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
//http://localhost/MVCProject7/site/board/list.do
@Controller
public class BoardModel {
	@RequestMapping("site/board/list.do")
	public String board_list(HttpServletRequest request, HttpServletResponse response){
		List<EmpVO> list=EmpDAO.empAllData();
		
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "board/list.jsp");
		CommonData.commonData(request);
		return "../main.jsp";
	}
}
