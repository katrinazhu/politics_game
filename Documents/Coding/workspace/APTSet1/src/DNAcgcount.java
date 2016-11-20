
public class DNAcgcount
{
    public double ratio(String dna)
    {
    	double count = 0.0;
    	for(int x = 0; x<dna.length(); x++){
    		if (dna.charAt(x)=='c' || dna.charAt(x)=='g')
    			count++;
    	}
    	double l = (double)dna.length();
    	if (dna.length()==0)
    		return 0.0;
    	else
    		return count/l;
    }
    
    public static void main(String[] args){
    	DNAcgcount cat = new DNAcgcount();
    	double k = cat.ratio("cgattacgatccaa");
    	System.out.println(k);
    }
}
