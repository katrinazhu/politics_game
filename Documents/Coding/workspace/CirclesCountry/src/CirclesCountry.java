
public class CirclesCountry {
	public boolean inside (int a, int b, int c, int d, int e){
		if (Math.sqrt((double)((a-c)^2 + (b-d)^2))<e)
			return true;
		else
			return false;
	}
	public int leastBorders(int[] x, int[] y, int[] r, int x1, int y1, int x2, int y2) {
		int count = 0;
		for (int k = 0; k<x.length; k++){
			if (inside(x1, y1, x[k], y[k], r[k]) && inside(x2, y2, x[k], y[k], r[k]))
				;
			else if (!inside(x1, y1, x[k], y[k], r[k]) && !inside(x2, y2, x[k], y[k], r[k]))
				;
			else
				count++;
		}
		return count;
		}
	}



