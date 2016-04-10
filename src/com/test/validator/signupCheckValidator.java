package com.test.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class signupCheckValidator extends Validator{
	protected void validate(Controller controller) {
		validateRequiredString("email", "message", "请输入邮箱");
		validateRequiredString("code", "message", "验证码不存在");
		validateEmail("email", "message", "请输入正确的邮箱格式|");
	}
	
	protected void handleError(Controller controller) {
		controller.setAttr("code", 10005);
		controller.renderJson();
	}
}
