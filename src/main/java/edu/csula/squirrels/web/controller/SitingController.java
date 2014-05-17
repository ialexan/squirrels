package edu.csula.squirrels.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

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

	
	
	@RequestMapping( value = "/siting/exportSightings", method = RequestMethod.GET)
	public String exportSightings( ModelMap models )
	{
		models.put( "sitings", sitingDao.getVerifiedSitings() );
		return "siting/exportSightings";
	}
	
	@RequestMapping( value =  "/siting/exportSightings", method = RequestMethod.POST)
	public String exportSightings(  ModelMap models, @RequestParam(required = false) String starttime, 
			@RequestParam(required = false) String endtime, @RequestParam(required = false) boolean verifiedcheckbox )
	{
		models.put( "sitings", sitingDao.getVerifiedSitings() );
		return "siting/exportSightingsCSV";
	}
	
	
	
	@RequestMapping(value = "/siting/submit", method = RequestMethod.GET) 
	public String sightingsubmit( ModelMap models )
	{
		return "siting/submit";
	}

}
