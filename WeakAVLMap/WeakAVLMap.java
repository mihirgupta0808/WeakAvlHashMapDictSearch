package col106.assignment4.WeakAVLMap;
import java.util.Vector;
import java.util.Queue; 
import java.util.LinkedList; 

public class WeakAVLMap<K extends Comparable,V> implements WeakAVLMapInterface<K,V>{
    
	
	int rc = 0 ; 
	class node { 
	    K key ; 
	    V value;
	    int height;
	    node left, right,parent; 
	    node(){
	    	height = 0 ; 
	    }
	    // initialising a node is quivalent to setting its height to 0 and making it an external node 
	    // other things are null but .
	} 
	node root = new node() ;  
	public WeakAVLMap(){
		// write your code here
		root = new node() ; 
	}

	int max(int a , int b ) {
		if ( a > b ) return a ; 
		else return b ; 
	}
	int rankdiff(node n) {
		//prints
		//System.out.println("parent height " +  n.parent.height + " height " + n.height);
		return n.parent.height - n.height ; 
	}
	public node rebalop(node q) {
		if(q.parent == null || q.parent.key == null ) return q ; 
		node p = q.parent;
		//re_p = false ;
		//System.out.println(q.height + " " + q.key);
		if( rankdiff(q) == 1 ) return q ;
		if(rankdiff(q) == 0 ) {
			node s = new node() ;
			
			if(q == p.left) {
				//p.right = s ; 
				if(p.right == null) {
					p.right = new node();
				}
				s = p.right ;
			}
			else {
				//p.left = s ; 
				if(p.left == null ) {
					p.left = new node();
				}
				s = p.left ;
			}
			s.parent = p ;
			if(rankdiff(s) == 1) {
				p.height += 1 ;
				//re_p = true ;
				q = p ; 
				//p = rebalop(p);
				q = rebalop(q);
				return q ;
			}
			else if (rankdiff(s) == 2 ) {
				node lq = q.left ; 
				node rq = q.right ;
				node t = new node() ;
				if(lq!=null && lq.key!=null ) {
					if(rankdiff(lq) == 1) {
						t = lq ; 
					}
				}
				if(rq!= null && rq.key!=null) {
					if(rankdiff(rq) == 1) {
						t = rq ;
					}
				}
				
				// trinode restructure
				restructure = true ; 
				q = trinodeRestructure(q);
			}
		}
		else {
			System.out.println("something is wrong");
		}
		return q ;
	}
	node trinodeRestructure(node q) {
		node t = new node() ; 
		node p = q.parent ; 
		Boolean first = true ;
		Boolean second = true ;
		// true means chid is right child
		if(p.left == q) {
			first = false;
		}
		else if(p.right == q) {
			first = true;
		}
		if( q.right != null ) {
			if ( rankdiff(q.right) == 1 ) {
				t = q.right ; 
				second = true;
				
			}
		}
		if( q.left != null ) {
			if ( rankdiff(q.left) == 1 ) {
				t = q.left ; 
				second = false ;
				
			}
		}
		
		// left left
		if( first == false && second == false ) {
			rc++;
			node gp = p.parent ; 
			q.parent = gp ;
			if ( !    (gp == null || gp.key == null ) ) {
				
				if ( gp.right == p ) {
					
					gp.right = q ;
				}
				else {
					gp.left = q ; 
					
				}
			}
			else {
				// p is root 
				
			}
			node t2 ;
			if(q.right == null ) {
				t2 = new node() ;
			}
			else {
				t2 = q.right ;
			}
			q.right = p ;
			p.left = t2 ;
			p.height -= 1 ; 
			
	
			p.parent = q ;
			t2.parent = new node() ;
			t2.parent = p ;
		}
		// right right same as left left
		else if ( first == true && second == true ) {
			rc++;
			node gp = p.parent ; 
			q.parent = gp ;
			if (!    (gp == null || gp.key == null )) {
				
				if ( gp.left == p ) {
					
					gp.left = q ;
				}
				else {
					gp.right = q ; 
					
				}
			}
			else {
				// p is root 
				
			}
			
			node t2 ;
			if(q.left == null ) {
				t2 = new node() ;
			}
			else {
				t2 = q.left ;
			}
			q.left = p ;
			p.right = t2 ;
			p.height -= 1 ; 
			 
	
			p.parent = q ;
			t2.parent = new node();
			t2.parent = p ;
		}
		//left right 
		else if(first == false && second == true ) {
			rc += 2 ;
			node gp = p.parent ;
			t.parent = gp ;
			if ( !    (gp == null || gp.key == null ) ) {
				if(gp.right == p) {
					gp.right = t ;
				}
				else {
					gp.left = t ; 
				}
			}
			if(t.right == null) {
				t.right = new node();
			}
			if(t.left == null) {
				t.left = new node() ; 
			}
			node t1 = t.left ; 
			node t2 = t.right ;
			t.left = q ; 
			t.right = p ;
			t.height+=1 ;
			q.right = t1 ; 
			q.parent = t ;
			q.height -= 1 ;
			p.left = t2 ;
			p.parent = t ; 
			p.height-=1;
			t1.parent = q ;
			t2.parent = p ;
		}
		//right left same as left right
		else {
			rc += 2 ;
			node gp = p.parent ;
			t.parent = gp ;
			if ( !    (gp == null || gp.key == null ) ) {
				if(gp.left == p) {
					gp.left = t ;
				}
				else {
					gp.right = t ; 
				}
			}
			if(t.right == null) {
				t.right = new node();
			}
			if(t.left == null) {
				t.left = new node() ; 
			}
			node t1 = t.right ; 
			node t2 = t.left ;
			t.right = q ; 
			t.left = p ;
			t.height+=1 ;
			q.left = t1 ; 
			q.parent = t ;
			q.height -= 1 ;
			p.right = t2 ;
			p.parent = t ; 
			p.height-=1;
			
			t1.parent = q ;
			t2.parent = p ;
		}
		return q ; 
	}
	Boolean rebal = false ;
	//Boolean re_p = false ;  
	Boolean restructure = false ; 
	//Boolean insert = false ;
	Boolean right = false ; 
	public void r_insert(K key,V value, node n,node par) {
		if ( n == null || n.key == null ) {
			if(par == null ) {
				par = new node();
			}
			n = new node() ; 
			n.height = 1 ; 
			n.key = key ; 
			n.value = value ; 
			n.parent = par ;
			node q = n ; 
			if(!(par == null || par.key == null)) {
				if( right ) {
					par.right = n ; 
				}
				else {
					par.left = n ; 
				}
				
				q = rebalop(q);
				
			}
			
			while(!(q.parent == null || q.parent.key == null) ){
				q = q.parent ;
			}
			//return q ; 
			root = q ; 
		}
		else if(key.compareTo(n.key) ==  0 ) {
			oldval = n.value ; 
			n.value = value ; 
			//return n ; 
			return ; 
		}
		else if( key.compareTo(n.key) < 0 ) {
			right = false ; 
			//n.left = r_insert(key,value,n.left,n);
			r_insert(key,value,n.left,n);
			
			//n.left.parent = n ;
			//n = n.left.parent;
		}
		else if ( key.compareTo(n.key) > 0 ) {
			right = true ; 
			//n.right = r_insert(key,value,n.right,n);
			r_insert(key,value,n.right,n);
			//n.right.parent = n ; 
			//n = n.right.parent;
		}
		
		
	}
	V oldval ; 
	
