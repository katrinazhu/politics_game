import java.util.ArrayList;

public class CountAppearances {
     public int numberTimesAppear(int number, int digit) {
    	 int count = 0;
    	 ArrayList <Integer> cat = new ArrayList<Integer>();
    	 while (number>0){
    		 int x = 1;
    		 while (number/x>=10)
    			 x*=10;
    		 cat.add(number/x);
    		 number-=(number/x)*x;
    	 }
    	 for(int y = 0; y<cat.size(); y++){
    		 if(cat.get(y)==digit)
    			 count++;
    	 }
    	 return count;
    	     	 
     }
     public static void main (String[]args){
    	 CountAppearances dog = new CountAppearances();
    	 int test = dog.numberTimesAppear(319830130, 3);
    	 System.out.println(test);
     }
}