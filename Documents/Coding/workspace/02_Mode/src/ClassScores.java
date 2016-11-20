import java.io.FileNotFoundException;
import java.io.File;
import java.util.*; // for Scanner and ArrayList
import javax.swing.JFileChooser;


public class ClassScores {
    // chooser allows users to select a file by navigating through
    // directories
    private static JFileChooser ourChooser = new JFileChooser(System
            .getProperties().getProperty("user.dir"));

    /**
     * Brings up chooser for user to select a file
     * @return Scanner for user selected file, null if file not found
     */
    public static final int MAX_VALUE = 100;
    public  Scanner getScanner(){
        
        int retval = ourChooser.showOpenDialog(null);
        
        if (retval == JFileChooser.APPROVE_OPTION){
            File f = ourChooser.getSelectedFile();	
            Scanner s;
            try {
                s = new Scanner(f);
            } catch (FileNotFoundException e) {
                return null;
            }
            return s;
	    }
        return null;
    }

    /**
     * Reads in all of the words in a file and prints each one out
     * one per line to the console.
     * @param in initialized Scanner for file
     */
    public void readAndPrintFile(Scanner in) {
    	while(in.hasNext())
    		System.out.println(in.next());
    }

    /**
     * Returns the "mode" of a set of data points: the most common
     * value(s)
     * @param scores scores on each test papers in range [0,100]
     * @return mode of the set of scores. In the case of more than
     * one number, they should be returned in increasing order. 
     */
    public int count (int[] scores, int val){
    	int occur = 0;
    	for (int k: scores){
    		if(k==val)
    			occur++;
    	}
    	return occur;
    }
    public int[] findMode(int[] scores) {
    	int []counts = new int[MAX_VALUE +1];
    	for(int k = 0; k<= MAX_VALUE; k+=1)
    		counts[k] = count(scores, k);
    	//counts has how often each number occurs
    	//compute the max in counts
    	int max_occur = 0;
    	for (int occur: counts){
    		if (occur > max_occur)
    			max_occur = occur;
    	}
        int modes = 0;
        for (int count: counts){
        	if(count == max_occur)
        		modes+=1;
        	}
        int [] result = new int[modes];
        //find all modes and put them in the result
        int index = 0;
       
        for (int x = 0; x<scores.length; x++){
        	if (counts[x]==max_occur){
        		result[index] = scores[x];
        		index++;
        	}        		
        }
        Arrays.sort(result);
        return result;
    }
    public static void printArray(int[] a){
    	for (int elem: a){
    		System.out.print(elem);
    		System.out.print(" ");
    	}
    }

    /**
     * Returns the "mode" of a set of data points: the most common
     * value(s)
     * @param in legal (i.e., non-null) input where each token is a
     * integer
     * @return mode of the set of scores. In the case of more than
     * one number, they should be returned in increasing order. 
     */
    public int[] findMode(Scanner in) {
        // TODO: complete findMode for a file
    	ArrayList<Integer> list = new ArrayList <Integer>();
    	while(in.hasNext()){
    		if (in.hasNext())
    				list.add(in.nextInt());
    		else
    			in.next();
    	}
        // FIX: returning empty array
    	int[] result = new int[list.size()];
    	for (int k=0; k<result.length; k++)
    		result [k]= list.get(k);
    	return findMode(result);
    }
}

    /*public static void main(String[] args) {
        int[] example = { 88, 70, 65, 70};

        ClassScores cs = new ClassScores();
        printArray(cs.findMode(example));
        //Scanner in = cs.getScanner();
        //cs.readAndPrintFile(in);
        
        // TODO: test out mode implementations
       // findMode(in);
    }
}*/