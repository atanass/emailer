package com.atanass.mail.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientRequestFactory;
import org.jboss.resteasy.client.ClientResponse;

import com.atanass.mail.client.mandrill.Message;
import com.atanass.mail.client.mandrill.Recipient;
import com.atanass.mail.client.mandrill.Request;

public class MailRestClient {

	private String provider;
	
	private MailRestClient(){
		super();
	}
	
	protected MailRestClient(String provider){
		this.provider = provider;
	}
	
	public String sendRequest(String body) {
		String result = new String();
		try {
			ClientRequestFactory reqFactory = new ClientRequestFactory();
			
			ClientRequest clientRequest = reqFactory.createRequest(this.provider);
			
			clientRequest.body(MediaType.APPLICATION_JSON, body);
			
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


