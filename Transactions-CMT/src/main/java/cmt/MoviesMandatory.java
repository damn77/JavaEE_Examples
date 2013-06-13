package cmt;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

import static javax.ejb.TransactionAttributeType.MANDATORY;

@Stateless(name = "MoviesMandatory")
@TransactionAttribute(MANDATORY)
public class MoviesMandatory extends Movies{

    @PersistenceContext(unitName = "movie-unit", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public void addMovie(Movie movie) throws Exception {
    	entityManager.persist(movie);
    }

    public void deleteMovie(Movie movie) throws Exception {
    	entityManager.remove(movie);
    }

    @SuppressWarnings("unchecked")
    public List<Movie> getMovies() throws Exception {
    	Query query = entityManager.createQuery("SELECT m from Movie as m");
    	return query.getResultList();
    }

}
