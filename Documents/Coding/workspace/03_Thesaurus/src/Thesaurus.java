import java.util.*;
/**
 * Name: YOUR NAME HERE
 * Course: CompSci 201
 * Problem: Recitation 3
 * Date: September 12, 2014
 * Purpose:
 *   Solve a problem by applying some set operations 
 */

public class Thesaurus
{
    /**
     * Converts the elements of a String to a set. The format
     * of a n-word String should be "word1 word2 word3... wordn"
     * That is, each word should be separated by exactly one space
     * and there should be no leading or trailing spaces. 
     * @param s words with individual spaces separating
     * words
     * @return elements of s as a Set
     */
     public Set<String> sToSet(String s)
     {
         String []x = s.split(" ");
         TreeSet<String> a = new TreeSet<String>();
         for(int y = 0; y<x.length; y++){
        	 a.add(x[y]);
         }
         return a;
     }
     
     /**
      * Converts the elements in a collection to a space-separated list.
      * That is, if the collection contains [A, B, C, D], the method
      * should return "A B C D". There should be no leading or trailing
      * spaces
      * 
      * @return the elements of elems as a space-separated String
      */
     public String collToS(Collection<String> elems)
     {
         Collection <String>c = new TreeSet<String>();
         c.add(" ");
         elems.removeAll(c);
         String[]y = new String[0];
         String[]x = elems.toArray(y);
         String d = "";
         for(int a = 0; a<x.length-1; a++){
        	 d.concat(x[a]+" ");
         }
         d.concat(x[x.length-1]);
         return d;
     }

     
     /**
      * Returns the number of elements contained in both sets.
      * The sets passed in should not be changed.
      * @param a a set of words
      * @param b another set of words
      * @return number of elements in common to a and b
      */
     public int numInCommon(Set<String> a, Set<String> b)
    {
    	String [] array = new String[0];
        int x = 0;
        String[]arraya = a.toArray(array);
        String[]arrayb = b.toArray(array);
        for(int y = 0; y<arraya.length; y++){
        	for(int z = 0; z<arrayb.length; x++){
        		if(arraya[y].equals(arrayb[z]))
        				x++;
        	}
        }
        return x;
    }


     /**
      * Creates a new set that is the union of the given sets. 
      * The union of two sets is the set that contains all of the 
      * elements of both sets. The sets passed in should not
      * be changed.
      *
      * @param a a set of words
      * @param b another set of words
      * @return union of sets a and b
      */
     public Set<String> union(Set<String> a, Set<String> b)
    {
    	 Set<String> results = new TreeSet<String>();
         /*for(String s: a)
         	results.add(s);*/
         results.addAll(a);
         results.addAll(b);
         return results;
    }
 
    /**
     * Creates an edited version of  Thesaurus entries.
     * 
     * If any two entries have 2 or more words in common 
     * then they should be combined into a single entry.
     * The final Thesaurus must contain no pair of entries
     * that have 2 or more words in common. Each entry must
     * contain no duplicates. The words within each element 
     * of the returned value must be in alphabetical order,
     * and the elements must appear in alphabetical order
     *  
     * @param entry each element is a list of words that are all synonyms
     * @return edited version of Thesaurus entries
     */
    public String[] edit(String[] entry) {
        // TODO: Convert entries from array of Strings to ArrayList of Sets
    	ArrayList<Set> entrySet = new ArrayList<Set>();
    	for(int x = 0; x<entry.length; x++)
            entrySet.add(sToSet( entry[x]));
        // TODO: Keep merging entries until nothing gets merged
    	//Let n be the number of elements in entrySet
    	  //For every pair (i,j) where 0 ≤ i,j < n and i < j
    	    //if entrySet[i] and entrySet[j] have ≥ 2 in common
    	      //merge entrySet[i] and entrySet[j]
    	int n = entrySet.size();
    	
        // TODO: Convert list of Sets to an array of Strings

        // TODO: Sort entries in alphabetical order

        return entry;
    }
    
    public static void main(String[] args) {
        Thesaurus t = new Thesaurus();
        String[] input = {"ape monkey wrench", "wrench twist strain", "monkey twist frugue strain" };
        for(int x = 0; x<input.length; x++)
        System.out.println(t.sToSet( input[x]));
        // TODO: add more tests
    }
}
