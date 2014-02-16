/**
 * FileDao.java
 * 
 * $Author: Ishag Alexanian $
 */
package edu.csula.squirrels.model.dao;

import edu.csula.squirrels.model.File;


public interface FileDao {

    File getFile( String name );

    File getFile( Long id );
    
    File saveFile( File file );

}
