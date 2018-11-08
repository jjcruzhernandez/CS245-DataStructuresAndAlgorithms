import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PrimAlgorithm {
	private Graph graph; // stores the graph of CityNode-s and edges connecting
	// them
	private List<Edge> edgesMST = new ArrayList<>(); // edges that belong to
	// MinimalSpanningTree

	public PrimAlgorithm(String filename) {
		graph = new Graph();
		graph.loadGraph(filename);
	}

	/**
	 * A class that represents a graph where nodes are cities (of type CityNode)
	 * and edges connect them and the cost of each edge is the distance between
	 * the cities.
	 */
	public class Graph {

		private CityNode[] nodes; //all vertexes
		private Edge[] adjacencyList; // for each vertex store a linked list of edges
		// FILL IN: other instance variables as needed
		private HashMap hashMap; //maps city to id
		
		/**
		 * Read graph info from the given file, and create nodes and edges of
		 * the graph.
		 * 
		 * @param filename
		 */
		public void loadGraph(String filename) {
			// FILL IN CODE
			//create hashMap
			hashMap = new HashMap<String, Integer>();
			//Scan through the file 
			File inputFile = new File(filename);
			try(Scanner input = new Scanner(inputFile)) {
				while(input.hasNext()) {
					String str1 = input.nextLine(); //stores the first line
					if(str1.equals("NODES")){ //checks that first line is "NODES"
						String numNodesStr = input.nextLine(); //stores the number of nodes that will follow from the next line
						int numNodes = Integer.parseInt(numNodesStr); //makes number an integer
						nodes = new CityNode[numNodes]; //creates an array of CityNodes based of the number of nodes
						adjacencyList = new Edge[numNodes]; //creates adjacencyList based of the number of nodes
						//reads in information to create CityNodes 
						int i = 0;
						while(i < numNodes){
							String tmp = input.nextLine();
							String[] info = tmp.split(" ");
							double x = Double.parseDouble(info[1]);
							double y = Double.parseDouble(info[2]);
							CityNode node = new CityNode(info[0], x, y);
							nodes[i] = node; //adds CityNode to nodes array 
							hashMap.put(info[0], i); //adds city to Hashmap along with its key which is the cities id
							i++;
						}	
					}
					String str2 = input.nextLine();
					if(str2.equals("ARCS")){ //checks that line is "ARCS"
						while(input.hasNext()){ //reads in edge information
							String tmp = input.nextLine();
							String[] info = tmp.split(" ");
							int cost = Integer.parseInt(info[2]);
							int from = Integer.parseInt(String.valueOf(hashMap.get(info[0])));
							int to = Integer.parseInt(String.valueOf(hashMap.get(info[1])));
							Edge edge = new Edge(from, to, cost); //create edge
							Edge edge2 = new Edge(to, from, cost); //create edge going opposite direction
							if(adjacencyList[from] == null){ //add edge1 to head if that index is empty
								adjacencyList[from] = edge;
							}else{
								Edge tmpEdge = adjacencyList[from]; //else add to the head
								while(tmpEdge.next() != null){
									tmpEdge = tmpEdge.next();
								}
								tmpEdge.setNext(edge); //sets the last edge's next to the new edge created
							}
							if(adjacencyList[to] == null){ //add edge2 to head if that index is empty
								adjacencyList[to] = edge2;
							}else{
								Edge tmpEdge = adjacencyList[to]; //else add to the head
								while(tmpEdge.next() != null){
									tmpEdge = tmpEdge.next();
								}
								tmpEdge.setNext(edge2); //sets the last edge's next to the new edge created
							}
						}		
					}	
				}
				
			}catch(FileNotFoundException fnf) {
				System.out.println(fnf.getMessage());
			}
		}

	} // class MapGraph ------------------

	/**
	 * Compute minimum spanning tree for this graph using Prim's algorithm,
	 * starting with vertex =  startNode. 
	 * Add edges of MST to edgesMST list.
	 */
	public void computeMST(int startNode) {
		// FILL IN CODE
		PriorityQueue heap = new PriorityQueue(graph.nodes.length+1); //create min heap of nodes
		double[][] table = new double[graph.nodes.length][graph.nodes.length]; //create table storing the cost and parent of each node
		for(int j = 0; j < table.length; j++){ //set all node's costs to infinity and parents to -1
			table[j][0] = Double.POSITIVE_INFINITY;
			table[j][1] = -1;
		}
		heap.insert(0, 0); //insert starting node to heap
		for(int j = 1; j < graph.nodes.length; j++){ //add other nodes to heap setting there priority to max val
			heap.insert(j, Integer.MAX_VALUE);
		}
		for(int i = 0; i < graph.nodes.length; i++){ //Prim's algorithm 
			int min = heap.removeMin(); //remove element w/ lowest cost
			int[] positions = heap.getPositions(); //get positions to check if node is known
			int size = heap.size(); //get size to compare to position and check if node is known 
			for(Edge tmp = graph.adjacencyList[min]; tmp != null; tmp = tmp.next()){ //add costs from nodes that are in adjacencylist
				if((positions[tmp.getId2()] <= size) && (table[tmp.getId2()][0] > tmp.getCost())){ //change elem cost if it is not known and new cost is less
					heap.reduceKey(tmp.getId2(), tmp.getCost()); //reduce cost of elem
					table[tmp.getId2()][0] = tmp.getCost(); //update table
					table[tmp.getId2()][1] = min;
				}
			}
		}	
		for(int j = 0; j < table.length; j++){ //add all MSTedges to arraylist 
			Double d = table[j][1];
			int to = d.intValue();
			if(to != -1){
				Edge edge = new Edge(j, to, 0);
				edgesMST.add(edge);
			}	
		}
}

	// -------------------- Methods needed for GUIApp-------------------
	/**
	 * Used in GUIApp to display the MST. Returns a 2D Array, where each element
	 * represents an edge and is an array of two Points (where this edge starts
	 * and where it is going).
	 */
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

	/**
	 * Used in GUIApp to display the edges of the graph. Returns a 2D array that
	 * contains edge info that is easy for GUI to display. For each edge, we
	 * store an array of two Points, v1 and v2. v1 is the source vertex for this
	 * edge, v2 is the destination vertex. This info can be obtained from the
	 * adjacency list
	 */
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

	/**
	 * Used in GUIApp to display the nodes of the graph. Return a list of Points
	 * that correspond to nodes of the graph.
	 */
	public Point[] getNodes() {
		Point[] nodes = new Point[graph.nodes.length];
		// FILL IN CODE
		for(int i = 0; i < graph.nodes.length; i++){ //iterate through nodes array and get the location of each
			nodes[i] = graph.nodes[i].getLocation(); //add location of each node to the nodes array
		}
		return nodes;
	}

	/**
	 * Used in GUIApp to display the names of the airports. Return the list that
	 * contains the "names" of nodes (corresponding cities)
	 */
	public String[] getCities() {
		String[] labels = new String[graph.nodes.length];
		// FILL IN CODE
		for(int i = 0; i < graph.nodes.length; i++){ //iterate through the nodes array and get the City name
			labels[i] = graph.nodes[i].getCity(); //add city name to labels array
		}
		return labels;
	}

}
