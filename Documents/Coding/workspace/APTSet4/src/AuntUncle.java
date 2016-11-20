import java.util.Arrays;


public class AuntUncle {
       public String[] list(String[] parents, String target) {
           String parent1 = "";
           String parent2 = "";
           String grandparent1 = "";
           String grandparent2 = "";
           String grandparent1a = "";
           String grandparent2a = "";
           String names = "";
           String [] emptyArray = new String[0];
    	   for(int x = 0; x<parents.length; x++){
        	   String[]temp = new String[3];
        	   temp = parents[x].split(" ");
        	   if(temp[2].equals(target)){
        		   parent1 = temp[0];
        		   parent2 = temp[1];
        		   break;
        	   }        	   
           }
    	   for(int x = 0; x<parents.length; x++){
        	   String[]temp = new String[3];
        	   temp = parents[x].split(" ");
        	   if(temp[2].equals(parent1)){
        		   grandparent1 = temp[0];
        		   grandparent2 = temp[1];
        		   break;
        	   }        	   
           }
    	   for(int x = 0; x<parents.length; x++){
        	   String[]temp = new String[3];
        	   temp = parents[x].split(" ");
        	   if(temp[2].equals(parent2)){
        		   grandparent1a = temp[0];
        		   grandparent2a = temp[1];
        		   break;
        	   }        	   
           }
    	   for(int x = 0; x<parents.length; x++){
        	   String[]temp = new String[3];
        	   temp = parents[x].split(" ");
        	   if((temp[0].equals(grandparent1)||temp[1].equals(grandparent1))&&!temp[2].equals(parent1)){
        		   if(!temp[2].equals(target)&&!temp[2].equals(parent2))
        			   names += temp[2] + " ";
        	   }        	   
           }
    	   for(int x = 0; x<parents.length; x++){
        	   String[]temp = new String[3];
        	   temp = parents[x].split(" ");
        	   if((temp[0].equals(grandparent2)||temp[1].equals(grandparent2))&&!temp[2].equals(parent1)){
        		   if(!temp[2].equals(target)&&!names.contains(temp[2])&&!temp[2].equals(parent2))
        			   names += temp[2] + " ";
        	   }        	   
           }
    	   for(int x = 0; x<parents.length; x++){
        	   String[]temp = new String[3];
        	   temp = parents[x].split(" ");
        	   if((temp[0].equals(grandparent1a)||temp[1].equals(grandparent1a))&&!temp[2].equals(parent2)){
        		   if(!temp[2].equals(target)&&!names.contains(temp[2])&&!temp[2].equals(parent1))
        			   names += temp[2] + " ";
        	   }        	   
           }
    	   for(int x = 0; x<parents.length; x++){
        	   String[]temp = new String[3];
        	   temp = parents[x].split(" ");
        	   if((temp[0].equals(grandparent2a)||temp[1].equals(grandparent2a))&&!temp[2].equals(parent2)){
        		   if(!temp[2].equals(target)&&!names.contains(temp[2])&&!temp[2].equals(parent1))
        			   names += temp[2] + " ";
        	   }        	   
           }
    	   if(names=="")
    		   return emptyArray;
    	   String[]list = names.split(" ");
    	   Arrays.sort(list);
    	   return list;
       }
   }
