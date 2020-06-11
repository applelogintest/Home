package com.sist.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 	articleDiv.first() // 첫 번째 div에서 썸네일 url을 얻어온다.
                                .attr("style")
                                .replace("background-image:url(", "")
                                .replace(")", "");
 */
public class NewsManager {
	public void newsAllData()
	{
		NewsDAO dao=new NewsDAO();
		try{
			
			for(int i=1; i<=8; i++){
				Document doc=Jsoup.connect("https://movie.daum.net/magazine/new?tab=nws&regdate=20200522&page="+i).get();
				Elements newsList=doc.select(".tit_line");
				for(int j=0; j<newsList.size(); j++)
				{
					NewsVO vo=new NewsVO();
					Element titleElement=doc.select(".tit_line").get(j);
					String title=newsElementTextData(titleElement);
					
					Element linkElement=doc.select(".tit_line a").get(j);
					String link=newsElementAttrData(linkElement,"href");
					
					Element subjectElement=doc.select(".desc_line").get(j);
					String subject=newsElementTextData(subjectElement);
					
					Element thumbnailElement=doc.select(".thumb_img").get(j);
					String thumbnail=newsTumbnailData(thumbnailElement);
					
					Document detailDoc=Jsoup.connect(link).get();
					String info=detailDoc.html();
					String temp="";
					String author="";
					String regdate="";
					try{
						
						temp=info.substring(info.indexOf("pageMeta = {"),info.indexOf("pageMeta = {")+400);
						int targetNum=temp.indexOf("author\":")+10;
						temp=temp.substring(targetNum);
						author=temp.substring(0,temp.indexOf("\""));
						if(targetNum==9)
						{
							author="";
						}
						
					}catch (Exception ex) {}
					
					try{
						temp=info.substring(info.indexOf("pageMeta = {"),info.indexOf("pageMeta = {")+400);
						int targetNum=temp.indexOf("regdate\":")+11;
						temp=temp.substring(targetNum);
						regdate=temp.substring(0,temp.indexOf("\""));
					}catch (Exception ex) {}
					
					Element contentElement=detailDoc.selectFirst(".article_view");
					String content=newsElementTextData(contentElement);
					
					vo.setTitle(title);
					vo.setSubject(subject);
					vo.setThumbnail(thumbnail);
					vo.setRegdate(regdate);
					vo.setAuthor(author);
					vo.setContent(content);
					
					dao.newsDataInsert(vo);
					
					System.out.println(title);
					System.out.println(subject);
					System.out.println(thumbnail);
					System.out.println(regdate);
					System.out.println(author);
					System.out.println(link);
					System.out.println(content);
					System.out.println("===================================================");
				}
				
			}
			
		}catch (Exception ex) {			
			ex.printStackTrace();
		}
		
	}
	
	public String newsTumbnailData(Element e)
	{
		String res="";
		try{
			res=e.attr("style").replace("background-image:url(", "").replace(");", "");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
	public String newsElementTextData(Element e)
	{
		String res="";
		try{
			res=e.text();
		}catch (Exception ex) {
			res="null";
		}
		return res;
	}
	public String newsElementAttrData(Element e,String key)
	{
		String res="";
		try{
			res=e.attr(key);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
	
	public static void main(String[] args)
	{
		NewsManager nm=new NewsManager();
		nm.newsAllData();
	}
}
