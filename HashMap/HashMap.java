package col106.assignment4.HashMap;
import java.util.Vector;

public class HashMap<V> implements HashMapInterface<V> {
	int mapsize;
	//node[] map; 
	V[] map ;
	String[] keys ;
	/*class node{
		V value ; 
		Boolean occupied ;
		node(V val) {
			value = val ; 
			occupied = true ; 
		}
	}
	*/
	int hashval(String s){
		int a = 41 ; 
		int l = s.length();
		int val = 0 ; 
		int mult = 1 ;
		if(l!= 0) {
			for(int i = 0 ; i < l ; i++) {
				val += (((int)s.charAt(i)%mapsize)*mult%mapsize)%mapsize ; 
				//System.out.println(val);
				mult *= a ; 
				mult = mult % mapsize;
			}
			return val % mapsize; 
			
		}
		else {
			System.out.println("empty string ");
			return 0;
		}
	}
	

	public HashMap(int size) {
		// write your code here
		mapsize = size ;
		//System.out.println("mapsize is " + mapsize);
		map = (V[])new Object[mapsize];
		//map =  (node[])new Object[mapsize];
		keys = new String[mapsize];
		
		
	}

	public V put(String key, V value){
		// write your code here
		int hash = hashval(key);
		int first = hash; 
		//System.out.println("first hash is :" + first);
		
		while(true) {
			if(keys[hash]== null || keys[hash].equals("") ) { 
				keys[hash] = key;
				map[hash] = value;
				return null;
				
			}
			else if(keys[hash].equals(key)) {
				V old = map[hash];
				map[hash] = value ; 
				return old ;
			}
			else {
				hash+=1;
				hash %= mapsize;
			}
		}
	}

	public V get(String key){
		// write your code here
		int hash = hashval(key);
		
        while (keys[hash]!=null) {
            if (keys[hash].equals(key))
                return map[hash];
            else {
            	hash = (hash + 1)%mapsize;
            }
           
        }            
        return null;
	}

	public boolean remove(String key){
		// write your code here
		if(!contains(key)) return false;
		int oldhash = hashval(key);
		int hash = hashval(key);
		while(!key.equals(keys[hash])){
			hash = (hash+1)%mapsize;
		}
		map[hash] = null ;
		keys[hash] = null ;
		/*int gap = hash ;
		for(int i = hash +1  ; i <mapsize ;i++){
			if((hashval(keys[i])) == oldhash) {
				keys[gap] = keys[i];
				map[gap].occupied = true;
				map[gap].value = map[i].value ;
				
				keys[i] = null;
				map[i].occupied = false;
				gap = i ;
			}
		}
		*/
		for(int i = 0 ; i < mapsize ; i++) {
			if(keys[i]!= null) {
				int first = hashval(keys[i]);
				int j = first;
				int k = (j+1)%mapsize;
				// j will point to gaps , k will point to ahead values
				while(k!=first) {
					// k is initial ahead value
					if(keys[j] == null) {
						//gap found
						if(keys[k] == null) {
							j = k ; 
							k = (j+1)%mapsize;
						}
						else if(hashval(keys[k]) != first) {
							k = (k+1)%mapsize;
						}
						else {
							// hashval is same now 
							keys[j] = keys[k];
							map[j] = map[k];
							map[k] = null ;
							keys[k] = null ;
							j = k ;
							k = (j+1)%mapsize;
						}
					}
					else {
						j = (j+1)%mapsize;
						k = (j+1)%mapsize;
					}
				}	
			}
		}
		return true;
	}

	public boolean contains(String key){
		// write your code here
		if(get(key) != null) return true;
		return false;
	}

	public Vector<String> getKeysInOrder(){
		// write your code here
		Vector<String> v = new Vector<String>();
		for(int i = 0 ; i < mapsize ; i++) {
			
			if(keys[i] != null) {
				v.add(keys[i]);
				//System.out.println(i);
			}
		}
		return v;
	}
    /*
	public HashMap(int size) {
		// write your code here
	}

	public V put(String key, V value){
		// write your code here
		return null;
	}

	public V get(String key){
		// write your code here
		return null;
	}

	public boolean remove(String key){
		// write your code here
		return false;
	}

	public boolean contains(String key){
		// write your code here
		return false;
	}

	public Vector<String> getKeysInOrder(){
		// write your code here
		return null;
	}
	*/
}
