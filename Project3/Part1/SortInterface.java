public interface SortInterface {
		
	public void shakerSort(Comparable[] array, int lowindex, int highindex);
	public void heapSort(Comparable[] array, int lowindex, int highindex);
	public void randomizedQuickSort(Comparable[] array, int lowindex, int highindex);
	public void externalSort(String inputFile, String outputFile, int n, int m);
	public void rgbSort(String[] array);
	
}