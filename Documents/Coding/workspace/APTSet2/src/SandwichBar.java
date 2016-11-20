import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Name: Katrina Zhu
 * Course: CompSci 201
 * Date: September 17, 2014
 * Purpose:
 *   Class that takes a set of orders and sees which can be fulfilled based on a list of available ingredients.
 */
public class SandwichBar
   {
	/**
	 * Method that converts an array to a map with integers representing the indices of an array
	 * and turns the Array's strings into ArrayLists in the map 
	 */
	public Map<Integer, ArrayList<String>> arrayToMap(String[]orders){
		Map<Integer, ArrayList<String>> myMap = new HashMap<Integer, ArrayList<String>>();

  	  for(int x = 0; x<orders.length; x++){
  		  ArrayList<String> order = new ArrayList<String>();
  		  String oneOrder = orders[x];
  		  if(oneOrder.contains(" ")){
  			  String[]y = oneOrder.split(" ");
  			  String firstOrder = y[0];
  			  order.add(firstOrder);
  			  for(int z = 1; z<y.length; z++){
  				  if(y[z].equals(firstOrder)){
  					  firstOrder = y[z];
  				  }
  				  else
  				  order.add(y[z]);
  			  }
  			  myMap.put(x, order);
  		  }
  		  else{
  			  order.add(oneOrder);
  			  myMap.put(x, order);
  		  }
  	  }
  	  return myMap;
	}
	/**
	 * Method that sees if the available contents fulfill any of the orders and returns the index of that order
	 */
      public int whichOrder(String[] available, String[] orders){
    	  Map<Integer, ArrayList<String>> myMap = arrayToMap(orders);
    	  for(int x = 0; x<myMap.size(); x++){
    		  int count = 0;
    		  for(int y = 0; y<myMap.get(x).size(); y++){    			  
    			  for(int z = 0; z<available.length; z++){
    				  if(available[z].equals(myMap.get(x).get(y))){
    					  count++;
    					  break;
    				  }
    			  }
    			  
    			  }
    		  if(count == myMap.get(x).size()){
				  return x;
    		  }
    	  }
    	  return -1;
      }
      /**
       * Main method which creates instance of the class and carries out method whichOrder
       */  
      public static void main (String[]args){
    	  SandwichBar b = new SandwichBar();
    	  int x = b.whichOrder(new String []{"ham", "mustard", "pickles"}, new String[]{"pickles", "ham ham cheese bread","mustard ham mustard pickles"});
    	  System.out.println(x);
      }
   }
   