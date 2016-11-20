
import java.util.Arrays;


public class TypingJob {
    public int bestTime(int[] pages) {
    	Arrays.sort(pages);
    	int[]pages1 = new int[pages.length];
    	for(int x = 0; x<pages.length; x++){
    		pages1[pages.length-1-x] = pages[x];
    	}

        return bestTime(pages1, 0, 0, 0, 0);
    }
  
    public int bestTime(int[] pages, int index, int t1, int t2, int t3) {
        if (index >= pages.length)
        		return Math.max(t1, Math.max(t2, t3));
        return Math.min(Math.min(bestTime(pages, index+1, t1+pages[index], t2, t3), bestTime(pages, index+1, t1, t2+pages[index], t3)), bestTime(pages, index+1, t1, t2, t3+pages[index]));
        

    }
    public static void main (String[]args){
    	TypingJob t = new TypingJob();
    	System.out.print(t.bestTime(new int[]{5, 4, 3, 2, 1}));
    }
}
