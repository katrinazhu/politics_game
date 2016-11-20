import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

import javax.swing.JFileChooser;

/*************************************************************************
 * Author:Katrina Zhu (kz37)
 * Course: CompSci 201, Fall 2014 
 * Comments:
 * This class takes a given file of numbers and a given index.
 * It outputs how many times each digit occurs in the given indices of all the numbers.
 * Known bugs: none
 * 
 *************************************************************************/

public class Prestidigitation {
	public static final int MAX_DIGIT = 10;

	// chooser allows users to select a file by navigating through
	// directories
	private static JFileChooser ourChooser = new JFileChooser(System
			.getProperties().getProperty("user.dir"));

	/**
	 * Brings up chooser for user to select a file
	 * 
	 * @return Scanner for user selected file, null if file not found
	 */
	public Scanner getScanner() {
		int retval = ourChooser.showOpenDialog(null);
		if (retval == JFileChooser.APPROVE_OPTION) {
			File f = ourChooser.getSelectedFile();
			Scanner s;
			try {
				s = new Scanner(f);
			} catch (FileNotFoundException e) {
				return null;
			}
			return s;
		}
		return null;
	}

	/**
	 * Reads all of the integers from the given file
	 * 
	 * @param sc
	 *            in legal (i.e., non-null) text file for reading
	 * @return list of integers from the file
	 */
	public ArrayList<Integer> readNumbers(Scanner sc) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		while(sc.hasNext()){
			if(sc.hasNextInt()){ //if the line only has an int, add the int
				a.add(sc.nextInt());
			}
			else{ //if the line has words...
				String str = sc.nextLine();
				str = str.replaceAll("\\(.*?\\)", ""); //replace everything between parentheses with empty string
				//if numbers remain in the line
				if(str.contains("1")||str.contains("2")||str.contains("3")||str.contains("4")||str.contains("5")||str.contains("6")||str.contains("7")||str.contains("8")||str.contains("9")||str.contains("0")){
				str = str.replaceAll("\\s",""); //get rid of whitespace
				int val;
				val = Integer.parseInt(str);
				a.add(val);
				}
			}
		}
				
		return a;
	}
	
	/**
	 * Returns the nth highest order digit of num, i.e., the nth digit from the
	 * left. We take the leftmost digit to be the 0th. nthDigit should evaluate
	 * to 0 for digits beyond the "end" of the number. 
	 */
	public int nthDigit(int n, int num) {
		ArrayList<Integer> h = new ArrayList<Integer>();
		ArrayList<Integer> h2 = new ArrayList<Integer>();
		while (num > 0) {
		    int digit = num % 10;  // Store digit in a variable
		    num = num/10;
		    // Add digit to the list
		    h.add(digit);
		}
		//h now contains all digits, but in backwards order
		int x = h.size()-1;
		while(x>=0){ //flips the digits of h into ArrayList h2 for the correct order
			h2.add(h.get(x));
			x--;
		}		
		if (n<h2.size())
			return h2.get(n);
		else
			return 0;
	}

	/**
	 * Returns the [0-MAX_DIGIT] digit distribution for nums
	 * nthDigitTally([12176, 5476, 543, 3490, 24892, 28619, 2595, 603, 2527,
	 * 1465, 1858], 0) should return [0, 3, 4, 1, 0, 2, 1, 0, 0, 0] for
	 * MAX_DIGIT == 9
	 * 
	 * @param nums
	 *            non-null list of positive integers
	 * @param n
	 *            zero-indexed digit, so that 0 is the first/leftmost digit, 1
	 *            is second digit, etc.
	 * @return distribution for each digit in range [0,MAX_DIGIT]
	 */
	public int[] nthDigitTally(int n, ArrayList<Integer> nums) {
		 int[] g = new int[10];
		 //for loop goes through ArrayList of ints, finds a digit at an index, and increments that digit value in array g
		 for(int x = 0; x<nums.size(); x++){
			 int a = nthDigit(n, nums.get(x));
			 g[a]+=1;
		 }
		return g;
	}
	
	/**
	 * Main method prompts user for a Scanner file and to enter a given index.
	 * Then executes method nthDigitTally using given file and index, calculates percent occurrence of each digit at given index, and prints results.
	 */
	public static void main(String[] args) {
		Prestidigitation p = new Prestidigitation();
		Scanner in = p.getScanner(); 
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		System.out.print("Please enter the value of n: ");
		int n = scanner.nextInt();
		int[]x = (p.nthDigitTally(n, p.readNumbers(in)));
		double count = 0;
		for(int y = 0; y<x.length; y++){
			count += x[y];
		}
		for (int y = 0; y<x.length; y++){
			int percent = (int) (x[y]/count*100 + 0.5);
			System.out.println(y+"s:  "+x[y]+"  ("+ percent+ "%)");
		}
	}
}
