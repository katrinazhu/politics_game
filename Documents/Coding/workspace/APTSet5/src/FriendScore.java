import java.util.ArrayList;


public class FriendScore {
	ArrayList<Integer>friendScores = new ArrayList<Integer>();
    private int helper(int friend, String[]friends){
    	ArrayList<Integer>store = new ArrayList<Integer>();
    	store.add(friend);
    	int count = 0;
    	String s = friends[friend];
    	for(int x = 0; x<s.length(); x++){
    		if(s.charAt(x)=='Y'){
    			if(!store.contains(x)){
    				count++;
    				store.add(x);
    			}
    			String f = friends[x];
    			for(int y = 0; y<f.length(); y++){
    				if(f.charAt(y)=='Y'){
    					if(!store.contains(y)){
    						count++;
    						store.add(y);
    					}
    				}
    			}
    		}
    	}
    	return count;
    	
    }
	public int highestScore(String[] friends) {
         for(int x = 0; x<friends.length; x++){
        	 friendScores.add(helper(x, friends));
         }
         int max = 0;
         for(int x = 0; x<friendScores.size(); x++){
        	 if(max<friendScores.get(x))
        		 max = friendScores.get(x);
	}
         return max;
	}
   }
