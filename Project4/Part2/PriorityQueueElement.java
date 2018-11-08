/** A class that represents an "entry" in the priority queue.
 * 	Implements Comparable (comparing by priority).
    PriorityQueueElement stores node id for a particular node in the graph 
    and priority of this node. Priority is the "cost" of the vertex from 
    the table used in Prim's algorithm. 
 */
public class PriorityQueueElement implements Comparable<PriorityQueueElement>{
		private int nodeId; //element nodeId
		private int priority; //element's cost

		//constructor taking in priority and nodeId
		public PriorityQueueElement(int priority, int nodeId) {
			this.nodeId = nodeId;
			this.priority = priority;
		}

		//method that returns cost 
		public int getPriority(){
			return priority;
		}
		
		//method that returns nodeId
		public int getNodeId(){
			return nodeId;
		}
		
		//method that changes element's cost
		public void setPriority(int cost){
			priority = cost;
		}
		
		//method overriding the elem's compareTo method to compare by cost
		@Override
		public int compareTo(PriorityQueueElement o) {
			// FILL IN CODE
			return Integer.compare(this.priority, o.priority);
		}
}
