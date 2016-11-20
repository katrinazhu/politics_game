import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
public class SortByFreqs {
	Map<String, Integer> myFreqs;
	class FreqCompare implements Comparator<String>{
		@Override
		public int compare(String o1, String o2) {
			return myFreqs.get(o2) - myFreqs.get(o1);
		}
	}
	public String[]sort (String[]data){
		myFreqs = new TreeMap<String, Integer>();
		for(String s: data){
			if(myFreqs.containsKey(s))
				//we've seen this word before
				myFreqs.put(s, myFreqs.get(s)+1);
			else
				//it's a new word
				myFreqs.put(s, 1);
		}
			String[]result = myFreqs.keySet().toArray(new String[0]);
			Arrays.sort(result, (String a, String b)->
				myFreqs.get(b) - myFreqs.get(a));
			return result;
		}
	}	


