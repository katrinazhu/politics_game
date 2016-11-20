import java.util.ArrayList;

public class BoggleScore {
    		    private char[][] myBoard;
    		    private ArrayList<long[][]> myGrids = new ArrayList<long[][]>();
    		    private static final long INF = (long) 1E13;
    		    
    		    public long bestScore(String[] grid, String[] words){
    		        myBoard = new char[4][4];
    		        
    		        for(int i=0; i < 4; i++){
    		            for(int j=0; j < 4; j++){
    		                myBoard[i][j] = grid[i].charAt(j);
    		            }
    		        }
    		        for(int i=0; i < 50; i++){
    		            myGrids.add(new long[4][4]);
    		        }
    		        long total = 0L;
    		        for(String s : words){
    		            total = (total + score(s)) % INF;
    		        }
    		        return total;
    		    }
    		    //helper method scores
    		    private long score(String s) {
    		        
    		        char first = s.charAt(0);
    		        long[][] current = myGrids.get(0);
    		        for(int i=0; i < 4; i++){
    		            for(int j=0; j < 4; j++){
    		                if (myBoard[i][j] == first) {
    		                    current[i][j] = 1L;
    		                }
    		                else {
    		                    current[i][j] = 0L;
    		                }
    		            }
    		        }
    		        
    		        for(int k=1; k < s.length(); k++){
    		            char ch = s.charAt(k);
    		            long[][] previous = current;
    		            current = myGrids.get(k);
    		            for(int i=0; i < 4; i++){
    		                for(int j=0; j < 4; j++){
    		                    if (myBoard[i][j] == ch) {
    		                        current[i][j] = neighborsum(previous,i,j);
    		
    		                    }
    		                    else {
    		                        current[i][j] = 0L;
    		                    }
    		                }
    		            }
    		        }
    		        long sum = 0;
    		        for(int x = 0; x<current.length; x++){
    		        	for(int y = 0; y<current.length; y++){
    		        		sum+=current[x][y];
    		        	}
    		        	
    		        }
    		        long length = s.length();
    		        sum=sum*length*length;
    		        return sum;
    		    }
    		        
    		        private long neighborsum (long[][] previous, int i, int j){
    		        	long count = 0L;
    		        	int[] rdelta = {-1,-1,-1, 0, 0, 1, 1, 1};
    		        	int[] cdelta = {-1, 0, 1,-1, 1,-1, 0, 1};
    		        	for (int k = 0; k<rdelta.length; k++){
    		        		if(i+rdelta[k]<0||i+rdelta[k]>=previous.length||j+cdelta[k]<0||j+cdelta[k]>=previous.length)
    		        			count+=0L;
    		        		else{
    		        			count+=previous[i+rdelta[k]][j+cdelta[k]]%INF;
    		        		}
    		        	}
    		        	return count;
    		        }
    		        public static void main (String[]args){
    		        	BoggleScore b = new BoggleScore();
    		        	long f = b.bestScore(new String[]{"XXXX", "XAAA", "XXBX", "XXXX"}, 
    		        			new String[]{"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAABAAAAAAAAAAAABAAAAAAAAAAAAAABAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB", "AAAAAAAAACCCAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAZ", "A", "B", "C"});
    		        }
    		    }
