package com.own.ehCacheP.intercepter;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Assert;
import org.springframework.beans.factory.InitializingBean;

import com.own.ehCacheP.domain.Student;

/***
 *环绕通知
 *
 */

public class MethodCacheInterceptor implements MethodInterceptor,
		InitializingBean {
    private Cache cache;
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
      Assert.assertNotNull("set cache",cache);
	}
    /**
     * 拦截service/dao的方法，并查找该结果是否存在，如果存在就返回cache中的值。
     * 否则返回数据库中查询结果，并将查询结果放入cache
     */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		String targetName=invocation.getThis().getClass().getName();
		String methodName=invocation.getMethod().getName();
		Object[] arguments=invocation.getArguments();
		Object result;
		CacheConfiguration conf=cache.getCacheConfiguration();
		System.out.println("使用的缓存的属性是  名字:"+conf.getName()
				  +",缓存中元素的最大允许的个数:"+conf.getMaxElementsInMemory()
				  +",元素的过期时间:"+conf.getTimeToLiveSeconds()
				  +",元素的抛弃策略:"+conf.getMemoryStoreEvictionPolicy()
				  );
		String cacheKey=getCacheKey(targetName,methodName,arguments);
		Element element=cache.get(cacheKey);
		if(element==null){
			System.out.println("缓存中没有拿到数据，从数据库中获取，并再次放入缓存");
			//执行被代理的方法
			result = invocation.proceed();
			element=new Element(cacheKey,(Serializable)result);
			cache.put(element);
		}
		return element.getObjectValue();
	}
    private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		// TODO Auto-generated method stub
    	StringBuffer sBuffer=new StringBuffer();
    	sBuffer.append(targetName).append(".").append(methodName);
    	if((arguments!=null)&&(arguments.length!=0)){
    		for(int i=0;i<arguments.length;i++){
    			sBuffer.append(".").append(arguments[i]);
    		}
    	}
		return sBuffer.toString();
	}
	public MethodCacheInterceptor(){
    	super();
    }
	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

}
