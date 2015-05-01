package com.atanass.mail.mail;

public class ResponseHandler {

	public boolean validateResponse(String response){
		if (response.contains("error")){
			MailApplication.failOver();
			return false;
		} else {
			return true;
		}
	}
	
}
