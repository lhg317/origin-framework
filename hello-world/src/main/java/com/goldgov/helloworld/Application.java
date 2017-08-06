package com.goldgov.helloworld;

import com.goldgov.origin.core.OriginApplication;
import com.goldgov.origin.core.discovery.IsWebGate;

/**
 * 如果部署到Weblogic，你的启动类必须直接实现WebApplicationInitializer，
 * 即使OriginApplication已经间接实现了此接口
 * @author LiuHG
 * @version 1.0
 */
public class Application extends OriginApplication implements IsWebGate{

	public static void main(String[] args) {
		Application.startup(Application.class, args);
	}
}
