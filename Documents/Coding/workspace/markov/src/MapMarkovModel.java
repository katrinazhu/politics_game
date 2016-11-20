import java.util.*;

public class MapMarkovModel extends MarkovModel{
	static Map <Integer, Map<String, ArrayList<String>>> maps = new HashMap<Integer, Map<String, ArrayList<String>>>();
	public Map <String, ArrayList<String>> createMap(int k){
		String wrapAroundString = myString + myString.substring(0,k);   
		Map <String, ArrayList<String>> words= new HashMap<String, ArrayList<String>>();
		for (int j=0; j<myString.length(); j++) {
			String ngram = wrapAroundString.substring(j, j+k); 
			if (!words.containsKey(ngram)) {
			words.put(ngram, new ArrayList<String>()); 
			}
			ArrayList<String> list = words.get(ngram);
			list.add(wrapAroundString.substring(j+1, j+k+1)); 
			}
		Integer k2 = new Integer(k);
		maps.put(k2, words);
		return words;
	}
	
	public String makeNGram (int k, int numLetters){
		Map<String, ArrayList<String>> words = new HashMap<String, ArrayList<String>>();
		if(maps.containsKey(k)){
			words = maps.get(k);
		}
		else{
		words = createMap(k);
		}
		int start = myRandom.nextInt(myString.length() - k + 1);
        String seed = myString.substring(start, start + k);
		String nGram = seed;
		for (int x = k-1; x<numLetters; x++){
			ArrayList<String> followSeed = new ArrayList<String>();
			followSeed = words.get(seed);
			String kgram = followSeed.get(myRandom.nextInt(followSeed.size()));
			nGram+=kgram.charAt(k-1);
			seed = kgram;
		}
		return nGram;		
	}
}
