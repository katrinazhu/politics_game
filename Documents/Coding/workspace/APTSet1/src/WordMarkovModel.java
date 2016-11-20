
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//public class WordMarkovModel extends MarkovModel{
	static Map <Integer, Map<WordNgram, ArrayList<WordNgram>>> maps = new HashMap<Integer, Map<WordNgram, ArrayList<WordNgram>>>();

	
	public Map <WordNgram, ArrayList<WordNgram>> createMap(int k){
		String wrapAroundString = myString + myString.substring(0,k); 
		String[] text = wrapAroundString.split("\\s+");
		Map <WordNgram, ArrayList<WordNgram>> words= new HashMap<WordNgram, ArrayList<WordNgram>>();
		for (int j=0; j<text.length; j++) {
			WordNgram ngram = //WordNgram from j to k in array text 
			if (!words.containsKey(ngram)) {
			words.put(ngram, new ArrayList<WordNgram>()); 
			}
			ArrayList<WordNgram> list = words.get(ngram);
			list.add(//WordNgram from j+1 to k+1 in the text); 
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
		String wrapAroundString = myString + myString.substring(0,k); 
		String[] text = wrapAroundString.split("\\s+");
		int start = myRandom.nextInt(text.length - k + 1);
		WordNgram seed = ;//WordNgram from start to start+k;
		ArrayList<String> nGram = new ArrayList<String>();
		for(int y = 0; y<seed.length; y++){
			//need to implement length method
		}
		for (int x = k-1; x<numWords; x++){
			ArrayList<WordNgram> followSeed = new ArrayList<WordNgram>();
			followSeed = words.get(seed);
			WordNgram kgram = followSeed.get(myRandom.nextInt(followSeed.size()));
			nGram+=kgram.charAt(k-1); //take the last word from the word n gram and add to the arraylist
		}
		return nGram;		
	}
}