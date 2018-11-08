public interface SortInterface {
	//project pt.1	
	public void shakerSort(Comparable[] array, int lowindex, int highindex);
	public void heapSort(Comparable[] array, int lowindex, int highindex);
	public void randomizedQuickSort(Comparable[] array, int lowindex, int highindex);
	public void externalSort(String inputFile, String outputFile, int n, int m);
	public void rgbSort(String[] array);
	
	//project pt.2
	public void combSort(Comparable[] array, int lowindex, int highindex);
	public void iterativeMergeSort(Comparable[] array);
	public void hybridSort(Comparable[] array, int lowindex, int highindex);
	//public void bucketSort(Record[] array, int lowindex, int highindex);
	public void radixSort(int[] array, int lowindex, int highindex);
}