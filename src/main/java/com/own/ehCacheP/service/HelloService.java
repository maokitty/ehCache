package com.own.ehCacheP.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
@Service
public class HelloService {
@Cacheable(value="annoCache",key="'getMsg_'+#id")
public String getMsg(String id){
	System.out.println("我在数据库里面更新数据了 "+id);
	return "hello "+id;
}
@CacheEvict(value="annoCache",key="'getMsg_'+#id")
public void updateMsg(String id){
	System.out.println("我在数据库里更新数据 "+id);
}
}
