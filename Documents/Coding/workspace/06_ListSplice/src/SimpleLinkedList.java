import java.util.Iterator;

/**
 * Illustrate a singly-linked list without a header node. The lack of header
 * complicates logic when first node is removed. The single-link characteristic
 * (rather than doubly-linked) makes removal cumbersome to code correctly.
 * <P>
 * Meant for study, not for "real" use.
 * 
 * @author ola
 */
public class SimpleLinkedList<Type> implements ISimpleList<Type> {

    /**
     * The inner class <code>Node</code> represents one node in the
     * singly-linked list that makes up the values of this list.
     * 
     * @author ola
     * 
     */
    private class Node {
        Type value;

        Node next;

        public Node(Type t, Node link) {
            value = t;
            next = link;
        }
    }

    private int mySize; // number of elements in list

    private Node myFront; // first value/node in list

    private Node myBack; // last value/node in list

    public SimpleLinkedList() {
        mySize = 0;
        myFront = null;
        myBack = null;
    }

    public void add(Type t) {
        if (myFront == null) {
            myFront = new Node(t, null);
            myBack = myFront;
        } else {
            myBack.next = new Node(t, null);
            myBack = myBack.next;
        }
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
        if (index == 0) {
            myFront = new Node(t, myFront);
        } else if (index == mySize) {
            myBack.next = new Node(t, myBack);
            myBack = myBack.next;
        } else {
            Node list = myFront;
            for (int k = 0; k < index - 1; k++) {
                list = list.next;
            }
            list.next = new Node(t, list.next);
        }
        mySize++;
    }

    public Iterator<Type> iterator() {
        return new LocalIterator();
    }

    public Type remove(int index) {
        rangeCheck(index);
        if (index == 0) {
            if (myFront == null) {
                System.out.println("size = " + mySize);
            }
            Type dead = myFront.value;
            myFront.value = null;
            myFront = myFront.next;
            mySize--;
            if (myFront == null) {
                System.out.println("all gone and size = " + mySize);
            }
            return dead;
        }
        Node list = myFront;
        for (int k = 0; k < index - 1; k++) {
            list = list.next;
        }
        // list is one before value/node to be removed
        Type dead = list.next.value;
        list.next.value = null;
        list.next = list.next.next;
        mySize--;
        return dead;
    }

    public boolean remove(Type t) {
        Node list = myFront;
        Node previous = null;
        while (list != null) {
            if (list.value.equals(t)) {
                if (previous == null) {
                    myFront = myFront.next;
                    list.value = null;
                } else {
                    previous.next = list.next;
                    list.value = null;
                }
                mySize--;
                return true;
            }
            previous = list;
            list = list.next;
        }
        return false;
    }

    public Type get(int index) {
        rangeCheck(index);
        Node list = myFront;
        for (int k = 0; k < index; k++) {
            list = list.next;
        }
        return list.value;
    }

    public int indexOf(Type t) {
        Node list = myFront;
        int count = 0;
        while (list != null) {
            if (list.value.equals(t)) {
                return count;
            }
            count++;
            list = list.next;
        }
        return -1;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= mySize) {
            throw new IndexOutOfBoundsException("index out of bounds " + index
                    + " of " + mySize);
        }
    }

    private class LocalIterator implements Iterator<Type> {

        private Node myCurrent;

        private Node myTwoBefore;

        private Node myLastReturned;

        public LocalIterator() {
            myCurrent = myFront;
            myTwoBefore = null;
            myLastReturned = null;
        }

        public boolean hasNext() {
            return myCurrent != null;
        }

        public Type next() {
            Type t = myCurrent.value;
            if (myTwoBefore == null || myLastReturned == myFront) {
                myTwoBefore = myFront;
            } else if (myLastReturned != null) {
                myTwoBefore = myTwoBefore.next;
            }
            myLastReturned = myCurrent;
            myCurrent = myCurrent.next;
            return t;
        }

        public void remove() {
            if (myTwoBefore == null || myLastReturned == null) {
                throw new IllegalStateException();
            } else if (myLastReturned == myFront) {
                myFront = myFront.next;
                myLastReturned = null;
            } else {
                myTwoBefore.next = myLastReturned.next;
                myLastReturned.value = null;
                myLastReturned = null;
            }
            mySize--;
        }

    }
}
