package edu.csula.squirrels.web.service;

/**
 * This class encapsulates the response to certain web service operations such
 * as register user, log in, log out, and so on. 
 */
public class ServiceResponse {

    String message;

    public ServiceResponse( String message )
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

}
