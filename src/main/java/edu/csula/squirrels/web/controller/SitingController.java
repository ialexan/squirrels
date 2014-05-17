package edu.csula.squirrels.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.csula.squirrels.model.dao.SitingDao;

@Controller
public class SitingController {

	@Autowired
	private SitingDao sitingDao;


	@RequestMapping("/siting/unverified")
	public String unverified( ModelMap models )
	{
		models.put( "sitings", sitingDao.getUnverifiedSitings() );
		return "siting/unverified";
	}
	
	@RequestMapping("/siting/verified")
	public String verified( ModelMap models )
	{
		models.put( "sitings", sitingDao.getVerifiedSitings() );
		return "siting/verified";
	}

	@RequestMapping("/siting/exportSightings")
	public String exportSightings( ModelMap models )
	{
		models.put( "sitings", sitingDao.getVerifiedSitings() );
		return "siting/exportSightings";
	}
	
	@RequestMapping(value = "/siting/submit", method = RequestMethod.GET) 
	public String sightingsubmit( ModelMap models )
	{
		return "siting/submit";
	}

}
