/**
 * A class that stores movie ratings for a user in a custom singly linked list of
 * MovieRatingNode objects. Has various methods to manipulate the list. Stores
 * only the head of the list (no tail!). The list should be sorted by
 * rating (from highest to smallest).
 */
import java.util.Iterator;
import java.util.HashMap;

public class MovieRatingsList implements Iterable<MovieRatingNode> {

	// FILL IN CODE: store the head of the list
	MovieRatingNode head = null;
	
	/** A getter for the head variable */
	public MovieRatingNode head() {
		// FILL IN CODE
		return head; // change to return the head
	}

	/**
	 * Return the reference to the node that contains the given movie title or
	 * null if such node does not exit.
	 * 
	 * @param movieTitle
	 * @return
	 */
	public MovieRatingNode find(String movieTitle) {
		// FILL IN CODE
		MovieRatingNode temp = head();
		while(temp.getMovieTitle() != movieTitle){
				if (temp.next() != null){ //diff temp!= null
					temp = temp.next();
				}else{
					return null;
				}
		}	
		return temp; // change it
	}

	/**
	 * Return the rating for a given movie. If the movie is not in the list,
	 * return -1.
	 */
	public int getRating(String movieTitle) {
		// FILL IN CODE
		MovieRatingNode temp = head();
		while(temp != null){
			if (temp.getMovieTitle() != movieTitle){
				temp = temp.next();
			}else{
				return temp.getMovieRating();
			}
		}
		return -1; // change

	}

	/**
	 * Create a new node with the given movie and rating, and appends it at the
	 * end of the array
	 * 
	 * @param movie
	 * @param rating
	 */
	public void append(String movie, int rating) {
		// FILL IN CODE
		MovieRatingNode movieNode = new MovieRatingNode(movie, rating);
		if (head == null){
			head = movieNode;
		}else{
			MovieRatingNode current = head;
			while(current.next() != null) {
				current = current.next();
			}
			current.setNext(movieNode);
		}
		
	}

	/**
	 * Insert a new node (with a given movie and a given rating) into the list.
	 * Insert it in the right place based on the value of the rating. Assume the
	 * list is sorted by the value of ratings, from highest to smallest. The
	 * list should remain sorted after this insert operation.
	 */
	public void insertByRating(String movie, int rating) {
		// insert a node into the sorted list
		// FILL IN CODE
		MovieRatingNode movieNode = new MovieRatingNode(movie, rating);
		if (head == null){
			head =  movieNode;

		}else if(head.next() == null && (movieNode.getMovieRating() > head.getMovieRating())){
			movieNode.setNext(head);
			head = movieNode;
		}else if(head.next() == null && (movieNode.getMovieRating() <= head.getMovieRating())){
			head.setNext(movieNode);
		}else{
			MovieRatingNode prev = head;
			MovieRatingNode next = head.next();
			while(next != null && (next.getMovieRating() > movieNode.getMovieRating())){
				prev = next;
				next = next.next();
			}
			if(prev == head && prev.getMovieRating() <= movieNode.getMovieRating()){
				movieNode.setNext(head);
				head = movieNode;
			}else{
				movieNode.setNext(next);
				prev.setNext(movieNode);
			}	
		}

	}

	/**
	 * Change the rating for a given movie to newRating.The position of the node
	 * within the list should be changed accordingly, so that the list remains
	 * sorted by rating (from largest to smallest).
	 * 
	 * @param movieTitle
	 * @param newRating
	 */
	public void setRating(String movieTitle, int newRating) { //if movie not in list?
		// FILL IN CODE
		MovieRatingNode temp = head();
		MovieRatingNode temp2 = head();
		while(temp.getMovieTitle() != movieTitle){
			temp = temp.next();
			if(temp2.next().getMovieTitle() != movieTitle){
				temp2 = temp2.next();
			}//deleting
		}
		temp2.setNext(temp.next());
		insertByRating(movieTitle, newRating);	
	}

	/**
	 * Compute the cosine similarity between two lists of ratings. Note: You are
	 * allowed to use a HashMap for this method.
	 */
	public double computeSimilarity(MovieRatingsList otherList) {
		// FILL IN CODE
		int dotProd = 0;
		HashMap<String, Integer> tempHM = new HashMap<String, Integer>();
		MovieRatingNode temp = head();
		int tempLength = 0;
		while(temp != null){ // temp v temp.next()?
			tempHM.put(temp.getMovieTitle(), temp.getMovieRating());
			tempLength = tempLength + (temp.getMovieRating()*temp.getMovieRating());
			temp = temp.next();
		}
		double finalTempLength = Math.sqrt(tempLength);
		int temp2Length = 0;
		MovieRatingNode temp2 = otherList.head();
		while(temp2 != null){
			if(tempHM.containsKey(temp2.getMovieTitle()) == true){
				dotProd = dotProd + (tempHM.get(temp2.getMovieTitle())*temp2.getMovieRating());
			}
			temp2Length = temp2Length + (temp2.getMovieRating()*temp2.getMovieRating());
			temp2 = temp2.next();
		}
		double finalTemp2Length = Math.sqrt(temp2Length);
		double prodLengths = finalTempLength*finalTemp2Length;
		double similarity = dotProd/prodLengths;
		return similarity; // change it

	}

