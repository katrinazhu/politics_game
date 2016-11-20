import java.util.*;


public class ClientsList implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		String[]x = o1.split(" ");
		String[]y= o2.split(" ");
		if(!x[1].equals(y[1]))
			return x[1].compareTo(y[1]);
		else
			return x[0].compareTo(y[0]);
	}
	
	public String[]inOrder(String[]a){
		for(int x = 0; x<a.length; x++){
			if(a[x].contains(", ")){
				String[]temp = a[x].split(", ");
				String ordered = temp[1] + " " + temp[0];
				a[x] = ordered;
			}
		}
		return a;
	}
	
	public String[] dataCleanup(String[] names) {
        String[]names2 = inOrder(names);
        Arrays.sort(names2, (String a, String b)->compare(a, b));
        return names2;
   }
}
