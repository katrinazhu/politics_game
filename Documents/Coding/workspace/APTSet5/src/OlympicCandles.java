import java.util.ArrayList;
import java.util.Collections;

public class OlympicCandles{
	private int recursiveNights (ArrayList<Integer>candles, int night){
		if(candles.size()<night)
			return night;
		Collections.sort(candles);
		int[] maxFirstCandles = new int[candles.size()];
		int index = 0;
		for(int x = candles.size()-1; x>=0; x--){
			maxFirstCandles[index] = candles.get(x);
			index++;
		}
		for(int n = 0; n<night; n++){
			int height = maxFirstCandles[n];
			maxFirstCandles[n]=height-1;
		}
		ArrayList<Integer>newCandles = new ArrayList<Integer>();
		for(int i = 0; i<maxFirstCandles.length; i++){
			if(maxFirstCandles[i]!=0)
				newCandles.add(maxFirstCandles[i]);
		}
		return recursiveNights(newCandles, night+1);
	}
   public int numberOfNights(int[] candles){
      ArrayList<Integer> c = new ArrayList<Integer>();
      for(int x = 0; x<candles.length; x++)
    	  c.add(candles[x]);
      return recursiveNights(c, 1)-1;
   }
 }
