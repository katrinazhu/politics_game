import java.util.ArrayList;

public class Problem {
	
	static ArrayList<Double> primefactors = new ArrayList<Double>();
	private static void recurse (double d, double j){
		boolean primeI = false;
		boolean primeJ=false;
		long n=2;
		while(n<d){
			if(d%n==0){
				d=d/n;
				j=n;
				break;
			}
			n++;
		}
		if(n>=d){
			primeI=true;
			primefactors.add(d);
		}
		if(primeI==false)
			recurse(d, j);
		n=2;
		while(n<j){
			if(j%n==0){
				j=j/n;
				d=n;
				break;
			}
			n++;
		}
		if(n>=j){
			primeJ=true;
			primefactors.add(j);
		}
		if(primeJ==false)
			recurse(d, j);
	}
	public static void main(String[]args){
		recurse(600851475143.0, 1);
		double max=0;
		for(int i=0; i<primefactors.size(); i++)
			if(primefactors.get(i)>max)
				max=primefactors.get(i);
		System.out.println(max);
	}
}
