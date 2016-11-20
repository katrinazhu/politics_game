
public class AccessLevel {
     public String canAccess(int[] rights, int minPermission) {
    	 String access = "";    	 
         for (int x = 0; x<rights.length; x++){
            	if (rights[x]>=minPermission)
            		access=access+'A';
            	else
            		access=access+'D';
         }
         return access;
     }
     public static void main (String [] args){
    	 AccessLevel cat = new AccessLevel();
    	 String dog = cat.canAccess(new int[] {1, 2, 3, 4}, 2);
    	 System.out.println(dog);
     }
  }
