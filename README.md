# emailer

The "Emailer" application provides a basic web form for filling in basic email details for submitting messages:
 - email from which the message is submitted
 - name of the sender
 - recipient email address
 - subject of the message
 - text (the message itself)

# Flow
1. A user fills the email details and submits the message
2. The html form is passed to the RESTfull backend application
3. The backend recieves the details as parameters and builds a "Request" prototype object
4. According to the current (at run-time) active email provider, the application formats the request object and creates a body,
which is applicable to the respective 3rd party email provider's API
5. The backend sends a client request, containing the data previously received by the user and recieves a response from the provider
6. Message submission status is returned to the user.


# Technology stack choice
I've chosen to write the back-end application in Java and have it compiled with Maven as war file, because thisway provides
easy method for integrating different libraries (including such as the ones provided by the email engine providers)
I have developed it locally, running it in JBoss 7.1 container server as this is the web server I have most experience with.
I think JBoss can provide scaling and customization of the running application, including environment specific configuration management.

List of used technology
 - Java - jdk1.6.0_43
 - Maven - 2.2.1

Dependencies: 
 - org.codehaus.jackson libraries for structured data binding and parsing
 - org.jboss.resteasy.resteasy-jaxrs library for RESTful interface implementation (as it is suitable for the preferred deployment platform mentioned above)


#Personal notes:
I've tried to make the back-end application as much moduled as possible in order to make integrating a new mail provider easier. I hope I got at least close to the idea :)



# IN PROGRESS...
