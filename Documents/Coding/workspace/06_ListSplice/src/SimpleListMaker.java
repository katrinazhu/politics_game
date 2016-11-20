import java.util.*;

public class SimpleListMaker
{
    public double addBack(ISimpleList<String> list, int n){
        double start = System.currentTimeMillis();
        for(int k=0; k < n; k++){
            list.add(new String("hello"+k));
        }
        double end = System.currentTimeMillis();        
        return (end-start)/1000.0;
    }
    
    public double addFront(ISimpleList<String> list, int n){
        double start = System.currentTimeMillis();
        for(int k=0; k < n; k++){
            list.add(0,new String("hello"+k));
        }
        double end = System.currentTimeMillis();        
        return (end-start)/1000.0;
    }

    public static void main(String[] args){
        SimpleListMaker maker = new SimpleListMaker();
        for(int k=10000; k <= 100000; k+= 10000){
            ISimpleList<String> linked = new SimpleLinkedList<String>();
            ISimpleList<String> array = new SimpleArrayList<String>();
            
            double ltime = maker.addBack(linked,k);
            double atime = maker.addBack(array,k);
            System.out.printf("%d\t%1.3f\t%1.3f\n",k,ltime ,atime);
        }           
    }
}
