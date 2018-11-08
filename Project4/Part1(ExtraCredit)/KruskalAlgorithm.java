import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class KruskalAlgorithm {
	private Graph graph; // stores the graph of CityNode-s and edges connecting them
	private List<Edge> edgesMST = new ArrayList<>(); // edges that belong to MinimalSpanningTree

	public KruskalAlgorithm(String filename) {
		graph = new Graph();
		graph.loadGraph(filename);
	}
	
	
	/**
	 * Inner class Graph.
	 * A class that represents a graph where nodes are cities (of type CityNode)
	 * and edges connect them and the cost of each edge is the distance between
	 * the cities.
	 */
	public class Graph {

		private CityNode[] nodes; // stores a CityNode for each node id
		private Edge[] adjacencyList; // for each vertex store a linked list of edges;
		// FILL IN: add a HashTable that maps the name of each city to the corresponding integer id
		private HashTable table;
		
		
		//method that returns the next prime number
		public int primeNum(int q){
			q = q  + 1; 
			boolean isPrime = true;
			while(isPrime == true){
				for(int i = 2; i <= q/2; i++){ //checks that q is not divisible by any numbers before it
					if(q % i == 0){
						isPrime = false;
					}
				}
				q = q + 1; //if number is not prime then add one and check again
			}
			return q;
		}
		
		/**
		 * Read graph info from the given file, and create nodes and edges of
		 * the graph.
		 * Must catch I/O exceptions inside the method.
		 * @param filename
		 */
		public void loadGraph(String filename) {
			// FILL IN CODE
			//Scan through the file 
			File inputFile = new File(filename);
			try(Scanner input = new Scanner(inputFile)) {
				while(input.hasNext()) {
					String str1 = input.nextLine(); //stores the first line
					if(str1.equals("NODES")){ //checks that first line is "NODES"
						String numNodesStr = input.nextLine(); //stores the number of nodes that will follow from the next line
						int numNodes = Integer.parseInt(numNodesStr); //makes number an integer
						int prime = primeNum(numNodes+numNodes);
						table = new HashTable(prime); //creates HashTable
						nodes = new CityNode[table.length()]; //creates an array of CityNodes based of the number of nodes
						//reads in information to create CityNodes 
						int i = 0;
						while(i < numNodes){
							String tmp = input.nextLine();
							String[] info = tmp.split(" ");
							double x = Double.parseDouble(info[1]);
							double y = Double.parseDouble(info[2]);
							CityNode node = new CityNode(info[0], x, y);
							table.insert(info[0]); //adds city to Hashmap along with its key which is the cities id
							int pos = table.getId(info[0]);
							nodes[pos] = node;  //adds CityNode to nodes array 
							i++;
						}	
					}
					String str2 = input.nextLine();
					adjacencyList = new Edge[table.length()]; //creates adjacencyList based of the number of nodes
					if(str2.equals("ARCS")){ //checks that line is "ARCS"
						while(input.hasNext()){ //reads in edge information
							String tmp = input.nextLine();
							String[] info = tmp.split(" ");
							int cost = Integer.parseInt(info[2]);
							int from = table.getId(info[0]);
							int to = table.getId(info[1]);
							Edge edge = new Edge(from, to, cost); //create edge
							if(adjacencyList[from] == null){ //add edge to head if that index is empty
								adjacencyList[from] = edge;
							}else{
								Edge tmpEdge = adjacencyList[from]; //else add to the head
								while(tmpEdge.next() != null){
									tmpEdge = tmpEdge.next();
								}
								tmpEdge.setNext(edge); //sets the last edge's next to the new edge created
							}
						}		
					}	
				}
				
			}catch(FileNotFoundException fnf) {
				System.out.println(fnf.getMessage());
			}
		}
	} // inner class Graph finished ------------------

	//Helper method for randomizedQuickSort() which split the array into sublists
	private int partition(Edge[] array, int lowindex, int highindex){
		Edge pivot;
		Edge tmp;
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
	public void randomizedQuickSort(Edge[] array, int lowindex, int highindex){
		if (lowindex < highindex) {
			//Split the array depending on where pivot is
			int pivot = partition(array, lowindex, highindex);
			//Recursively call randomzedQuicSort() on sublists
			randomizedQuickSort(array, lowindex, pivot - 1);
			randomizedQuickSort(array, pivot + 1, highindex);
		}
	}
	
	
	/**
	 * Compute minimum spanning tree for this graph. Add edges of Minimal Spanning Tree to
	 * edgesMST list. Should use DisjointSet class.
	 */
	public void computeMST() {
		// FILL IN CODE
		DisjointSets ds = new DisjointSets(); 
		int n = graph.adjacencyList.length; //length of adjacencyList
		ds.createSets(n); //create set of parent indicies 
		Edge[] tmp = new Edge[n*n]; //create an array of Edges that will be sorted by cost
		//add all edges to the tmp array from adjacencyList
		int i = 0;
		for(int v = 0; v < graph.adjacencyList.length; v++){
			for(Edge tmpEdge = graph.adjacencyList[v]; tmpEdge != null; tmpEdge = tmpEdge.next()){
				tmp[i] = tmpEdge;
				i = i + 1;
			}
		}
		//sort the edges by cost using randomizedQuickSort()
		randomizedQuickSort(tmp, 0, i-1);
		for(int j = 0; tmp[j] != null; j++){ //iterate through the tmp array to find MST edges
			int startpt = ds.find(tmp[j].getId1()); //find the parent of the first city
			int endpt = ds.find(tmp[j].getId2()); //find the parent of the second city
			if(startpt != endpt){ //if they do not have the same parent then add (union) the edge to arrayList of MST edges
				ds.union(tmp[j].getId1(), tmp[j].getId2());
				edgesMST.add(tmp[j]);
			}
		}		
	}


	// -------------------- methods needed for GUIApp-------------------
	/** Used in GUIApp to display the MST. Returns a 2D Array, where each element represents
	 * an edge and is an array of two Points (where this edge starts and where it is going). */
	public Point[][] getMSTEdges() {
		Point[][] edges = new Point[edgesMST.size()][2];
		// FILL IN CODE
		for(int i = 0; i < edgesMST.size(); i++){ //iterate though the arrayList of MST edges 
			Edge tmp = edgesMST.get(i); //get the edge from arrayList
			CityNode start = graph.nodes[tmp.getId1()]; //get CityNode for the first city
			CityNode end = graph.nodes[tmp.getId2()]; //get CityNode for the second city
			edges[i][0] = start.getLocation(); //add the first cities points to the 2D array
			edges[i][1] = end.getLocation(); //add the second cities points to the 2D array	
		}
		return edges;
	}

	/** Used in GUIApp to display the edges of the graph. Returns a 2D array that contains edge info
	 *  that is easy for GUI to display. For each edge, we store an array of two Points, v1 and v2. 
	 *  v1 is the source vertex for this edge, v2 is the destination vertex. 
	 *  This info can be obtained from the adjacency list. */
	public Point[][] getEdges() {
		int n = 0; //count number of edges
		for(int v = 0; v < graph.adjacencyList.length; v++){
			for(Edge tmp = graph.adjacencyList[v]; tmp != null; tmp = tmp.next()){
				n = n + 1;
			}
		}
		Point[][] edges2D = new Point[n][2]; //create 2D array based on number of edges 
		// FILL IN CODE
		int i = 0;
		for(int v = 0; v < graph.adjacencyList.length; v++){ //iterate through the adjacencyList
			for(Edge tmpEdge = graph.adjacencyList[v]; tmpEdge != null; tmpEdge = tmpEdge.next()){
				CityNode start = graph.nodes[tmpEdge.getId1()]; //get CityNode for the first city
				CityNode end = graph.nodes[tmpEdge.getId2()]; //get CityNode for the second city
				edges2D[i][0] = start.getLocation(); //add the first cities points to the 2D array
				edges2D[i][1] = end.getLocation(); //add the second cities points to the 2D array
				i++;
			}
		}
		return edges2D;

	}

	/** Used in GUIApp to display the nodes of the graph. Return a list of Points that
	 *  correspond to nodes of the graph. */
	public Point[] getNodes() {
		int n = 0; //count number of edges
		for(int v = 0; v < graph.nodes.length; v++){
			if(graph.nodes[v] != null){
				n = n + 1;
			}
		}
		Point[] nodes = new Point[n]; //creates array based on number of Nodes created
		// FILL IN CODE
		int j = 0;
		for(int i = 0; i < graph.nodes.length; i++){//iterate through nodes array and get the location of each
			if(graph.nodes[i] != null){
				nodes[j] = graph.nodes[i].getLocation(); //add location of each node to the nodes array
				j = j + 1;
			}
		}
		return nodes;
	}

	/** Used in GUIApp to display the names of the cities. Return the list that
	 * contains the "names" of city nodes. */
	public String[] getCities() {
		int n = 0; //count number of edges
		for(int v = 0; v < graph.nodes.length; v++){
			if(graph.nodes[v] != null){
				n = n + 1;
			}
		}
		String[] labels = new String[n];
		// FILL IN CODE
		int j = 0;
		for(int i = 0; i < graph.nodes.length; i++){ //iterate through the nodes array and get the City name
			if(graph.nodes[i] != null){
				labels[j] = graph.nodes[i].getCity(); //add city name to labels array
				j = j + 1;
			}
		}
		return labels;
	}
}
