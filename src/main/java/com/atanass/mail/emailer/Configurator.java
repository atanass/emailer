package com.atanass.mail.emailer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurator {

	private static final String PROPS_FILE = "engine.properties";
	public static final String IP_POOL = "Main Pool";
	
	
	/**
	 * Loads the main property file the configuration file and returns the property for the entry
	 * @param key Key of the properties entry
	 * @return Value of the property
	 */
	public String getConfig(String key) {
		Properties props = new Properties();
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPS_FILE);
		
		if (inputStream != null){
			try {
				props.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return (props.getProperty(key));
	}
	
}
