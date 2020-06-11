package com.sist.data;
import java.util.*;

public class JsonMainClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			ReviewAnalysisDAO dao=new ReviewAnalysisDAO();
			List<ReviewAnalysisVO> list=dao.reviewList();
			ReviewJson rjson=new ReviewJson();
			rjson.reviewToJson(list);
		}catch (Exception ex) {}
		
	}

}
