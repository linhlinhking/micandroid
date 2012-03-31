package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class BaseTest{
	
	protected static ApplicationContext applicationContext = 
		new ClassPathXmlApplicationContext(
				new String[]{"spring/application*.xml"});
	
	public static void main(String[] args){
	}
	
}