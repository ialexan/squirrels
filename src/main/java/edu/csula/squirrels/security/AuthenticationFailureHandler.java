package edu.csula.squirrels.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import edu.csula.squirrels.web.service.ServiceResponse;

@Component
public class AuthenticationFailureHandler extends
    SimpleUrlAuthenticationFailureHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure( HttpServletRequest request,
        HttpServletResponse response, AuthenticationException exception )
        throws IOException, javax.servlet.ServletException
    {
        if( request.getParameter( "mobile" ) != null )
            objectMapper.writeValue( response.getWriter(), new ServiceResponse( "failedToLogin" ) );
        else
            super.onAuthenticationFailure( request, response, exception );
    }

}
