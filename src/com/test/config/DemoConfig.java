package com.test.config;

import com.test.controller.index.IndexController;
import com.test.model._MappingKit;


import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.test.controller.user.*;

import demo.HelloController;

public class DemoConfig extends JFinalConfig {
	/**
	 * ��������
	 */
	public void configConstant(Constants me) {
		me.setDevMode(true);// ������ģʽ
	}

	/**
	 * ·�ɿ���
	 */
	public void configRoute(Routes me) {
		me.add("/hello", HelloController.class);
		me.add("/", IndexController.class,"/index");
		me.add("/user", UserController.class);
		me.add("/auth", AuthController.class);
		
	}

	public void configPlugin(Plugins me) {
		C3p0Plugin cp = new C3p0Plugin("jdbc:mysql://localhost:3306/test", "root", "lailin");
		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		me.add(arp);
		// ���������� MappingKit �и㶨
		_MappingKit.mapping(arp);
	
	}

	public void configInterceptor(Interceptors me) {
	}

	public void configHandler(Handlers me) {
	}
}