package cmt;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

import static javax.ejb.TransactionAttributeType.*;

@Stateful(name = "MoviesSupports")
@TransactionAttribute(SUPPORTS)
public class MoviesSupports extends Movies{

    @PersistenceContext(unitName = "movie-unit", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void addMovie(Movie movie) throws Exception {
        em.persist(movie);
    }

    public void deleteMovie(Movie movie) throws Exception {
    	em.remove(movie);
    }

    @SuppressWarnings("unchecked")
	public List<Movie> getMovies() throws Exception {
        Query query = em.createQuery("SELECT m from Movie as m");
        return query.getResultList();
    }
    
    public String MethodWithoutTransactionRequirements(){
    	return "<br />Since this method doesn't communicate with datasource it can finish even outside of transaction";
    }
}
