import java.util.ArrayList;

/**
 * Name: Katrina Zhu
 * Course: CompSci 201
 * Date: September 17, 2014
 * Purpose:
 *   Class that outputs number of possible locations of a NewHouse, given coordinates of old houses
 *   New House must have an old house to the east, south, north, and west
 */
public class NewHouse {
	/**
	 * Method that traverses through all possible coordinates and checks if they have a house to the N, S, E, W
	 * If they do have a house in all directions, adds the coordinates to an ArrayList and returns the size of the list
	 */
       public int count(int[] x, int[] y){
           ArrayList<Integer> is = new ArrayList<Integer>();
           ArrayList<Integer> js = new ArrayList<Integer>();
           for (int i = -99; i<=99; i++){
        	   ArrayList<Integer> list = new ArrayList<Integer>();
        	   for(int z=0; z<x.length; z++){
        		   if (x[z]==i){
        			   list.add(z);
        		   }
        	   }
        	   if(list.size()>=2){
        		   for(int j = -99; j<=99; j++){
        			   for(int a = 0; a<list.size(); a++){
        				   if(j<y[list.get(a)]){
        					   for(int b = 0; b<list.size(); b++){
        						   if(j>y[list.get(b)]){
        							   
        							   //we have a point (i,j) and know there is a point above and below it
        							   ArrayList<Integer> list2 = new ArrayList<Integer>();
        							   for (int g = 0; g<y.length; g++){
        								   
        								   if(y[g]==j){
        									   list2.add(g);
        									   //now we are adding the index of y coordinates that match up
        								   }
        							   }
        							   //are there more than 2 y coordinates that match up with this point?
        							   if(list2.size()>=2){
        								   //are these y coordinates to the left and right of this point?
        								   for(int d = 0; d<list2.size(); d++){
        									   if(x[list2.get(d)]<i){
        										   for(int e = 0; e<list2.size(); e++){
        											   if(x[list2.get(e)]>i){
        												   if(is.size()==0){
        													   is.add(i);
        													   js.add(j);
        													   //System.out.println("only first one");
        												   }
        												   else{
        												   
        													   if(i==is.get(is.size()-1)){
        														   if(j==js.get(is.size()-1))
        															   ;
        														   else{
        															   is.add(i);
        															   js.add(j);
        															   //System.out.println("first is the same second is not");
        														   }
        														   
        													   }
        													   else{
        													   is.add(i);
            												   js.add(j);
            												   //System.out.println("completely new coordinate");
        													   }
        												   }
        												          												  
        												   //System.out.print("("+i+", "+j+") ");
        											   }
        										   }
        									   }
        								   }
        							   }
        							   list2.removeAll(list2);
        							   }
        						   }
        					   }
        				   }
        			   }
        		   }
        	   
        	   list.removeAll(list);
           }
           return is.size();           
           }
       /**
        * Main method that creates an instance of NewHouse and executes count method.
        */
       public static void main(String[]args){
    	   NewHouse c = new NewHouse();
    	   int [] x = new int []{1, 7, 3, 4, 9, 3, 7, 1, 1, 6, 1, 6, 1, 9, 7, 9, 4, 9, 1, 4, 7, 1, 2, 5, 3, 8, 7, 7, 1};
    	   int [] y = new int[]{7, 2, 8, 9, 4, 3, 4, 1, 4, 1, 7, 8, 1, 1, 1, 4, 7, 1, 9, 4, 9, 1, 4, 1, 1, 1, 2, 4, 3};
    	   int cat = c.count(x, y);
    	   System.out.print(cat);
       }
   }
