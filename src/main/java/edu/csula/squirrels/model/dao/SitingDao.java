package edu.csula.squirrels.model.dao;

import java.util.List;
import java.util.Date;

import edu.csula.squirrels.model.Siting;

public interface SitingDao {

    Siting getSiting( Long id );

    List<Siting> getUnverifiedSitings();

    List<Siting> getVerifiedSitings();

	List<Siting> getSitingsToExport(Date starttime, Date endtime, boolean verifiedcheckbox);
    
    Siting saveSiting( Siting siting );

}
