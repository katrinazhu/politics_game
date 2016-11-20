import java.util.*;
import java.io.*;
import javax.swing.JFileChooser;
 
/***************************************************************
 * Name(s):          Katrina Zhu
 * Course:        CompSci 201, Fall 2014
 * Collaborators:
 * Resources Used
 * Date Started:  
 * Purpose: Read a file, print each word and line numbers
 * on which word occurs, words printed in alphabetical order
 *
 */ 


public class WordLines
{
        // dialog for choosing file 
    private static JFileChooser ourChooser = new JFileChooser(System
            .getProperties().getProperty("user.dir"));
    /**
     * Displays file chooser for browsing in the project directory. and opens
     * the selected file
     * 
     * @return reference to the selected file.  null if file was not
     * selected
     */
    public File selectFileFromDialog()
    {
        int retval = ourChooser.showOpenDialog(null);

        if (retval == JFileChooser.APPROVE_OPTION)
        {
            File f = ourChooser.getSelectedFile();
            return f;
        }
        return null;
    }
    
    // EXAMPLE
    public Map<String, Integer> getWordCounts (Scanner input)
    {
        Map<String, Integer> results = new TreeMap<String, Integer>();

        while (input.hasNext())
        {
            String word = input.next();

            Integer count = results.get(word);
            if (count == null)
            {
                results.put(word, 1);
            }
            else
            {
                results.put(word, count + 1);
            }
        }
        return results;
    }


    public Map<String, Set<Integer>> getLineNumbers (Scanner input)
    {
        Map<String, Set<Integer>> results = new TreeMap<String, Set<Integer>>();
        int lineCount = 0;

        while (input.hasNext())
        {
            lineCount++;
            Scanner line = new Scanner(input.nextLine());
            // TODO: correctly adds words on this line to map
            while(line.hasNext()){
            	String word = line.next();
            	Set<Integer> lineOccur = results.get(word);
            	if(lineOccur == null){
            		lineOccur = new TreeSet<Integer>();
            		;
            	}
            	lineOccur.add(lineCount);
            	results.put(word, lineOccur);
            	//put replaces what's there
            }
        }

        return results;
    }

    public Map<String, Set<String>> getFrequencies (Scanner input)
    {
        Map<String, Set<String>> results = new TreeMap<String, Set<String>>();

        while (input.hasNext())
        {
            Scanner line = new Scanner(input.nextLine());
            // TODO: correctly adds words on this line to map
        }

        return results;
    }

    public Map<String, ?> getLocations(Scanner input)
    {
        // TODO: complete getLocations
        return null;
    }

    // TO EXPLAIN
    public void printResults (Map<String, ?> results)
    {
        for (String key : results.keySet())
        {
            System.out.println(key + "\t" + 
            		results.get(key).toString());
        }
    }
        // OR:
        // for (Map.Entry<String, ?> current : results.entrySet())
        // {
        //     System.out.println(current.getKey() + "\t" + current.getValue());
        // }



    public static void main (String args[]) throws FileNotFoundException
    {
        WordLines stats = new WordLines();
        File f = stats.selectFileFromDialog();
        
        stats.printResults(stats.getWordCounts(new Scanner(f)));
        stats.printResults(stats.getLineNumbers(new Scanner(f)));
        stats.printResults(stats.getFrequencies(new Scanner(f)));
    }
}
