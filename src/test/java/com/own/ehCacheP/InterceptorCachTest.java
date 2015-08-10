package com.own.ehCacheP;

import java.util.List;

import net.sf.ehcache.CacheManager;

import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.own.ehCacheP.domain.Student;
import com.own.ehCacheP.service.TestService;
/**
 * 通过拦截器对某些特定方法做拦截，拦截的到的先进行缓存查找，有的话，优先缓存
 * @author liwangchun
 *
 */
public class InterceptorCachTest {
public static void main(String [] args) {
		// TODO Auto-generated method stub
	  ApplicationContext context=new ClassPathXmlApplicationContext("Aop/applicationContext.xml");
	  CacheManager cm=(CacheManager) context.getBean("defaultCacheManager");
	  TestService testService=(TestService) context.getBean("testService");
	  System.out.println("****第一次查找数据****");
	  List<Student> stulist=testService.getAllObject();
	  if (stulist!=null) {
		for (Student student : stulist) {
			System.out.println(student);
		}
	}
	  System.out.println("****第二次查找数据****");
	  stulist=testService.getAllObject();
	  if (stulist!=null) {
			for (Student student : stulist) {
				System.out.println(student);
			}
	  }
	  System.out.println("****更新数据库****");
	  testService.updateObject(new Student());
	  System.out.println("******第三次重新查找数据*******");
	  testService.getAllObject();
	  //In non-web application, you need to shut down the Spring context manually, 
	  //so that Ehcache got chance to shut down as well, 
	  //otherwise Ehcache manager will hang there.
      cm.shutdown();
}
}
