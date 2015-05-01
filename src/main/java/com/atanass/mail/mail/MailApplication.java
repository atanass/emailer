package com.atanass.mail.mail;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class MailApplication extends Application{
	
	private static final String DEFAULT = "default.provider";
	private static final String FAILOVER = "failover.provider";
	
	private static Configurator appConfig = new Configurator();
	private static String activeProvider = appConfig.getConfig(DEFAULT);
	private static String endpoint = appConfig.getConfig(activeProvider);
	private static String mode = "alpha";
	
	protected static void failOver(){
		if (mode.equals("alpha")){
			activeProvider = appConfig.getConfig(FAILOVER);
			mode = "beta";
		} else if (mode.equals("beta")){
			activeProvider = appConfig.getConfig(DEFAULT);
			mode = "alpha";
		}
		endpoint = appConfig.getConfig(activeProvider);
	}
	
	public static String getActiveProvider(){
		return activeProvider;
	}
	
	static String getEndpoint(){
		return endpoint;
	}
	
}
