package com.test.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class EmailValidator extends Validator{
	protected void validate(Controller controller) {
		validateRequiredString("email", "message", "����������");
		validateEmail("email", "message", "��������ȷ�������ʽ|");
	}
	
	protected void handleError(Controller controller) {
		controller.setAttr("code", 10001);
		controller.renderJson();
	}
}
