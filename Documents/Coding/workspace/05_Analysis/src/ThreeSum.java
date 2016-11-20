/*********************************************
 *  Sedgewick & Wayne: 4.1
 *  A program that reads in a list of integers
 *  and counts the number of triples that sum
 *  to exactly 0.
 *
 *  Limitations
 *  -----------
 *     - we ignore integer overflow
 *
 **********************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class ThreeSum {

    // print distinct triples (i, j, k) such that a[i] + a[j] + a[k] = 0
    public static int printAll(long[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        System.out.println(a[i] + " " + a[j] + " " + a[k]);
                    }
                }
            }
        }
        return cnt;
    } 

    // return number of distinct triples (i, j, k) such that a[i] + a[j] + a[k] = 0
    public static int count(long[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    } 

    // dialog for choosing file with values
    private static JFileChooser ourChooser = new JFileChooser(System
            .getProperties().getProperty("user.dir"));
    
    /**
     * Displays file chooser for browsing in the project directory. and opens
     * the selected file
     * 
     * @return a new Scanner that produces values scanned from the selected
     *         file. null if file could not be opened or was not selected
     */
    public static Scanner openFileFromDialog()
    {

        int retval = ourChooser.showOpenDialog(null);

        if (retval == JFileChooser.APPROVE_OPTION)
        {
            File f = ourChooser.getSelectedFile();
            Scanner s;
            try
            {
                s = new Scanner(f);
            } catch (FileNotFoundException e)
            {
                return null;
            }
            return s;
        }
        return null;
    }

    public static void main(String[] args)  { 
        Scanner in = openFileFromDialog();
        ArrayList<Long> list = new ArrayList<Long>();
        while (in.hasNext())
            list.add(in.nextLong());
        // TODO: copy list into array a
        long[] a = new long[list.size()];


        int cnt = count(a);
        System.out.println(cnt);
        if (cnt < 10) {
            printAll(a);
        }
    } 
} 
