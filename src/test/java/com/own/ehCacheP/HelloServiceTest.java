package com.own.ehCacheP;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.own.ehCacheP.service.HelloService;

public class HelloServiceTest {
	@Autowired
	
	public static void main(String [] args){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("ano/applicationContext.xml");
		HelloService helloService=(HelloService) applicationContext.getBean(HelloService.class);
		 CacheManager cManager=(CacheManager)applicationContext.getBean("ehcache");
		System.out.println("第一次获取");
		String msg=helloService.getMsg("1001");
		System.out.println(msg);
		System.out.println("第二次获取数据");
		msg=helloService.getMsg("1001");
		System.out.println(msg);
		System.out.println("更新数据");
		Cache cache=cManager.getCache("dbCache");
		System.out.println("更新前:"+cache.getSize());
		helloService.updateMsg("1001");
		System.out.println("更新后:"+cache.getSize());
		System.out.println("第三次获取数据");
	    msg=helloService.getMsg("1001");
	    System.out.println(msg);
	    //没有这个线程不会结束
	    cManager.shutdown();
	}

}