	void printInorder(node block) {
		
		if(block!= null && block.key!= null) {
			if(block.left!= null && block.left.key!= null) {
				printInorder(block.left);
				
			}
			System.out.println(" !! key :"+block.key +", height :"+ block.height +",value :" + block.value);
			if(block.right!= null && block.right.key!= null) {
				printInorder(block.right);
				
			}
		}
		
		
	}
	
	public V put(K key, V value){
		// write your code her 
		
		//System.out.println("putting :" + key + ", " + value );
		oldval = null ;
		//root = r_insert(key,value,root,null);
		r_insert(key,value,root,null);
		//root = makeAVL(key,root);
		//print comments
		
		//System.out.println("!!printing in order");
		//System.out.println("!!root is : " + root.key + ", rank :" + root.height + ",value :" + root.value);
		//printInorder(root);
		
		return oldval ; 
		
	}
	
 
	 
	node minNode(node r) { 
		
		//System.out.println("minNode param key : " + r.key);
		//printNode(r);
        node naya = r ;
        while (!(naya.left == null || naya.left.key == null)) 
        { 
             
            naya = naya.left; 
        } 
        //System.out.println("minNode");
        //printNode(naya);
        return naya; 
    }
	
node rebal_del(node q) {
	if( q.parent == null || q.parent.key == null) return q ;
	node p = q.parent ; 
	node s = new node( );
	Boolean s_isleft = false ; 
	if(p.left == q) {
		s = p.right ; 
	}
	else {
		s = p.left ; 
		s_isleft = true ;
	}
	if( rankdiff(s) == 2 ) {
		p.height-=1 ; 
		q = p ; 
		q = rebal_del(q);
	}
	else if(rankdiff(s) == 1 ) {
		if(s.left ==null) s.left = new node();
		if(s.right ==null) s.right = new node();
		/*
		if(s!= null && s.key!= null) {
			System.out.println("s is");
			printNode(s);
		}
		if(s.left!= null && s.left.key!= null) {
			System.out.println("sleft is");
			printNode(s.left);
		}
		if(s.right!= null && s.right.key!= null) {
			System.out.println("sright is");
			printNode(s.right);
		}
		*/
		s.left.parent = s ;
		s.right.parent = s ; 
		if(rankdiff(s.left) == 2 && rankdiff(s.right) == 2) {
			p.height-=1 ;
			s.height-=1;
			q = p ;
			q = rebal_del(q);	
		}
		else {
			Boolean first = false;
			Boolean second = false ; 
			node t = new node();
			if(         !(s.left == null || s.left.key == null)  ) {
				if(rankdiff(s.left) == 1 ) {
					first = true ;
				}
			}
			if(         !(s.right == null || s.right.key == null)  ) {
				if(rankdiff(s.right) == 1 ) {
					second = true ;
				}
			}
			if( first && (!second)) {
				t = s.left;
			}
			if( second && (!first)) {
				t = s.right;
			}
			if(first&&second) {
				if(s_isleft) {
					t =s.left ;
				}
				else {
					t = s.right;
				}
				
			}
			t = del_restructure(t);	
		}
	}
	return q ; 
}


