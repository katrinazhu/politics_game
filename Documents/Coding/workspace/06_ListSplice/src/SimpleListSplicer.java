import java.util.*;

public class SimpleListSplicer {
    
    private static final String GOODBYE = "goodbye";
    
    public double removeFirst(ISimpleList<String> list) {
        double start = System.currentTimeMillis();
        while (list.size() != 1){
            list.remove(0);
        }
        double end = System.currentTimeMillis();
        return (end-start)/1000.0;
    }
    
    public double removeMiddleIndex(ISimpleList<String> list) {
        double start = System.currentTimeMillis();
        while (list.size() != 1){
            list.remove(list.size()/2);
        }
        double end = System.currentTimeMillis();
        return (end-start)/1000.0;
    }
    
    public double removeMiddleValue(ISimpleList<String> list) {
        double start = System.currentTimeMillis();
        while (list.size() != 1){
            int index = list.indexOf(GOODBYE);
            if (index != 0){
                list.remove(0);
            }
            if (index < list.size()-1){
                list.remove(index+1);
            }
        }
        double end = System.currentTimeMillis();
        return (end-start)/1000.0;
    }

    public double removeMiddleValueIterate(ISimpleList<String> list) {
        double start = System.currentTimeMillis();
        while (list.size() != 1){
            Iterator<String> iter = list.iterator();
            while (iter.hasNext()){
                String s = iter.next();
                if (s.equals(GOODBYE)){
                    if (iter.hasNext()){
                        iter.next();
                        iter.remove();
                    }
                    break;
                }
            }
            if (list.size() > 1){
                list.remove(0);
            }               
        }
        double end = System.currentTimeMillis();
        return (end-start)/1000.0;
    }       
    
    
    public ISimpleList<String> create(ISimpleList<String> list, int size){
        for(int k=0; k < size; k++){
            if (k == size/2){
                list.add(new String(GOODBYE));
            }
            else {
                list.add(new String("hello"));
            }
        }
        return list;
    }
    
    public static void main(String[] args){
        SimpleListSplicer splicer = new SimpleListSplicer();
        for(int k=10000; k <= 80000; k+= 10000){
            ISimpleList<String> linked = new SimpleLinkedList<String>();
            ISimpleList<String> array = new SimpleArrayList<String>();
            
            double ltime = splicer.removeMiddleValueIterate(splicer.create(linked,k));
            double atime = splicer.removeMiddleValueIterate(splicer.create(array,k));

	    // double ltime = splicer.removeMiddleIndex(splicer.create(linked,k));
	    // double atime = splicer.removeMiddleIndex(splicer.create(array,k));

            System.out.println(k + "\t" + ltime + "\t" + atime);
        }
        
    }
}
