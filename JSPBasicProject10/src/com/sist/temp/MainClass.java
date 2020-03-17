package com.sist.temp;

import java.lang.reflect.Method;
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Scanner scan=new Scanner(System.in);
			System.out.print("입력");
			String str=scan.next();
			
			Class acls=Class.forName("com.sist.temp.A"); // 리플렉션 
			//A a=(A)acls.newInstance();
			//a.display();
			 Object obj=acls.newInstance();
			 Method[] m=acls.getDeclaredMethods();
			 for(Method mm:m)
			 {
				 

			 }
			
					
		}catch (Exception ex) {}

	}

}
