import java.util.*;
public class Dirsort implements Comparator<String>{
	
	@Override
	public int compare(String o1, String o2) {
		String[]a = o1.split("/");
		String[]b = o2.split("/");
		if(a.length!=b.length)
			return a.length-b.length;
		else{
			for(int x = 0; x<a.length; x++){
				if(a[x].compareTo(b[x])!=0)
					return a[x].compareTo(b[x]);
			}
		return 0;
		}
	} 
	
	public String[] sort(String[] dirs){
    	 
          Arrays.sort(dirs, (String a, String b)->compare(a,b));
          return dirs;
     }

	
  }