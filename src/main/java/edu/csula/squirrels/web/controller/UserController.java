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


}
