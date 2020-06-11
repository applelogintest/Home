package com.sist.data;
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CastManager cm=new CastManager();
		RevieManager2 rm=new RevieManager2();
		try{
			/*cm.castAllData();*/
			/*int[] result=rm.getMovieCount();*/
			rm.movieReviewAllData();
			
			/*for(ReviewVO vo:list)
			{
				System.out.println(vo.getUser_id());
			}*/
		}catch (Exception ex) {}
		
	}

}
