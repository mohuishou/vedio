package com.test.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class signupValidator extends Validator{
	protected void validate(Controller controller) {
		validateRequiredString("email", "emailMessage", "请输入邮箱");
		validateRequiredString("name", "message", "用户名不存在");
		validateEmail("email", "message", "请输入正确的邮箱格式|");
		validateRequiredString("password", "message", "请输入密码");
	}
	
	protected void handleError(Controller controller) {
		controller.setAttr("code", 10005);
		controller.renderJson();
	}
}
