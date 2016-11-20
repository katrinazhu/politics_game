import java.awt.Point;
import java.util.ArrayList;


public class FloodRelief {
	//0 is lowest ground; 25 would be highest
	private int [][] myGrid;
	private int pumps;
	private int cellsToBePumped;
	private static final int DONE=26;
	
	private ArrayList<Point> findSpot(int level){
		ArrayList<Point> points = new ArrayList<Point>();
		//Point result = new Point(-1,-1);
		for(int i = 0; i<myGrid.length; i++){
			for(int j = 0; j<myGrid[0].length; j++){
				if(myGrid[i][j]==level){
					Point temp = new Point(j, i);
					points.add(temp);
				}
			}
		}
		if(points.size()>0)
			return points;
		return null;
	}
	
      public int minimumPumps(String[] heights){
    	  myGrid = new int[heights.length][heights[0].length()];
         for(int k = 0; k<heights.length; k++){
        	 for(int col = 0; col<heights[k].length(); col++){
        		 myGrid[k][col] = heights[k].charAt(col)-'a';
        	 }
         }
         pumps = 0;
         cellsToBePumped = myGrid.length * myGrid[0].length;
         for(int level = 0; level<26; level++){
        	 if(cellsToBePumped==0)
        		 break;
        	 //find a spot at level
        	 if(findSpot(level)!=null){
        		 ArrayList<Point> points = new ArrayList<Point>();
        		 points = findSpot(level);
        		 for(int i = 0; i<points.size(); i++){
        		 Point start = points.get(i);
        			 int x = pump(start, level);
        			 if(x==1){
        				 pumps+=1;
        		 }
        	 }
            	 //pump all the water starting at that spot
        	 }
        	 //mark all the places where we pumped as done (26)
        	 //call pump         	
         }
         return pumps;
      }
      private int pump(Point start, int level){
    	  //base cases
    	  if(start.y<0||start.y>=myGrid.length||start.x<0||start.x>=myGrid[0].length)
    		  return 0;
    	  //already pumped
    	  if(myGrid[start.y][start.x] == DONE){
    		  return 0;
    	  }
    	  //if cell is lower than level
    	  if(myGrid[start.y][start.x]<level)
    		  return 0;
    	  else{
    		  level = myGrid[start.y][start.x];
    		  cellsToBePumped-=1;
    		  myGrid[start.y][start.x]=DONE;
    		  Point right = new Point(start.x+1, start.y);
    		  Point left = new Point(start.x-1, start.y);
    		  Point up = new Point(start.x, start.y+1);
    		  Point down = new Point(start.x, start.y-1);
    		  pump(right, level);
    		  pump(left, level);
    		  pump(up, level);
    		  pump(down, level);   		  
    	  }
 	  return 1;
      }
      public static void main(String[]args){
    	  FloodRelief f = new FloodRelief();
    	  System.out.println(f.minimumPumps(new String[]{"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwx"}));
      }      
  }
