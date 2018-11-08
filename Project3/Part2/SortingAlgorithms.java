import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
/**
 COMMITS ARE UNDER cs245/project3
 **/
public class SortingAlgorithms implements SortInterface{
			
	//Constructor that takes in no parameters 
	public SortingAlgorithms(){
	}
	
	//Cocktail Shaker Sort (a variation of bubble sort)
	//Takes in an array of Comparable(s)
	public void shakerSort(Comparable[] array, int lowindex, int highindex){
		for (int pos = 0; pos < highindex+1/2; pos++) {
			//bubbles the largest element to the end
			for (int j = lowindex; j < highindex; j++) {
				if(array[j].compareTo(array[j + 1]) >= 0){
					Comparable tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;
				}
			}
			//bubbles the smallest element to the beginning 
			for (int k = highindex; k > lowindex; k--) {
				if(array[k].compareTo(array[k - 1]) <= 0){
					Comparable tmp = array[k];
					array[k] = array[k - 1];
					array[k - 1] = tmp;
				}
			}
			highindex--;
			lowindex++;
		}
	}
	
	//Helper method for heapSort(), builds max heap from bottom up O(n)
	private void maxHeap(Comparable[] heap, int max){
		max = max-1;
		for(int i = max/2; i >= 0; i--){
			//calls helper method pushDown() to fix heap
			heap = pushDown(i, heap.length-1, heap);
		}
	}	
	
	
	//Helper method for heapSort(), returns the index of the leftChild
	private int leftChild(int pos){
		return 2*pos+1;
	}
	
	//Helper method for heapSort(), returns the index of the rightChild
	private int rightChild(int pos){
		return 2*pos+2;
	}
	
	//Helper method for heapSort(), returns the index of the parent
	private int parent(int pos){
		return (pos-1)/2;
	}
	
	//Helper method for heapSort(), returns true if current position is a leaf & false otherwise
	private boolean isLeaf(int pos, int size) {
		return ((pos >= size/ 2) && (pos <= size));
	}
	
	//Helper method for heapSort(), that swaps two Comparables to fix heap
	private void swap(int pos1, int pos2, Comparable[] heap) {
		Comparable tmp;
		tmp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = tmp;
	}
	
	//Helper method for heapSort(), that removes the max number in heap
	public Comparable[] removeMax(Comparable[] heap, int size) {
		swap(0, size, heap); // swap the end of the heap into the root
		size--; // removed the end of the heap   
		// fix the heap property - push down as needed
		if (size != 0){
			heap = pushDown(0, size, heap);
		}
		return heap;	
	}
	
	//Helper method for heapSort(), that pushes down a Comparable to fix heap 
	private Comparable[] pushDown(int position, int size, Comparable[] heap) {
		int smallestchild;
		int tmp = size+1;
		
		//Only runs through this code if the position is not a leaf
		while (!isLeaf(position, tmp)) {
		        smallestchild = leftChild(position); // set the smallest child to left child
		    if ((smallestchild < size) && (heap[smallestchild].compareTo(heap[smallestchild + 1]) < 0)){ 
		    	smallestchild = smallestchild + 1; // right child was smaller, so smallest child = right child
		    }
			// the value of the smallest child is less than value of current,
			// the heap is already valid
		    if (heap[position].compareTo(heap[smallestchild]) > 0){
		    	return heap;
			}
		    //if heap not valid, swap to make valid
			swap(position, smallestchild, heap);
			position = smallestchild;
			
		}
		return heap;
		
	}
	
	//Method to print the heap 
	public void print(Comparable[] heap) {
		int i;
		for (i = 0; i < heap.length; i++)
			System.out.print(heap[i] + " ");
		System.out.println();
	}
	
	//In-place Heap Sort that builds the heap from a given array
	//and removes the largest element and adds it to the end of the array
	public void heapSort(Comparable[] heap, int lowindex, int highindex){
		//Calls helper method maxHeap() to build heap from given array
		maxHeap(heap, highindex);
		//maxHeap(heap, highindex);
		for(int i = 0; i < heap.length-1; i++){
			//Calls helper method removeMax() which removes the largest element
			//and adds it to the end of the array
			heap = removeMax(heap, highindex);
			highindex = highindex -1; //subtract index for next largest element to be placed
		}
	}
	
