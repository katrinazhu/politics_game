
public class Loop {

	
	public static void main(String[]args){
		double result=0;
		for(int x=0; x<=5; x++){
			for (int y=x; y<=x+5; y++){
				result+=x*(2/3)^x*(1/3)^(5-x)*y*(2/3)^(y-x)*(1/3)^(10-y-x);
			}
		}
		System.out.println(result);
	}
}
