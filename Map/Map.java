package col106.assignment4.Map;
import col106.assignment4.WeakAVLMap.WeakAVLMap;
import col106.assignment4.HashMap.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.IOException; 


public class Map<V> {
	/*
	public Map() {
		// write your code here	
	}

	public void eval(String inputFileName, String outputFileName) {
		// write your code here	
	}
	*/
    
	public Map() {
		// write your code here	
	}

	public void eval(String inputFileName, String outputFileName){
		// write your code here	
		try {
			File file = new File(inputFileName);
			file = new File("map_input");
			BufferedReader br = null;
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine().trim();
			int ops  = Integer.parseInt(line);
			HashMap<String> hmap = new HashMap<String>(ops);
			WeakAVLMap<String,String> avlmap = new WeakAVLMap<String,String>()  ;
			long tavlinsert= 0;
			long thminsert = 0 ;
			long tavldelete = 0;
			long thmdelete = 0;
			for(int t = 0 ; t< ops ; t++) {
				String st = br.readLine();
				String[] arr = st.split(" ");
				if(arr.length == 2) {
					String command = arr[0];
					String key = arr[1];
					//System.out.println(key);
					
					long start = System.currentTimeMillis();
					//long start = System.nanoTime();
					//System.out.println("start:" + start);
					hmap.remove(key);
					long end = System.currentTimeMillis(); 
					//long end = System.nanoTime();
					//System.out.println(hmap.getKeysInOrder());
					//System.out.println("end:" + end);
					thmdelete += end -start ;
					
					start = System.currentTimeMillis();
					 //start = System.nanoTime();
					//System.out.println("start:" + start);
					avlmap.remove(key);
					end = System.currentTimeMillis(); 
					//end = System.nanoTime();
					//System.out.println("end:" + end);
					tavldelete += end -start ;	
					//System.out.println(avlmap.BFS());
				}
				else if(arr.length == 3) {
					String command = arr[0];
					String key = arr[1];
					String val = arr[2];
					//System.out.println(key);
					//System.out.println(val);
					long start = System.currentTimeMillis();
					//long start = System.nanoTime();
					//System.out.println("start:" + start);
					hmap.put(key,val);
					//long end = System.nanoTime();
					//System.out.println(hmap.getKeysInOrder());
					long end = System.currentTimeMillis(); 
					//System.out.println("end:" + end);
					thminsert += end -start ;
					
					start = System.currentTimeMillis();
					 //start = System.nanoTime();
					//System.out.println("start:" + start);
					avlmap.put(key,val);
					//end = System.nanoTime();
					//System.out.println(avlmap.BFS());
					end = System.currentTimeMillis(); 
					//System.out.println("end:" +end);
					tavlinsert += end -start ;	
				}
			}
			PrintStream out = new PrintStream(new FileOutputStream(outputFileName, false), true);
	        System.setOut(out);
	        
	        
	        System.out.println("Operations WAVL HashMap");
	        System.out.println("Insertions " + tavlinsert + " " + thminsert );
	        System.out.println("Deletions " + tavldelete + " " + thmdelete );
	        
	        
	        
		}catch (FileNotFoundException e) {
            System.err.println("Input file Not found. " );
		}catch (NullPointerException ne) {
            ne.printStackTrace();
		}catch(IOException e) {
			System.err.println("IO exception");
			
		}
		
    }
	/*
	public static void main(String[] args) throws IOException {
		Map map = new Map();
		map.eval("smap_input", "map_output");
		
	
	}
	*/
}
