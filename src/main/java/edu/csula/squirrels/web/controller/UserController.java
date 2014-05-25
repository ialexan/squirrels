package edu.csula.squirrels.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;


import edu.csula.squirrels.model.dao.UserDao;
import edu.csula.squirrels.model.User;


@Controller
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/user/management")
    public String user( ModelMap models )
    {
        models.put( "users", userDao.getUserList() );
        return "user/management";
    }



    // Adding a user
    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String addUser( )
    {
        return "user/adduser";
    }

	@RequestMapping( value =  "/user/add", method = RequestMethod.POST)
	public String addUser(  @RequestParam String username, @RequestParam String password,
            @RequestParam String firstname, @RequestParam String lastname,
            @RequestParam String email, @RequestParam String role, ModelMap models ) throws Exception
	{
        User user = userDao.getUser( username, email );

        if( user == null )
        {
            user = new User();
            user.setEmail( email );
            user.setPassword( password );
            user.setFirstName( firstname );
            user.setLastName( lastname );
            user.setUsername(username);
            user.getRoles().add( role );
            user = userDao.saveUser( user );

            return "redirect:/user/management";
        }
        else
        {
            models.addAttribute( "status", "user already exists");
            return "user/adduser";
        }
	}	



    // Editing a user
    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    public String editUser( ModelMap models, @RequestParam String username )
    {
        User user = userDao.getUser( username );


        models.addAttribute( "user", user);
        return "user/edituser";
    }

	@RequestMapping( value =  "/user/edit", method = RequestMethod.POST)
	public String editUser(  @ModelAttribute User user ) throws Exception
	{
		user = userDao.saveUser( user );
		
		return "redirect:/user/management";
	}	


    // Disabling a user
    @RequestMapping(value = "/user/disableuser", method = RequestMethod.GET)
    public String disableUser( @RequestParam Long userId, ModelMap models )
    {
        User user = userDao.getUser( userId );

        user.setEnabled(false);

        user = userDao.saveUser( user );

        models.put( "status", "disabled");
        return "jsonView";
    }

}
