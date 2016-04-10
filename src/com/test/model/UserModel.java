package com.test.model;


import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class UserModel extends Model<UserModel>{
	
	public static final UserModel me=new UserModel();
	
	public void singup(){
		
	}
	
	public String getPassword(String email){
		UserModel user = UserModel.me.findFirst("select * from vedio_user where email='"+email+"'");
		String a="";
		if(user==null){
			return a;
		}else {
			return user.getStr("password");
		}
	}
}
