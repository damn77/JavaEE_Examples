package test;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRequiredException;
import javax.ejb.embeddable.EJBContainer;

import cmt.Movie;
import cmt.Movies;
import cmt.MoviesMandatory;
import cmt.MoviesRequired;
import cmt.MoviesSupports;


import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;

public class MoviesTest{

    @EJB
    private MoviesMandatory moviesM;
    
    @EJB
    private MoviesSupports moviesS;
    
    @EJB
    private MoviesRequired moviesR;

    @EJB
    private Caller transactionalCaller;

    protected void setUp() throws Exception {
        final Properties p = new Properties();
        p.put("movieDatabase", "new://Resource?type=DataSource");
        p.put("movieDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("movieDatabase.JdbcUrl", "jdbc:hsqldb:mem:moviedb");

        EJBContainer.createEJBContainer(p).getContext().bind("inject", this);
    }

    private String actions(Movies m) throws Exception{
    	String result;
    	m.addMovie(new Movie("Quentin Tarantino", "Reservoir Dogs", 1992));
        m.addMovie(new Movie("Joel Coen", "Fargo", 1996));
        m.addMovie(new Movie("Joel Coen", "The Big Lebowski", 1998));

        List<Movie> list = m.getMovies();
        
        if ( 3 == list.size()) result = "<br />Input of movies to database was <b>succesful</b>";
        else result = "<br />Input of movies to database <b> failed </b>";

        for (Movie movie : list) {
        	m.deleteMovie(movie);
        }
        if (0 == m.getMovies().size()) result += "<br />Removing of movies from database was <b>succesful</b>";
        else result += "<br />Removing of movies from database <b> failed </b>";
        
        return result;
    }
    
    private String mandatoryWithTransaction() throws Exception {
    	return "Mandatory : " + actions(moviesM);
    }
    
    private String supportsNoTransaction() throws Exception {
    	return moviesS.MethodWithoutTransactionRequirements();
    }
    
    private String supportsWithTransaction() throws Exception {
    	return "Supports : " + actions(moviesS);
    }

    private String requiredWithTransaction() throws Exception {
    	return "Required : " + actions(moviesR);
    }

	public String testWithTransaction(char attribute) throws Exception {
        final char attr = attribute;
        
		return (String) transactionalCaller.call(new Callable<Object>() {
            public Object call() throws Exception {
            	String result = "";
            	switch (attr) {
				case 'M':
					result = mandatoryWithTransaction();
					break;
				case 'S':
					result = supportsWithTransaction();
					break;
				case 'R':
					result = requiredWithTransaction();
					break;
				default:
					break;
				}            	            	
                return result;
            }
        });
    }

    public String testWithoutTransaction(char attr) throws Exception {

    	switch (attr) {
		case 'M':
			try {
				return mandatoryWithTransaction();
	        } catch (EJBTransactionRequiredException e) {
	            return  "MoviesMandatory bean is using MANDATORY as we want and failed because of it";
	        }			
		case 'S':
			String str = supportsNoTransaction();
			try {
	    		return supportsWithTransaction();
	    	}
	    	catch (Exception e) {
	            return  str+ "<br />Because MoviesSupports bean is using SUPPORTS, it will be allowed to run but will fail on persist(movie)";
	        }
		case 'R':
			return requiredWithTransaction() + "<br />Required will create its own cmt so it should work (with some changes handling detaching of entities)";			
		default:
			return "";
    	}	
    }
}
