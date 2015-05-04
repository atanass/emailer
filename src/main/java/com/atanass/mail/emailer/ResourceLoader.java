package com.atanass.mail.emailer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ResourceLoader {

	private static final String APP_IDENTIFICATOR = "emailerAppIdentification";
	
	/**
	 * Loads a resource
	 * @param path Path of the resource
	 * @return The requested resources as String
	 */
	public String loadResource(String path){
		InputStream inps = this.getClass().getClassLoader().getResourceAsStream(path);
		Scanner scanner = new Scanner(inps);
		StringBuffer strBuff = new StringBuffer();
		while (scanner.hasNextLine()) {
			strBuff.append(scanner.nextLine());
		}
		scanner.close();
		try {
			inps.close();
		} catch (IOException e) {
			System.out.println("Error while closing scanner");
		}
		return strBuff.toString();
	}
	
	/**
	 * Loads a resources and replaces the default APP_IDENTIFICATOR String with another value
	 * @param path Path of the resources
	 * @param subtitution A String to replace the default identificator with
	 * @return The requested resource
	 */
	public String loadDynamicResource(String path, String subtitution){
		String originalResource = loadResource(path);
		return originalResource.replace(APP_IDENTIFICATOR, subtitution);
	}
	
}
