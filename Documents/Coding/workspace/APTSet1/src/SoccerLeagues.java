
public class SoccerLeagues {
    public int checkAwayWin (String awayTeam, int homeTeam){
    	if (awayTeam.charAt(homeTeam)=='W'||awayTeam.charAt(homeTeam)=='-')
    		return 0;
    	else if (awayTeam.charAt(homeTeam)=='L')
    		return 3;
    	else
    		return 1;
    }
    public int calculatePoints (String[] games, int team){
    	String homeGames = games[team];
    	int count = 0;
    	for (int x = 0; x<homeGames.length(); x++){
    		if (homeGames.charAt(x)=='W')
    			count+=3;
    		else if (homeGames.charAt(x)=='D')
    			count++;
    		else
    			count+=0;
    	}
    	for (int y = 0; y<games.length; y++){
    		count+=checkAwayWin(games[y], team);
    	}
    	return count;
    	    	
    }
	public int[] points(String[] matches) {
		int[] point = new int[matches.length];
		for (int k = 0; k<matches.length; k++)
			point[k] = calculatePoints(matches, k);
		return point;
     }
	public static void main(String[] args){
		SoccerLeagues cat = new SoccerLeagues();
		String [] a = new String[]{"-WW", "W-W", "WW-"};
		System.out.print(cat.points(a));
	}
 }