	/**
	 * Return a sublist of this list where the rating values are in the range
	 * from begRating to endRating, inclusive.
	 */
	public MovieRatingsList sublist(int begRating, int endRating) {
		MovieRatingsList res = new MovieRatingsList();
		// FILL IN CODE
		MovieRatingNode temp = head();
		while(temp != null){
			if(temp.getMovieRating() == begRating){
				res.append(temp.getMovieTitle(), temp.getMovieRating());
			}else{
				int i = begRating+1;
				while(i != endRating+1){
					if(temp.getMovieRating() == i){
						res.append(temp.getMovieTitle(), temp.getMovieRating());
					}
					i++;
				}
				
			}
			temp = temp.next();
		}
		return res;
	}

	/**
	 * Return an array of movie titles from the ratings list. The movie title
	 * should be in the array in the same order in which they were in the
	 * ratings list. (ordered by ratings, not titles)
	 */
	public String[] getTitles() {
		MovieRatingNode temp = head();
		int count = 0;
		while(temp != null){
			count++;
			temp = temp.next();
		}
		String[] titles = new String[count];
		// FILL IN CODE
		MovieRatingNode temp2 = head();
		for(int i = 0; i < count; i++){
			titles[i] = temp2.getMovieTitle();
			temp2 = temp2.next();
		}
		return titles;
	}

	/** Traverse the list and print info about each node */
	public void print() {
		// FILL IN CODE
		MovieRatingNode temp = head();
		while(temp != null){
			System.out.println(temp.toString());
			temp = temp.next();
		}

	}

	/**
	 * Returns the middle node in the list - the one half way into the list.
	 * Needs to have the running time O(n), should be done in one pass. Hint:
	 * Use slow and fast pointers.
	 * 
	 * @return
	 */
	public MovieRatingNode getMiddleNode() {
		// FILL IN CODE
		MovieRatingNode fast = head();
		MovieRatingNode slow = head();
		while(fast.next() != null){ 	
			fast = fast.next().next();
			slow = slow.next();
		}
		return slow; // change it
	}

	/**
	 * Return the median rating (the number that is halfway into the sorted
	 * list). To compute it, find the middle node and return it's rating. If the
	 * middle node is null, return -1.
	 */
	public int getMedianRating() { //assuming an odd num list?
		// FILL IN CODE
		return getMiddleNode().getMovieRating(); // change it
	}

	/**
	 * Return a MovieRatingsList that contains n best rated movies. These are
	 * the first n movies from the beginning of the list. If the list is
	 * shorter than size n, it will return the whole list.
	 */
	public MovieRatingsList getNBestRankedMovies(int n) {
		MovieRatingsList result = new MovieRatingsList();
		// FILL IN CODE
		int count = 0;
		MovieRatingNode temp = head();
		while(temp.next() != null){
			count++;
			temp = temp.next();
		}
		if(n == count){
			MovieRatingNode temp2 = head();
			while(temp2.next() != null){
				result.append(temp2.getMovieTitle(), temp2.getMovieRating());
				temp2 = temp2.next();
			}
			return result; 
		}
		MovieRatingNode temp3 = head();
		int i = 0;
		while(i < n){
			result.append(temp3.getMovieTitle(), temp3.getMovieRating());
			temp3 = temp3.next();
			i++;	
		}
		return result;
	}

	/**
	 * Return a MovieRatingsList that contains n worst rated movies for this user.
	 * These are the last n movies from the end of the list. Use slow & fast
	 * pointers to find the n-th node from the end (required). Note: This method
	 * should compute the result in one pass. Do NOT use reverse().
	 */
	public MovieRatingsList getNWorstRankedMovies(int n) {
		MovieRatingsList result = new MovieRatingsList();
		// FILL IN CODE
		MovieRatingNode fast = head();
		MovieRatingNode slow = head().next();
		int i = 0;
		while(i < n){
			fast = fast.next();
			i++;
		}
		while(fast.next() != null){ 	
			fast = fast.next();
			slow = slow.next();
		}
		while(slow != null){
			result.append(slow.getMovieTitle(), slow.getMovieRating());
			slow = slow.next();
		}
		return result; // change it
	}

	/**
	 * Return a new list that is the reverse of the original list.
	 */
	public MovieRatingsList reverse(MovieRatingNode h) { 
		MovieRatingsList r = new MovieRatingsList();
		// FILL IN CODE
		if(head.next() == null){
			r.append(head.getMovieTitle(), head.getMovieRating());
			return r;
		}
		MovieRatingNode temp = head();
		head = head.next();
		r = reverse(h);
		head = temp;
		r.append(head.getMovieTitle(), head.getMovieRating());
		return r;
	}

	/** Return an iterator that allows to traverse the list */
	@Override
	public Iterator<MovieRatingNode> iterator() {
		return new RatingsListIterator(0);
	}

	/**
	 * The inner class that represents the iterator for the movie ratings list.
	 * Iterates over the MovieRatingNode-s of the list.
	 */
	private class RatingsListIterator implements Iterator<MovieRatingNode> {

		MovieRatingNode curr = null;

		public RatingsListIterator(int index) {
			// FILL IN CODE
			curr = head;
			
			for (int i = 0; i < index; i++) {
				curr = curr.next();
			}
		}

		@Override
		public boolean hasNext() {
			// FILL IN CODE
			return curr!= null;
		}

		@Override
		public MovieRatingNode next() {
			// FILL IN CODE
			if (!hasNext()) {
				System.out.println("No next element");
				return null;
			}
			MovieRatingNode old = curr;
			curr = curr.next();
			return old;
		}

	}

}