package edu.csula.squirrels.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.csula.squirrels.model.User;
import edu.csula.squirrels.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser( Long id )
    {
        return entityManager.find( User.class, id );
    }

    @Override
    public User getUser( String username )
    {
        // This method is mainly used by the security code which usually needs
        // both the user credentials (i.e. username and password) and the
        // granted authorities (i.e. roles), so here we load the roles
        // collection "eagerly" using a join fetch to avoid a second query.
        
//    	String query = "from User user left join fetch user.roles "
//            + "where username = :username";

    	String query = "from User where username = :username";	
    	
        List<User> users = entityManager.createQuery( query, User.class )
            .setParameter( "username", username )
            .getResultList();
        return users.size() == 0 ? null : users.get( 0 );
    }
    
    @Override
    public User getUser( String username, String email )
    {
    	String query = "from User where username = :username or email = :email";	
    	
        List<User> users = entityManager.createQuery( query, User.class )
            .setParameter( "username", username )
            .setParameter( "email", email )
            .getResultList();
        return users.size() == 0 ? null : users.get( 0 );
    }

    @Override
    public User getUserByEmail( String email )
    {
        String query = "from User where email = :email";    
        
        List<User> users = entityManager.createQuery( query, User.class )
            .setParameter( "email", email )
            .getResultList();
        return users.size() == 0 ? null : users.get( 0 );
    }

    
    @Override
    public User getUserByPassword( String username, String password){
    	String query = "from User where username = :username and password = :password";	
    	
        List<User> users = entityManager.createQuery( query, User.class )
            .setParameter( "username", username )
            .setParameter( "password", password )
            .getResultList();
        
        return users.size() == 0 ? null : users.get( 0 );
    }

    @Override
    @Transactional
    public User saveUser( User user )
    {
        return entityManager.merge( user );
    }
    
    @Override
    public List<User> getUserList()
    {
        String query = "from User where enabled is true order by id desc";

        return entityManager.createQuery( query, User.class ).getResultList();
    }

}
