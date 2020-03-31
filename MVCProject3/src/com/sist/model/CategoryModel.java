package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;
import java.util.*;

public class CategoryModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<CategoryVO> list=FoodDAO.categoryListData();
		request.setAttribute("list",list);
		
		return "food/category.jsp";
	}

}
