/** A class that represents the Disjoint Sets data structure. Must use Union by Rank and Path compression. */
public class DisjointSets {
	// array of "parent" indices. Stores either the index of the parent or (-height) for the root
	private int[] parent; 

	//method that creates an array of parent indices all set to -1
	public void createSets(int n) {
		// FILL IN CODE
		parent = new int[n]; //make the the array of size n (number of nodes)
		for(int i = 0; i < parent.length; i++){
			parent[i] = -1; //set each parent index to -1
		}
	}

	/**
	 * Returns the root of the "tree" that x belongs to. Uses path compression
	 * heuristic.
	 */
	public int find(int x) {
		// FILL IN CODE
		if(parent[x] < 0){ // if parent[x] is negative then you have reached the parent return x
			return x;
		}else{
			parent[x] = find(parent[x]); //recursively call find until you find the parent
			return parent[x];
		}	

	}

	/** Merges two sets: the one with x and the one with y. Should use Union by Rank heuristic. */
	public void union(int x, int y) {
		// FILL IN CODE
		int rootx = find(x); //find parent of x 
		int rooty = find(y); //find parent of y
		if(parent[rootx]  < parent[rooty]){ //if parent of x is less the parent of y then make x's parent the root
			parent[rooty] = rootx;
		}else{
			if(parent[rootx] == parent[rooty]){ //if parent of x is equal to the parent of y then make y's parent the root
				parent[rooty]--; //subtract from parent y's index
			}
			if (rootx != rooty){ //only create the union if there parent's are NOT the same
				parent[rootx] = rooty;
			}	
		}
	}
}
