package com.atanass.mail.client;

public class MailRestClientFactory {

	private volatile static MailRestClientFactory factoryInstance = new MailRestClientFactory();
	
	private MailRestClientFactory(){
		super();
	}
	
	/**
	 * A method that returns a factory for {@link MailRestClient}
	 * @return A single factory instance
	 */
	public static MailRestClientFactory getFactory(){
		return factoryInstance;
	}
	
	/**
	 * Creates {@link MailRestClient} and returns it
	 * @param provider Service endpoint of the provider
	 * @return {@link MailRestClient}
	 */
	public MailRestClient createMailRestClient(String provider){
		return new MailRestClient(provider);
	}
	
}
