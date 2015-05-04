package com.atanass.mail.client.message;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.atanass.mail.emailer.Configurator;

@XmlAccessorType(XmlAccessType.NONE)
public class Request {

	@XmlElement
	private String key;
	
	@XmlElement
	private Message message;
	
	@XmlElement
	private boolean async;
	
	@XmlElement
	private String ip_pool;
	
	@XmlElement
	private String send_at;

	public void setKey(String key){
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}

	public Message getMessage() {
		return message;
	}

	public boolean isAsync() {
		return async;
	}

	public String getIp_pool() {
		return ip_pool;
	}

	public String getSend_at() {
		return send_at;
	}

	public Request(String key, Message message, boolean async, String ip_pool,
			String send_at) {
		super();
		this.key = key;
		this.message = message;
		this.async = async;
		this.ip_pool = ip_pool;
		this.send_at = send_at;
	}
	
	
	/**
	 * Builds request
	 * @param html Html parameter of the user request
	 * @param text Email text
	 * @param subject Email subject
	 * @param senderName Sender's name
	 * @param senderEmail Sender's email
	 * @param recipientName Recipient's name
	 * @param recipientEmail Recipient's email
	 * @param recipientsType The type of the recipients - "to", "cc", "bcc"
	 * @param important "Important" flag of the email
	 * @return {@link Request} instance with the provided data
	 */
	public static Request buildRequest(String html, String text, String subject, String senderName, String senderEmail, String recipientName, String recipientEmail, String recipientsType, boolean important){
		List<Recipient> recipients = new ArrayList<Recipient>();
		recipients.add(new Recipient(recipientEmail, recipientName, recipientsType));
		Message message = new Message(html, text, subject, senderName, senderEmail, recipients, null, false);
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date now = new Date();
		now.setDate(now.getDate()-1);
		Request req = null;
		req = new Request(new Configurator().getConfig("mandrill.auth.key"), message, important, Configurator.IP_POOL, format.format(now));
		return req;
	}
	
	
}
