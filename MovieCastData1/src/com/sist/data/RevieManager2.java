package com.sist.data;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class RevieManager2 {
	public List<String> movieLinkData()
	{
		List<String> list=new ArrayList<String>();
		try{
			for(int i=1; i<=40; i++)
			{
				Document doc=Jsoup.connect("https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date=20200517&page="+i).get();
				Elements link=doc.select("td.title div.tit5 a");
				for(int j=0; j<link.size(); j++)
				{
					String strLink=link.get(j).attr("href");
					strLink=strLink.substring(strLink.lastIndexOf("=")+1);
					list.add("https://movie.naver.com/movie/bi/mi/point.nhn?code="+strLink);
				}
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public int totalReviewCount(Element e)
	{
		int res=0;
		try{
			String totalReview=e.text();
			totalReview=totalReview.replaceAll(",", "");
			res=Integer.parseInt(totalReview);
			
			System.out.println(res);
			
			if(res%10==0)
			{
				res=res/10;
			}
			else
			{
				res=(res/10)+1;
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return res;
	}
	
	public String getUserIdSubstr(String user_id)
	{
		String res="";
		try{
			/*if(user_id.indexOf("(")==-1)
			{
				//System.out.println(user_id);
				res=user_id.replaceAll("\\*", "");
				
			}
			else
			{
				//System.out.println(user_id);
				res=user_id.substring(user_id.indexOf("(")+1,user_id.indexOf("*"));
				
			}*/
			res=user_id;
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
	
	public String[] getUserInfo(Elements e,int count)
	{
		String[] res=new String[2];
		int even=count*2;
		int odd=even+1;
		try{
			res[0]=e.get(even).text();
			res[1]=e.get(odd).text();
		}catch (Exception ex) {
		}
		return res;
	}
	public String getElementsAllString(Elements e,int count)
	{
		String res="";
		try{
			res=e.get(count).text();
		}catch (Exception ex) {}
		return res;
		
	}
	
	public String getElementString(Element e)
	{
		String res="";
		try{
			res=e.text();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return res;
	}
	
	public void movieReviewAllData()
	{
		try{
			List<String> list=movieLinkData();
			Elements allElements=null;
			Element reviewElement=null;
			ReviewDAO dao=new ReviewDAO();
			for(int i=0; i<list.size(); i++)
			{
					Document doc=Jsoup.connect(list.get(i)).get();
					Element iframeUrl=doc.selectFirst("iframe");
					String reviewUrl=iframeUrl.attr("src");
					Document doc2=Jsoup.connect("https://movie.naver.com/"+reviewUrl).get();
					Element totalElement=doc2.selectFirst(".total > em");
					int totalReviewtPage=totalReviewCount(totalElement);
					
				
					for(int j=1; j<=totalReviewtPage; j++)
					{
						doc2=Jsoup.connect("https://movie.naver.com/"+reviewUrl+"&page="+j).get();
						Elements reviewSizePer=doc2.select(".score_reple");
						for(int z=0; z<reviewSizePer.size(); z++)
						{
							ReviewVO vo=new ReviewVO();
							reviewElement=doc2.selectFirst(".score_reple #_filtered_ment_"+z);
							vo.setContent(getElementString(reviewElement));
							
							
							allElements=doc2.select(".star_score > em");
							vo.setRate(Integer.parseInt(getElementsAllString(allElements,z)));
							
							
							allElements=doc2.select(".score_reple > dl > dt > em");
							String[] user_info=getUserInfo(allElements, z);
							
							vo.setUser_id(getUserIdSubstr(user_info[0]));
							vo.setRegdate(user_info[1].substring(0,user_info[1].indexOf(" ")));
							vo.setMovie_id(Integer.parseInt(list.get(i).substring(list.get(i).lastIndexOf("=")+1)));
							
							System.out.println(vo.getMovie_id());
							System.out.println("user_id :" + vo.getUser_id());
							System.out.println("regdate : "+ vo.getRegdate());
							System.out.println(vo.getContent());
							System.out.println(vo.getRate());
							System.out.println("=================================================================");
							
							dao.reviewDataInsert(vo);
							
						}
						
					}
				}
					
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
}
