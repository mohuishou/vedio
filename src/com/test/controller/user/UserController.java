package com.test.controller.user;

import com.test.controller.BaseController;

public class UserController extends BaseController{
	public void register() {
		render("register.html");
	}
	
	public void login() {
		render("login.html");
	}
	
	public void signup() {
		render("signup.html");
	}
}
