/**
 * FileServlice.java
 * 
 * $Author: Ishag Alexanian $
 */
package edu.csula.squirrels.web.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.csula.squirrels.model.File;
import edu.csula.squirrels.model.User;
import edu.csula.squirrels.model.dao.FileDao;
import edu.csula.squirrels.security.SecurityUtils;
import edu.csula.squirrels.util.FileIO;

@Controller
public class FileService {

	@Autowired
	FileDao fileDao;

	@Autowired
	FileIO fileIO;
	
	@RequestMapping("/service/file")
    public String nofile( HttpServletResponse response )
        throws IOException
    {
        return null;
    }
	
	
	@RequestMapping("/service/file/{id}")
    public String fileById( @PathVariable Long id, HttpServletResponse response )
        throws IOException
    {
	    response.setContentType( "image/jpg" );
        fileIO.copy( fileDao.getFile( id ), response.getOutputStream() );

        return null;
    }
	

	@RequestMapping("/service/filebyname/{name}")
	public String fileByName( @PathVariable String name, HttpServletResponse response )
			throws IOException
	{
		response.setContentType( "image/jpg" );
		fileIO.copy( fileDao.getFile( name ), response.getOutputStream() );
	
		return null;
	}

	@RequestMapping(value = "/service/siting/submitphoto", method = RequestMethod.POST)
	public String savingFile( @RequestParam MultipartFile uploadedFile, ModelMap models) throws IOException {

		models.clear(); 
		try {
			User user = SecurityUtils.getUser();

			File file = new File();
			String fileName = uploadedFile.getOriginalFilename();
			file.setName( fileName );
			file.setOwner( user );
			file.setType( uploadedFile.getContentType() );
			file.setSize( uploadedFile.getSize() );
			file.setDate( new Date() );
			file = fileDao.saveFile( file );

			fileIO.save( file, uploadedFile );
			
			models.put( "id", file.getId() );
			
		}catch(Exception e){
			models.put( "id", "failed" + e.toString());
		}

		return "jsonView";
	}

}
