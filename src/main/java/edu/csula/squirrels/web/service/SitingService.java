package edu.csula.squirrels.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import edu.csula.squirrels.model.File;
import edu.csula.squirrels.model.Siting;
import edu.csula.squirrels.model.Species;
import edu.csula.squirrels.model.User;
import edu.csula.squirrels.model.dao.FileDao;
import edu.csula.squirrels.model.dao.SitingDao;
import edu.csula.squirrels.model.dao.UserDao;
import edu.csula.squirrels.security.SecurityUtils;
import edu.csula.squirrels.util.FileIO;
import edu.csula.squirrels.web.editor.SpeciesPropertyEditor;
import edu.csula.squirrels.web.editor.UserPropertyEditor;

@Controller
public class SitingService {

	@Autowired
	private SitingDao sitingDao;

	@Autowired
	FileDao fileDao;

	@Autowired
	FileIO fileIO;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	UserDao userDao;

	@InitBinder
	public void initBinder( WebDataBinder binder )
	{
		binder.registerCustomEditor( Species.class,
				(SpeciesPropertyEditor) context.getBean( "speciesPropertyEditor" ) );
		binder.registerCustomEditor( User.class,
				(UserPropertyEditor) context.getBean( "userPropertyEditor" ) );
		binder.registerCustomEditor( Date.class, new CustomDateEditor(
				new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss:SS" ), true ) );
	}

	@RequestMapping(value = "/service/siting/submit", method = RequestMethod.POST)
	public String submit( @RequestParam Date timestamp, @RequestParam(required = false) MultipartFile uploadedFile,
			@RequestParam double latitude, @RequestParam double longitude, @RequestParam Species species, 
			@RequestParam String username, @RequestParam String password, ModelMap models)
	{
		User submittedBy = SecurityUtils.getUser();

		Siting siting;

		if ( uploadedFile == null || uploadedFile.isEmpty()  ){
			siting = new Siting(timestamp, latitude, longitude, species, submittedBy);
		}
		else{		
			File file = new File();
			String fileName = uploadedFile.getOriginalFilename();
			file.setName( fileName );
			file.setOwner( submittedBy );
			file.setType( uploadedFile.getContentType() );
			file.setSize( uploadedFile.getSize() );
			file.setDate( new Date() );
			file = fileDao.saveFile( file );

			fileIO.save( file, uploadedFile );

			siting = new Siting(timestamp, latitude, longitude, species, submittedBy, file);
		}

		siting = sitingDao.saveSiting( siting );

		models.clear(); 
		models.put( "id", siting.getId() );
		return "jsonView";
	}

	/*	
	@RequestMapping(value = "/service/siting/submit",
			method = RequestMethod.POST)
	public String submit( @ModelAttribute Siting siting, ModelMap models )
	{
		siting = sitingDao.saveSiting( siting );

		models.clear(); // remove the siting attribute
		models.put( "id", siting.getId() );
		return "jsonView";
	}
	 */

	@RequestMapping(value = "/mobileservice/siting/submit", method = RequestMethod.POST)
	public String submitMobile( @RequestParam Date timestamp, @RequestParam(required = false) MultipartFile uploadedFile,
			@RequestParam double latitude, @RequestParam double longitude, @RequestParam Species species, 
			@RequestParam String username, @RequestParam String password, ModelMap models)
	{

		User submittedBy = userDao.getUserByPassword(username, password);

		models.clear(); 

		if (submittedBy != null )
		{
			Siting siting;

			if ( uploadedFile == null || uploadedFile.isEmpty()  ){
				siting = new Siting(timestamp, latitude, longitude, species, submittedBy);
			}
			else{		
				File file = new File();
				String fileName = uploadedFile.getOriginalFilename();
				file.setName( fileName );
				file.setOwner( submittedBy );
				file.setType( uploadedFile.getContentType() );
				file.setSize( uploadedFile.getSize() );
				file.setDate( new Date() );
				file = fileDao.saveFile( file );

				fileIO.save( file, uploadedFile );

				siting = new Siting(timestamp, latitude, longitude, species, submittedBy, file);
			}

			siting = sitingDao.saveSiting( siting );

			models.put( "id", siting.getId() );

		}
		else
		{
			models.put( "id", "user does not exit");
		}


		return "jsonView";
	}
	

	
	@RequestMapping(value = "/service/siting/verify", method = RequestMethod.GET)
	public String verify( @RequestParam String action, @RequestParam Long sitingId, ModelMap models)
	{
		Siting siting = sitingDao.getSiting(sitingId);
		
		User verifiedBy = SecurityUtils.getUser();
		siting.setVerifiedBy(verifiedBy);
		
		String result = "";
		
		if (action.equals("accept")){
			result = "verified";
		}
		else {
			siting.setRejected(true);
			result = "rejected";
		}
		
		siting = sitingDao.saveSiting( siting );
		
		models.put( "status", result);
		return "jsonView";
	}

}
