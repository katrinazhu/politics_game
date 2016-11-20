import java.util.*;
public class SortedFreqs implements Comparator<String>{
	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}
	
	public int[] freqs(String[] data) {
		if(data.length==0)
			return new int[0];
      Arrays.sort(data, (String a, String b)->compare(a,b));
      int size = 1;
      String unique = data[0];
      for(int x = 1; x<data.length; x++){
    	  if(data[x].equals(unique));
    	  else{
    		  size++;
    		  unique = data[x];
    	  }
      }
      int[]freq = new int[size];
      int index = 0;
      String temp = data[0];
      int count = 1;
      for(int x = 1; x<data.length; x++){
    	  if(data[x].equals(temp)){
    		  count++;
    	  }
    	  else{
    		  temp = data[x];
    		  freq[index] = count;
    		  index++;
    		  count = 1;
    	  }
    		  
      }
      freq[size-1]=count;
      return freq;
    }

	public static void main (String[]args){
		SortedFreqs cat = new SortedFreqs();
		String[] fish = new String[]{"a", "b", "c", "d"};
		int[]dog = cat.freqs(fish);
		for(int x = 0; x<dog.length; x++)
			System.out.print(dog[x]+ " ");
	}

 }