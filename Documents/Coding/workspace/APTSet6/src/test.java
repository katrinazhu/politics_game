import java.util.ArrayList;


public class test {
	boolean isTrue = false;
	public void makeTrue(){
		isTrue = true;
	}
	public void isittrue(){
		if(isTrue==true){
			System.out.println("yes!");
		}
	}
	public static void main(String[]args){
		test meep = new test();
		meep.makeTrue();
		meep.isittrue();
	}
}
