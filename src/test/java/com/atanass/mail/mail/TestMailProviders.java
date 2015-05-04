package com.atanass.mail.mail;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atanass.mail.client.MailRestClient;
import com.atanass.mail.client.MailRestClientFactory;
import com.atanass.mail.client.message.Message;
import com.atanass.mail.client.message.Recipient;
import com.atanass.mail.client.message.Request;
import com.atanass.mail.emailer.FormatAdaptor;
import com.atanass.mail.emailer.MailApplication;
import com.atanass.mail.emailer.ResponseHandler;

public class TestMailProviders {

	private Request request;
	MailRestClient client;
	ResponseHandler handler;

	@BeforeClass
	public void start() {
		client = MailRestClientFactory.getFactory().createMailRestClient(
				"https://mandrillapp.com/api/1.0/messages/send.json");
		handler = new ResponseHandler();
	}

	@BeforeTest
	public void createRequest() {
		Recipient rec = new Recipient("recipient@email.com", "Recipient name",
				"to");
		List<Recipient> recipList = new ArrayList<Recipient>();
		recipList.add(rec);
		request = new Request("", new Message("html", "text", "subject",
				"from_name", "from@email.com", recipList,
				new ArrayList<String>(), false), false, "Main Pool",
				"04/01/2015 10:00 AM");

	}

	@Test
	public void givenInvalidAuthKeyWhenMandrillCalledReturnFalse() {
		String invalidKey = "abc";
		request.setKey(invalidKey);
		String result = client.sendRequest(FormatAdaptor.adaptRequest(request));
		Assert.assertTrue(result.contains("error"));
	}

	@Test
	public void givenInvalidParameterWhenCallProviderThenFailover(){
		request.setKey("invalid-key");
		String beforeProvider = MailApplication.getActiveProvider();
		String result = client.sendRequest(FormatAdaptor.adaptRequest(request));
		handler.validateResponse(result); //validates the response and if response contains "error", switches to the other provider
		String afterProvider = MailApplication.getActiveProvider();
		Assert.assertFalse(beforeProvider.equals(afterProvider));
	}
	
	@Test
	public void givenValidAuthKeyForMandrillWhenPingReturnTrue(){
		client = MailRestClientFactory.getFactory().createMailRestClient("https://mandrillapp.com/api/1.0/users/info.json");
		String result = client.sendRequest("{\"key\":\"RqZIi60kpApbl9uwowwhUQ\"}");
		Assert.assertFalse(result.contains("Invalid_Key"));
	}

}
