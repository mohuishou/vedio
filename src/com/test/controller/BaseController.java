package com.test.controller;

import com.jfinal.core.Controller;

public class BaseController extends Controller{
	public void reSuccess(String message){
		this.setAttr("code", 200);
		this.setAttr("message",message);
	}
	
	public void reError(int code,String message){
		this.setAttr("code",code);
		this.setAttr("message",message);
	}
}
