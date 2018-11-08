/** A min-heap implementation of a priority queue. */
public class PriorityQueue {
	private PriorityQueueElement[] heap; // min heap
	private int[] positions; // maps each node id to the index of the minheap
	private int size;

	/** Constructor. Takes the size of the heap array as a parameter */
	public PriorityQueue(int size) {
		// FILL IN
		positions = new int[size]; //create positions array 
		heap = new PriorityQueueElement[size]; //create heap
		size = 0; //start size at 0
		PriorityQueueElement min = new PriorityQueueElement(Integer.MIN_VALUE, Integer.MIN_VALUE);
		heap[0] = min; // no actual data is stored at heap[0]. Assigned MIN_VALUE so that it's easier to bubble up
	}

	// FILL IN: add getters/setters as needed
	
	/** Return the index of the left child */
	private int leftChild(int pos) {
		return 2 * pos;
	}

	/** Return the index of the right child */
	private int rightChild(int pos) {
		return 2 * pos + 1;
	}

	/** Return the index of the parent */
	private int parent(int pos) {
		return pos / 2;
	}

	/** Returns true if the node in a given position is a leaf */
	private boolean isLeaf(int pos) {
		return ((pos > size/ 2) && (pos <= size));
	}
	
	//return true if the node is at the root
	private boolean isRoot(int pos){
		if(pos != 0){
			return false;
		}
		return true;
	}
	/**
	 * Insert (priority, nodeId) element into priority queue
	 */
	public void insert(int nodeId, int priority) {
		// FILL IN CODE
		PriorityQueueElement elem = new PriorityQueueElement(priority, nodeId);
		size++;
		heap[size] = elem;
		positions[nodeId] = size;
		int current = size;
		while (heap[current].compareTo(heap[parent(current)]) < 0) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	/**
	 * Remove the minimum element from the min heap (the element on top) and
	 * return its value.
	 */
	public int removeMin() {
		// FILL IN CODE
		swap(1, size); // swap the end of the heap into the root
		size--;  	   // removed the end of the heap   
		// fix the heap property - push down as needed
		if (size != 0){
			pushDown(1);
		}	
		return heap[size + 1].getNodeId();
	}

	/**
	 * Take a nodeId and a new priority for this nodeId. 
	 * new priority will be <= old priority. Update the min heap
	 * accordingly.
	 */
	public void reduceKey(int nodeId, int newPriority) {
		// FILL IN CODE
		heap[positions[nodeId]].setPriority(newPriority); //change cost
		pushUp(positions[nodeId]); //fix heap
	}
	
	// FILL IN: Add other helper methods as needed 
	// (such as "bubble down", etc.) */
	private void swap(int pos1, int pos2) {
		//swap in heap
		PriorityQueueElement tmp;
		tmp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = tmp;
		
		//swap in positions array
		int swap1 = -1;
		int swap2 = -1;
		for(int i = 0; i < heap.length; i++){ //find index in position that has the positions from heap
			if(positions[i] == pos1){
				swap1 = i;
			}else if(positions[i] == pos2){
				swap2 = i;
			}
		}
		if(swap1 != -1 && swap2 != -1){ //swap in positions array
			int tmpP = positions[swap1];
			positions[swap1] = positions[swap2];
			positions[swap2] = tmpP;
		}
	}	

	//method that fixes heap after deletion
	private void pushDown(int position) {
		int smallestchild;
		while (!isLeaf(position)) {
		        smallestchild = leftChild(position);// set the smallest child to left child
			if ((smallestchild < size) && (heap[smallestchild].compareTo(heap[smallestchild + 1])) > 0)
				smallestchild = smallestchild  + 1; // right child was smaller, so smallest child = right child
			// the value of the smallest child is less than value of current,
			// the heap is already valid
			if (heap[position].compareTo(heap[smallestchild]) <= 0)
				return;
			swap(position, smallestchild);
			position = smallestchild;
		}
	}
	
	//method that fixes heap after change in elem's priority
	private void pushUp(int position) {
		int parent;
		while (!isRoot(position)) {
		        parent = parent(position);//set parent to the position's parent
		    //at root stop
		    if(isRoot(parent)){
		    	return;
		    }
			if ((parent > 0) && (heap[parent].compareTo(heap[parent - 1])) < 0 )
				parent = parent  - 1; //the parent is still bigger than position so keep going up
			//heap is valid
			if (heap[position].compareTo(heap[parent]) > 0)
				return;
			swap(position, parent);
			position = parent;
		}
	}
	
	//method that returns the positions array
	public int[] getPositions(){
		return positions;
	}
	
	/** Print the array that stores the heap */
	public void printHeap() {
		System.out.println("HEAP: ");
		int i;
		for (i = 1; i <= size; i++)
			System.out.print(heap[i].getNodeId() + ", cost: " + heap[i].getPriority() + " ; ");
		System.out.println();
	}
	
	/** Print the array that stores the positions */
	public void printPositions() {
		System.out.println("POSITIONS: ");
		int i;
		for (i = 0; i < heap.length-1; i++)
			System.out.print(positions[i] + " ");
		System.out.println();
	}
	
	//returns the size of the heap
	public int size(){
		return size;
	}
}
