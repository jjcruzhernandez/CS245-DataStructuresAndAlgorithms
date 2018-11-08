/** A class that stores a generalizes suffix tree for a given list of strings and a given  list of terminal markers. */

import java.util.ArrayList;

//import SuffixTree.Node;

public class GeneralizedSuffixTree {
	private final static int ASCII = 97;
	private Node root; // the root of the generalized suffix tree
	private ArrayList<String> markers = null; // a list of markers to use after each string
	private ArrayList<String> strings = null; // a list of strings for this generalized suffix tree  - you do not have to use it
	private String concatString = null; // The string that is "all strings concatenated into one"
	
	/** Create a GeneralizedSuffixTree for a given list of strings, with the given terminal markers.
	 *  Size of the markers array should be the same as the strings array */
	public GeneralizedSuffixTree(ArrayList<String> strings, ArrayList<String> markers) {
		// FILL IN CODE
		// Concatenate all strings into one, separated by markers given in the array
		// Iterate backwards over the suffixes of the concatString and call insert on each suffix
		//System.out.println(markers.size());
		this.strings = strings;
		this.markers = markers;
		String word = "";
		for(int i = 0; i< strings.size(); i++){
			word = word + strings.get(i) + markers.get(i);
		}
		insert("",-1);
		for(int i = word.length()-1; i >= 0; i--){
			insert(word.substring(i), i);
		}
	}

	// ------------------ A node of the GeneralizedSuffixTree ------------------------
	private class Node {
		private String string;
		private Node[] children;
		private int index;
		private int numMarkers;
		// private int depth = 0; // you are not required to use it, but might find useful
		
		public Node(String string, int index, int numMarkers) {
			children = new Node[numMarkers + 26]; // 26 letters + however many markers we have
			this.string = string;
			this.index = index;
		}

	} // -----------------------------------------------------------------------

