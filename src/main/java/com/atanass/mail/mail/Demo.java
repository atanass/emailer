package com.atanass.mail.mail;

import java.io.IOException;

public class Demo {

	public static void main(String[] args) {

		try {
			System.out.println(new Configurator().getConfig("mandrill.auth.key"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
