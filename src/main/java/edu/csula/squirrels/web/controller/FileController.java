package edu.csula.squirrels.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class FileController {

	@RequestMapping(value = "/siting/submitphoto", method = RequestMethod.GET) 
	public String sightingsubmit( ModelMap models )
	{
		return "siting/submitphoto";
	}

}
