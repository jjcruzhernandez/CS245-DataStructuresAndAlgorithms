import java.util.Random; 

public class Test{
	
	//Testing time for randomlist
	public static double randomlist(int size, int iterations){
		double durationOverhead;
		Random randomGenerator = new Random();
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = randomGenerator.nextInt();
			}	
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("randomlist: " + durationOverhead);	
		return durationOverhead;
	}
	
	//Testing time for sorted list
	public static double sortedlist(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = j;
			}		
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("sortedlist: " + durationOverhead);	
		return durationOverhead;
	}
	
	////Testing time for reversed list
	public static double reversedlist(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			int n = list.length-1;
			for(int j = 0; j < list.length; j++){
				list[j] = n;
				n--;
			}		
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("reversedlist: " + durationOverhead);
		return durationOverhead;
	}
	
	//shakerSort time efficieny methods (random, sorted and randomized)
	public static double randomShakerSort(int size, int iterations){
		double durationOverhead;
		Random randomGenerator = new Random();
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = randomGenerator.nextInt();
			}
			sorting.shakerSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("randomShakerSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double sortedShakerSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = j;
			}	
			sorting.shakerSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("sortedShakerSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double reversedShakerSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			int n = list.length-1;
			for(int j = 0; j < list.length; j++){
				list[j] = n;
				n--;
			}	
			sorting.shakerSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("reversedShakerSort: " + durationOverhead);	
		return durationOverhead;
	}
	
	//heapSort time efficieny methods (random, sorted and randomized)
	public static double randomHeapSort(int size, int iterations){
		double durationOverhead;
		Random randomGenerator = new Random();
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = randomGenerator.nextInt();
			}
			sorting.heapSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("randomHeapSort: " + durationOverhead);	
		return durationOverhead;
	}
	
	public static double sortedHeapSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = j;
			}	
			sorting.heapSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("sortedHeapSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double reversedHeapSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			int n = list.length-1;
			for(int j = 0; j < list.length; j++){
				list[j] = n;
				n--;
			}	
			sorting.heapSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("reversedHeapSort: " + durationOverhead);
		return durationOverhead;
	}
	
	//randomizedQuickSort, time efficieny methods (random, sorted and randomized)
	public static double randomRandomizedQuickSort(int size, int iterations){
		double durationOverhead;
		Random randomGenerator = new Random();
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = randomGenerator.nextInt();
			}
			sorting.randomizedQuickSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("randomRandomizedQuickSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double sortedRandomizedQuickSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = j;
			}	
			sorting.randomizedQuickSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("sortedRandomizedQuickSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double reversedRandomizedQuickSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			int n = list.length-1;
			for(int j = 0; j < list.length; j++){
				list[j] = n;
				n--;
			}	
			sorting.randomizedQuickSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("reversedRandomizedQuickSort: " + durationOverhead);	
		return durationOverhead;
	}
	
	//combSort, time efficieny methods (random, sorted and randomized)
	public static double randomCombSort(int size, int iterations){
		double durationOverhead;
		Random randomGenerator = new Random();
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = randomGenerator.nextInt();
			}
			sorting.combSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("randomCombSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double sortedCombSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = j;
			}	
			sorting.combSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("sortedCombSort: " + durationOverhead);	
		return durationOverhead;
	}
	
	public static double reversedCombSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			int n = list.length-1;
			for(int j = 0; j < list.length; j++){
				list[j] = n;
				n--;
			}	
			sorting.combSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("reversedCombSort: " + durationOverhead);
		return durationOverhead;
	}
	
	//iterativeMergeSort time efficieny methods (random, sorted and randomized)
	public static double randomIterativeMergeSort(int size, int iterations){
		double durationOverhead;
		Random randomGenerator = new Random();
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = randomGenerator.nextInt();
			}
			sorting.iterativeMergeSort(list);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("randomIterativeMergeSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double sortedIterativeMergeSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = j;
			}	
			sorting.iterativeMergeSort(list);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("sortedIterativeMergeSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double reversedIterativeMergeSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			int n = list.length-1;
			for(int j = 0; j < list.length; j++){
				list[j] = n;
				n--;
			}	
			sorting.iterativeMergeSort(list);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("reversedIterativeMergeSort: " + durationOverhead);	
		return durationOverhead;
	}
	
	//hybridSort time efficieny methods (random, sorted and randomized)
	public static double randomHybridSort(int size, int iterations){
		double durationOverhead;
		Random randomGenerator = new Random();
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = randomGenerator.nextInt();
			}
			sorting.hybridSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("randomizedHybridSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double sortedHybridSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = j;
			}	
			sorting.hybridSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("sortedHybridSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double reversedHybridSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Comparable[] list = new Comparable[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			int n = list.length-1;
			for(int j = 0; j < list.length; j++){
				list[j] = n;
				n--;
			}	
			sorting.hybridSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("reversedHybridSort: " + durationOverhead);	
		return durationOverhead;
	}
	
	//bucketSort time efficieny methods (random, sorted and randomized)
	public static double randomBucket(int size, int iterations){
		double durationOverhead;
		Random randomGenerator = new Random();
		SortingAlgorithms sorting = new SortingAlgorithms();
		Record[] records = new Record[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < records.length; j++){
				int n = randomGenerator.nextInt();
				Record record = new Record(n, "");
				records[j] = record;
			}
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("randomBucket: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double sortedBucket(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Record[] records = new Record[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < records.length; j++){
				Record record = new Record(j, "");
				records[j] = record;
			}	
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("sortedBucket: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double reversedBucket(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Record[] records = new Record[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			int j = 0;
			for(int o = records.length; o > 0; o--){
				Record record = new Record(o, "");
				records[j] = record;
				j++;
			}	
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("reversedBucket: " + durationOverhead);	
		return durationOverhead;
	}
	
	public static double randomBucketSort(int size, int iterations){
		double durationOverhead;
		Random randomGenerator = new Random();
		SortingAlgorithms sorting = new SortingAlgorithms();
		Record[] records = new Record[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < records.length; j++){
				int n = randomGenerator.nextInt();
				Record record = new Record(n, "");
				records[j] = record;
			}
			sorting.bucketSort(records, 0, records.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("randomBucketSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double sortedBucketSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Record[] records = new Record[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < records.length; j++){
				Record record = new Record(j, "");
				records[j] = record;
			}	
			sorting.bucketSort(records, 0, records.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("sortedBucketSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double reversedBucketSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		Record[] records = new Record[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			int j = 0;
			for(int o = records.length; o > 0; o--){
				Record record = new Record(o, "");
				records[j] = record;
				j++;
			}	
			sorting.bucketSort(records, 0, records.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("reversedBucketSort: " + durationOverhead);	
		return durationOverhead;
	}
	
	
	//radixSort time efficieny methods (random, sorted and randomized)
	public static double randomRadixSort(int size, int iterations){
		double durationOverhead;
		Random randomGenerator = new Random();
		SortingAlgorithms sorting = new SortingAlgorithms();
		int[] list = new int[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = randomGenerator.nextInt();
			}
			sorting.radixSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("randomRadixSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double sortedRadixSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		int[] list = new int[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			for(int j = 0; j < list.length; j++){
				list[j] = j;
			}	
			sorting.radixSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("sortedRadixSort: " + durationOverhead);
		return durationOverhead;
	}
	
	public static double reversedRadixSort(int size, int iterations){
		double durationOverhead;
		SortingAlgorithms sorting = new SortingAlgorithms();
		int[] list = new int[size];
		long startTimeOverhead = System.currentTimeMillis();
		for(int i = 0;i < iterations; i++) {
			int n = list.length-1;
			for(int j = 0; j < list.length; j++){
				list[j] = n;
				n--;
			}	
			sorting.radixSort(list, 0, list.length-1);
		}
		long endTimeOverhead = System.currentTimeMillis();
		durationOverhead = ((double) (endTimeOverhead - startTimeOverhead)) / iterations;
		System.out.println("reversedRadixSort: " + durationOverhead);	
		return durationOverhead;
	}
	
	//main
	public static void main(String[] args) {
//****************************************testing shakerSort********************************
//		double i = randomlist(100, 120000);
//		double j = randomShakerSort(100,120000);
		
//		double i = randomlist(1000, 1110);
//		double j = sortedShakerSort(1000,1110);
		
//		double i = randomlist(10000, 24);
//		double j = randomShakerSort(10000, 24);
		
//		double i = randomlist(100000, 20);
//		double j = randomShakerSort(100000,20);

//----------------------------------------------		
		
//		double i = sortedlist(100, 2750);
//		double j = sortedShakerSort(100,2750);
		
//		double i = sortedlist(1000, 100);
//		double j = sortedShakerSort(1000,100);
		
//		double i = sortedlist(10000, 24);
//		double j = sortedShakerSort(10000, 24);
		
//		double i = sortedlist(100000, 30);
//		double j = sortedShakerSort(100000, 30);
		
//----------------------------------------------	
		
//		double i = reversedlist(100, 2195);
//		double j = reversedShakerSort(100,2195);
		
//		double i = reversedlist(1000, 1000);
//		double j = reversedShakerSort(1000,1000);
		
//		double i = reversedlist(10000, 1000);
//		double j = reversedShakerSort(10000, 1000);
		
//		double i = reversedlist(100000, 20);
//		double j = reversedShakerSort(100000,20);
		
//*****************************************testing heapSort**************************************
		//testing shakerSort
//		double i = randomlist(100, 120010);
//		double j = randomHeapSort(100,120010);
		
//		double i = randomlist(1000, 1130);
//		double j = sortedHeapSort(1000,1130);
		
//		double i = randomlist(10000, 22);
//		double j = randomHeapSort(10000, 22);
		
//		double i = randomlist(100000, 25);
//		double j = randomHeapSort(100000,25);

//----------------------------------------------		
		
//		double i = sortedlist(100, 28000);
//		double j = sortedHeapSort(100,28000);
		
//		double i = sortedlist(1000, 100);
//		double j = sortedHeapSort(1000,100);
		
//		double i = sortedlist(10000, 30);
//		double j = sortedHeapSort(10000, 30);
		
//		double i = sortedlist(100000, 50);
//		double j = sortedHeapSort(100000, 50);
		
//----------------------------------------------	
		
//		double i = reversedlist(100, 5000);
//		double j = reversedHeapSort(100,5000);
		
//		double i = reversedlist(1000, 1100);
//		double j = reversedHeapSort(1000,1100);
		
//		double i = reversedlist(10000, 1000);
//		double j = reversedHeapSort(10000, 1000);
		
//		double i = reversedlist(100000, 100);
//		double j = reversedHeapSort(100000,100);
		
//*************************************testing randomizedQuickSort*************************************
//		double i = randomlist(100, 500000);
//		double j = randomRandomizedQuickSort(100,500000);
		
//		double i = randomlist(1000, 30000);
//		double j = sortedRandomizedQuickSort(1000,30000);
		
//		double i = randomlist(10000, 1000);
//		double j = randomRandomizedQuickSort(10000, 1000);
		
//		double i = randomlist(100000, 1000);
//		double j = randomRandomizedQuickSort(100000,1000);

//----------------------------------------------		
		
//		double i = sortedlist(100, 500000);
//		double j = sortedRandomizedQuickSort(100,500000);
		
//		double i = sortedlist(1000, 400000);
//		double j = sortedRandomizedQuickSort(1000,400000);
		
//		double i = sortedlist(10000, 71000);
//		double j = sortedRandomizedQuickSort(10000, 71000);
		
//		double i = sortedlist(100000, 6500);
//		double j = sortedRandomizedQuickSort(100000, 6500);
		
//----------------------------------------------	
		
//		double i = reversedlist(100, 300000);
//		double j = reversedRandomizedQuickSort(100,300000);
		
//		double i = reversedlist(1000, 280000);
//		double j = reversedRandomizedQuickSort(1000,280000);
		
//		double i = reversedlist(10000, 20000);
//		double j = reversedRandomizedQuickSort(10000, 20000);
		
//		double i = reversedlist(100000, 11000);
//		double j = reversedRandomizedQuickSort(100000,11000);
		
//*******************************************testing combSort**********************************************
//		double i = randomlist(100, 200000);
//		double j = randomCombSort(100,200000);
		
//		double i = randomlist(1000, 170000);
//		double j = randomCombSort(1000,170000);
		
//		double i = randomlist(10000, 10000);
//		double j = randomCombSort(10000, 10000);
		
//		double i = randomlist(100000, 7000);
//		double j = randomCombSort(100000,7000);

//----------------------------------------------		
		
//		double i = sortedlist(100, 1000000);
//		double j = sortedCombSort(100, 1000000);
		
//		double i = sortedlist(1000, 100000);
//		double j = sortedCombSort(1000,100000);
		
//		double i = sortedlist(10000, 50000);
//		double j = sortedCombSort(10000, 50000);
		
//		double i = sortedlist(100000, 1000);
//		double j = sortedCombSort(100000, 1000);
		
//----------------------------------------------	
		
//		double i = reversedlist(100, 1000000);
//		double j = reversedCombSort(100, 1000000);
		
//		double i = reversedlist(1000, 600000);
//		double j = reversedCombSort(1000,600000);
		
//		double i = reversedlist(10000, 70000);
//		double j = reversedCombSort(10000, 70000);
		
//		double i = reversedlist(100000, 5000);
//		double j = reversedCombSort(100000, 5000);
		
//************************************testing iterativeMergeSort*****************************************
//		double i = randomlist(128, 1500000);
//		double j = randomIterativeMergeSort(128,1500000);
		
//		double i = randomlist(1024, 600300);
//		double j = sortedIterativeMergeSort(1024,600300);
		
//		double i = randomlist(8192, 71000);
//		double j = randomIterativeMergeSort(8192, 71000);
		
//		double i = randomlist(131072, 5000);
//		double j = randomIterativeMergeSort(131072, 5000);

//----------------------------------------------		
		
//		double i = sortedlist(128, 5000000);
//		double j = sortedIterativeMergeSort(128, 5000000);
		
//		double i = sortedlist(1024, 750000);
//		double j = sortedIterativeMergeSort(1024, 750000);
		
//		double i = sortedlist(8192, 40000);
//		double j = sortedIterativeMergeSort(8192, 40000);
		
//		double i = sortedlist(131072, 5000);
//		double j = sortedIterativeMergeSort(131072, 5000);
		
//----------------------------------------------	
		
//		double i = reversedlist(128, 5000010);
//		double j = reversedIterativeMergeSort(128, 5000010);
//		
//		double i = reversedlist(1024, 720000);
//		double j = reversedIterativeMergeSort(1024, 720000);
		
//		double i = reversedlist(8192, 65100);
//		double j = reversedIterativeMergeSort(8192, 65100);
		
//		double i = reversedlist(131072, 7000);
//		double j = reversedIterativeMergeSort(131072,7000);
		
//*********************************************testing hybridSort************************************
//		double i = randomlist(100, 500000);
//		double j = randomHybridSort(100,500000);
		
//		double i = randomlist(1000, 30000);
//		double j = sortedHybridSort(1000, 30000);
		
//		double i = randomlist(10000, 1000);
//		double j = randomHybridSort(10000, 1000);
		
//		double i = randomlist(100000, 1000);
//		double j = randomHybridSort(100000,1000);

//----------------------------------------------		
		
//		double i = sortedlist(100, 500000);
//		double j = sortedHybridSort(100, 500000);
		
//		double i = sortedlist(1000, 400000);
//		double j = sortedHybridSort(1000,400000);
		
//		double i = sortedlist(10000, 71000);
//		double j = sortedHybridSort(10000, 71000);
		
//		double i = sortedlist(100000, 6500);
//		double j = sortedHybridSort(100000, 6500);
		
//----------------------------------------------	
		
//		double i = reversedlist(100, 300000);
//		double j = reversedHybridSort(100, 300000);
		
//		double i = reversedlist(1000, 280000);
//		double j = reversedHybridSort(1000, 280000);
		
//		double i = reversedlist(10000, 20000);
//		double j = reversedHybridSort(10000, 20000);
		
//		double i = reversedlist(100000, 11000);
//		double j = reversedHybridSort(100000,11000);
		
//****************************************testing bucketSort*******************************
//		double i = randomBucket(100, 121000);
//		double j = randomBucketSort(100,121000);
		
//		double i = randomBucket(1000, 12200);
//		double j = sortedBucketSort(1000,12200);
		
//		double i = randomBucket(10000, 200);
//		double j = randomBucketSort(10000, 200);
		
//		double i = randomBucket(100000, 20);
//		double j = randomBucketSort(100000, 20);

//----------------------------------------------		
		
//		double i = sortedBucket(100, 200000);
//		double j = sortedBucketSort(100, 200000);
		
//		double i = sortedBucket(1000, 10010);
//		double j = sortedBucketSort(1000,10010);
		
//		double i = sortedBucket(10000, 250);
//		double j = sortedBucketSort(10000, 250);
		
//		double i = sortedBucket(100000, 20);
//		double j = sortedBucketSort(100000, 20);
		
//----------------------------------------------	
		
//		double i = reversedBucket(100, 310000);
//		double j = reversedBucketSort(100, 310000);
		
//		double i = reversedBucket(1000, 1000);
//		double j = reversedBucketSort(1000,1000);
		
//		double i = reversedBucket(10000, 1000);
//		double j = reversedBucketSort(10000, 1000);
		
//		double i = reversedBucket(100000, 20);
//		double j = reversedBucketSort(100000,20);
		
//**************************************testing radixSort******************************************
//		double i = randomlist(100, 120000);
//		double j = randomRadixSort(100,120000);
		
//		double i = randomlist(1000, 1210);
//		double j = sortedRadixSort(1000,1310);
		
//		double i = randomlist(10000, 50);
//		double j = randomRadixSort(10000, 50);
		
//		double i = randomlist(100000, 20);
//		double j = randomRadixSort(100000,20);

//----------------------------------------------		
		
//		double i = sortedlist(100, 300750);
//		double j = sortedRadixSort(100,300750);
		
//		double i = sortedlist(1000, 100000);
//		double j = sortedRadixSort(1000,100000);
		
//		double i = sortedlist(10000, 3000);
//		double j = sortedRadixSort(10000, 3000);
		
//		double i = sortedlist(100000, 700);
//		double j = sortedRadixSort(100000, 700);
		
//----------------------------------------------	
		
//		double i = reversedlist(100, 230000);
//		double j = reversedRadixSort(100,230000);
		
//		double i = reversedlist(1000, 10000);
//		double j = reversedRadixSort(1000,10000);
		
//		double i = reversedlist(10000, 1300);
//		double j = reversedRadixSort(10000, 1300);
		
//		double i = reversedlist(100000, 50);
//		double j = reversedRadixSort(100000,50);
		
		//Total
//		System.out.println("Total: " + (j-i));
	}	

}