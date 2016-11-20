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
    
    /**
     * returns mininum number of  minutes to type papers in pages
     * starting with index-th paper and given minutes assigned to typists
     * @param pages number of pages for each paper
     * @param index of paper to start with. That is, consider papers[index,papers.length-1]
     * @param t1 number of pages typed so far by typist 1
     * @param t2 number of pages typed so far by typist 2
     * @param t3 number of pages typed so far by typist 3
     * @return minimum number of pages to type all papers given current
     * assignment
     */
    public int bestTime(int[] pages, int index, int t1, int t2, int t3) {
        // TODO complete bestTime(int[], int, int, int, int)
        if (index >= pages.length)
        		return Math.max(t1, Math.max(t2, t3));
        return Math.min(Math.min(bestTime(pages, index+1, t1+pages[index], t2, t3), bestTime(pages, index+1, t1, t2+pages[index], t3)), bestTime(pages, index+1, t1, t2, t3+pages[index]));
        
        /*if(Math.min(t1, Math.min(t2, t3))==t1){
        	t1+=pages[index];
        }
        if(Math.min(t1, Math.min(t2, t3))==t2)
        	t2+=pages[index];
        if(Math.min(t1, Math.min(t2, t3))==t3)
        	t3+=pages[index];
        return bestTime(pages, index+1, t1, t2, t3);*/
    }
    public static void main (String[]args){
    	TypingJob2 t = new TypingJob2();
    	System.out.print(t.bestTime(new int[]{5, 4, 3, 2, 1}));
    }
}
