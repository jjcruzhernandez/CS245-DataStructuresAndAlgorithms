import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/** The driver class for SuffixTree */
public class SuffixTreeDriver {
	public static void main(String[] args) {
		processStrings("inputStringsSimple");
	}

	/** Process strings from the given file. See comments inside the method */
	public static void processStrings(String filename) {
		// FILL IN CODE 
		// Read each string from the file, construct a suffix tree
		File myResults = new File("myResults");
		try (PrintWriter printWriter = new PrintWriter (myResults)){ 
			File inputFile = new File(filename);
			try(Scanner input = new Scanner(inputFile)) {
				while(input.hasNext()) {
					// First, write the suffix tree
					// Then empty line
					String word = input.nextLine();
					//SuffixTree tree = new SuffixTree(word);
					SuffixTree tree = new SuffixTree(word);
					printWriter.println(tree.toString());
				
					// Then read valid suffixes, call containsSuffix for each of them, and
					// write the results to the file
					String validSuffixes = input.nextLine();
					String[] vSuffix = validSuffixes.split(", ");
					String indexString = "";
					for(int i = 0; i < vSuffix.length; i++){
						indexString = indexString + String.valueOf(tree.containsSuffix(vSuffix[i])) + " ";
					}
					printWriter.println(indexString);
					
					// Read invalid suffixes, call containsSuffix for each of them, and
					// write the results to the file (should all be -1)
					String invalidSuffixes = input.nextLine();
					String[] iSuffix = invalidSuffixes.split(", ");
					String indexString2 = "";
					for(int i = 0; i < iSuffix.length; i++){
						indexString2 = indexString2 + String.valueOf(tree.containsSuffix(iSuffix[i])) + " ";
					}
					printWriter.println(indexString2);
				
					// Read valid substrings, call getSubstringIndices and write results to
					// the file
					String validSubstrings = input.nextLine();
					String[] vSubstrings = validSubstrings.split(", ");
					String indexList = "";
					for(int i = 0; i < vSubstrings.length; i++){
						indexList = indexList + String.valueOf(tree.getSubstringIndices(vSubstrings[i])) + " ";
					}
					printWriter.println(indexList);
					
					// Read invalid substrings, call getSubstringIndices and write results
					// to the file (should all be [])
					String invalidSubstrings = input.nextLine();
					String[] iSubstrings = invalidSubstrings.split(", ");
					String indexList2 = "";
					for(int i = 0; i < iSubstrings.length; i++){
						indexList2 = indexList2 + String.valueOf(tree.getSubstringIndices(iSubstrings[i])) + " ";
					}
					printWriter.println(indexList2);
					// read one empty line
					// repeat for the next string in the file
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
