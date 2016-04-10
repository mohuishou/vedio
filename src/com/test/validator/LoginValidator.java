package com.test.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator {
	protected void validate(Controller controller) {
		validateRequiredString("email", "message", "����������");
		validateEmail("email", "message", "��������ȷ�������ʽ|");
		validateRequiredString("password", "message", "����������");
	}
	
	protected void handleError(Controller controller) {
		controller.setAttr("code", "10002");
		controller.renderJson();
	}
}