	//Helper method for randomizedQuickSort() which split the array into sublists
	private int partition(Comparable[] array, int lowindex, int highindex){
		Comparable pivot;
		Comparable tmp;
		int max = highindex;

		//Choose a random element from list to be pivot
		Random rand = new Random();
		int randomNum = rand.nextInt((highindex) - lowindex + 1) + lowindex;
		
		//swap the pivot with the last index of the array
		tmp = array[randomNum];
		array[randomNum] = array[highindex];
		array[highindex] = tmp;
		pivot = array[highindex];
		lowindex--;
		
		//split index into numbers > and < the pivot
		do {
			while (array[++lowindex].compareTo(pivot) < 0);
			while ((lowindex < highindex) && (array[--highindex].compareTo(pivot) > 0));
			tmp = array[lowindex];
			array[lowindex] = array[highindex];
			array[highindex] = tmp;	
		} 
		while (lowindex < highindex);
		tmp = array[lowindex];
		array[lowindex] = array[max];
		array[max] = tmp;
		return lowindex;
	}
	
	//Randomized Quick Sort, quick sort algorithm using a random element of the array as a pivot
	public void randomizedQuickSort(Comparable[] array, int lowindex, int highindex){
		//if (lowindex < highindex) {
		if (lowindex < highindex) {
			//Split the array depending on where pivot is
			int pivot = partition(array, lowindex, highindex);
			//Recursively call randomzedQuicSort() on sublists
			randomizedQuickSort(array, lowindex, pivot - 1);
			randomizedQuickSort(array, pivot + 1, highindex);
		}
	}
	
	//Helper method for externalSort() that removes any nulls from a chunk array 
	private Comparable[] shrinkChunk(Comparable[] chunk, int count){
		//Create new array of size the number non-nulls in the chunk array
		Comparable[] newChunk = new Comparable[count];
		//Moves everything from the chunk array to the newChunk array
		for(int i = 0; i < count; i++){
			newChunk[i] = chunk[i];
		}
		return newChunk;
	}
	
	//Helper method for externalSort() that reads in and splits the numbers from a file intp chunks
	private Comparable[] getChunk(String inputFile, int n, int k, int done){
		//Create new array to hold k numbers that will be read in
		Comparable[] chunk = new Comparable[k];
		//Create new filename to read in inputFile
		File filename = new File(inputFile);
		try(Scanner input = new Scanner(filename)) {
			int j = 0;
			//Skips over numbers already read into previous chunks
			while(j < done && input.hasNext()){
				Comparable blankLine = input.nextInt();
				j++;
			}
			//Reads in new numbers and adds them to chunk array
			int i = 0;
			while(input.hasNext() && i < k){
				Comparable num = input.nextInt();
				chunk[i] = num;
				i++;		
			}
		}catch(FileNotFoundException fnf) {
			System.out.println(fnf.getMessage());
		}
		//Counts the number of valid numbers(non-null) in a chunk
		int count = 0;
		for(int x = 0; x < k; x++){
			if(chunk[x] != null){
				count++;
			}
		}
		//If there are nulls in the chunk then remove the nulls, call shrinkChunk() method
		if(count != k){
			chunk = shrinkChunk(chunk, count);
		}
		return chunk;
	}
	
