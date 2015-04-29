package com.atanass.mail.mail;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.atanass.mail.client.MailRestClientFactory;

@Path("/message")
public class MailRestService {

	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String param) {

		String result = "Restful example: " + param;
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/submit")
	@Consumes("application/json")
	public Response submitMessage(String body) {

		String response = MailRestClientFactory.getFactory()
				.createMailRestClient(Routing.getDefaultEndpoint())
				.sendRequest(body);
		return Response.status(201).entity("Response: " + response).build();
	}

}
