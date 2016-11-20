import java.util.ArrayList;


public class Problem4 {

	static int maxPal = 0;
	ArrayList<Integer> palindromes = new ArrayList<Integer>();
	public static int pal(){
		for(int i = 100; i<=999; i++){
			for(int j=100; j<=999; j++){
				boolean isPalindrome=true;
				int pp=i*j;
				int origpal=pp;
				ArrayList<Integer> p = new ArrayList<Integer>();
				while(pp>0){
					p.add(pp%10);
					pp=pp/10;
				}
				ArrayList<Integer>p2=new ArrayList<Integer>();
				for (int x=p.size()-1; x>=0; x--){
					p2.add(p.get(x));
				}
				for (int x=0; x<p.size(); x++){
					if(p.get(x)!=p2.get(x)){
						isPalindrome=false;
						break;
					}				
				}
				if(isPalindrome==true){
					if(maxPal<origpal)
						maxPal=origpal;
				}
	}
	}
		return maxPal;
}
	public static void main(String[]args){
		System.out.println(Problem4.pal());
	}
}
