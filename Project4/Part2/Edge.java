/**
 * Edge class represents an edge in the adjacency list of the graph. Implements
 * Comparable. Compares Edges based on the cost.
 */
class Edge implements Comparable<Edge> {
	// FILL IN CODE: add variables and a constructor
    private int id1; //source vertex
    private int id2; //destination vertex 
	private int cost; //cost from source to destination vertex
	private Edge next; //Edge's next 
	
	//constructor of Edge takes in id1 (id of the starting city), id2 (id of the end city) and cost (the cost of the edge)
	Edge(int id1, int id2, int cost){
		// FILL IN CODE
		this.id1 = id1;
		this.id2 = id2;
		this.cost = cost;
	}

	// FILL IN: getters and setters as needed
	//method that gets next
	public Edge next(){
		return next;
	}
	
	//method that sets next
	public void setNext(Edge edge){
		next = edge;
	}
	
	//method that gets the id of the first city
	public int getId1(){
		return id1;
	}
	
	//method that gets the id of the second city
	public int getId2(){
		return id2;
	}
	
	//method that gets the cost of the edge 
	public int getCost(){
		return cost;
	}
	
	//method overriding the compareTo method of Edge
	@Override
	public int compareTo(Edge o) {
		// FILL IN CODE
		//returns the comparison of edges by cost
		return Integer.compare(this.cost, o.cost);
	}

}