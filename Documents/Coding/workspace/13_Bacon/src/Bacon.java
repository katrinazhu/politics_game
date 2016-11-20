import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.JFileChooser;

/**
 * Reads file with Traverses a graph from a single source using breadth-first
 * search algorithm from source s on a graph G. After preprocessing the graph,
 * can process shortest path queries from s to any vertex.
 * 
 * 
 */
public class Bacon {

	// name -> Actor object
	private HashMap<String, Actor> myActors;
	// name -> Movies that actor has been in
	private HashMap<String, Movie> myMovies;
	private Graph myG;

	private static final String SOURCE = "Bacon, Kevin";
	private static final String DEFAULT_DELIMITER = "/";
	public Bacon() {
		myG = new Graph();
		myActors = new HashMap<String, Actor>();
		myMovies = new HashMap<String, Movie>();
		readCasts(getScanner(), DEFAULT_DELIMITER);
	}

    // chooser allows users to select a file by navigating through
    // directories
    private static JFileChooser ourChooser = new JFileChooser(System
            .getProperties().getProperty("user.dir"));

    /**
     * Brings up chooser for user to select a file
     * @return Scanner for user selected file, null if file not found
     */
    public  Scanner getScanner(){
        
        int retval = ourChooser.showOpenDialog(null);
        
        if (retval == JFileChooser.APPROVE_OPTION){
            File f = ourChooser.getSelectedFile();	
            Scanner s;
            try {
                s = new Scanner(f);
            } catch (FileNotFoundException e) {
                return null;
            }
            return s;
	    }
        return null;
    }
    
	/**
	 * Reads in the Actors and Movies from a Scanner initialized to the casts of
	 * a list of movies.
	 * Each line in the data file consists of a movie title, followed by a list 
	 * of actors and actresses that appeared in that movie, delimited by delimiter
	 * 
	 * @param in
	 *            the file to be read, does not do anything if the file cannot
	 *            be read for any reason
	 * @param delimiter
	 *            the String that appears between Movie and Actor names on each
	 *            line
	 */
	public void readCasts(Scanner in, String delimiter) {
		long begin = System.currentTimeMillis();
		// Each line of file
		// movie name/actor 1/actor 2/ .... / actress n
		while(in.hasNext()){
			String line = in.nextLine();
			String[]elems = line.split(delimiter);
			if(elems.length > 0){
				String movieName = elems[0];
				Movie moov = new Movie(movieName);
				myMovies.put(movieName, moov);
				for(int k = 1; k<elems.length; k++){
					Actor a = new Actor(elems[k]); // actor
					a.add(moov);
					moov.addActor(a);
					myActors.put(elems[k], a);
				}
			}
		}
		// TODO complete readCasts to read in Movie and Actors from file
		//      and add names of movies/actors to myMovies, myActors maps as keys
		
		System.out.println("File read. Time elapsed: "
				+ (System.currentTimeMillis() - begin) * .001 + "s");
	}


	/**
	 * Using the actors and movies maps, create an Actor graph where:
	 * <ul>
	 * <li>Vertices: Actor names
	 * <li>Edges: two Actors are connected iff they appeared in the same movie
	 * </ul>
	 */
	public void createActorGraph() {
            // TODO: complete createActorGraph
		Graph g = new Graph();
		//the vertices are all of the actors
		for(String a: myActors.keySet()){
			g.addVertex(a);
			Actor kevin = myActors.get(a);
			for(Movie m: kevin.getMovies()){
				for(Actor other: m.getActors())
					g.addEdge(a, other.getName());
			}
		}
		//there is an edge between two actors
		//iff those actors appeared in the same movie
	}

	/**
	 * Using the actors and movies maps, create an Actor-Movie graph where:
	 * <ul>
	 * <li>Vertices: Actor and Movie names
	 * <li>Edges: an Actor is connected to a Movie, iff he or she appeared in
	 * that movie
	 * </ul>
	 */
	public void createActorMovieGraph() {
		Graph g = new Graph();
		for(String a: myActors.keySet()){
			g.addVertex(a);
			Actor kevin = myActors.get(a);
			for(Movie m: kevin.getMovies()){
				g.addEdge(a, m.name);
			}
		}
	}
 
	/**
	 * Using the actors and movies maps, create Movie graph where:
	 * <ul>
	 * <li>Vertices: Movie names
	 * <li>Edges: two Movies are connected iff they share an Actor
	 * </ul>
	 */
	public void createMovieGraph() {
		Graph g = new Graph();
		for(String m: myMovies.keySet()){
			g.addVertex(m);
			Movie moov = myMovies.get(m);
			for(Actor a: moov.getActors()){
				for(Movie other: a.getMovies()){
					g.addEdge(m, other.name);
				}
			}
		}
	}

	/**
	 * Traverse the graph from node named source
	 * 
	 * @param source
	 *            the String name of a vertex
	 */
	public void traverse(String source) {
		if (myG.hasVertex(source))
			traverse(myG, myG.getVertex(source));
	}

	/**
	 * Traverse the graph using breadth-first search on g from source
	 * 
	 * If source is not in the Graph, then the traversal will do nothing.
	 * 
	 * @param g
	 *            Graph that should be initialized and all vertices must have
	 *            distance set to Vertex.Infinity. After traversal, distance and
	 *            predecessor fields will be set.
	 * @param source
	 *            the Vertex from which to begin the traversal
	 */
	public void traverse(Graph g, Vertex source) {
		Queue<Vertex> fringe = new LinkedList<Vertex>();
		fringe.add(source);
		source.distance = 0;
		long begin = System.currentTimeMillis();
		// O(V+E)
		while (!fringe.isEmpty()) {
			Vertex v = fringe.remove();
			// V times
            // TODO: complete traverse
			
		}
		System.out.println("Graph with " + myG.numVertices() + " vertices & "
				+ myG.numEdges() + " edges traversed. Time elapsed: "
				+ (System.currentTimeMillis() - begin) * .001 + "s");

	}

	/**
	 * Print the chain from Kevin Bacon to specified actor or actress. If no
	 * such actor or actress. print error message 
	 *   Actor 1 Name has a Bacon number of X 
	 *   Actor 1 Name appeared in Movie 1 Name with Actor 2 Name 
	 *   ... 
	 *   Actor Z Name appeared in Movie Z Name with Kevin Bacon
	 * 
	 * @param name
	 *            for actor or actress.
	 */
	public void printChain(String name) {
		Vertex start = myG.getVertex(SOURCE);
		Vertex dest = myG.getVertex(name);
		if (dest == null) {
			System.out.println("No such actor " + name);
		} else if (dest.distance == Vertex.INFINITY) {
			System.out.println(dest.name + " has a Bacon number of infinity");
		} else {
			// TODO complete printChain
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialize Bacon & read file
		Bacon bfs = new Bacon();
		
		// Create graph
		long begin = System.currentTimeMillis();
		bfs.createActorGraph();
		System.out.println("Graph created. Time elapsed: "
				+ (System.currentTimeMillis() - begin) * .001 + "s");

		// Traverse graph and print chain
		bfs.traverse(Bacon.SOURCE);
		bfs.printChain("Bacon, Kevin");
	}

}
