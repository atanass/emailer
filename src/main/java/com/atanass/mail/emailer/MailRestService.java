package com.atanass.mail.emailer;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.atanass.mail.client.MailRestClientFactory;
import com.atanass.mail.client.message.Request;

@Path("/")
public class MailRestService {

	private static final String SUCCESS_MSG = "Message was sent successfully";
	private static final String ERROR_MSG = "Sorry, your message was not sent succesfully: ";

	@POST
	@Path("/submit")
	public Response submitMessage(@FormParam("from_name") String from_name,
			@FormParam("from_email") String from_email,
			@FormParam("to_name") String to_name,
			@FormParam("to_email") String to_email,
			@FormParam("subject") String subject,
			@FormParam("important") boolean important,
			@FormParam("msg") String message) {

		// Create the request object and the tools to process it
		Request req = Request.buildRequest("some html demo", message, subject,
				from_name, from_email, to_name, to_email, "to", important);
		MailRestClientFactory clientFactory = MailRestClientFactory.getFactory();
		ResponseHandler handler = new ResponseHandler();
		String emailerResponse = new String();
		String providerResponse = new String();
		
		// Adapt the request according to the current default provider and process it
		String body = FormatAdaptor.adaptRequest(req);
		System.out.println(body);
		
		providerResponse = clientFactory.createMailRestClient(
				MailApplication.getEndpoint()).sendRequest(body);

		// Check if the email processing was successful and if not - tries again after the failover
		boolean initialSuccess = handler.validateResponse(providerResponse);
		if (!initialSuccess) {
			body = FormatAdaptor.adaptRequest(req);
			providerResponse = clientFactory.createMailRestClient(
					MailApplication.getEndpoint()).sendRequest(body);
			initialSuccess = handler.validateResponse(providerResponse);
		}

		// Form the end response message that will be returned to the user
		emailerResponse = initialSuccess ? SUCCESS_MSG : ERROR_MSG + providerResponse ;
		return Response.status(200).entity(new ResourceLoader().loadDynamicResource("postSubmit.html", emailerResponse))
				.build();
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
		Response resp = Response.status(200).entity(loader.loadResource("assets/" + asset)).build();
		return resp;
	}
	
	@GET
	@Path("/provider")
	public Response getActiveProvider(){
		return Response.status(200).entity(MailApplication.getActiveProvider()).build();
	}
	
	@GET
	@Path("/assets/javascript/{param}")
	public Response javascripts(@PathParam("param") String script) {
		ResourceLoader loader = new ResourceLoader();
		Response resp = Response.status(200)
				.header("Content-Type", "application/javascript")
				.entity(loader.loadResource("assets/javascript/" + script)).build();
		return resp;
	}

}