	/** Insert a given suffix into the tree (the suffix starts at index = ind in the concatString */
	public void insert(String word, int ind) {
		root = insert(word.toLowerCase(), ind, root);
	}
	
	
	/**
	 * Insert a new suffix that starts at index = ind, into the suffix tree with the given root
	 */
	private Node insert(String word, int ind, Node tree) {
		if (tree == null) {
			//System.out.println("Created a new node with " + word);
			Node temp = new Node(word, ind, markers.size());
			return temp;
		}	
		 //FILL IN CODE
		if(tree.string.equals("") && tree.children[getChildIndex(word)] == null){
			tree.children[getChildIndex(word)] = insert(word, ind, tree.children[getChildIndex(word)]);
			return tree;
		}else if(tree.children[getChildIndex(word)] == null && !tree.string.endsWith(markers.get(markers.size()-1))){
			String common = commonPrefix(word, tree.string);
			int index = tree.string.indexOf(common) + common.length();
			String newString = word.substring(index, word.length());
			if(!common.equals("") && tree.children[getChildIndex(newString)] ==null){
				Node commonNode = new Node(common, -1, markers.size());
				tree.children[getChildIndex(common)] = insert(newString, ind, commonNode);
			}else{
				tree.children[getChildIndex(word)] = insert(word, ind, tree.children[getChildIndex(word)]);
			}
			return tree;
		}else if(tree.children[getChildIndex(word)] == null && tree.string.endsWith(markers.get(markers.size()-1))){
			String common = commonPrefix(word, tree.string);
			int index = tree.string.indexOf(common) + common.length();
			String newString = word.substring(index, word.length());
			tree.children[getChildIndex(word)] = insert(newString, ind, tree.children[getChildIndex(word)]);
			return tree;
		//} else if(tree.children[getChildIndex(word)].string.contains(markers.get(markers.size()-1)) && !commonPrefix(word, tree.children[getChildIndex(word)].string).equals("")){
		} else if(tree.children[getChildIndex(word)].string.endsWith(markers.get(markers.size()-1)) && !commonPrefix(word, tree.children[getChildIndex(word)].string).equals("")){	
			Node temp = tree.children[getChildIndex(word)];
			int tempIndex = tree.children[getChildIndex(word)].index;
			Node[] tempChildren = new Node[markers.size() + 26];
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
			Node newNode = new Node(newString, ind, markers.size());
			Node oldNode = new Node(oldString, tempIndex, markers.size());
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
		return common; // don't forget to change it
	}

	
	/** Return a suffix tree as a string in human readable form (using preorder traversal and indentations) */
	public String toString() {
		return toString(root, 0);
	}

	/**
	 * A private recursive method that returns the string representation of the suffix tree with the given root
	 * with i indentations.
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
	
	
	/** Return the index in the children array that corresponds to the first letter of the given word. If 
	 * the first letter is 'a', the method returns 0; if the first letter is 'b', the method returns 1 etc.
	 * Markers are handled separately: the index of the first marker child is 26 (the last one in the array),
	 * the index of the second marker child is 27, etc..
	 * @param word
	 * @return
	 */
	public int getChildIndex(String word) {
		int childIndex = word.charAt(0) - ASCII;
		String s = Character.toString(word.charAt(0));
		int i = markers.indexOf(s);
		if (i != -1 ) // one of the markers
			childIndex = 26 + i;
		return childIndex;
	}

	//returns array of non null children indexes that have children
	public ArrayList<Integer> numChildren(Node tree){
		ArrayList<Integer> numChildren = new ArrayList<Integer>();
		for(int i = 0; i < tree.children.length; i++){
			if(tree.children[i] != null){
				numChildren.add(i);
			}
		}
		return numChildren;
	}
	
	//returns true or false if tree has children
	public boolean hasChildren(Node tree){
		int num = 0;
		for(int i = 0; i < tree.children.length; i++){
			if(tree.children[i] != null){
				num++;
			}
		}
		if(num == 0){
			return false;
		}
		return true;
	}
	
	/** Return an ArrayList of common longest substrings of the strings in the generalized suffix tree */
	public ArrayList<String> findLCS() {
		ArrayList<String> lcsStrings = new ArrayList<String>();
		// FILL IN CODE
		lcsStrings = findLCS(root, "");
		int len = 0;
		for(int i = 0; i < lcsStrings.size(); i++){
			if(lcsStrings.get(i).length() > len){
				len = lcsStrings.get(i).length();
			}
		}
		ArrayList<String> lcsStringsFinal = new ArrayList<String>();
		for(int j = 0; j < lcsStrings.size(); j++){
			if(lcsStrings.get(j).length() == len){
				lcsStringsFinal.add(lcsStrings.get(j));
			}
		}
		return lcsStringsFinal;
	}
	
	private ArrayList<String> findLCS(Node tree, String substring) {
		ArrayList<String> lcsStrings = new ArrayList<String>();
		if(tree.string.endsWith(markers.get(markers.size()-1))){
			lcsStrings.add(substring);
			return lcsStrings;
		}
		
		ArrayList<Integer> numTreeChildren = numChildren(tree);
		for(int i = 0; i < numTreeChildren.size(); i++){
			if(tree.children[numTreeChildren.get(i)] != null){
				ArrayList<Integer> numChildren = numChildren(tree.children[numTreeChildren.get(i)]);
				if(numChildren.size() == 0){
					ArrayList<String> tempSubstring = findLCS(tree.children[numTreeChildren.get(i)], substring);
					if(tempSubstring.size() != 0){
						lcsStrings.add(tempSubstring.get(0));
					}	
				}
			}else if(!tree.children[numTreeChildren.get(i)].string.endsWith(markers.get(markers.size()-1))){
					substring = substring + tree.children[numTreeChildren.get(i)].string;
					ArrayList<String> tempSubstring2 = findLCS(tree.children[numTreeChildren.get(i)], substring);
					if(tempSubstring2.size() != 0){
						lcsStrings.add(tempSubstring2.get(0));
					}
			}
		}
		return lcsStrings;
	}	
}		