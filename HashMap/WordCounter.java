package col106.assignment4.HashMap;


public class WordCounter {
	int c;

	public WordCounter(){
		// write your code here
		
	}

	public int count(String str, String word){
		// write your code here
		 c = str.length();
		 HashMap map = new HashMap<Integer>(1);
		 map.put(word, 0);
		 int tot = 0 ; 
		 int l = word.length();
		 for(int i = 0 ; i+l <= str.length() ; i++) {
			 String sub = str.substring(i,i+l);
			 if(sub.equals(word)) {
				 tot++;
				 map.put(word, tot);
			 }
			 
		 }
		return (int)map.get(word);
	}

	
}
