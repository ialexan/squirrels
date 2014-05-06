package edu.csula.squirrels.model.dao;

import java.util.List;

import edu.csula.squirrels.model.User;

public interface UserDao {

    User getUser( Long id );
    
    User getUser( String username);

    User getUser( String username, String email );
    
    User getUserByPassword (String username, String password);
    
    List<User> getUserList();
    
    User saveUser( User user );

}
