package com.atanass.mail.mail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurator {

	private static final String PROPS_FILE = "engine.properties";
	
	public String getConfig(String key) throws IOException{
		Properties props = new Properties();
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPS_FILE);
		
		if (inputStream != null){
			props.load(inputStream);
		} else {
			throw new FileNotFoundException("property file " + PROPS_FILE + " not found");
		}
		return (props.getProperty(key));
	}
	
}
