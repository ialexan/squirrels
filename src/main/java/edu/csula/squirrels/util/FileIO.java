/*
 * ialexan
 */
package edu.csula.squirrels.util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import edu.csula.squirrels.model.File;

@Component
public class FileIO {

    @Value("#{applicationProperties['file.dir']}")
    private String fileDir;

    private static final Logger logger = LoggerFactory.getLogger( FileIO.class );

    public FileIO()
    {
    }

    public void save( File file, MultipartFile uploadedFile )
    {
        String fileName = file.getName();
        java.io.File diskFile = new java.io.File( fileDir, fileName );
        
        try
        {
            uploadedFile.transferTo( diskFile );
        
        }
        catch( Exception e )
        {
            logger.error( "Failed to save uploaded file", e );
        }
    }
   
    public void SaveImage( File file, BufferedImage image )
    {
        String fileName = file.getName();
        java.io.File diskFile = new java.io.File( fileDir, fileName );
        
        try
        {    
            ImageIO.write( image, "png", diskFile);
        }
        catch( Exception e )
        {
            logger.error( "Failed to save thumbnail", e );
        }
    }   

    public void copy( InputStream in, OutputStream out )
    {
        byte[] buffer = new byte[4096];
        int bytesRead;
        try
        {
            while( (bytesRead = in.read( buffer )) != -1 )
                out.write( buffer, 0, bytesRead );
        }
        catch( IOException e )
        {
            logger.error( "Failed to copy input to output", e );
        }
    }

    public void copy( File file, OutputStream out )
    {
        try
        {
            String fileName = file.getName();
            java.io.File diskFile = new java.io.File( fileDir, fileName );
            FileInputStream in = new FileInputStream( diskFile );
            copy( in, out );
            in.close();
        }
        catch( Exception e )
        {
            logger.error( "Failed to copy file to output", e );
        }
    }

}
