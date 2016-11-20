// TODO Add your names and NetIDs below (comma-separated)
/*
 * Linked list practice code
 * 
 * SUBMIT-NAMES: 
 * SUBMIT-NetIDs: 
 */
public class ListNode {
	String info;
	ListNode prev;
	ListNode next;

	public ListNode(String s, ListNode before, ListNode after) {
		info = s;
		prev = before;
		next = after;
	}

	/**
	 * @returns the node whose info field is largest (alphabetically), null if
	 *          list is null
	 */
	public static ListNode maxNodeIter(ListNode list) {
		// TODO Complete iterative version of maxNode
		if (list == null)
			return null;
		ListNode max = null;
		for (ListNode current = list; current != null; current = current.next) {
			if (max == null || max.info.compareTo(current.info) < 0)
				max = current;
		}
		return max;
	}

	/**
	 * @returns the node whose info field is largest (alphabetically), null if
	 *          list is null. Should be recursive
	 */
	public static ListNode maxNode(ListNode list) {
		// TODO Complete recursive version of maxNode
		if(Head.value.compareTo.maxNode(Head.next))
		return null;
	}

	/**
	 * Move node containing word to front of list and return modified list. If
	 * word not found, no changes made to list, original list returned
	 * 
	 * @param list
	 *            is list being modified
	 * @param word
	 *            is targeted node for move-to-font
	 * @return first node in possibly modified list.
	 */
	public static ListNode moveToFront(ListNode list, String word) {
		ListNode first = list;
		// TODO Complete moveToFront

		return null;
	}

	// add to front
	public static ListNode add(ListNode list, String s) {
		ListNode head = new ListNode(s, null, list);
		list.prev = head;
		return head;
	}
    
    public static void main(String[] args) {
        // TODO make a list ("A", "B", "C")
        ListNode list = add(add(add(null, "C")
        						    , "B")
        					    , "A");
        /* ListNode list = new ListNode("A", null, null);
        ListNode b = new ListNode("B", list, null);
        ListNode c = new ListNode("C", b, null);
        list.next = b;
        b.next = c; */
    }
}
