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
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String role ) throws Exception
	{
        user.getRoles().add( role );

		user = userDao.saveUser( user );
		
		return "redirect:/user/management";
	}	


 //    // Editing a user
 //    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
 //    public String editUser( ModelMap models )
 //    {
 //        return "user/edituser";
 //    }

	// @RequestMapping( value =  "/user/edit", method = RequestMethod.POST)
	// public String editUser(  @ModelAttribute User user ) throws Exception
	// {
	// 	user = userDao.saveUser( user );
		
	// 	return "redirect:/user/management";
	// }	

}
