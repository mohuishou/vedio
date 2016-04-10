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
	 * 发送激活邮件
	 */
	@Before(EmailValidator.class)
	public void signupSend() {
		String email = this.getPara("email");
		UserModel user = new UserModel();
		String password = user.getPassword(email);
		if (!password.isEmpty()) {
			this.reError(10006, "该邮箱已被注册！");
			this.setAttr("next","/user/login");
			renderJson();
		} else {
			// 验证码=email的md5字符串
			String code = Md5.getMd5Str(email);
			StringBuffer emailContent = new StringBuffer("点击下面链接注册账号，链接只能使用一次！</br>");
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
			this.reSuccess("邮件发送成功，请查收");
			renderJson();
		}

	}

	/**
	 * 检查激活邮件
	 */
	@Before(signupCheckValidator.class)
	public void signupCheck() {
		String email = this.getPara("email");
		String code = this.getPara("code");
		String rCode = Md5.getMd5Str(email);
		// 注意两个对象之间的比较不能直接用==
		if (code.equals(rCode)) {
			renderJavascript("alert('验证成功,点击确认完成注册！')");
			redirect("/user/register");
		} else {
			this.reError(10007, "验证失败|请重新注册");
			redirect("/user/signup");
		}
	}

	/**
	 * 注册
	 */
	@Before(signupValidator.class)
	public void signup() {
		getModel(UserModel.class).save();
	}

	/**
	 * 登陆
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
				reError(10004, "密码错误！");
				renderJson();
			}
		} else {
			this.reError(10003, "不存在该用户！");
			renderJson();
		}
	}
}
