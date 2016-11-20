import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;


public class TakeOutCommas {
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
	public void removeCommas(Scanner sc){
		while(sc.hasNext()){
			String s = sc.next();
			s= s.replaceAll(",", "");
			System.out.println(s);
		}
	}
	public static void main(String[]args){
		TakeOutCommas c = new TakeOutCommas();
		Scanner in = c.getScanner();
		c.removeCommas(in);
	}
}
