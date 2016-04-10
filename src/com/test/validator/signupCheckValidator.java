package com.test.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class signupCheckValidator extends Validator{
	protected void validate(Controller controller) {
		validateRequiredString("email", "message", "����������");
		validateRequiredString("code", "message", "��֤�벻����");
		validateEmail("email", "message", "��������ȷ�������ʽ|");
	}
	
	protected void handleError(Controller controller) {
		controller.setAttr("code", 10005);
		controller.renderJson();
	}
}
