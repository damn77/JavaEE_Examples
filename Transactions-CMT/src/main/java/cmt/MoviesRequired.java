package cmt;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

import static javax.ejb.TransactionAttributeType.*;

@Stateful(name = "MoviesRequired")
@TransactionAttribute(REQUIRED)
public class MoviesRequired extends Movies{

    @PersistenceContext(unitName = "movie-unit", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void addMovie(Movie movie) throws Exception {
        em.persist(movie);
    }

    public void deleteMovie(Movie movie) throws Exception {
    	/* 
    	instance movie was persisted in different tranasaction so we have to make sure insatnce in database is valid
    	this way the changes that would be made are overwritten - and since we are about to delete it anyway loss of data doesn't
    	concern us
    	*/
    	em.remove(em.merge(movie));
    }

    @SuppressWarnings("unchecked")
	public List<Movie> getMovies() throws Exception {
        Query query = em.createQuery("SELECT m from Movie as m");
        return query.getResultList();
    }

}
