import java.util.ArrayList;

public class RatRoute {
		int rx = 0;
		int ry = 0;
		int cx = 0;
		int cy = 0;
		int numRoutes = 0;
		ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
		private int recursive(String[]enc, int x, int y, String direction, String otherDir){
			
			if(x<0||y<0||x>=enc[0].length()||y>=enc.length)
				return 0;
			if(direction.equals("right")){
				if(otherDir.equals("down")){
					if((y+1>=enc.length||y+1>cy||enc[y+1].charAt(x)=='X')&&(x+1>=enc[y].length()||x+1>cx||enc[y].charAt(x+1)=='X'))
						return 0;
				}
				if(otherDir.equals("up")){
					if((y-1<0||y-1<cy||enc[y-1].charAt(x)=='X')&&(x+1>=enc[y].length()||x+1>cx||enc[y].charAt(x+1)=='X'))
						return 0;
				}
				
				 if(x+1>=enc[y].length()||x+1>cx||enc[y].charAt(x+1)=='X'){
					return 0;
				}
				 else if(enc[y].charAt(x+1)=='C'){
						 numRoutes++;										
				}
				else{
					recursive(enc, x+1, y, "right", otherDir);
					recursive(enc, x+1, y, otherDir, "right");
				}
					
			}
			if(direction.equals("left")){
				if(otherDir.equals("down")){
					if((y+1>=enc.length||y+1>cy||enc[y+1].charAt(x)=='X')&&(x-1<0||x-1<cx||enc[y].charAt(x-1)=='X'))
						return 0;
				}
				if(otherDir.equals("up")){
					if((y-1<0||y-1<cy||enc[y-1].charAt(x)=='X')&&(x-1<0||x-1<cx||enc[y].charAt(x-1)=='X'))
						return 0;
				}
				if(x-1<0||x-1<cx||enc[y].charAt(x-1)=='X'){
					return 0;
				}
				else if(enc[y].charAt(x-1)=='C'){
					numRoutes++;
				}
				
				else{
					recursive(enc, x-1, y, "left", otherDir);
					recursive(enc, x-1, y, otherDir, "left");
				}
			}
			if(direction.equals("up")){
				if(otherDir.equals("right")){
					if((x+1>=enc[y].length()||x+1>cx||enc[y].charAt(x+1)=='X')&&(y-1<0||y-1<cy||enc[y-1].charAt(x)=='X'))
						return 0;
				}
				if(otherDir.equals("left")){
					if((x-1<0||x-1<cx||enc[y].charAt(x-1)=='X')&&(y-1<0||y-1<cy||enc[y-1].charAt(x)=='X')){						
						
						return 0;
					}
				}
				
				if(y-1<0||y-1<cy||enc[y-1].charAt(x)=='X'){
					return 0;
				}
				else if(enc[y-1].charAt(x)=='C'){
					numRoutes++;
				}
				else{
					recursive(enc, x, y-1, "up", otherDir);
					recursive(enc, x, y-1, otherDir, "up");
				}
			}
			if(direction.equals("down")){
				if(otherDir.equals("right")){
					if((x+1>=enc[y].length()||x+1>cx||enc[y].charAt(x+1)=='X')&&(y+1>=enc.length||y+1>cy||enc[y+1].charAt(x)=='X'))
						return 0;
				}
				if(otherDir.equals("left")){
					if((x-1<0||x-1<cx||enc[y].charAt(x-1)=='X')&&(y+1>=enc.length||y+1>cy||enc[y+1].charAt(x)=='X'))
						return 0;
				}
				
				 if(y+1>=enc.length||y+1>cy||enc[y+1].charAt(x)=='X'){
					return 0;
				}
				else if(enc[y+1].charAt(x)=='C'){
					numRoutes++;
				}
				else{
					recursive(enc, x, y+1, "down", otherDir);
					recursive(enc, x, y+1, otherDir, "down");
				}
			}
			if(direction.equals("none")){
				return 0;
			}
			return numRoutes;
		}
       public int numRoutes(String[] enc) {
    	   String direction = "none";
    	   String otherDir = "none";
           for(int x = 0; x<enc.length; x++){
        	   if(enc[x].contains("R")){
        		   ry = x;
        		   rx = enc[x].indexOf("R");
        	   }
        	   if(enc[x].contains("C")){
        		   cy = x;
        		   cx = enc[x].indexOf("C");
        	   }
           }
           if(rx>cx)
        	   direction = "left";          
           if(rx<cx)
        	   direction = "right";
           if(ry<cy)
        	   otherDir = "down";
           if(ry>cy)
        	   otherDir = "up";
           int routes1 = recursive(enc, rx, ry, direction, otherDir);
           numRoutes = 0;
           int routes2 = recursive(enc, rx, ry, otherDir, direction);
           return routes1 + routes2;
       }
       public static void main(String[]args){
    	   RatRoute r = new RatRoute();
    	   int x = r.numRoutes(new String[]{".CX....", "..R.X.."});
    	   System.out.println(x);
       }
    }