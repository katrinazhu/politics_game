
import java.util.*;
/**
 * Name: Katrina Zhu
 * Course: CompSci 201
 * Date: September 17, 2014
 * Purpose:
 *   Class that takes an array of Strings, merges all Strings with 2 or more similar words, and outputs the resulting array 
 */

public class Thesaurus
{
    /**
     * Converts the elements of a String to a set. The format
     * of a n-word String should be "word1 word2 word3... wordn"
     * That is, each word should be separated by exactly one space
     * and there should be no leading or trailing spaces. 
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
      */
     public String collToS(Collection<String> elems)
     {
         Collection <String>c = new TreeSet<String>();
         c.add(" ");
         elems.removeAll(c);
         Object[]x = elems.toArray();
         
         String d = "";
         for(int a = 0; a<x.length-1; a++){
        	 d=d.concat(x[a].toString()+" ");
         }
         d=d.concat(x[x.length-1].toString());
         return d;
         
     }

     
     /**
      * Returns the number of elements contained in both sets.
      */
     public int numInCommon(Set<String> a, Set<String> b)
    {
    	String c = collToS(a);
    	String d = collToS(b);
        String[]x = c.split(" ");
        String[]y = d.split(" ");
        int count = 0;
    	for(int z = 0; z<x.length; z++){
        	for(int f = 0; f<y.length; f++){
        		if(x[z].equals(y[f])){
        				count++;
        		}
        	}
        }
        return count;
    }
     
     /**
      * Creates a new set that is the union of the given sets. 
      * The union of two sets is the set that contains all of the 
      * elements of both sets. The sets passed in should not
      * be changed.

      */
     public Set<String> union(Set<String> a, Set<String> b)
    {
    	 Set<String> results = new TreeSet<String>();
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

     */
    public String[] edit(String[] entry) {
    	ArrayList<Set> entrySet = new ArrayList<Set>();
    	for(int x = 0; x<entry.length; x++)
            entrySet.add(sToSet( entry[x]));      
    	int i = 0;
    	int j = 1;
    	while( i<entrySet.size()-1){
    		while(j<entrySet.size()){
    			Set<String> c = entrySet.get(i);
    			Set<String> d = entrySet.get(j);
    			
    		if(numInCommon(c, d)>=2){
    			entrySet.set(i, union(c, d));
    			entrySet.remove(j);
    			i=0;
    			j=1;
    		}
    		else{
    			j++;
    			
    		}
    		}
    		i++;
    		j=1+i;
    		
    		}
    	String[]entry2 = new String[entrySet.size()];
    	for(int x = 0; x<entry2.length; x++){
    		entry2[x]=collToS(entrySet.get(x));
    	}
    	Arrays.sort(entry2);
        return entry2;
}
    /**
     * Main method that creates an instance of Thesaurus and executes edit.
     */    	   
    public static void main(String[] args) {
        Thesaurus t = new Thesaurus();
        String[] input = {"ape monkey wrench", "wrench twist strain", "monkey twist frugue strain"};        
        System.out.println(t.edit(input));
    }
}

