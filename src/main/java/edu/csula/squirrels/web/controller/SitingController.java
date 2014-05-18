package edu.csula.squirrels.web.controller;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.security.access.annotation.Secured;

import edu.csula.squirrels.model.dao.SitingDao;

@Controller
public class SitingController {

	@Autowired
	private SitingDao sitingDao;

	// Listing unverified list
	@Secured({ "ADMIN","APPROVER" })
	@RequestMapping("/siting/unverified")
	public String unverified( ModelMap models )
	{
		models.put( "sitings", sitingDao.getUnverifiedSitings() );
		return "siting/unverified";
	}
	
	// Listing verified list
	@Secured({ "ADMIN","APPROVER" })
	@RequestMapping("/siting/verified")
	public String verified( ModelMap models )
	{
		models.put( "sitings", sitingDao.getVerifiedSitings() );
		return "siting/verified";
	}



	
	// Exporting Sightings
	@Secured({ "ADMIN","APPROVER" })
	@RequestMapping( value = "/siting/exportSightings", method = RequestMethod.GET)
	public String exportSightings( )
	{
		return "siting/exportSightings";
	}
	
	@Secured({ "ADMIN","APPROVER" })
	@RequestMapping( value =  "/siting/exportSightings", method = RequestMethod.POST)
	public String exportSightings(  ModelMap models, @RequestParam String starttime, 
			@RequestParam String endtime, @RequestParam(required = false) boolean verifiedcheckbox ) throws Exception
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		models.put( "sitings", sitingDao.getSitingsToExport(formatter.parse(starttime), formatter.parse(endtime), verifiedcheckbox) );
		
		return "siting/exportSightingsCSV";
	}
	


	
	// Submit Sightings
	@RequestMapping( value = "/siting/submit", method = RequestMethod.GET) 
	public String sightingsubmit( )
	{
		return "siting/submit";
	}



}
