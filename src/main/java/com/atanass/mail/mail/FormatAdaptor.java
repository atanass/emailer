package com.atanass.mail.mail;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.Module;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.atanass.mail.client.mandrill.Recipient;
import com.atanass.mail.client.mandrill.Request;


public class FormatAdaptor {

	private static String mandrilize(Request value) {
		ObjectWriter mapper = new ObjectMapper().writer();
		String result = "error";
		try {
			result = mapper.writeValueAsString(value);
		} catch (JsonGenerationException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return result;
	}
	
	private static String sendgridize(Request value){
		StringBuffer strBuff = new StringBuffer();
		List<Recipient> recipients = value.getMessage().getTo();
		for (int recIndex = 0; recIndex < recipients.size(); recIndex++) {
			strBuff.append("to[]=").append(recipients.get(recIndex).getEmail() + ";");
			strBuff.append("toname[]=").append(recipients.get(recIndex).getName() + ";");
		}
		strBuff.append("subject=" + value.getMessage().getSubject() + ";");
		strBuff.append("text=" + value.getMessage().getText() + ";");
		strBuff.append("from=" + value.getMessage().getFrom_email());
		
		return strBuff.toString();
	}
	
	public static String adaptRequest(Request request){
		String provider = MailApplication.getActiveProvider();
		String formattedRequest = "";
		if (provider.equals("mandrill")){
			formattedRequest = mandrilize(request);
		} else if (provider.equals("sendgrid")){
			formattedRequest = sendgridize(request);
		}

		return formattedRequest;
	}

}