	node del_restructure(node t) {
		node s = t.parent ; 
		node p = s.parent ; 
		Boolean first = false ; 
		Boolean second = false ; 
		// true means right 
		if(p.right == s ) {
			first = true ; 
		}
		if(s.right == t ) {
			second = true ; 
		}
		
		//left left 
		if(!first && !second ) {
			rc+=1;
			node gp = p.parent ; 
			s.parent = gp ; 
			if(!    (gp == null || gp.key == null )     ) {
				if(gp.right == p ) {
					gp.right = s ; 
				}
				else {
					gp.left = s ; 
				}	
			}
			p.parent = s ; 
			node t2 = s.right ; 
			s.right = p ;
			p.left = t2 ; 
			s.height+=1;
			p.height-=1;
		}
		//right right 
		else if(first && second ) {
			rc+=1;
			node gp = p.parent ; 
			s.parent = gp ; 
			if(!    (gp == null || gp.key == null )     ) {
				if(gp.right == p ) {
					gp.right = s ; 
				}
				else {
					gp.left = s ; 
				}	
			}
			p.parent = s ; 
			node t2 = s.left ; 
			s.left = p ;
			p.right = t2 ; 
			s.height+=1;
			p.height-=1;
		}
		//left right 
		else if(!first && second) {
			rc+=2;
			node gp = p.parent ; 
			if(!    (gp == null || gp.key == null )) {
				if ( p == gp.right) {
					gp.right = t ; 
				}
				else {
					gp.left = t ; 
				}
			}
			t.parent = gp ; 
			node t1 = t.left ; 
			node t2 = t.right ; 
			t.left = s ; 
			t.right = p ; 
			t.height+=2 ; 
			p.height-=2;
			p.parent = t ; 
			p.left = t2 ; 
			s.height-=1 ; 
			s.right=t1;
			s.parent = t;	
		}
		// right left 
		else if(first && !second) {
			rc+=2;
			node gp = p.parent ; 
			if(!    (gp == null || gp.key == null )) {
				if ( p == gp.left) {
					gp.left = t ; 
				}
				else {
					gp.right = t ; 
				}
			}
			t.parent = gp ; 
			node t1 = t.right ; 
			node t2 = t.left ; 
			t.right = s ; 
			t.left = p ; 
			t.height+=2 ; 
			p.height-=2;
			p.parent = t ; 
			p.right = t2 ; 
			s.height-=1 ; 
			s.left=t1;
			s.parent = t;	
		}
		return t ; 
	}
    void printNode(node n) {
    	if(n==null) {
    		System.out.println("node is null");
    		return ; 
    	}
    	if(n.key == null) {
    		System.out.println("key is null");
    		return ; 
    		
    	}
    	System.out.println("key :" + n.key + ",value :" + n.value + ",rank:" + n.height);
    }
	void r_delete(K key,node v,node par) {
		//System.out.println("v");
		//printNode(v);
		//System.out.println("par");
		//printNode(par);
		
		if (v == null || v.key == null) {
			found = false ;
			delval = null;
			return  ; 
		}
		else if( key.compareTo(v.key) < 0) {
			r_delete(key,v.left,v);
		}
		else if( key.compareTo(v.key) > 0 ) {
			r_delete(key,v.right,v);
		}
		else{
			if(!found) {
				delval = v.value ;
				found = true ;
			}
			if( (v.left == null ||v.left.key == null) || (v.right == null || v.right.key == null) ) {
				node u = new node() ; 
				node q = new node();
				
				if (v.left == null || v.left.key == null) {
					if(v.left == null) {
						v.left = new node();
					}
					if(v.right == null) {
						v.right = new node();
					}
					u = v.left ; 
					q = v.right ;
				}
				else if (v.right == null || v.right.key == null) {
					if(v.left == null) {
						v.left = new node();
					}
					if(v.right == null) {
						v.right = new node();
					}
					u = v.right;
		        	q = v.left ; 	
		        }
				if ( v.parent == null || v.parent.key == null ) { 
					q.parent = new node();
					root = q ; 
					return ;
				}
				else {
					//System.out.println("v:");
					//printNode(v);
					
					//node p = v.parent ;
					node p = par ;
					v.parent = par ; 
					//System.out.println("p" );
					//printNode(p);
					q.parent = p ;
					node sib ;
					if ( p.right == v ) {
						p.right = q ; 
						sib = p.left ; 
					}
					else {
						p.left = q ;
						sib = p.right ; 
					}
					//System.out.println("p :" + p.key + "hp :" + p.height);
					if(q == null || q.key == null) {
						q.height = 0 ;
					}
					if( rankdiff(q) == 3) {
						rebal_del(q);
						//System.out.println("after rebalancing q is ");
						//printNode(q);
						//System.out.println("after rebalancing p is ");
						//printNode(q.parent);
					}
					if( rankdiff(q) == 2 && q.key == null ) {
						if(sib == null || sib.key == null) {
							p.height-=1 ;
							if(p!=null && p.key!= null && p.parent!= null && p.parent.key!= null) {
								if(rankdiff(p)== 3) {
									q = p ; 
									rebal_del(q);
									
								}
								
							}
							
						}
					}
				}
				while(!(q.parent == null || q.parent.key == null)){
					q = q.parent ; 
				}
				root = q ; 
				return  ; 
			}
			// two children     
	        else {
	        	//System.out.println("here v is :" + v.key);
	        	node suc = minNode(v.right);
	        	//System.out.println("sc kry:"+ suc.key + ",sc value:" + suc.value);
	        	v.key = suc.key ; 
	        	v.value = suc.value ; 
	        	//v.key = minKey(v.right);
	        	r_delete(suc.key,v.right,v);	
	        }   
	     }  
	}
	
