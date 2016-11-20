import java.util.*;
public class TheBestName implements Comparator<String>{
	
	public int weight(String name){
		if (name.equals("JOHN")){
			return Integer.MAX_VALUE;
		}
		int sum = 0;
		for(int k = 0; k<name.length(); k++){
			sum+=name.charAt(k) - 'A'+1;
		}
		return sum;
	}
	
	public int compare(String o1, String o2) {
		return weight(o2) - weight(o1);
	}
	
	public String[]sort(String[] names){
		Arrays.sort(names, (String a,String b) -> weight(b) - weight(a));
		for(int x = 0; x<names.length-1; x++){
			if(weight(names[x])==weight(names[x+1])){
				if(names[x].compareTo(names[x+1])>0){
					String temp = names[x];
					names[x]=names[x+1];
					names[x+1]=temp;
					x=-1;
				}
			}
		}
		return names;
	}
	
	public static void main(String[]args){
		TheBestName cat = new TheBestName();
		String []names = new String[]{"CC", "BBB", "AAAAAA"};
		String[]dog = cat.sort(names);
		System.out.print("FINAL: ");
		for(int x=0; x<dog.length; x++)
			System.out.print( dog[x]+", ");
	}
}
