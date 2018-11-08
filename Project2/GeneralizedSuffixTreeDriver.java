import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/** The driver class for GeneralizedSuffixTree */
public class GeneralizedSuffixTreeDriver {
	public static void main(String[] args) {
		testLongestPalindrome("banana");
		testLCS("inputStringsForGeneralizedSuffixTree", "lcsResults");

	}

	/**
	 * A method that uses a generalized suffix tree to find the longest common
	 * palindrome in a given string
	 */
	public static void testLongestPalindrome(String s) {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(s);
		String reverse = "";
		for (int i = s.length() - 1; i >= 0; i--)
			reverse += s.charAt(i);
		arr.add(reverse);

		ArrayList<String> markers = new ArrayList<String>();
		markers.add("#");
		markers.add("$");

		GeneralizedSuffixTree tree = new GeneralizedSuffixTree(arr, markers);
		ArrayList<String> lcs = tree.findLCS();
		System.out.println("Longest palindrome(s): " + lcs);
	}

	/**
	 * Read a list of strings and a list of markers from the file. Build a
	 * generalized suffix tree. Invoke findLCS method. Print output to the file.
	 * Repeat: Read another set of strings&markers, build a tree, call findLCS
	 * method etc.
	 */
	public static void testLCS(String inputFile, String outputFile) {
		// FILL IN CODE 
		// Repeat until the end of file:
		File outputFilex = new File(outputFile);
		try (PrintWriter printWriter = new PrintWriter (outputFilex)){ 
			File inputFilex = new File(inputFile);
			try(Scanner input = new Scanner(inputFilex)) {
				while(input.hasNext()) {
					// Read a line with strings, strings are separated by comma space
					String words = input.nextLine();
					String[] wordsList = words.split(", ");
					ArrayList<String> strings = new ArrayList<String>();
					for(int i = 0; i < wordsList.length; i++){
						strings.add(wordsList[i]);
					}
					
					// read a line with markers (separated by comma space)
					String markers = input.nextLine();
					String[] markersList = markers.split(", ");
					ArrayList<String> markersArray = new ArrayList<String>();
					for(int i = 0; i < markersList.length; i++){
						markersArray.add(markersList[i]);
					}
					
					// Build a generalized suffix tree
					GeneralizedSuffixTree tree = new GeneralizedSuffixTree(strings, markersArray);
					printWriter.println(tree.toString());
					
					// call findLCS
					printWriter.println(tree.findLCS());
					
					// output results to the file
					if(input.hasNext()){
						String blankLine = input.nextLine();
					}
					printWriter.println();
				}
			}catch(FileNotFoundException fnf) {
				System.out.println(fnf.getMessage());
			}
			printWriter.close ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
