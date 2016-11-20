import java.util.HashMap;
import java.util.Map;

public class AnotherCoinProblem {
	private Map<Long, Integer> myMemo = 
			new HashMap<Long, Integer>();
	public int minimumCoins (long cost){
		if(myMemo.containsKey(cost)){
			return myMemo.get(cost);
		}
		if(cost < 10){
			myMemo.put(cost, (int) cost);
			//System.out.println("less than 10: " + cost);
			return (int) cost;
		}

		//largest dime coin is 10^dimePower
		int dimePower = (int) Math.log10(cost);
		//largest quarter coin is 25*100^quarterPower
		int quarterPower = (int) (Math.log10(cost/25.0)/2.0);
		//how many coins do we need if
		//we use the big dime
		int dimeOption = 1 + minimumCoins(cost-(long)(Math.pow(10, dimePower)));
		if(cost<25){
			myMemo.put(cost, dimeOption);
			return dimeOption;
		}
		int quarterOption = 1 + minimumCoins(cost-(long)(25*Math.pow(100, quarterPower)));		
		int optimal = Math.min(dimeOption, quarterOption);
		myMemo.put(cost, optimal);		
		return optimal;
	}
	public static void main(String[]args){
		AnotherCoinProblem a = new AnotherCoinProblem();
		System.out.println(a.minimumCoins(35227460790L));
	}
}