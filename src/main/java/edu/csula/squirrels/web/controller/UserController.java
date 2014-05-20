package edu.csula.squirrels.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


import edu.csula.squirrels.model.dao.UserDao;


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
    @RequestMapping(value = "/user/adduser", method = RequestMethod.GET)
    public String addUser( ModelMap models )
    {
        User user = new User();

        models.addAttribute( "user", user );
        return "user/adduser";
    }

	@RequestMapping( value =  "/siting/adduser", method = RequestMethod.POST)
	public String addUser(  @ModelAttribute User user ) throws Exception
	{
		user = userDao.saveUser( user );
		
		return "redirect:/user/management";
	}	


    // Editing a user
    @RequestMapping(value = "/user/edituser", method = RequestMethod.GET)
    public String editUser( ModelMap models )
    {
        User user = new User();

        models.addAttribute( "user", user );
        return "user/edituser";
    }

	@RequestMapping( value =  "/siting/edituser", method = RequestMethod.POST)
	public String editUser(  @ModelAttribute User user ) throws Exception
	{
		user = userDao.saveUser( user );
		
		return "redirect:/user/management";
	}	

}
