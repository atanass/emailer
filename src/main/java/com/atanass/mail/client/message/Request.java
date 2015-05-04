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
	private String ipPool;
	
	@XmlElement
	private String sendAt;

	public Request(String html, String text, String subject, String senderName, String senderEmail, String recipientName, String recipientEmail, String recipientsType, boolean important){
		this.key = new Configurator().getConfig("mandrill.auth.key");
		List<Recipient> recipients = new ArrayList<Recipient>();
		recipients.add(new Recipient(recipientEmail, recipientName, recipientsType));
		Message message = new Message(html, text, subject, senderName, senderEmail, recipients, null, important);
		this.message = message; 
		this.async = false;
		this.ipPool = Configurator.IP_POOL;
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date now = new Date();
		now.setDate(now.getDate()-1);
		this.sendAt = format.format(now);
	}
	
	
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
		return ipPool;
	}

	public String getSend_at() {
		return sendAt;
	}

}
