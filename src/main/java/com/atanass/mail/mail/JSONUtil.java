package com.atanass.mail.mail;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.Module;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.atanass.mail.client.mandrill.Request;


public class JSONUtil {

	private static ObjectWriter mapper = new ObjectMapper().writer();

	public static String jsonify(Object value) {
		
		String result = "error";
		try {
			result = mapper.writeValueAsString(value);
		} catch (JsonGenerationException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return result;
	}

}
