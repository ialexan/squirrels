package edu.csula.squirrels.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.csula.squirrels.model.User;
import edu.csula.squirrels.model.dao.UserDao;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername( String username )
        throws UsernameNotFoundException, DataAccessException
    {
        User user = userDao.getUser( username );
        if( user == null )
            throw new UsernameNotFoundException( username + " is not found." );

        return user;
    }

}
