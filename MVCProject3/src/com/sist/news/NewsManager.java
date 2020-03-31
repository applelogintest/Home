package com.sist.news;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.net.*;
public class NewsManager {
	public List<Item> newsAllData(String fd)
	{
		List<Item> list=new ArrayList<Item>();
		try{
			URL url=new URL("http://newssearch.naver.com/search.naver?where=rss&query="+URLEncoder.encode(fd, "UTF-8"));
			JAXBContext jb=JAXBContext.newInstance(Rss.class);
			Unmarshaller un=jb.createUnmarshaller();
			Rss rss=(Rss)un.unmarshal(url);
			list=rss.getChannel().getItem();
		}catch (Exception ex) {}
		return list;
	}
}
