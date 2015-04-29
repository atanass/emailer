package com.atanass.mail.mail;

import java.io.IOException;

/*
 * Contains information about the routing of the submitted message
 */
public class Routing {


	private static String defaultRoute;
	private static String endpoint;
	static {
		Configurator c = new Configurator();
		try {
			defaultRoute = c.getConfig("default.provider");
			endpoint = c.getConfig(defaultRoute);
		} catch (IOException e) {
			System.out.println("Error while getting configs");
		}
	}
	
	
	/**
	 * Changes the default routing and the endpoint of the service respectively, according to the engine.properties file
	 * @param newValue The name of the default routing (mandrill or ...)
	 */
	public static void setDefaultRouting(String newValue){
		defaultRoute = newValue;
		try {
			endpoint = new Configurator().getConfig(defaultRoute);
		} catch (IOException e) {
			System.out.println("Error while getting configuration for " + defaultRoute);
		}
	}
	
	public static String getDefaultRouting(){
		return defaultRoute;
	}
	
	/**
	 * Get the endpoint of the currently used 3rd party mail engine
	 * @return 3rd party service endpoint
	 */
	public static String getDefaultEndpoint(){
		return endpoint;
	}
	
}
