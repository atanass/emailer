package com.atanass.mail.client.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class Recipient {

	@XmlElement
	private String email;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String type;

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Recipient(String email, String name, String type) {
		super();
		this.email = email;
		this.name = name;
		this.type = type;
	}
	
	
}
