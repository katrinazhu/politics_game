import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class ErdosNumber {
	ArrayList<String>authors = new ArrayList<String>();
	ArrayList<String>authorCounts = new ArrayList<String>();
	HashMap<String, Integer> count = new HashMap<String, Integer>();
	ArrayList<String> pubs2 = new ArrayList<String>();
	public String[] calculateNumbers(String[] pubs) {
		for(int x = 0; x<pubs.length; x++)
			pubs2.add(pubs[x]);
		count.put("ERDOS", 0);
		for(int x = 0; x<pubs2.size(); x++){
			String [] words = pubs2.get(x).split(" ");
			for(int y = 0; y<words.length; y++){
				if(!authors.contains(words[y]))
					authors.add(words[y]);
			}
		}
		recurse("ERDOS");
		while(!authors.isEmpty()){
			if(count.get(authors.get(0))==null)
				authorCounts.add(authors.get(0));
			else
				authorCounts.add(authors.get(0) + " " + count.get(authors.get(0)));
			authors.remove(0);
		}
		
		Collections.sort(authorCounts);
		String[]authorCountsArray = new String[authorCounts.size()];
		for(int x = 0; x<authorCounts.size(); x++)
			authorCountsArray[x]=authorCounts.get(x);
		return authorCountsArray;
	}
	public void recurse (String s){
		for(int x = 0; x<pubs2.size(); x++){
			String otherAuthors = pubs2.get(x);
			String[]others = otherAuthors.split(" ");
			ArrayList<String>others2 = new ArrayList<String>();
			for(int z = 0; z<others.length; z++)
				others2.add(others[z]);
			if(others2.contains(s)){
				for(int y = 0; y<others.length; y++){
					if(!count.containsKey(others[y])||count.get(others[y])>count.get(s)+1){
						count.put(others[y], count.get(s)+1);
						recurse(others[y]);
					}
				}
			}			
		}
	}
	public static void main(String[]args){
		ErdosNumber cat = new ErdosNumber();
		String[]x = cat.calculateNumbers(new String[]{"ERDOS A", "A B", "B AA C"});
		
	}
}