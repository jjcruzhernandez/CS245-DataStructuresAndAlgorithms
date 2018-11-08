/** Class SuffixTree. Has methods to build a suffix tree for a given string and use it to find suffixes, substrings etc.  
 *  You may add additional helper methods (in fact, it is recommended), but may not change the signatures of the given methods. */
//import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
//import GeneralizedSuffixTree.Node;

public class SuffixTree {
	private Node root; // the root of the suffix tree
	private final static int ASCII = 97; // offset of letter 'a' in the ASCII table

	/** Constructor of SuffixTree. The root contains an empty string and index = -1 */
	public SuffixTree() {
		root = new Node("", -1);
	}

	/** Create a SuffixTree for a given string s.
	 *  Iterate backwards, and call insert method to insert each suffix into the tree. */
	public SuffixTree(String s) {
		String newS = s + "$"; // add $ to the end
		// Iterate backwards and insert into the suffix tree
		// FILL IN CODE
		insert("",-1);
		for(int i = newS.length()-1; i >= 0; i--){
			insert(newS.substring(i), i);
		}
	}

	// ------------------ Inner class: A node of the SuffixTree ------------------------
	private class Node {
		private String string; // a string stored at the node
		private Node[] children; // an array of children
		private int index; // an index in the original string where this suffix starts

		/** Constructor for Node */
		public Node(String string, int index) {
			children = new Node[27]; // 26 letters + "$"
			this.string = string;
			this.index = index;
		}
	}  // ------------------------------------------------------------------------------

	/** Insert a new suffix (that starts at index ind in the string) into the suffix tree */
	public void insert(String word, int ind) {
		root = insert(word.toLowerCase(), ind, root);
	}

	/**
	 * Insert a new suffix that starts at index = ind, into the suffix tree with
	 * the given root.
	 */
	private Node insert(String word, int ind, Node tree) {
		if (tree == null) {
			Node temp = new Node(word, ind);
			return temp;
		}
		if(tree.string.equals("") && tree.children[getChildIndex(word)] == null){
			tree.children[getChildIndex(word)] = insert(word, ind, tree.children[getChildIndex(word)]);
			return tree;
		}else if(tree.children[getChildIndex(word)] == null){
			String common = commonPrefix(word, tree.string);
			int index = tree.string.indexOf(common) + common.length();
			String newString = word.substring(index, word.length());
			tree.children[getChildIndex(word)] = insert(newString, ind, tree.children[getChildIndex(word)]);
			return tree;	
		}else if(tree.children[getChildIndex(word)].string.contains("$")){
			Node temp = tree.children[getChildIndex(word)];
			int tempIndex = tree.children[getChildIndex(word)].index;
			Node[] tempChildren = new Node[27];
			for(int i = 0; i < tree.children[getChildIndex(word)].children.length; i++){
				tempChildren[i] = tree.children[getChildIndex(word)].children[i];
			}
			String common = commonPrefix(word, temp.string);
			int index = temp.string.indexOf(common) + common.length();
			String newString = word.substring(index, word.length());
			String oldString = temp.string.substring(index, temp.string.length());
			tree.children[getChildIndex(word)] = null;
			tree.children[getChildIndex(word)] = insert(common, -1, tree.children[getChildIndex(word)]);
			Node temp2 = tree.children[getChildIndex(word)];
			Node newNode = new Node(newString, ind);
			Node oldNode = new Node(oldString, tempIndex);
			oldNode.children = tempChildren;
			temp2.children[getChildIndex(newString)] = newNode;
			temp2.children[getChildIndex(oldString)] = oldNode;
			return tree;
		}else{
			Node temp = tree.children[getChildIndex(word)];
			String common = commonPrefix(word, temp.string);
			int index = temp.string.indexOf(common) + common.length();
			String newString = word.substring(index, word.length());
			Node temp2 = insert(newString, ind, temp);
		}
		return tree;		
	}

