package edu.csula.squirrels.model.dao;

import java.util.List;

import edu.csula.squirrels.model.Species;

public interface SpeciesDao {

    Species getSpecies( Long id );

    Species getSpecies( String name );

    List<Species> getSpecies();

}
