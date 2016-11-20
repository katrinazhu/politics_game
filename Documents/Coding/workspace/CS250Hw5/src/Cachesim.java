import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.File;
import java.io.FileNotFoundException;
public class Cachesim {
	String zeros = "0000000000000000000000000000000000000000000000000000000000000000000000";
	ArrayList<String>infolist= new ArrayList<String>();
	int linecount=0;
	//ArrayList<String> addresses = new ArrayList<String>();
	TreeMap<String, String> main_mem = new TreeMap<String, String>();
	ArrayList<ArrayList<String>> cache = new ArrayList<ArrayList<String>>();
	TreeMap<String, String> cache_data = new TreeMap<String, String>();
	TreeMap<String, Integer> cache_LRU = new TreeMap<String, Integer>();
	TreeMap<String, Integer> cache_dirty= new TreeMap<String, Integer>();
	int tagnum=0;
	int indexnum=0;
	int offsetnum=0;
	int LRUcount=0;
	int indeces=0;
	
	public void readFile(File f, int cachesize, int ways, int blocksize){
		String info[] = new String[4];
		Scanner s=null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}			
		while(s.hasNextLine()){
			String line = s.nextLine();
			info=line.split("\\s+");
			//addresses.add(info[1].substring(2));
			for (int i=0; i<info.length; i++){
				infolist.add(info[i]);
			}
		}
		offsetnum=offsetbits(blocksize);
		indexnum=indexbits(cachesize, ways, blocksize);
		tagnum=tagbits(cachesize, ways, blocksize);
		for (int i=0; i<indeces; i++){
			cache.add(new ArrayList<String>());
		}
	}
	
	public String hexToBinary(String hex) {
	    int i = Integer.parseInt(hex, 16);
	    String bin = Integer.toBinaryString(i);
	    //System.out.println(bin);
	    return bin;
	}
	public int offsetbits(int blocksize){
		double b= (double) blocksize;
		double a = Math.log(b)/Math.log(2.0);
		return (int) a;
	}
	public int indexbits(int cachesize, int ways, int blocksize){
		int cachebytes = cachesize*1024;
		int bytes_per_index = ways*blocksize;
		indeces = cachebytes/bytes_per_index;
		double i = (double)indeces;
		double bits = Math.log(i)/Math.log(2.0);
		return (int) bits;
	}
	public int tagbits(int cachesize, int ways, int blocksize){
		return 24-indexbits(cachesize, ways, blocksize)-offsetbits(blocksize);		
	}
	public String padWithZeros(String address){
		int length=address.length();
		int num_zeros=24-length;
		String zeros_pad=zeros.substring(0, num_zeros);
		return zeros_pad+address;
	}
	public void iterate(int numways, int blocksize){
		while(linecount<infolist.size()){
		String ls=infolist.get(linecount);
		int skip=3;
		String data="";
		if(ls.equals("store")){
			skip=4;
			data = infolist.get(linecount+3);
		}
		String bytes_s = infolist.get(linecount+2);
		int bytes = Integer.parseInt(bytes_s);
		String hexaddress = infolist.get(linecount+1).substring(2);
		String binaryaddress=hexToBinary(hexaddress);
		binaryaddress=padWithZeros(binaryaddress);
		//System.out.println(binaryaddress);
		String tag=binaryaddress.substring(0, tagnum);
		String index_s=binaryaddress.substring(tagnum, indexnum+tagnum);
		String offset_s=binaryaddress.substring(indexnum+tagnum);
		int index;
		if(index_s.equals("")){
			index = 0;
		}
		else
			index = Integer.parseInt(index_s, 2);
		int offset;
		if(offset_s.equals("")){
			offset=0;
		}
		else
			offset= Integer.parseInt(offset_s, 2);
		//System.out.println(tag);
		//System.out.println(index);
		//System.out.println(offset);
		if(ls.equals("load"))
			load(index, tag, offset, bytes, numways, blocksize);
		else
			store(index, tag, data, numways, offset, blocksize);
		linecount=linecount+skip;
		}
	}
	
	public void load(int index, String tag, int offset, int bytes, int numways, int blocksize){
		String h_m = "";
		String data="";
		boolean hit = false;
		ArrayList<String> ways=new ArrayList<String>();
		ways=cache.get(index);
		for(int j=0; j<ways.size(); j++){
			if(ways.get(j).equals(tag))
				hit=true;
		}
		if(hit){
			h_m="hit";
			String longdata=cache_data.get(tag);
			data=longdata.substring(offset*2, (offset+bytes)*2);
			cache_LRU.put(tag, LRUcount);
		}
		else{
			h_m="miss";
			String poss_data=main_mem.get(tag);
			if (poss_data!=null){
				data=poss_data.substring(offset*2, (offset+bytes)*2);
			}
			else
				data=zeros.substring(0, bytes*2);
			ways=addToCache(ways, numways, tag, data, false, offset, blocksize);
			cache.set(index, ways);
		}
		LRUcount++;
		System.out.print("load ");
		System.out.print(infolist.get(linecount+1)+" ");
		System.out.print(h_m + " ");
		System.out.println(data);
	}
	public void store(int index, String tag, String data, int numways, int offset, int blocksize){
		String h_m="";
		boolean hit = false;
		ArrayList<String> ways=new ArrayList<String>();
		ways=cache.get(index);
		for(int j=0; j<ways.size(); j++){
			if(ways.get(j).equals(tag))
				hit=true;
		}
		if(hit){
			h_m="hit";
			cache_data.put(tag, data);
			cache_dirty.put(tag, 1);
			cache_LRU.put(tag, LRUcount);
		}
		else{
			h_m="miss";
			ways=addToCache(ways, numways, tag, data, true, offset, blocksize);
			cache.set(index, ways);			
		}
		LRUcount++;
		System.out.print("store ");
		System.out.print(infolist.get(linecount+1)+" ");
		System.out.println(h_m);
	}
	
	public ArrayList<String> addToCache(ArrayList<String>wayslist, int numways, String tag, String data, boolean dirty, int offset, int blocksize){
		boolean needReplace=false;
		if(wayslist.size()>=numways)
			needReplace=true;
		if(needReplace){
			wayslist=evict(wayslist);
		}
		wayslist.add(tag);
		data=offset_String(offset, data, blocksize);
		cache_data.put(tag, data);
		cache_LRU.put(tag, LRUcount);
		if(dirty){
			cache_dirty.put(tag, 1);
		}
		return wayslist;
	}
	
	public ArrayList<String> evict(ArrayList<String>wayslist){
		int tag_replace_index = -1;
		String tagreplace="";
		int min_LRU=999999999;
		for(int j=0; j<wayslist.size(); j++){
			String temptag=wayslist.get(j);
			int tempmin=cache_LRU.get(temptag);
			if(tempmin<min_LRU){
				tag_replace_index=j;
				min_LRU=tempmin;
				tagreplace=temptag;
			}
		}
		wayslist.remove(tag_replace_index);
		if (cache_dirty.get(tagreplace)==1){
			String data=cache_data.get(tagreplace);
			main_mem.put(tagreplace, data);
		}
		return wayslist;
	}
	public String offset_String (int offset, String data, int blocksize){
		String newData = "";
		newData=zeros.substring(0, offset*2);
		newData=newData+data;
		int size=newData.length();
		String tempZ=zeros.substring(0, blocksize*2-size);
		newData=newData+tempZ;
		return newData;
	}
	public static void main(String[] args){
		String f = args[0];
		String size = args[1];
		String associative = args[2];
		String block = args[3];
		File file = new File(f);
		int s=Integer.parseInt(size);
		int a= Integer.parseInt(associative);
		int b = Integer.parseInt(block);
		Cachesim cache = new Cachesim();
		cache.readFile(file, s, a, b);
		cache.iterate(a, b);
	}
}
