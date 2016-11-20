import java.util.*;

/**
 * Illustrates how an ArrayList class could be implemented. The underlying
 * storage doubles when new storage is needed. This class is similar to
 * java.util.ArrayList, but meant to be studied rather than used in "real"
 * applications.
 * 
 * @author ola
 */

public class SimpleArrayList<Type> implements ISimpleList<Type> {

    private static final int START_SIZE = 32; // initial storage

    private Type[] myData; // the storage for this list's elements

    private int mySize; // the number of elements in this list

    public SimpleArrayList() {
        myData = (Type[]) new Object[START_SIZE];
        mySize = 0;
    }

    public void add(Type t) {
        checkSize();
        myData[mySize] = t;
        mySize++;
    }

    public int size() {
        return mySize;
    }

    public void add(int index, Type t) {
        if (index < 0 || index > mySize) {
            throw new IndexOutOfBoundsException("index out of bounds " + index
                    + " of " + mySize);
        }
        checkSize();
        System.arraycopy(myData, index, myData, index + 1, mySize - index);
        myData[index] = t;
        mySize++;
    }

    public Type remove(int index) {
        rangeCheck(index);

        Type dead = myData[index];
        System.arraycopy(myData, index + 1, myData, index, mySize - index - 1);
        myData[mySize] = null; // help garbage collection
        mySize--;
        return dead;
    }

    public boolean remove(Type t) {
        int index = indexOf(t);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    public int indexOf(Type t) {
        for (int k = 0; k < mySize; k++) {
            if (myData[k].equals(t)) {
                return k;
            }
        }
        return -1;
    }

    public Type get(int index) {
        rangeCheck(index);
        return myData[index];
    }

    public Iterator<Type> iterator() {
        return new LocalIterator();
    }

    private void checkSize() {
        if (mySize >= myData.length) {
            Type[] newData = (Type[]) new Object[myData.length * 2];
            System.arraycopy(myData, 0, newData, 0, myData.length);
            myData = newData;
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= mySize) {
            throw new IndexOutOfBoundsException("index out of bounds " + index
                    + " of " + mySize);
        }
    }

    /**
     * Inner class that indexes into enclosing array, returning current element
     * or advancing as required.
     * 
     * @author ola
     */
    private class LocalIterator implements Iterator<Type> {

        private int myIndex; // index in enclosing array of current element

        public LocalIterator() {
            myIndex = 0;
        }

        public boolean hasNext() {
            return myIndex < mySize;
        }

        public Type next() {
            Type t = myData[myIndex];
            myIndex++;
            return t;
        }

        public void remove() {
            if (myIndex == 0) {
                throw new IllegalStateException();
            }
            SimpleArrayList.this.remove(myIndex - 1);
        }

    }
}
