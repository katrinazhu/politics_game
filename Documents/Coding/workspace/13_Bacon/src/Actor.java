import java.util.ArrayList;
import java.util.*;
/**
 * Datatype that holds information on Actors
 *
 */
public class Actor {
	/**
	 * Name of the actor - assumed to be unique
	 */
	public String name;
	private ArrayList<Movie> myMovies;
	private HashMap<Actor,Movie> myCoStars;
	
	public Actor(String person)
	{
		name = person;
		myMovies = new ArrayList<Movie>();
		myCoStars = null;
	}
	/**
	 * Returns the name of the Actor
	 * @return the String name of the Actor
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a List of all movies that this actor has
	 * appeared in
	 * @return the ArrayList of Movies that this actor has
	 * appeared in
	 */
	public Iterable<Movie> getMovies() {
		return myMovies;
	}

	/**
	 * Determines all of the co-stars that this Actor has had.
	 * Stores the list of co-stars in a map where each co-star
	 * is mapped to the movie in which the actors appeared 
	 * together.
	 * <p>
	 * Notes:
	 * <ul>
	 * <li> All Actors and Movies must be initialized before this
	 * method is called. 
	 * <li> This method will compute the map of coStars the
	 * first time it is called and cache the result. After
	 * that point, the method should return the result in
	 * constant time. 
	 * <li> The map will not be updated if any
	 * Actor or Movie is added later.
	 * </ul>
	 * @return a Map of all of the Actors that this Actor
	 * has costarred, where each Actor is mapped to the Movie
	 * where they starred together 
	 */
	public Map<Actor, Movie> coStars() {
		if (myCoStars != null)
			return myCoStars;
		myCoStars = new HashMap<Actor,Movie>();
		for (Movie m: myMovies)
		{
			for (Actor a: m.getActors())
			{
				if (a != this)
				{
					myCoStars.put(a, m);
				}
			}
		}
		return myCoStars;
	}
	/**
	 * Adds m to the list of Movies that this Actor has appeared
	 * in.
	 * @param m the Movie to be added
	 */
	public void add(Movie m)
	{
		myMovies.add(m);
	}
	
	/**
	 * The name of the Actor is assumed to be unique, so it
	 * is used as a HashCode
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return name.hashCode();
	}
	
}
