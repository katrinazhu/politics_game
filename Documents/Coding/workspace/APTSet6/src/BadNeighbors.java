
public class BadNeighbors {
	/**
	 * returns a new array
	 * a[start, end]

	 */
	  public int [] subArray(int []a, int start, int end){
		  if(start>end)
			  return new int[0];
		  int[]result = new int[end-start+1];
		  System.arraycopy(a, start, result, 0, result.length);
		  return result;
	  }
	  
      public int maxDonations(int[] donations) {
    	  if(donations.length==0)
    		  return 0;
      	  int first = donations[0] + maxHelper(subArray(donations, 2, donations.length-2));
    	  int skip = donations[1] + maxHelper(subArray(donations, 3, donations.length-1));
    	  int last = donations[donations.length-1] + maxHelper(subArray(donations, 1, donations.length-3));

    	  return Math.max(Math.max(first, skip), last);

      }
      
      //returns the maximum sum not including any adjacent elements
      public int maxHelper(int[]d){
    	  if(d.length == 0)
    		  return 0;
    	  //use the first element
    	  int first = d[0]+ maxHelper(subArray(d, 2, d.length-1));
    	  //don't use the first element
    	  int skip = 0;
    	  if(d.length>1)
    		  skip = d[1] + maxHelper(subArray(d, 3, d.length-1));
    	  int last = d[d.length-1] + maxHelper(subArray(d, 0, d.length-3));
    	  return Math.max(Math.max(first, skip), last);
    	  
    	  
      }
      public static void main (String[]args){
    	  BadNeighbors cat = new BadNeighbors();
    	  int x = cat.maxDonations(new int[]{917, 19, 978, 687, 346, 245, 677, 708, 565, 940, 211, 127, 993, 768, 469, 279, 460, 335, 734, 130, 691, 783, 182, 391, 827, 295, 534, 263, 193, 498, 327, 120, 690});
    	  System.out.println("result: " + x);
      }
  }
