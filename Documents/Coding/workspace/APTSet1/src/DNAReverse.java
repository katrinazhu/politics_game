
public class DNAReverse {
	String newdna = "";
	public String reverse (String dna){
		for (int x = dna.length()-1; x >= 0; x--){
			newdna = newdna + dna.charAt(x);
		}
		return newdna;
	}
	
	public static void main(String[] args) {
		DNAReverse d = new DNAReverse();
		System.out.println(d.reverse("hello"));
		
	}
}