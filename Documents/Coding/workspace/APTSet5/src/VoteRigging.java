
public class VoteRigging {
	int count = 0;

      public int minimumVotes(int[] votes) {
    	int max = 0;
  		int maxIndex = -1;
  		for(int x = votes.length-1; x>=0; x--){
  			if(votes[x]>max){
  				max=votes[x];
  				maxIndex = x;
  			}
  		}
  		if(maxIndex == 0)
  			return count;
  		votes[maxIndex] = max-1;
  		int temp = votes[0];
  		votes[0] = temp+1;
  		count++;
  		return minimumVotes(votes);
      }
   }