	/** A helper method: returns the longest common prefix of word1 and word2.
	 *  Example: if word1 = "banana" and word2 = "band", the  longest common prefix is "ban". */
	private String commonPrefix(String word1, String word2) {
		// FILL IN CODE
		String common; 
		int index = 0;
		int done = 0;
		for(int i = 0; i < Math.min(word1.length(),  word2.length()); i++){
			if(i == 0){
				if(word1.charAt(i) == (word2.charAt(i))){
					index++;
				}	
			}else if(word1.charAt(i) == (word2.charAt(i)) && done == i-1){
				index++;
				done++;
			}
		}
		common = word1.substring(0,index);
		return common;
	}
	
	/** Return a suffix tree as a string in human readable form (using preorder traversal and indentations). For the root, 
	 * print "Root" instead of an empty string. See project description for details. */
	public String toString() {
		return toString(root, 0);
	}

	/**
	 * A private recursive method that returns the string representation of the suffix tree with the given root
	 * with i indentations. If i = 0, the value at the root should not be indented. 
	 * If i = 1, there should be one space printed before the value at the root.
	 * If i = 2, there should be two spaces etc..
	 * Hint: Use StringBuilder or StringBuffer (using "+" for concatenation is very slow).
	 */
	private String toString(Node tree, int i) {
		StringBuilder blr = new StringBuilder();
		// FILL IN CODE
		if(tree.string.equals("")){
			blr.append("Root");
		}
		for (int j = 0; j < i; j++){
			blr.append(" ");	
		}
		blr.append(tree.string);
		if(tree.index != -1){
			blr.append(": ");
			blr.append(tree.index);
		}
		blr.append(System.lineSeparator());
		for(int k = 0; k < tree.children.length; k++){
			if (tree.children[k] != null){
				blr.append(toString(tree.children[k], i+1));
			}	
		}
		return blr.toString();
	}
	
	/** Return an index in the array of children that corresponds to the first letter of the given word. If 
	 * the first letter is 'a', the method returns 0; if the first letter is 'b', the method returns 1 etc.
	 * '$' is handled separately: the index of the '$' child is 26 (the last one in the array).
	 * @param word
	 * @return
	 */
	public int getChildIndex(String word) {
		int childIndex = word.charAt(0) - ASCII;
		if (word.charAt(0) == '$')
			childIndex = 26;
		return childIndex;
	}

	
	/**
	 * Return true if a string represented by a given suffix tree contains a given
	 * word. Return false otherwise.
	 */
	public boolean containsSubstring(String word) {

		return containsSubstring(word, root);
	}
	
	/**
	 * Return true if a string represented by the suffix tree with the given root,
	 * contains a given word. Return false otherwise. 
	 * Should be recursive and make use of the suffix tree.
	 */
	private boolean containsSubstring(String word, Node tree) {
		// FILL IN CODE
		if (tree == null){
			System.out.println("tree == null");
			return false;
		}
		if(tree.string == "" && tree.children[getChildIndex(word)].string.contains(word)){
			return true;
		}else if(tree.children[getChildIndex(word)] == null){
			return false;
		}else if (word.contains(tree.children[getChildIndex(word)].string) && !tree.children[getChildIndex(word)].string.contains("$")){
			tree = tree.children[getChildIndex(word)];
			String common = commonPrefix(word, tree.string);
			int indexCommon = tree.string.indexOf(common) + common.length();
			word = word.substring(indexCommon, word.length());
			boolean inTree = containsSubstring(word, tree);
			return inTree;
		}else if(tree.children[getChildIndex(word)].string.contains(word)){
			return true;
		}
		return false;
	}
	
	/**
	 * Check if a string represented by a given suffix tree contains a given
	 * word, and if yes, return the list of indices where each occurrence of word starts. 
	 * Should be sorted in ascending order.
	 * Example: if the suffix tree is built for the word "banana" and we call this method on "ana",
	 * the method should return [1, 3]. 
	 */
	public List<Integer> getSubstringIndices(String word) {

		List<Integer> indicesOccurrences = getSubstringIndices(word, root);
		return indicesOccurrences;
	}

