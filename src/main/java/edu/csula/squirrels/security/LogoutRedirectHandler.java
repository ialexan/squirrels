/*
 * This file is part of the CSNetwork Services (CSNS) project.
 * 
 * Copyright 2012, Chengyu Sun (csun@calstatela.edu).
 * 
 * CSNS is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * CSNS is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with CSNS. If not, see http://www.gnu.org/licenses/agpl.html.
 */
package edu.csula.squirrels.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import edu.csula.squirrels.model.User;
import edu.csula.squirrels.web.service.ServiceResponse;

@Component
public class LogoutRedirectHandler implements LogoutSuccessHandler {

    @Autowired
    ObjectMapper objectMapper;

    private final static Logger logger = LoggerFactory.getLogger( LogoutRedirectHandler.class );

    @Override
    public void onLogoutSuccess( HttpServletRequest request,
        HttpServletResponse response, Authentication authentication )
        throws IOException, ServletException
    {
        User user = (User) authentication.getPrincipal();
        logger.info( user.getUsername() + " signed out." );

        if( request.getParameter( "mobile" ) != null )
            objectMapper.writeValue( response.getWriter(), new ServiceResponse("loggedOut") );
        else
        {
            SimpleUrlLogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
            logoutSuccessHandler.setDefaultTargetUrl( "/" );
            logoutSuccessHandler.onLogoutSuccess( request, response,
                authentication );
        }
    }

}
