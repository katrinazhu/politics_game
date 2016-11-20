import java.util.*;


public class LinkStrand implements IDnaStrand{
	public class Node { 
		String value;
		Node next;
		public Node(String s, Node link) { 
			value = s;
			next = link; 
		}
	}
	private Node myFront, myBack; 
	private StringBuilder myInfo;
    private int myAppends;
    private int mySize;

    
	public LinkStrand(){
		this("");
	}
	
	public LinkStrand(String s) {
		myInfo = new StringBuilder(s);
		Node myNode = new Node(s, null);
		myFront = myNode;
		myBack = myNode;
		mySize=s.length();
	}

	@Override
	public IDnaStrand cutAndSplice(String enzyme, String splicee) {
		if (myFront.next != null){ 
			throw new RuntimeException("link strand has more than one node"); 
			}
		int pos = 0;
        int start = 0;
        boolean first = true;
        LinkStrand ret = null;
        while ((pos = myBack.value.indexOf(enzyme, pos)) >= 0) {
            if (first){
                ret = new LinkStrand(myBack.value.substring(start, pos));
                first = false;
            }
            else {
                 ret.append(myBack.value.substring(start, pos));
                 
            }
            start = pos + enzyme.length();
            ret.append(splicee);
            pos++;
        }

        if (start < myBack.value.length()) {
        	if (ret == null){
        		ret = new LinkStrand("");
        	}
        	else {
        		ret.append(myBack.value.substring(start));
        	}
        }
        return ret;
	}

	@Override
	public long size() {
		long count = 0;
		Node n = myFront;
		while(n.next!=null){
			count+=n.value.length();
			n=n.next;
		}
		count+=n.value.length();
		return count;
	}

	@Override
	public void initializeFrom(String source) {
		myInfo = new StringBuilder(source);
		Node myNode = new Node(source, null);
		myFront = myNode;
		myBack = myNode;
		mySize=source.length();

	}

	public String strandInfo() {
        return this.getClass().getName();
    }

	@Override
	public IDnaStrand append(IDnaStrand dna) {		
		if (dna instanceof LinkStrand) {
            LinkStrand ss = (LinkStrand) dna;
            myBack.next =ss.myFront;
            myAppends++;
            return this;
        } else {
            return append(dna.toString());
        }
        
	}

	@Override
	public IDnaStrand append(String dna) {
		Node myNode = new Node(dna, null);
		myBack.next=myNode;
		myBack = myNode;
		myAppends++;
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		StringBuilder s = new StringBuilder(myFront.value);
		StringBuilder s2 = s.reverse();
		IDnaStrand backwards = new LinkStrand(s2.toString());
		Node n = myFront.next;
		
		while(n.next!=myBack){
			StringBuilder t = new StringBuilder(n.value);
			StringBuilder t2 = t.reverse();
			backwards.append(t2.toString());
			n=n.next;
		}
		StringBuilder t = new StringBuilder(n.value);
		StringBuilder t2 = t.reverse();
		backwards.append(t2.toString());
		return backwards;
	}

	public String getStats() {
        return String.format("# append calls = %d",myAppends);
    }

}

