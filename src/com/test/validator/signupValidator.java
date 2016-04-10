package com.test.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class signupValidator extends Validator{
	protected void validate(Controller controller) {
		validateRequiredString("email", "emailMessage", "����������");
		validateRequiredString("name", "message", "�û���������");
		validateEmail("email", "message", "��������ȷ�������ʽ|");
		validateRequiredString("password", "message", "����������");
	}
	
	protected void handleError(Controller controller) {
		controller.setAttr("code", 10005);
		controller.renderJson();
	}
}