	/**
	 * Check if a string represented by the suffix tree with the given root,
	 * contains a given word. Return the List of indices where the
	 * substring occurrences start. Should be recursive and make use of the suffix tree.
	 */
	private List<Integer> getSubstringIndices(String word, Node tree) {
		List<Integer> indices = new ArrayList<Integer>();
		// FILL IN CODE
		if(tree == null){
			Collections.sort(indices);
			return indices;
		}
		if(tree.children[getChildIndex(word)] == null){
			Collections.sort(indices);
			return indices;
		}else if(tree.string == "" && tree.children[getChildIndex(word)].string.contains(word) && tree.children[getChildIndex(word)].string.contains("$")){
			indices.add(tree.children[getChildIndex(word)].index);
		}else if(tree.string == "" && tree.children[getChildIndex(word)].string.contains(word)){
			Node temp = tree.children[getChildIndex(word)];
			for(int i = 0; i < temp.children.length; i++){
				if(temp.children[i] != null){
					if(temp.children[i].index == -1){
						indices = getSubstringIndices(temp.children[i].string, temp);
					}else if(temp.children[i].string.contains("$")){
						indices.add(temp.children[i].index);
					}
				}	
			}
			Collections.sort(indices);
			return indices;
		}else if(tree.children[getChildIndex(word)].string.contains("$")){
			String temp = tree.children[getChildIndex(word)].string;
			int exact = 0;
			for(int i = 0; i <  Math.min(word.length(), temp.length()); i++){
				if(temp.charAt(i) == word.charAt(i)){
					exact++;
				}
			}
			if(exact == word.length()){
				indices.add(tree.children[getChildIndex(word)].index);
			}
			Collections.sort(indices);
			return indices;
		}else if(tree.children[getChildIndex(word)].string.equals(word)){
			Node temp = tree.children[getChildIndex(word)];
			for(int i = 0; i < temp.children.length; i++){
				if(temp.children[i] != null){
					indices.add(temp.children[i].index);
				}	
			}
			Collections.sort(indices);
			return indices;
		}else{
			tree = tree.children[getChildIndex(word)];
			String common = commonPrefix(word, tree.string);
			int indexCommon = tree.string.indexOf(common) + common.length();
			word = word.substring(indexCommon, word.length());
			indices = getSubstringIndices(word, tree);	
		}
		Collections.sort(indices);
		return indices; 
	}
	
	/**
	 * Return the number of occurrences of a given word in the string, represented by the suffix tree
	 */
	public int numOccurrences(String word) {
		// FILL IN CODE
		// Hint: you can call getSubstringIndices method you wrote above
		List<Integer> indices = new ArrayList<Integer>();
		indices = getSubstringIndices(word);
		return indices.size(); // // don't forget to change
	}
	
	
	/** If the suffix tree contains a given suffix, return the index where it starts in the original string, 
	 * otherwise return -1.  */
	public int containsSuffix(String suffix) {
		return containsSuffix(suffix, root);
		
	}

	/** If a given suffix tree contains a given suffix, return its' index, otherwise return -1. 
	 * Should be recursive and make use of the suffix tree. */
	private int containsSuffix(String suffix, Node tree) {
		// FILL IN CODE
		if (tree == null){
			System.out.println("tree == null");
			return -1;
		}
		if(tree.string == "" && tree.children[getChildIndex(suffix)].string.equals(suffix)){
			return tree.children[getChildIndex(suffix)].index;
		}else if(tree.children[getChildIndex(suffix)] == null){
			return -1;
		}else if (suffix.contains(tree.children[getChildIndex(suffix)].string) && !tree.children[getChildIndex(suffix)].string.contains("$")){
			tree = tree.children[getChildIndex(suffix)];
			String common = commonPrefix(suffix, tree.string);
			int indexCommon = tree.string.indexOf(common) + common.length();
			suffix = suffix.substring(indexCommon, suffix.length());
			int i = containsSuffix(suffix, tree);
			return i;
		}else if(tree.children[getChildIndex(suffix)].string.equals(suffix)){
			return tree.children[getChildIndex(suffix)].index;
		}
		return -1;
	}
}