	//Helper method for externalSort() that creates the temp files that will store each sorted chunk
	private void createTempFile(Comparable[] chunk, int j){
		//Create new temp file
		String filename = "temp" + j;
		File tempFile = new File(filename);
		//Write chunk numbers into the file
		try (PrintWriter printWriter = new PrintWriter (tempFile)){ 
			for(int i = 0; i < chunk.length; i++){
				printWriter.println(chunk[i]);
			}
			printWriter.close ();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Helper method for externalSort() that merges all temp files together in ascending order 
	public void mergeTempFiles(String outputFile, int numTempFiles, int n){
		//Create new file for the outputFile
		File finalFile = new File(outputFile);
		//Create array of BufferedReaders to open up all temp files
		BufferedReader[] readers = new BufferedReader[numTempFiles];
		//Create array of Comparables that will hold the current number from each temp file
		Comparable[] numbers = new Comparable[numTempFiles];
		//Create Buffered reader
		BufferedReader reader = null;
		try {
			try (PrintWriter printWriter = new PrintWriter(finalFile)){ 
				//Create reader for each temp file and add to readers array
				int i = 0;
				while (i < numTempFiles){
					String tempFile = "temp" + (i+1);
					File file = new File(tempFile);
					reader = new BufferedReader(new FileReader(file));
					readers[i] = reader;
					i++;
				}
				//Add the first numbers of all temp files into the numbers array
				for(int j = 0; j < numTempFiles; j++){
					Comparable num = Integer.parseInt(readers[j].readLine());
					numbers[j] = num;
				}
				
				//Iterate through all numbers in temp files
				for(int y = 0; y < n; y++){
					Comparable min = numbers[0];
					int index = -1;
					//Iterate through all current numbers in the numbers array
					for(int x = 0; x < numbers.length; x++){
						//Sets min to the smallest number in the numbers array
						if(numbers[x] != null && min != null && min.compareTo(numbers[x]) >= 0){
							min = numbers[x];
							index = x;
						}else if(numbers[x] == null && min != null){
							min = min;
							if(index == -1){
								index = x;
							}
						}else if(min == null && numbers[x] != null){
							min = numbers[x];
							if(index == -1){
								index = x;
							}
						}
					}
					//Writes the smallest(min) number in the numbers array to the outputFile
					printWriter.println(min);
					//Read in another number form the file that contained the min
					String str = readers[index].readLine();
					if(str != null){
						Comparable num = Integer.parseInt(str);
						numbers[index] = num;
					}else{
						Comparable num = str;
						numbers[index] = num;
					}
				}
				printWriter.close ();
			}
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
	}
	
	//External Sort (for sorting a very large list stored in a file that does not fit into memory all at once
	public void externalSort(String inputFile, String outputFile, int n, int k){
		//Determine number of chunks
		Double numChunksCeiling = Math.ceil((double)n/k);
		int numChunks = numChunksCeiling.intValue();
		int done = 0;
		//For each chunk...
		for(int i = 0; i < numChunks; i++){
			//Create a chunk by calling getChunk()
			Comparable[] chunk = getChunk(inputFile, n, k, done);
			//Sort the chunk by calling randomizedQuickSort()
			randomizedQuickSort(chunk, 0, chunk.length-1);
			//Store chunk into a temporary file 
			createTempFile(chunk, i+1);
			done = done + k;
		}
		//Merge all chunk in the temp files together by calling mergeTempFiles()
		mergeTempFiles(outputFile, numChunks, n);
	}
	
	//Helper method for RGB Sort, this method sorts characters "R" and "G"
	public void rgSort(String[] array){
		int num = 0;
		//First, check if character is "r", if it is then place the character in the beginning of array
		for(int i = 0; i < array.length; i++){
			if(array[i] == "r"){
				//swap 'r' character with character at num index
				String tmp = array[i];
				array[i] = array[num];
				array[num] = tmp;
				num++;
			}
		}
		//Then, check if character is "g", if it is then place the character following the "r" characters in the array
		for(int j = 0; j < array.length; j++){
			if(array[j] == "g"){
				//swap 'g' character with character at num index
				String tmp = array[j];
				array[j] = array[num];
				array[num] = tmp;
				num++;
			}
		}
	}
	
	//RGB Sort
	public void rgbSort(String[] array){
		//Calls helper method rgSort which will sort 'r' and 'g' which will leave all "b"'s in the end
		rgSort(array);
	}
	
	//Helper method of combSort that shrinks the gap number
	private int shrink(int gap){
		//Takes the floor of the gap divided by 1.3
		Double gapDouble = Math.floor(gap/1.3);
		//Turns gap double to int 
		int gapI = gapDouble.intValue();
		return gapI;
	}
	
	//Helper method of combSort that performs bubbleSort based on the gap size
	private Comparable[] bubbleSort(Comparable[] arr, int endI, int lowindex, int highindex) {
		boolean swap = true;
		int gap = 1;
		//bubbleSort until the gap is shrinked to one 
		while(endI != 1){
			//if the gap is equal to one and you did not swap tan the sort is complete
			if(swap == false && endI == 1){
				return arr;
			}
			//bubbles up the current smallest element 
			swap = false;
			for (int j = (endI+lowindex); j < highindex+1; j++) {
				if(arr[j-endI].compareTo(arr[j]) > 0){
					swap = true;
					Comparable tmp = arr[j];
					arr[j] = arr[j - endI];
					arr[j - endI] = tmp;
				}
			}
			//shrink the gap
			endI = shrink(endI);
		}
		//keep performing bubble sort if gap is one and swap is true
		swap = true;
		while(swap == true){
			swap = false;
			//keep bubbling up the smallest number until swap == false (sorting is done)
			for (int j = (endI+lowindex); j < highindex+1; j++) {
				if(arr[j-endI].compareTo(arr[j]) > 0){
					swap = true;
					Comparable tmp = arr[j];
					arr[j] = arr[j - endI];
					arr[j - endI] = tmp;
				}
			}
		}
		return arr;

	}
	
	//combSort a variation of bubble sort where the gap starts off larger and shrinks
	public void combSort(Comparable[] array, int lowindex, int highindex){
		//Find gap = n/k
		//"shrink factor" = 1.3
		Double gapDouble = Math.floor((highindex+1)/1.3);
		int gap = gapDouble.intValue();
		//call bubbleSort on array with gap
		array = bubbleSort(array, gap, lowindex, highindex);
	}

	//Helper method for iterativerMergeSort which merges sublists together
	private void merge(Comparable[] arr, Comparable[] temp, int low, int mid, int high) {
		int k = low;
		int i = low;
		int j = mid;
		while (k <= high) {
			if (i >= mid) {// ran out of elements in the i sublist
				temp[k] = arr[j];
				k++;
				j++;
			} else if (j > high) {// ran out of elements in the j sublist
				temp[k] = arr[i];
				k++;
				i++;
			}else if (arr[i].compareTo(arr[j]) < 0){ //comparing sublist numbers together, sort in ascending order	
			 // place arr[i] in temp, move i
				temp[k] = arr[i];
				k++;
				i++;
			} else {
				temp[k] = arr[j]; // place arr[j] in temp, move j
				k++;
				j++;
			}
		}
		// copy the result from temp back to arr
		for (k = low; k <= high; k++)
			arr[k] = temp[k];
	}
	
	//iterativerMergeSort a variation of merger sort that is non-recursive
	public void iterativeMergeSort(Comparable[] array){
		Comparable[] temp = new Comparable[array.length];
		//assuming number of elements is some power of 2 
		//sublist size starts off at 2
		int size = 2;
		//call merge on every two sublists until the whole list only contains two sublists
		while(size <= array.length/2){
			//mid is the middle of the sublist 
			int mid = size/2;
			for(int i = 0; i < array.length-1; i = i + size){
				merge(array, temp, i ,mid, i+(size-1)); 
				mid = mid + size;
			}
			//sublist size should be multiplied by 2
			size = size * 2;
		}
		//Call merge on the last two sublists to sort the whole list 
		merge(array, temp, 0, array.length/2, array.length-1);
	}
	
	//Helper method for hybridSort which sorts sublists or lists based on insertionSort
	public static void insertionSort(Comparable[] arr, int lowindex, int highindex) {
		Comparable curr;
		int j;
		for (int i = lowindex+1; i < highindex; i++) {
			curr = arr[i];
			//j is index before curr 
			j = i - 1;
			//loop unitl j is equal to the first index
			while (j >= 0 && arr[j].compareTo(curr) > 0) {
				arr[j + 1] = arr[j]; // shift elements to the right
				j--;
			}
			//set set curr to index before all elements that were shifted 
			arr[j + 1] = curr;

		}
	}
	
	//Helper method for hybridSort which sorts based on randimizedQuickSort until sublists are a specific size
	public void hybridhelp(Comparable[] array, int lowindex, int highindex, int n) {
		if ((highindex - lowindex + 1) > n) { //10
			int pivot = partition(array, lowindex, highindex);
			hybridhelp(array, lowindex, pivot - 1, n);
			hybridhelp(array, pivot + 1, highindex, n);
		}
	}
	
	//hybridSort combines randomizedQuikSort with insertionSort
	public void hybridSort(Comparable[] array, int lowindex, int highindex){		
		//calls randomizedQuickSort on array
		int n = 10;
		hybridhelp(array, lowindex, highindex, n);
		//calls insertions sort on the whole list
		insertionSort(array, lowindex, highindex);
	}	
	
	// Method that prints a given array of records
	public static void print(Record[] arr) {
		for (Record elem : arr)
			System.out.print(elem + " ");
		System.out.println();
	}
	
	//bucketSort sorts an array of Records that contains an integer key and some data 
	public void bucketSort(Record[] array, int lowindex, int highindex){
		//create number of buckets
		 int buckets = (highindex -lowindex +1)/2;
		 //create LinkedList for each bucket
		 LinkedList<Record>[] list = new LinkedList[buckets];
		 //find max key value in array
		 int max = -1;
		 for(int j = lowindex; j < highindex+1; j++){
			 if(array[j].key() > max){
				 max = array[j].key();
			 }
		 }
		 //calculate range
		 int range =  max / buckets + 1;
		 //create LinkedList for each bucket
		 for(int k = 0; k < buckets; k++){
			 list[k] = new LinkedList<Record>();
		 }
		 //The first bucket should range from lowindex to lowindex+range
		 int start = lowindex;
		 int end = lowindex + range;
		 //loop through the number of buckets
		 for(int x = 0; x < buckets; x++){
			 //loop through the lowindex and highindex Records 
			 for(int y = lowindex; y < highindex+1; y++){
				 //sort Records in ascending order and add them to list 
				 if(array[y].key() >= start && array[y].key() <= end){
					 if(list[x].isEmpty() == true){
						 list[x].addLast(array[y]);
					 }else if(list[x].getFirst().key() >= array[y].key()){
						 list[x].addFirst(array[y]);
					 }else{
						 int i = 0;
						 Record curr = list[x].getFirst();
						 while(i < list[x].size() && array[y].key() > list[x].get(i).key()){
							 i = i +1;
						 }
						 list[x].add(i, array[y]); 
					 }
				 }
			 }
			 //increase the next bucket to the next set of ranges 
			 start = end + 1;
			 end = end + range + 1;
		 }
		 //transfer the record in the LinkedList to the array
		 int insertIndex = lowindex;
		 //loop through each bucket
		 for(int n = 0; n < buckets; n++){
			 //iterate through all Records in bucket 
			 Iterator<Record> it = list[n].iterator();
				while (it.hasNext()) {
					Record record = it.next();
					int key = record.key();
					array[insertIndex] = record;
					insertIndex++;
				}
		 }
	}

	//radixSort that sorts by least important digit
	public void radixSort(int[] array, int lowindex, int highindex){
		// First, compute the number of digits in each key
		// Since we assume they all have the same # of digits,
		// it's enough to compute the # of digits in the first key
		
		//if array is empty return array 
		if (array.length == 0)
			return;
		//calculate ndigits using base 10
		int ndigits = (int) (Math.log10(array[0])) + 1;
		//create an array that contains the elements from lowindex to highindex that need to be sorted

		//create temp array
		int[] temp = new int[(highindex-lowindex)+1];
		// count array for counting sort
		int[] count = new int[10]; 
		for (int i = 0, place = 1; i < ndigits; i++, place *= 10) {
			// place will be 1, then 10, then 100, then 1000, etc.
			// initialize count array
			for (int j = 0; j < 10; j++){
				count[j] = 0;
			}
			// iterate over array and fill out count array
			for (int j = 0; j < temp.length; j++) {
				int k = (array[j + lowindex] / place) % 10;
				count[k]++;
			}
			// modified count array
			for (int j = 1; j < 10; j++){ 
				count[j] += count[j - 1];
			}
			
			// result will be placed in temp array
			for (int j = temp.length-1; j >= 0; j--){
				temp[--count[(array[j + lowindex] / place) % 10]] = array[j + lowindex];
			}
			
			// copy the result back into sort array
			for (int j = 0; j < temp.length; j++){
				array[j + lowindex] = temp[j];
			}		
		}
	}	
}
