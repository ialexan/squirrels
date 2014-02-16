package edu.csula.squirrels.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.csula.squirrels.model.Siting;
import edu.csula.squirrels.model.User;
import edu.csula.squirrels.model.dao.SitingDao;

@Repository
public class SitingDaoImpl implements SitingDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Siting getSiting( Long id )
    {
        return entityManager.find( Siting.class, id );
    }

    @Override
    public List<Siting> getUnverifiedSitings()
    {
        String query = "from Siting where verifiedBy is null and rejected is false order by timestamp";

        return entityManager.createQuery( query, Siting.class ).getResultList();
    }
    
    @Override
    public List<Siting> getVerifiedSitings()
    {
        String query = "from Siting where verifiedBy is not null and rejected is false order by timestamp";

        return entityManager.createQuery( query, Siting.class ).getResultList();
    }
        

    @Override
    @Transactional
    public Siting saveSiting( Siting siting )
    {
        return entityManager.merge( siting );
    }
    
}
