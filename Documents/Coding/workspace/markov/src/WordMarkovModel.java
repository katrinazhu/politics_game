import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;


public class WordMarkovModel extends AbstractModel{
	static Map <Integer, Map<WordNgram, ArrayList<WordNgram>>> maps = new HashMap<Integer, Map<WordNgram, ArrayList<WordNgram>>>();
	protected String myString;
    protected Random myRandom;
    public static final int DEFAULT_COUNT = 100; // default # random letters generated
    public static int RANDOM_SEED = 1234;
    public WordMarkovModel() {
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
        ArrayList<String> text1 = makeNGram(k, numLetters);
        String text = "";
        for(int x=0; x<text1.size(); x++){
        	text=text+text1.get(x) + " ";
        }
        double etime = System.currentTimeMillis();
        double time = (etime - stime) / 1000.0;
        this.messageViews("time to generate: " + time);
        this.notifyViews(text);
        
    }
	public String[] wrapAroundArray(String s, int a){
		String[] myArray = s.split("\\s+");
		String[]wrapArray = new String[myArray.length+a];
		for(int x = 0; x<myArray.length; x++){
			wrapArray[x]=myArray[x];
		}
		for(int x=0; x<a; x++){
			wrapArray[myArray.length+x]=myArray[x];
		}
		return wrapArray;
	}
	public Map <WordNgram, ArrayList<WordNgram>> createMap(int k){
		String[] text = wrapAroundArray(myString, k);
		Map <WordNgram, ArrayList<WordNgram>> words= new HashMap<WordNgram, ArrayList<WordNgram>>();
		WordNgram ngram = new WordNgram(text, 0, k);
		System.out.println(ngram);
		for (int j=0; j<myString.split("\\s+").length; j++) {
			if (!words.containsKey(ngram)) {
			words.put(ngram, new ArrayList<WordNgram>()); 
			}
			ArrayList<WordNgram> list = words.get(ngram);
			WordNgram kgram = new WordNgram(text, j+1, k);
			list.add(kgram);
			ngram = kgram;
			System.out.println(ngram);
			}
		Integer k2 = new Integer(k);
		maps.put(k2, words);
		return words;
	}
	
	public ArrayList<String> makeNGram (int k, int numWords){
		Map<WordNgram, ArrayList<WordNgram>> words = new HashMap<WordNgram, ArrayList<WordNgram>>();
		if(maps.containsKey(k)){
			words = maps.get(k);
		}
		else{
		words = createMap(k);
		}

		String[] text = wrapAroundArray(myString, k);
		int start = myRandom.nextInt(text.length - k + 1);
		WordNgram seed = new WordNgram(text, start, k);
		ArrayList<String> nGram = new ArrayList<String>();
		for(int y = 0; y<seed.length(); y++){
			nGram.add(seed.wordAt(y));
			}
		for (int x = k-1; x<numWords; x++){
			ArrayList<WordNgram> followSeed = new ArrayList<WordNgram>();
			followSeed = words.get(seed);
			if(followSeed!=null){
			int y = followSeed.size();
			int z = myRandom.nextInt(y);
			WordNgram kgram = followSeed.get(z);
			nGram.add(kgram.wordAt(k-1)); 
			seed = kgram;
			}
		}
		return nGram;	
		
	}		
	}


