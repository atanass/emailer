package com.atanass.mail.emailer;

public class ResponseHandler {

	/**
	 * Validates mail provider's response and executes a failover if "error" found in it
	 * @param response Provided response as string
	 * @return true - if the response doesn't contain "error" and false if it does
	 */
	public boolean validateResponse(String response){
		if (response.contains("error")){
			MailApplication.failOver();
			return false;
		} else {
			return true;
		}
	}
	
}
