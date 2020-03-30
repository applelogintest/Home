package com.sist.controller;
import java.util.*;

public class A {
	Map map=new HashMap();
	public A(){
		map.put("b",new B());
	}
	
	public static void main(String[] args){
		A a=new A();
		B b=(B)a.map.get("b"); // B b=new B();
		B c=(B)a.map.get("b"); // B c=new B();
		
		System.out.println("b="+b);
		System.out.println("c="+c);
	}
}

class B{
	public void display(){
		System.out.println("display call");
	}
}
