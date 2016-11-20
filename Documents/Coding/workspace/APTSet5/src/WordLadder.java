import java.util.*;
public class WordLadder {
	private boolean oneAway(String s1, String s2){
		if(s1.length()!=s2.length())
			return false;
		for(int k = 0; k<s1.length(); k++){
			//compare character s1[k] to s2[k]
		}
	}
	//return an adjacency list representing the graph formed by
	//the words, from, and to
	private List<List<String>> createGraph(List<String>words, String from, String to){
		ArrayList<List<String>> adj = new ArrayList<List<String>>();
		int index = 0;
		for(String word: words){
			for(int k = 0; k<words.size(); k++){
				if(oneAway(word, words.get(k)))
					//add a connection from word to words[k] in adj list
			}
		}
	}
    public String ladderExists(String[] words, 
                               String from, String to) {
         // fill in code here
    }
  }
