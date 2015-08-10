package com.own.ehCacheP.domain;

public class Student {
private int id;
private String msg;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return id+":msg:"+msg;
}

}
