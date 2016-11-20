
public class Testing {
	public int x;
	public Testing(int x){
		this.x=x;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Testing t = new Testing(5);
		Integer y = (Integer)t.x;
		System.out.println(y.toString());
	}

}
