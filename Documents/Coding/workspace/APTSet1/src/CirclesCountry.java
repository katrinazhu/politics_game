
public class CirclesCountry {
	public boolean inside (int a, int b, int c, int d, double e){
		if (Math.sqrt((double)(Math.pow(a-c,2) + Math.pow(b-d,2)))<e)
			return true;
		else
			return false;
	}
	public int leastBorders(int[] x, int[] y, int[] r, int x1, int y1, int x2, int y2) {
		int count = 0;
		for (int k = 0; k<x.length; k++){
			if (inside(x1, y1, x[k], y[k], (double)r[k])){
				if(!inside(x2, y2, x[k], y[k], (double)r[k])){
					count++;
				}

			}
			if (!inside(x1, y1, x[k], y[k], (double)r[k])){
				if(inside(x2, y2, x[k], y[k], (double)r[k])){
					count++;
			}

			}
		}
		return count;
		}
	public static void main (String[]args){
		CirclesCountry a = new CirclesCountry();
		int[]x = new int []{1,3,100};
		int[]y = new int []{1,3,100};
		int [] r = new int []{1,1,1};
		int x1 = 1;
		int y1 = 1;
		int x2 = 3;
		int y2 = 3;
		int b = a.leastBorders(x, y, r, x1, y1, x2, y2);
		System.out.print(b);
	}
}

