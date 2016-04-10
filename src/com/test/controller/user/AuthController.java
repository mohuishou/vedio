package com.test.controller.user;


import com.jfinal.aop.Before;
import com.test.controller.BaseController;
import com.test.model.UserModel;
import com.test.validator.EmailValidator;
import com.test.validator.LoginValidator;
import com.test.validator.signupCheckValidator;
import com.test.validator.signupValidator;
import com.test.common.Mail;
import com.test.common.Md5;;

public class AuthController extends BaseController {

	/**
	 * ���ͼ����ʼ�
	 */
	@Before(EmailValidator.class)
	public void signupSend() {
		String email = this.getPara("email");
		UserModel user = new UserModel();
		String password = user.getPassword(email);
		if (!password.isEmpty()) {
			this.reError(10006, "�������ѱ�ע�ᣡ");
			this.setAttr("next","/user/login");
			renderJson();
		} else {
			// ��֤��=email��md5�ַ���
			String code = Md5.getMd5Str(email);
			StringBuffer emailContent = new StringBuffer("�����������ע���˺ţ�����ֻ��ʹ��һ�Σ�</br>");
			emailContent.append("<a href=\"http://lxl.520:8089/user/signupCheck?email=");
			emailContent.append(email);
			emailContent.append("&code=");
			emailContent.append(code);
			emailContent.append("\">http://lxl.520:8089/user/signupCheck?email=");
			emailContent.append(email);
			emailContent.append("&code=");
			emailContent.append(code);
			emailContent.append("</a>");
			Mail.send(email, emailContent.toString());
			this.reSuccess("�ʼ����ͳɹ��������");
			renderJson();
		}

	}

	/**
	 * ��鼤���ʼ�
	 */
	@Before(signupCheckValidator.class)
	public void signupCheck() {
		String email = this.getPara("email");
		String code = this.getPara("code");
		String rCode = Md5.getMd5Str(email);
		// ע����������֮��ıȽϲ���ֱ����==
		if (code.equals(rCode)) {
			renderJavascript("alert('��֤�ɹ�,���ȷ�����ע�ᣡ')");
			redirect("/user/register");
		} else {
			this.reError(10007, "��֤ʧ��|������ע��");
			redirect("/user/signup");
		}
	}

	/**
	 * ע��
	 */
	@Before(signupValidator.class)
	public void signup() {
		getModel(UserModel.class).save();
	}

	/**
	 * ��½
	 * 
	 */
	@Before(LoginValidator.class)
	public void login() {
		UserModel user = new UserModel();
		String email = this.getPara("email");
		String password = this.getPara("password");
		password = Md5.getMd5Str(password);
		String rPassword = user.getPassword(email);
		if (rPassword != null) {
			if (rPassword.equals(password)) {
				redirect("/user/signup.html");
			} else {
				reError(10004, "�������");
				renderJson();
			}
		} else {
			this.reError(10003, "�����ڸ��û���");
			renderJson();
		}
	}
}
