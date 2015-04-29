package com.atanass.mail.client;

public class MailRestClientFactory {

	private volatile static MailRestClientFactory factoryInstance = new MailRestClientFactory();
	
	private MailRestClientFactory(){
		super();
	}
	
	public static MailRestClientFactory getFactory(){
		return factoryInstance;
	}
	
	public MailRestClient createMailRestClient(String provider){
		return new MailRestClient(provider);
	}
	
}
