package com.sist.recipe.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.RecipeDAO;

import java.util.*;
import com.sist.vo.*;

/*
 	사용자 요청
 	========
 	<a> ==> link ==> Model (@RequestMapping) => SQL => DAO => Model => JSP
 	<button> => 
 */
@Controller
public class RecipeModel {
	@RequestMapping("recipe/recipe.do")
	public String recipe_recipe(HttpServletRequest request,HttpServletResponse response)
	{
		// 요청 => 처리
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<RecipeVO> list=RecipeDAO.recipeListData(map);
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>15)
			{
				title=title.substring(0,13).concat("...");
				vo.setTitle(title);
			}
		}
		int totalpage=RecipeDAO.recipeTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		// 1~10 , 11~20

		int allPage=totalpage;
		if(endPage>allPage)
			endPage=allPage;
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("BLOCK", BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("allPage", allPage);
		request.setAttribute("main_jsp", "../recipe/recipe.jsp");
		
		return "../main/main.jsp";
	}
	
	@RequestMapping("recipe/chef.do")
	public String recipe_chef(HttpServletRequest request,HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=30;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=(rowSize*curpage);
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<ChefVO> list=RecipeDAO.chefListData(map);
		int totalpage=RecipeDAO.chefTotalPage();
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		// 1~10 , 11~20

		int allPage=totalpage;
		if(endPage>allPage)
			endPage=allPage;
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("BLOCK", BLOCK);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("allPage", allPage);
		request.setAttribute("main_jsp", "../recipe/chef.jsp");
		
		return	"../main/main.jsp"; 
	}
	
	@RequestMapping("recipe/recipe_detail.do")
	public String recipe_detail(HttpServletRequest request, HttpServletResponse response)
	{
		String no=request.getParameter("no");
		// DAO
		int count=RecipeDAO.recipeCount(Integer.parseInt(no));
		
		if(count!=0)
		{
			RecipeDetailVO vo=RecipeDAO.recipeDetailData(Integer.parseInt(no));
			vo.setFoodmake(vo.getFoodmake().replace("\n", "@")); 
			request.setAttribute("vo", vo);
		}
		request.setAttribute("count", count);
		request.setAttribute("main_jsp", "../recipe/recipe_detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("recipe/chef_detail.do")
	public String recipe_chef_detail(HttpServletRequest request, HttpServletResponse response)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		
		String chef_name=request.getParameter("name");
		
		System.out.println("chef_name:"+chef_name);
		// DAO 연결
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("chef", chef_name);
		
		List<RecipeVO> list=RecipeDAO.chefDetailData(map);
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>15)
			{
				title=title.substring(0,13).concat("...");
				vo.setTitle(title);
			}
		}
		int totalpage=RecipeDAO.chefDataTotalPage(chef_name);
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("chef", chef_name);
		
		request.setAttribute("main_jsp", "../recipe/chef_detail.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("recipe/recipe_find.do")
	public String recipe_find(HttpServletRequest request, HttpServletResponse response)
	{
		String no=request.getParameter("no");
		if(no==null)
			no="1";
		String[] data={
				"전체","밑반찬","메인반찬","국/탕","찌개",
				"초스피드","손님접대","밥/죽/떡","술안주","면/만두",
				"일상","빵","다이어트","디저트","샐러드",
				"양식","김치/젓갈","도시락","간식","돼지고기",
				"영양식","과자","양념/소스","차/음료/술","닭고기",
				"야식","채소류","볶음","스프","소고기",
				"해물류","푸드스타일","육류","달걀/유제","부침",
				"조림","이유식","부침","해장","명절",
				"버섯류","가공식품","과일류","튀김","끓이기",
				"찜","비빔","밀가루","건어물류","절임",
				"굽기","삶기","회","쌀","콩/견과류",
				"곡류","데치기","퓨전"
		};
		List<String> nlist=Arrays.asList(data);
		request.setAttribute("nlist", nlist);
		request.setAttribute("main_jsp", "../recipe/recipe_find.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("recipe/recipe_find_ok.do")
	public String recipe_find_ok(HttpServletRequest request, HttpServletResponse response)
	{
		String[] data={
				"전체","밑반찬","메인반찬","국/탕","찌개",
				"초스피드","손님접대","밥/죽/떡","술안주","면/만두",
				"일상","빵","다이어트","디저트","샐러드",
				"양식","김치/젓갈","도시락","간식","돼지고기",
				"영양식","과자","양념/소스","차/음료/술","닭고기",
				"야식","채소류","볶음","스프","소고기",
				"해물류","푸드스타일","육류","달걀/유제","부침",
				"조림","이유식","부침","해장","명절",
				"버섯류","가공식품","과일류","튀김","끓이기",
				"찜","비빔","밀가루","건어물류","절임",
				"굽기","삶기","회","쌀","콩/견과류",
				"곡류","데치기","퓨전"
		};
		String no=request.getParameter("no");
		List<RecipeVO> list=RecipeDAO.recipeFindData(data[Integer.parseInt(no)-1].replace("/", "|"));
		for(RecipeVO vo:list)
		{
			String title=vo.getTitle();
			if(title.length()>12)
			{
				title=title.substring(0,9).concat("...");
				vo.setTitle(title);
			}
		}
		request.setAttribute("data", data);
		request.setAttribute("list", list);
		return "../recipe/recipe_find_ok.jsp";
	}

}
