package com.sist.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;
public class CastManager {
	
	public void castAllData() throws Exception{
		List<CastVO> list=new ArrayList<CastVO>();
		CastDAO dao=CastDAO.newInstance();
		int count=1;
		//438564
		try{
			for(int i=1; i<=438564; i++)
			{
					CastVO vo=new CastVO();
					
					Document doc=Jsoup.connect("https://movie.naver.com/movie/bi/pi/basic.nhn?code="+i).get();
					
					Element name=doc.select(".character > .h_movie").first();
					try{
						vo.setName(name.text());
					}catch (Exception ex) {
						vo.setName("");
					}
					
					Element birth=doc.select(".char_info> .char_born").first();
					try{
						vo.setBirth(birth.text());	
					}catch (Exception ex) {
						vo.setBirth("");
					}
					
					
					Element thmbnail=doc.select(".poster > img").first();
					try{
						String img=thmbnail.attr("alt");
						System.out.println(img);
						
						if(!img.equals("이미지가 존재하지 않습니다."))
						{
							String temp=thmbnail.attr("src");
							vo.setThumbnail(temp.substring(0,temp.indexOf("&")));
						}
						else
						{
							vo.setThumbnail(thmbnail.attr("src"));
						}
					}catch (Exception ex) {
						vo.setThumbnail("https://ssl.pstatic.net/static/movie/2012/06/dft_img77x96_1.png");
					}
					
					
					Element profile=doc.select(".con_tx").first();
					try{
					vo.setProfile(profile.text());
					}catch (Exception ex) {
						vo.setProfile("");
					}
					
					
					Element reward=doc.select(".char_award").first();
					try{
						vo.setReward(reward.text());
					}catch (Exception ex) {
						vo.setReward("");
					}
					

					vo.setCast_id(i);
					
					System.out.print(vo.getCast_id()+". ");
					
					
					try{
						System.out.println(name.text());
						System.out.println(birth.text());
						System.out.println(profile.text());
						System.out.println(vo.getThumbnail());
						System.out.println(reward.text());
					}catch (Exception ex) {}
					
					System.out.println("============================================================================================================================================================================");
					
					
					list.add(vo);
				
			}
			
			System.out.println("Cast Data Insert......");
			for(CastVO vo:list)
			{
				dao.castDataInsert(vo);
			}
			System.out.println("Cast Data End......");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
