package com.atanass.mail.mail;

import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;

import com.atanass.mail.client.mandrill.Request;

public class Demo {

	public static void main(String[] args) {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
		Date date = new Date();
		System.out.println(format.format(date));
	}

}
