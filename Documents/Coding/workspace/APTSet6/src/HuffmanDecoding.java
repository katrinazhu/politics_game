
public class HuffmanDecoding {
      public String decode(String archive, String[] dictionary) {
            int index = 0;
            String word = "";
            String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            while(index<archive.length()){
            	for(int i = 0; i<dictionary.length; i++){
            		if(dictionary[i].length()<=archive.length()-index){
            			String s = archive.substring(index, index+dictionary[i].length()-1);
            			s = s+archive.charAt(index+dictionary[i].length()-1);
            			if(s.length()<dictionary[i].length())
            				s = "0" + s;
            			if(dictionary[i].equals(s)){
            				word = word+alph.charAt(i);
            				index+=dictionary[i].length();
            				break;
            		}            			
            	}
            	}
            }
            return word;
      }
      public static void main (String[]args){
    	  HuffmanDecoding cat = new HuffmanDecoding();
    	  System.out.println(cat.decode("101", new String[]{"1", "01"}));
      }
   }
