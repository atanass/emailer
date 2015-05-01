package com.atanass.mail.mail;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atanass.mail.client.MailRestClientFactory;
import com.atanass.mail.client.mandrill.Request;

@Path("/message")
public class MailRestService {

	private static final String ERROR_MSG = "Sorry,your email was not sent successfully.";
	
	@GET
	@Path("/info")
	public Response printMessage() {

		return Response.status(200).entity("hi").build();
	}

	@POST
	@Path("/submit")
	// @Consumes("application/json")
	public Response submitMessage(@FormParam("from_name") String from_name,
			@FormParam("from_email") String from_email,
			@FormParam("to_name") String to_name,
			@FormParam("to_email") String to_email,
			@FormParam("subject") String subject,
			@FormParam("important") boolean important,
			@FormParam("msg") String message) {

		Request req = Request.buildRequest("some html demo", message, subject,
				from_name, from_email, to_name, to_email, "to", important);

		String body = FormatAdaptor.adaptRequest(req);

//		return Response.status(201).entity("Response: " + body).build();

		 String providerResponse = MailRestClientFactory.getFactory()
		 .createMailRestClient(MailApplication.getEndpoint())
		 .sendRequest(body);
		 
		 String emailerResponse = new ResponseHandler().validateResponse(providerResponse) ? providerResponse : ERROR_MSG;
		 
		 return Response.status(201).entity("Response: " + emailerResponse).build();
	}

	@GET
	@Path("/home/")
	public Response homePage() {

		ResourceLoader loader = new ResourceLoader();
		return Response.status(200).entity(loader.loadResource("index.html"))
				.build();

	}

	@GET
	@Path("/assets/{param}")
	public Response assets(@PathParam("param") String asset) {

		ResourceLoader loader = new ResourceLoader();
		Response resp = Response.status(200)
				.header("Content-Type", "application/javascript")
				.entity(loader.loadResource("assets/" + asset)).build();
		return resp;
		// return Response.status(200)
		// .entity(loader.loadResource("assets/" + asset)).build();

	}

}