	Boolean found = false ; 
	V delval ; 
	public V remove(K key){
		// write your code her 
		//System.out.println("Removing : " + key);
		found = false ;
		delval = null;
		//root = r_delete(key,root);
		r_delete(key,root,null);
		//System.out.println("!!printing in order");
		//System.out.println("!!root is : " + root.key + ", rank :" + root.height + ",value :" + root.value);
		//printInorder(root);
		
		
		if(!found) return null;
		else {
			return delval ; 
		}
	}

	
	V getRecursive(K key , node r) {
		if(r == null || r.key == null || r.value == null  ) {
			return null ; 
		}
		
		else if(key.compareTo(r.key)< 0 ) {
			return getRecursive(key,r.left);
		}
		else if( key.compareTo(r.key) > 0 ) {
			return getRecursive(key,r.right);
		}
		else {
		 return r.value ; 
		}
	}
	public V get(K key){
		// write your code her 
		
		V val = getRecursive(key,root);
		return val;
	}
    
	
	Vector<V> rangeVector ;
	void searchRangeRec(K key1 , K key2 , node r ) {
		if(r.left!= null) {
			searchRangeRec(key1,key2,r.left);
			
		}
		
		if( r == null || r.key == null ) return ; 
		if(r.key.compareTo(key1) >= 0 && r.key.compareTo(key2) <= 0  ) {
			rangeVector.add(r.value);
		}
		if(r.right!=null) {
			searchRangeRec(key1,key2,r.right);
			
		}
		
    }
	public Vector<V> searchRange(K key1, K key2){
		// write your code her
		rangeVector = new Vector<V>() ; 
		searchRangeRec(key1,key2,root);
		if(rangeVector.size() == 0 ) return null ; 
		else return rangeVector ; 
	}

