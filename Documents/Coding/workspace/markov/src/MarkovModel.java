import java.util.*;

/**
 * The model for the Markov text generation assignment. See methods for
 * details.  This model should use a brute-force algorithm for
 * generating text, i.e., the entire training text is rescanned each
 * time a new character is generated.
 */
public class MarkovModel extends AbstractModel {
    protected String myString;
    protected Random myRandom;
    public static final int DEFAULT_COUNT = 1600; // default # random letters generated
    public static int RANDOM_SEED = 1234; 
    
    
    public MarkovModel() {
        myRandom = new Random(RANDOM_SEED);
    }
    
    /**
     * Create a new training text for this model based on the information read
     * from the scanner.
     * 
     * @param s
     *            is the source of information
     */
    public void initialize(Scanner s) {
        double start = System.currentTimeMillis();
        int count = readChars(s);
        double end = System.currentTimeMillis();
        double time = (end - start) / 1000.0;
        super.messageViews("#read: " + count + " chars in: " + time + " secs");
    }
    
    /**
     * Read characters from entire file into myString
     * @param s non-null Scanner at the beginning of a file
     * @return number of characters read
     */
    protected int readChars(Scanner s) {
        myString = s.useDelimiter("\\Z").next();
        s.close();
        return myString.length();
    }
    
    /**
     * Generate N letters using an order-K markov process where the parameter is
     * a String containing K and N separated by whitespace with K first. If N is
     * missing it defaults to some value.
     */
    public void process(Object o) {
        String temp = (String) o;
        String[] nums = temp.split("\\s+");
        int k = Integer.parseInt(nums[0]);
        int numLetters = DEFAULT_COUNT;
        if (nums.length > 1) {
            numLetters = Integer.parseInt(nums[1]);
        }
        
        double stime = System.currentTimeMillis();
        String text = makeNGram(k, numLetters);
        double etime = System.currentTimeMillis();
        double time = (etime - stime) / 1000.0;
        this.messageViews("time to generate: " + time);
        this.notifyViews(text);
        
    }
    
    /**
     * Generates random text that is similar to the reference text (myString).
     * 
     * @param k order of n-gram       
     * @requires k > 0
     * @param numLetters number of characters to generate      
     * @return numLetters of randomly selected characters based on picking
     *         representative characters that follow each k characters
     */
    protected String makeNGram(int k, int numLetters) {
        // Appending to StringBuilder is faster than appending to String

        StringBuilder build = new StringBuilder();
        // Pick a random starting index
        int start = myRandom.nextInt(myString.length() - k + 1);
        String seed = myString.substring(start, start + k);
        // Allow for wraparound 
        String wrapAroundString = myString + myString.substring(0,k);   
        ArrayList<Character> list = new ArrayList<Character>();
        // generate numLetters characters
        for (int i = 0; i < numLetters; i++) {
            list.clear();
            int pos = 0;
            while ((pos = wrapAroundString.indexOf(seed, pos)) != -1 && pos < myString.length()) {
                char ch = wrapAroundString.charAt(pos + k);
                list.add(ch);
                pos++;
            }
            int pick = myRandom.nextInt(list.size());
            char ch = list.get(pick);
            build.append(ch);
            seed = seed.substring(1) + ch;
        }
        return build.toString();
    }
}