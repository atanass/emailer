package com.atanass.mail.client.message;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
public class Message {

	@XmlElement
	private String html;
	
	@XmlElement
	private String text;
	
	@XmlElement
	private String subject;
	
	@XmlElement
	private String from_name;
	
	@XmlElement
	private String from_email;
	
	@XmlElement
	private List<Recipient> to;
	
	@XmlElement
	private List<String> headers;
	
	@XmlElement
	private boolean important;

	public String getHtml() {
		return html;
	}

	public String getText() {
		return text;
	}

	public String getSubject() {
		return subject;
	}

	public String getFrom_name() {
		return from_name;
	}

	public String getFrom_email() {
		return from_email;
	}

	public List<Recipient> getTo() {
		return to;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public boolean isImportant() {
		return important;
	}

	public Message(String html, String text, String subject, String from_name,
			String from_email, List<Recipient> to, List<String> headers,
			boolean important) {
		super();
		this.html = html;
		this.text = text;
		this.subject = subject;
		this.from_name = from_name;
		this.from_email = from_email;
		this.to = to;
		this.headers = headers;
		this.important = important;
	}
	
	
	
}
