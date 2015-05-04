package com.atanass.mail.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientRequestFactory;
import org.jboss.resteasy.client.ClientResponse;

import com.atanass.mail.client.message.Message;
import com.atanass.mail.client.message.Recipient;
import com.atanass.mail.client.message.Request;
import com.atanass.mail.emailer.Configurator;
import com.atanass.mail.emailer.MailApplication;

public class MailRestClient {

	private String provider;
	
	private MailRestClient(){
		super();
	}
	
	protected MailRestClient(String provider){
		this.provider = provider;
	}
	
	/**
	 * Sends client request to the currently active mail provider
	 * @param The body to send to the mail provider
	 * @return Mail provider's response 
	 */
	public String sendRequest(String body) {
		String result = new String();
		ClientRequestFactory reqFactory = new ClientRequestFactory();
		ClientRequest clientRequest = reqFactory.createRequest(this.provider);
		try {
			if ( MailApplication.getActiveProvider().equals("mandrill")){
				clientRequest.body(MediaType.APPLICATION_JSON, body);
			} else if (MailApplication.getActiveProvider().equals("sendgrid")){
				clientRequest.body(MediaType.APPLICATION_FORM_URLENCODED, body);
				clientRequest.header("Authorization", "Bearer " + new Configurator().getConfig("sendgrid.auth.key"));
			}
			result = clientRequest.post().getEntity(String.class).toString();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}


