import java.util.ArrayList;

/**
 * Name: Katrina Zhu
 * Course: CompSci 201
 * Date: September 17, 2014
 * Purpose:
 *   Class that sees if player's words match up with dictionary words, and rewards points accordingly.
 */
public class SimpleWordGame {
	/**
	 * Method that takes two arrays, 
	 * sees if player array's words match with dictionary's words (no duplicates), 
	 * and calculates and outputs a score.
	 */
      public int points(String[] player, 
                        String[] dictionary) {
          ArrayList<String> usedWords = new ArrayList<String>();
          int score = 0;
          for(int x = 0; x<player.length; x++){
        	  boolean repeat = false;
        	  for(int y = 0; y<usedWords.size(); y++){
        		  if(usedWords.get(y).equals(player[x])){
        			  repeat=true;
        		  }
        	  }
        	  if(repeat==false){
        		  usedWords.add(player[x]);
        		  for(int z = 0 ; z<dictionary.length; z++){
        			  if(player[x].equals(dictionary[z])){
        				  score += dictionary[z].length()*dictionary[z].length();
        			  }
        		  }
        	  }
          }
          return score;
      }
      /**
       * Main method that creates an instance of SimpleWordGame and carries out method points
       */
      public static void main(String[]args){
    	  SimpleWordGame s = new SimpleWordGame();
    	  String [] player = new String[]{"apple", "orange", "strawberry"};
    	  String[]dictionary = new String[]{"strawberry", "orange", "grapefruit", "watermelon"};
    	  System.out.println(s.points(player, dictionary));
      }
  }
