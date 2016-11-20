import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class ArrayVsArrayList {
    /**
     * Returns an array of numElements length where each element is a random value in [0,1)
     */
    public double[] makeArray(int numElements){
        double[] a = new double[numElements];
	
        for(int i = 0; i < a.length; i++){
            a[i] = Math.random();
        }
	
        return a;
    }
    
    /**
     * Returns an ArrayList of numElements size where each element is a random value in [0,1)
     */
    public ArrayList<Double> makeArrayList(int numElements){
        ArrayList<Double> a = new ArrayList<Double>();
	
        for(int i = 0; i < numElements; i++){
            a.add(i, Math.random());
        }
	
        return a;	
    }
    
    public static void main(String[] args){
        ArrayVsArrayList array = new ArrayVsArrayList();
        int size = 49000000;
        // Take the size as an argument
        if (args.length >= 1)
            size = Integer.parseInt(args[0]);
        
        System.out.println("Compare Array vs. ArrayList with " + size + " elements.");

        double start = System.currentTimeMillis();
        array.makeArray(size);
        double end = System.currentTimeMillis();
        System.out.printf("Array:\t\t total time = %f\n", (end - start) / 1000);
	
        start = System.currentTimeMillis();
        array.makeArrayList(size);
        end = System.currentTimeMillis();
        System.out.printf("ArrayList:\t total time = %f\n", (end - start) / 1000);
    }
}
