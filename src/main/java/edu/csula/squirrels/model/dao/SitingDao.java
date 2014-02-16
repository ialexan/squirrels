package edu.csula.squirrels.model.dao;

import java.util.List;

import edu.csula.squirrels.model.Siting;

public interface SitingDao {

    Siting getSiting( Long id );

    List<Siting> getUnverifiedSitings();

    List<Siting> getVerifiedSitings();
    
    Siting saveSiting( Siting siting );
}
