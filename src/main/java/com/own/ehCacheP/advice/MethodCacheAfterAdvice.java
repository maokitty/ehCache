package com.own.ehCacheP.advice;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.ehcache.Cache;

import org.junit.Assert;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;

public class MethodCacheAfterAdvice implements AfterReturningAdvice,InitializingBean{
  private Cache cache;

@Override
public void afterPropertiesSet() throws Exception {
	// TODO Auto-generated method stub
	Assert.assertNotNull("not null", cache);
}

@Override
public void afterReturning(Object returnValue, Method method, Object[] args,
		Object target) throws Throwable {
	// TODO Auto-generated method stub
	String className=target.getClass().getName();
	List list=cache.getKeys();
	System.out.println("数据更新了，要把原来的缓存清除掉");
	for(int i=0;i<list.size();i++){
		String cacheKey=String.valueOf(list.get(i));
		if(cacheKey.startsWith(className)){
			cache.remove(cacheKey);
			System.out.println("删除缓存:"+cacheKey);
		}
	}
}

public Cache getCache() {
	return cache;
}

public void setCache(Cache cache) {
	this.cache = cache;
}
  
}
