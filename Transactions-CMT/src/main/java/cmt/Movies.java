package cmt;

import java.util.List;

public abstract class  Movies {

	public abstract void addMovie(Movie movie) throws Exception;
	public abstract void deleteMovie(Movie movie) throws Exception;
	public abstract List<Movie> getMovies() throws Exception;
	
}