	public int rotateCount(){
		// write your code her 
		
		return rc;
	}
    int hrec(node r) {
    	if(r == null || r.key == null  ) return 0 ; 
    	return 1 + max(hrec(r.left),hrec(r.right));
    	
    }
	public int getHeight(){
		// write your code her 
		return hrec(root);
		
	}

	Vector<K> bfs ; 
	
	public Vector<K> BFS(){
		bfs = new Vector<K>();
		// write your code her 
		Queue<node> q = new LinkedList<node>(); 
		if(root == null || root.key == null ) {
			return bfs ; 
		}
        q.add(root); 
        while (!q.isEmpty())  
        { 
            node temp = q.poll(); 
            bfs.add(temp.key);
            if (temp.left != null && temp.left.key!=null) { 
                q.add(temp.left); 
            } 
            if (temp.right != null && temp.right.key!= null ) { 
                q.add(temp.right); 
            } 
        } 
		//if(bfs.size() == 0 ) return null ; 
		return bfs ; 
	}

	
	
	
	/*
	public WeakAVLMap(){
		// write your code here
	}
	
	public V put(K key, V value){
		// write your code her 
		return null;
	}

	public V remove(K key){
		// write your code her 
		return null;
	}

	public V get(K key){
		// write your code her 
		return null;
	}

	public Vector<V> searchRange(K key1, K key2){
		// write your code her 
		return null;
	}

	public int rotateCount(){
		// write your code her 
		return 0;
	}

	public int getHeight(){
		// write your code her 
		return 0;
	}

	public Vector<K> BFS(){
		// write your code her 
		return null;
	}
	*/

}
