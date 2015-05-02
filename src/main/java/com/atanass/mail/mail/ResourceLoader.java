package com.atanass.mail.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ResourceLoader {

	private static final String APP_IDENTIFICATOR = "emailerAppIdentification";
	
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
	
	public String loadDynamicResource(String path, String subtitution){
		String originalResource = loadResource(path);
		return originalResource.replace(APP_IDENTIFICATOR, subtitution);
	}
	
}
