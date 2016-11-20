import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.JFileChooser;
public class PQDemo {
    private static JFileChooser ourChooser = new JFileChooser(System
            .getProperties().getProperty("user.dir"));
 

    public static void print(PriorityQueue<String> pq){
        while (pq.size() > 0){
            System.out.println(pq.remove());
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException{
        
        int retval = ourChooser.showOpenDialog(null);

        if (retval == JFileChooser.APPROVE_OPTION){
            File f = ourChooser.getSelectedFile();  
            Scanner s = new Scanner(f);
            ArrayList<String> list = new ArrayList<String>();
            
            while (s.hasNext()){
                list.add(s.next());
            }
            System.out.println("read # words = "+list.size());
            PriorityQueue<String> pq = new PriorityQueue<String>();
            pq.addAll(list);
            print(pq);
            
            System.out.println("\n reversed \n");
            PriorityQueue<String> rpq = new PriorityQueue<String>(10, Collections.reverseOrder());
            rpq.addAll(list);
            print(rpq);
            
        }
        else {
            System.out.println("operation cancelled");   
        }
    }
}
