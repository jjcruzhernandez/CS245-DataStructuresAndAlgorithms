/**
 * A custom linked list that stores user info. Each node in the list is of type
 * UserNode.
 * 
 * @author okarpenko
 *
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;

public class UsersList {
	private UserNode head = null; // head of the list
	private UserNode tail = null; // tail of the list

	/** A default constructor */
	public UsersList() {
	}

	/** A getter for the head */
	public UserNode head() {
		return head;
	}

	/** A getter for the tail */
	public UserNode tail() {
		return tail;
	}

	/** Add a new node to the end of list */
	public void append(UserNode newNode) {
		// FILL IN CODE
		if (head == null){
			head = tail = newNode;
		}else{
			tail.setNext(newNode);
			tail = newNode;
		}
	}

	/** Return a UserNode that contains a given userId */
	public UserNode get(int userId) {
		// FILL IN CODE
		UserNode temp = head;
		while(temp.getId() != userId){
			temp = temp.next();
		}
		return temp;
	}

	/**
	 * Read user ratings from the file and save data for each user in this list.
	 * For each user, the movie ratings list will be sorted by rating (from
	 * largest to smallest).
	 */
	public void loadUserInfo(String filename) {
		// FILL IN CODE
		File inputFile = new File(filename);
		
		try(Scanner input = new Scanner(inputFile)) {
			while(input.hasNext()) {
				String firstLine = input.nextLine();
				String[] line = firstLine.split(" ");
				int id = Integer.parseInt(line[0]);
				//System.out.println(id);
				MovieRatingsList movieList = new MovieRatingsList();
				int numMovie = Integer.parseInt(line[1]);
				for(int i = 0; i < numMovie; i++){
					String movie = input.nextLine();
					String[] movieLine = movie.split("; ");
					String title = movieLine[0];
					int rating = Integer.parseInt(movieLine[1]);	
					movieList.insertByRating(title, rating);
				}
				UserNode userNode = new UserNode(id, movieList);
				append(userNode);
				if(input.hasNext()){
					String blankLine = input.nextLine();
				}	
			}
			
		}catch(FileNotFoundException fnf) {
			System.out.println(fnf.getMessage());
		}
	}

	/**
	 * Compute the similarity between the user with the given userId and all the
	 * other users. Finds the maximum similarity and returns the
	 * "most similar user".
	 */
	public UserNode findMostSimilarUser(int userid) {
		// FILL IN CODE
		UserNode temp = head();
		while(temp.getId() != userid && temp.next() != null){
			temp = temp.next();
		}
		double max = 0.0;
		UserNode simUser = head();
		UserNode temp2 = head();
		while(temp2.next() != null){
			if (temp2 != temp){
				double sim = temp2.computeSimilarity(temp);
				if(sim > max){
					max = sim;
					simUser = temp2;
				}
			}
			temp2 = temp2.next();
		}
		return simUser; // change

	}

	/**
	 * Compute up to num movie recommendations for the user with the given user
	 * id and print these movie titles to the file with the given name. First
	 * calls findMostSimilarUser and then getFavoriteMovies(num) method on the
	 * "most similar user" to get up to num recommendations.
	 */
	public void findRecommendations(int userid, int num, String filename) {
		// -Compute similarity between userid and all the other users
		// -Find the most similar user and recommend movies that the most similar
		// user rated as 5. 
		// - Recommend only the movies that userid has not seen (has not
		// rated).

		// FILL IN CODE
		String[] recommendations = new String[num];
		UserNode temp = head();
		while(temp.getId() != userid && temp.next() != null){
			temp = temp.next();
		}
		UserNode simUser = findMostSimilarUser(userid);
		String[] simUserFavMovies = simUser.getFavoriteMovies(num);
		String[] tempMovies= temp.getMovies();
		int index = 0;
		for(int i = 0; i < simUserFavMovies.length; i++){
			int tempHasSeen = 0;
			for(int j = 0; j < tempMovies.length; j++){
				if (simUserFavMovies[i].equals(tempMovies[j])){
					tempHasSeen ++;
				}	
			}
			if(tempHasSeen == 0){
				recommendations[index] = simUserFavMovies[i];
				index++;
			}

		}

			File file = new File(filename);
			try (PrintWriter printWriter = new PrintWriter (file)){ 
				for(int n = 0; n < recommendations.length; n++){
					if(recommendations[n] != null){
						printWriter.println (recommendations[n]);
					}
				}
				printWriter.close ();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	

	/**
	 * Compute up to num movie anti-recommendations for the user with the given
	 * user id and print these movie titles to the given file. These are the
	 * movies the user should avoid. First calls findMostSimilarUser and then
	 * getLeastFavoriteMovies(num) method on the "most similar user" to get up
	 * to num movies the most similar user strongly disliked.
	 */
	public void findAntiRecommendations(int userid, int num, String filename) {
		// FILL IN CODE
		String[] AntiRecommendations = new String[num];
		UserNode temp = head();
		while(temp.getId() != userid && temp.next() != null){
			temp = temp.next();
		}
		UserNode simUser = findMostSimilarUser(userid);
		String[] simUserLeastMovies = simUser.getLeastFavoriteMovies(num);
		String[] tempMovies= temp.getMovies();
		int index = 0;
		for(int i = 0; i < simUserLeastMovies.length; i++){
			int tempHasSeen = 0;
			for(int j = 0; j < tempMovies.length; j++){
				if (simUserLeastMovies[i].equals(tempMovies[j])){
					tempHasSeen ++;
				}	
			}
			if(tempHasSeen == 0){
				AntiRecommendations[index] = simUserLeastMovies[i];
				index++;
			}

		}

			File file = new File(filename);
			try (PrintWriter printWriter = new PrintWriter (file)){ 
				for(int n = 0; n < AntiRecommendations.length; n++){
					if(AntiRecommendations[n] != null){
						printWriter.println (AntiRecommendations[n]);
					}
				}
				printWriter.close ();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	/**
	 * Return an array that contains the highest rated movie for each user.
	 * Since the MovieRatingsList is sorted from highest rated movie to lowest, to
	 * implement this method, you just need to traverse the list of user nodes,
	 * and for each user, get the first movie from their ratings list.
	 * 
	 * @return
	 */
	public String[] findFavoriteMoviesForAll() {
		// FILL IN CODE
		UserNode temp = head();
		int count = 0;
		while(temp != null){
			count++;
			temp = temp.next();
		}
		String[] fav = new String[count];
		UserNode temp2 = head();
		int i = 0;
		while(temp2 != null){
			fav[i] = temp2.getMovies()[0];
			temp2 = temp2.next();
			i++;
		}
		return fav;

	}

}