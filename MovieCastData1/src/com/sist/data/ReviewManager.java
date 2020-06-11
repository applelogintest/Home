package com.sist.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReviewManager {
	
	public int[] getMovieCount()
	{
		int[] result=new int[8];
		int i=0;
		int count=0;
		try{
			Document doc=Jsoup.connect("https://movie.naver.com/movie/sdb/browsing/bmovie_form.nhn").get();
			Elements countPer=doc.select("tbody td");
			/*for(Element a : countPer){
				System.out.println(a.text());
			}*/
			StringTokenizer st=new StringTokenizer(countPer.text()," ");
			
			while(st.hasMoreTokens())
			{
				String temp=st.nextToken();
				System.out.println("temp : " + temp);
				if(i%2==1)
				{
					temp=temp.replace("(", "");
					temp=temp.replace(")", "");
					result[count]=Integer.parseInt(temp);
					count++;
				}
				i++;
				
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public List<Integer> getMovieId()
	{
		List<Integer> list=new ArrayList<Integer>();
		String[] linkStr=new String[8];
		int[] movieCount=getMovieCount();
		
		try{
			Document doc=Jsoup.connect("https://movie.naver.com/movie/sdb/browsing/bmovie_form.nhn").get();
			Element link=null;
			for(int i=0; i<8; i++)
			{
				link=doc.select("tbody a").get(i);
				linkStr[i]=link.attr("href");
			}
			//linkStr.length
			for(int i=0; i<linkStr.length; i++)
			{
				int totalpage=0;
				if(movieCount[i]%20!=0)
					totalpage=(movieCount[i]/20)+1;
				else
					totalpage=movieCount[i]/20;
				//System.out.println("totalpage : " +totalpage);
				for(int j=1; j<=totalpage; j++)
				{
					Document doc2=Jsoup.connect("https://movie.naver.com/movie/sdb/browsing/"+linkStr[i]+"&page="+j).get();
					Elements mlinkSize=doc2.select(".directory_list > li > a");
					Element mlink=null;
					for(int k=0; k<mlinkSize.size(); k++)
					{
						mlink=doc2.select(".directory_list > li > a").get(k);
						String temp=mlink.attr("href");
						System.out.println("Movie Link Category("+i+") :"+temp);
						
						
						list.add(Integer.parseInt(temp.substring(temp.indexOf("=")+1)));
					}
					
				}
			}
			
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		int a=0;
		for(int c:list)
		{
			System.out.println(a+". "+c);
			a++;
			
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
	public List<ReviewVO> reviewListData()
	{
		List<ReviewVO> list=new ArrayList<ReviewVO>();
		List<Integer> movie_id=getMovieId();
		ReviewDAO dao=new ReviewDAO();
		Element reviewElement=null;
		Elements allElements=null;
		//movie_id.size()
		try{
			for(int i=0; i<movie_id.size(); i++)
			{
				//24239 movie_id.get(i)
				Document doc=Jsoup.connect("https://movie.naver.com/movie/bi/mi/point.nhn?code="+movie_id.get(i)).get();
				Element iframeUrl=doc.selectFirst("iframe");
				String reviewUrl=iframeUrl.attr("src");
				Document doc2=Jsoup.connect("https://movie.naver.com/"+reviewUrl).get();
				Element totalElement=doc2.selectFirst(".total > em");
				
				int totalReviewtPage=totalReviewCount(totalElement);
				
				System.out.println("totalReviewtPage :"+totalReviewtPage);
				/*int totalReviewCount=Integer.parseInt(totalElement.text());*/
				
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
						vo.setRegdate(user_info[1]);
						vo.setMovie_id(movie_id.get(i));
						
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
		
		return list;
		
	}
	
	public String getUserIdSubstr(String user_id)
	{
		String res="";
		try{
			if(user_id.indexOf("(")==-1)
			{
				//System.out.println(user_id);
				res=user_id.replaceAll("\\*", "");
				
			}
			else
			{
				//System.out.println(user_id);
				res=user_id.substring(user_id.indexOf("(")+1,user_id.indexOf("*"));
				
			}
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
}
