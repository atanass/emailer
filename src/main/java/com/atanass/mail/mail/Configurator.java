package com.atanass.mail.mail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurator {

	private static final String PROPS_FILE = "engine.properties";
	public static final String IP_POOL = "Main Pool";
	
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
