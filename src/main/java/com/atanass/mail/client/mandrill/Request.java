package com.atanass.mail.client.mandrill;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

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
	
	
	
}
