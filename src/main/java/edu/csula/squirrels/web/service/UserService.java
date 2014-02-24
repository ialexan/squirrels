/**
 * UserService.java
 * 
 * $Author: ialexan $
 */
package edu.csula.squirrels.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.csula.squirrels.model.User;
import edu.csula.squirrels.model.dao.UserDao;


@Controller
public class UserService {

    @Autowired
    UserDao userDao;

    @RequestMapping("/service/user/{id}")
    public String user( @PathVariable Long id, ModelMap models )
    {
        models.put( "user", userDao.getUser( id ) );
        return "jsonView";
    }

    @RequestMapping("/service/user/register")
    public String register( @RequestParam String email,
        @RequestParam String password, @RequestParam String lastName,
        @RequestParam String firstName, @RequestParam String username, 
        ModelMap models )
    {
        User user = userDao.getUser( username );
        String result = "";
        
        if( user == null )
        {
            user = new User();
            user.setEmail( email );
            user.setPassword( password );
            user.setFirstName( firstName );
            user.setLastName( lastName );
            user.setUsername(username);
            user.getRoles().add("REGULAR_USER");
            user = userDao.saveUser( user );
            result = "user registered";            
        }
        else
        	result = "user already exists";

        models.put( "user", result );
        return "jsonView";
    }

    
    @RequestMapping("/service/user/adduser")
    public String adduser( @RequestParam String email,
        @RequestParam String password, @RequestParam String lastName,
        @RequestParam String firstName, @RequestParam String username, 
        ModelMap models )
    {
        User user = userDao.getUser( username );
        String result = "";
        
        if( user == null )
        {
            user = new User();
            user.setEmail( email );
            user.setPassword( password );
            user.setFirstName( firstName );
            user.setLastName( lastName );
            user.setUsername(username);
            user.getRoles().add("REGULAR_USER");
            user = userDao.saveUser( user );
            result = "user registered";
        }
        else
            result = "user already exists";
        
       List<User> userList = userDao.getUserList();
        
       for (User userRow : userList){
    	   
       }
        
        
        models.put( "user", result );
        return "jsonView";
    }
    
}

