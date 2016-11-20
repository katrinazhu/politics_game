//Katrina Zhu
//Recitation 6
//10/3/2014
public class StringLinkedList implements Comparable<StringLinkedList> {

	private class Node {
		String myValue;
		Node myNext;

		Node(String value, Node next) {
			myValue = value;
			myNext = next;
		}
	}

	// You can add other instance variables, but you may then
	// need to update other methods
	Node myHead;

	// BE SURE YOU TEST THIS CODE WORKS WITH THE StringLinkedListTester
	// BEFORE YOU SUBMIT!

	/**
	 * Adds a new node with valueToAdd as value to the <em>end</em> of this
	 * linked list
	 */
	public void addAtEnd(String valueToAdd) {
		// TODO 1. Complete addAtEnd
		Node current = myHead;
		if(myHead == null){
			myHead = new Node(valueToAdd, null);
		}
		while (current.myNext!=null){
			current=current.myNext;
		}
		current.myNext = new Node (valueToAdd, null);
	}

	/**
	 * Adds a new node with valueToAdd as value to the <em>beginning</em> of
	 * this linked list
	 */
	public void addAtBeginning(String valueToAdd) {
		myHead = new Node(valueToAdd, myHead);
	}

	/**
	 * Removes the longest string from the list,
	 * 
	 * if you have the list [a,b,longstring,z,q] after this function runs you
	 * end up with [a,b,z,q] if more than one string has the same longest
	 * length, remove the first one if the list is empty, do nothing if the list
	 * has only 1 element, remove it
	 */
	public void removeLongestString() {
		// TODO 2 Complete removeLongestString
		// when you implement this function, be sure to think about
		// a. what if the list is empty
		// b. what if the longest element is the first element
		// c. what if the list has only 1 element
		//iterate through the list two times
		Node current = myHead;
		int max = -1;
		if(current==null);
		else if (current.myNext==null){
			myHead=null;
		}
		else{
		while (current!=null){
			if(current.myValue.length()>max)
				max = current.myValue.length();
			current = current.myNext;
		}
		current = myHead;
		while(current!=null){
			if(myHead.myValue.length() ==max){
				myHead = myHead.myNext;
				break;
			}
			if(current.myNext.myValue.length()==max){
				current.myNext = current.myNext.myNext;
				break;
			}
			current=current.myNext;
		}
		}
	}

	/**
	 * Repeats (doubles) each element [a,b,c] -> [a,a,b,b,c,c]
	 */
	public void doubleList() {
		if(myHead==null);
		else{
			Node current = myHead;
			while(current!=null){
				current.myNext = new Node(current.myValue, current.myNext);
				current = current.myNext.myNext;
			}
		}

	}

	/**
	 * Move k elements from the beginning of the list to the end [a,b,c,d,e] ->
	 * moveToEnd(2) -> [c,d,e,a,b]
	 */
	public void moveToEnd(int k) {
		//move head down k elements
		//make b point to null
		//make end element point to first element
		int a = 0;
		Node current = myHead;
		for(int i=0; i<k; i++){
			if(current==null){
				a = 1;
				break;
			}
		}
		if(a==0&&k>0){
			current = myHead;
			Node oldHead = myHead;
			for(int i=0; i<k; i++){
				current=current.myNext;
			}
			myHead = current;
			while(current.myNext!=null){
				current = current.myNext;
			}
			current.myNext = oldHead;
			while(current.myNext!=myHead){
				current=current.myNext;
			}
			current.myNext = null;
		}
	}

	public String toString() {
		// I've written this one for you!
		StringBuilder b = new StringBuilder();
		Node current = myHead;
		while (current != null) {
			b.append(current.myValue);
			// this is a little crude because it prints out a blank for last
			// element too
			// I opted to keep the code extra simple rather than print right
			b.append(" ");
			current = current.myNext;
		}
		return b.toString();
	}

	/**
	 * Implements a natural ordering on lists To compare string lists, first
	 * compare 1st elements, than second elements, etc. using the standard
	 * lexicographic string comparison. (e.g. [a z c] < [aa b c]) If one list
	 * has the same starting elements as another, but is shorter (e.g. [a] and
	 * [a b]) the shorter one is less if two StringLists have the same elements
	 * and are are the same length, then they are equal
	 */
	public int compareTo(StringLinkedList other) {
		this.compareTo(other);
		Node currentA = this.myHead;
		Node currentB = other.myHead;
		if(currentA==null&&currentB!=null){
			return -1;
		}
		while (currentA!=null){
			if(currentB==null){
				return 1;
			}
			int result = currentA.myValue.compareTo(currentB.myValue);
			if(result!=0)
				return result;
			currentA=currentA.myNext;
			currentB=currentB.myNext;
			if(currentA==null&&currentB!=null){
				return -1;
			}
		}
		
		return 0;
	}

}
