package com.test.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator {
	protected void validate(Controller controller) {
		validateRequiredString("email", "message", "请输入邮箱");
		validateEmail("email", "message", "请输入正确的邮箱格式|");
		validateRequiredString("password", "message", "请输入密码");
	}
	
	protected void handleError(Controller controller) {
		controller.setAttr("code", "10002");
		controller.renderJson();
	}
}
