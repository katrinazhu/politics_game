/**
 * Name: Katrina Zhu and Will Stewart
 * Course: CompSci 201
 * Purpose: Illustrating how we can count the number of unique words in file
 */

import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

public class WordCount {
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
				System.out.println("Opening "+ f.getCanonicalPath());
				s = new Scanner(f);
			} catch (IOException e) {
				return null;
			}
			return s;
		}
		return null;
	}


	/**
	 * Count the number of unique words in a file by adding each word into an
	 * ArrayList
	 * 
	 * @param sc
	 *            in legal (i.e., non-null) text file for reading
	 */
	public void countWordsLoop(Scanner sc) {
		ArrayList<String> list = new ArrayList<String>();
		while (sc.hasNext()) {
			list.add(sc.next());
		}
		Set<String> set = new HashSet<String>(list);
		System.out.printf("total number of words = %d\n", list.size());
		System.out.printf("unique # words = %d\n", set.size());
	}

	/**
	 * Count the number of unique words in a file using the Array split and
	 * asList methods
	 * 
	 * @param sc
	 *            in legal (i.e., non-null) text file for reading
	 */
	public void countWords(Scanner sc) {
		String wholeThing = sc.useDelimiter("\\Z").next();
		String[] words = wholeThing.split("\\s+");
		Set<String> set = new HashSet<String>(Arrays.asList(words));
		System.out.printf("total number of words = %d\n", words.length);
		System.out.printf("unique # words = %d\n", set.size());
	}
	
	/**
	 * Prints the number of Strings in common between two sets. 
	 */
	public void wordsInCommon(Set<String> s1, Set<String> s2) {
		int common = 0;
		for (String s: s1){
			if(s2.contains(s))
				common+=1;
		}
		System.out.printf("%d words in common between files.\n", common);
	}

	public static void main(String[] args) {
		double start, end; // for timing methods
		WordCount wc = new WordCount();

		// TEST #1
		Scanner in = wc.getScanner();
		if (in != null) {
			start = System.currentTimeMillis();
			wc.countWordsLoop(in);
			end = System.currentTimeMillis();
			System.out.printf("countWordsLoop total time = %.3f\n", (end - start) / 1000);
		}

		// TEST #2
		in = wc.getScanner();
		if (in != null) {
			start = System.currentTimeMillis();
			wc.countWords(in);
			end = System.currentTimeMillis();
			System.out.printf("countWords total time = %.3f\n", (end - start) / 1000);
		}
		
		in=wc.getScanner();
		Scanner in2=wc.getScanner();
		
		String wholeThing = in.useDelimiter("\\Z").next();
		String[] words = wholeThing.split("\\s+");
		Set<String> set = new HashSet<String>(Arrays.asList(words));
		
		String wholeThing2 = in2.useDelimiter("\\Z").next();
		String[] words2 = wholeThing2.split("\\s+");
		Set<String> set2 = new HashSet<String>(Arrays.asList(words2));
		
		wc.wordsInCommon(set, set2);
	}

}
