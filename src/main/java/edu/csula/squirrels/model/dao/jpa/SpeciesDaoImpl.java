package edu.csula.squirrels.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.csula.squirrels.model.Species;
import edu.csula.squirrels.model.dao.SpeciesDao;

@Repository
public class SpeciesDaoImpl implements SpeciesDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Species getSpecies( Long id )
    {
        return entityManager.find( Species.class, id );
    }

    @Override
    public Species getSpecies( String name )
    {
        return entityManager.createQuery( "from Species where name = :name",
            Species.class )
            .setParameter( "name", name )
            .getSingleResult();
    }

    @Override
    @Transactional
    public List<Species> getSpecies()
    {
        return entityManager.createQuery( "from Species", Species.class )
            .getResultList();
    }

}
