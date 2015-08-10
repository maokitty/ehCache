package com.own.ehCacheP.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.own.ehCacheP.domain.Student;
import com.own.ehCacheP.service.TestService;

public class TestServiceImpl implements TestService {

	@Override
	public List<Student> getAllObject() {
		// TODO Auto-generated method stub
		System.out.println("我在数据库里面");
		Student student=new Student();
		student.setId(1);
		student.setMsg("student");
		List<Student> list=new ArrayList<>();
		list.add(student);
		return list;
	}

	@Override
	public void updateObject(Student student) {
		// TODO Auto-generated method stub
        System.out.println("数据库执行更新语句");
	}

}
