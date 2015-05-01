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



# IN PROGRESS...
