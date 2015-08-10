package com.own.ehCacheP;


import java.util.List;
import java.util.concurrent.TimeUnit;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
/**
 * 单独使用ehcache
 * @author liwangchun
 *
 */
public class SimpleTest {
public static void main(String[] args) {
    //Ehcache在2.5之后中的版本中不再允许同名的多个CacheManager存在同一个JVM中，使用CacheManager.create()
	//来返回一个已经存在的单例的CacheManager，如果没有，则创建一个单例的CacheManager。
	CacheManager manager=CacheManager.create("src/main/resources/Simple/ehcache.xml");

	//	每个CacheManger都管理多个Cache。每个Cache都以一种类Hash的方式，关联多个Element。Element就是我们用于存放缓存内容的地方。
	Cache cache=manager.getCache("simpleCache");

	Element element=new Element("hello","world");
	Element element2=new Element("aaa","111");
	Element element3=new Element("bbb","222");
	Element element4=new Element("ccc","333");
	
	cache.put(element);
	cache.put(element2);
	cache.put(element3);
	cache.put(element4);
	
	System.out.println("缓存中元素的个数:"+cache.getSize());
	
	List<String> keys=cache.getKeys();
	System.out.println("所有的缓存元素:");
	for(String key:keys){
		System.out.println(key+":"+cache.get(key));
	}
	
	System.out.println("删除一个缓存中存在元素:");
	System.out.println(cache.remove("hello")+":hello");//存在就反回true
	
	System.out.println("删除一个缓存中不存在元素:");
	System.out.println(cache.remove("hello2")+":hello2");//不存在的key就反回false
	
	System.out.println("开始休眠3秒");
	try {
		TimeUnit.SECONDS.sleep(3);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("休眠3秒结束");
	
	System.out.println("获取缓存元素:"+cache.get("bbb"));
	cache.removeAll();
	System.out.println("size:"+cache.getSize());
	manager.shutdown();
}
}
