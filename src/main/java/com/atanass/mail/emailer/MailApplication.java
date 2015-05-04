package com.atanass.mail.emailer;

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
	
	/**
	 * Switches the active mail provider of the running application
	 */
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
	
	/**
	 * Gets the currently active provider
	 * @return Currently active provider
	 */
	public static String getActiveProvider(){
		return activeProvider;
	}
	
	/**
	 * Gets the currently active provider's endpoint
	 * @return The active provider's endpoint
	 */
	public static String getEndpoint(){
		return endpoint;
	}
	
}
