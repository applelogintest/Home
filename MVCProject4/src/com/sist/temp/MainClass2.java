package com.sist.temp;
import java.lang.reflect.Method;
import java.util.*;
/*
  	
 */
class A{
	@RequestMapping("a.do")
	public void aaa()
	{
		System.out.println("A:aaa() Call...");
	}
	@RequestMapping("b.do")
	public void bbb()
	{
		System.out.println("A:bbb() Call...");
	}
	@RequestMapping("c.do")
	public void ccc()
	{
		System.out.println("A:ccc() Call...");
	}
	@RequestMapping("d.do")
	public void ddd()
	{
		System.out.println("A:ddd() Call...");
	}
	@RequestMapping("e.do")
	public void eee()
	{
		System.out.println("A:eee() Call...");
	}
}
public class MainClass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		// a.do => aaa b.do=> bbb ...
		System.out.print("URI주소 입력 :");
		String uri=scan.next();
		/*
		A a=new A();
		if(uri.equals("a.do"))
			a.aaa();
		else if(uri.equals("b.do"))
			a.bbb();
		else if(uri.equals("c.do"))
			a.ccc();
		else if(uri.equals("d.do"))
			a.ddd();
		else if(uri.equals("e.do"))
			a.eee();*/
		try{
			// 1. 메모리할당
			Class clsName=Class.forName("com.sist.temp.A");
			Object obj=clsName.newInstance();
			// 위와 같음 A a=new A(); 하지만 클래스 포네임을 쓰면 어떤 클래스라도 메모리할당 가능 A a=new A() A만 할당가능 유동적이지 못함
			Method[] methods=clsName.getDeclaredMethods();
			for(Method m:methods)
			{
												  //어노테이션이 여러개 있는게 가능하기 때문에 원하는 어노테이션을 지정해줘야된다
				RequestMapping rm=m.getAnnotation(RequestMapping.class);
				if(rm.value().equals(uri))
				{
					m.invoke(obj, null);
				}
				//System.out.println(m.getName());
			}
			
			
		}catch (Exception ex) {	}
		
	}

}
