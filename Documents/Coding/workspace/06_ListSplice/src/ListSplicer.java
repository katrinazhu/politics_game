import java.util.*;

public class ListSplicer {

    private static final String GOODBYE = "goodbye";

    public double addFront(List<String> list, int n) {
        double start = System.currentTimeMillis();
        for (int k = 0; k < n; k++) {
            list.add(0, GOODBYE);
        }
        double end = System.currentTimeMillis();
        return (end - start) / 1000.0;
    }

    public double removeFirst(List<String> list) {
        double start = System.currentTimeMillis();
        while (list.size() != 1) {
            list.remove(0);
        }
        double end = System.currentTimeMillis();
        return (end - start) / 1000.0;
    }

    public double removeMiddleIndex(List<String> list) {
        double start = System.currentTimeMillis();
        while (list.size() != 1) {
            list.remove(list.size() / 2);
        }
        double end = System.currentTimeMillis();
        return (end - start) / 1000.0;
    }

    public double removeMiddleValue(List<String> list) {
        double start = System.currentTimeMillis();
        while (list.size() != 1) {
            int index = list.indexOf(GOODBYE);
            if (index != 0) {
                list.remove(0);
            }
            if (index < list.size() - 1) {
                list.remove(index + 1);
            }
        }
        double end = System.currentTimeMillis();
        return (end - start) / 1000.0;
    }

    public double removeMiddleValueIterate(List<String> list) {
        double start = System.currentTimeMillis();
        while (list.size() != 1) {
            Iterator<String> iter = list.iterator();
            while (iter.hasNext()) {
                String s = iter.next();
                if (s.equals(GOODBYE)) {
                    if (iter.hasNext()) {
                        iter.next();
                        iter.remove();
                    }
                    break;
                }
            }
            if (list.size() > 1) {
                list.remove(0);
            }
        }
        double end = System.currentTimeMillis();
        return (end - start) / 1000.0;
    }

    public List<String> create(List<String> list, int size) {
        for (int k = 0; k < size; k++) {
            if (k == size / 2) {
                list.add(new String(GOODBYE));
            } else {
                list.add(new String("hello"));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        ListSplicer splicer = new ListSplicer();
        for (int k = 10000; k <= 100000; k += 10000) {
            List<String> linked = new LinkedList<String>();
            List<String> array = new ArrayList<String>();

            double ltime = splicer.removeFirst(splicer.create(linked, k));
            double atime = splicer.removeFirst(splicer.create(array, k));

            // double ltime = splicer.removeMiddleIndex(splicer.create(linked,k));
            // double atime = splicer.removeMiddleIndex(splicer.create(array,k));
            System.out.printf("%d\t%2.4f\t%2.4f\n", k, ltime, atime);
        }

    }
}
