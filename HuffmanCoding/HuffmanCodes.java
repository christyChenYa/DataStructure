package lab08;

import java.util.PriorityQueue;

import java.util.Scanner;
import java.util.Stack;

import lab08.CS232LinkedBinaryTree.BTNode;

/**
 * This is a Huffman coding algorithm, a loseless data compression scheme. Given a text, we will
 * compress it for you. 
 * 
 * There are four intermediate results to display: 
 * F: Display the frequency count for the characters in the text.
 * T: Display the key,value pairs of the nodes in the final tree in level order.
 * H: Display the Huffman Code table for the characters in the text.
 * M: The original text encoded using the Huffman Code.
 * 
 * 
 * 
 * @author Yanxiu Chen & Max Barrett
 * @author Dickinson College
 * @version April 11th, 2018 
 */
public class HuffmanCodes {
	private static String outputFormat;
	private String input;
	private String storeInput;
	private int[] nodes;
	private static CS232ArrayHeap<huffmanTree, CS232LinkedBinaryTree<Integer, Character>> queue;
	private String[] huffmanCodes;

	/**
	 * 
	 * create trees to store letters
	 * 
	 */
	public static class huffmanTree extends CS232LinkedBinaryTree<Integer, Character>
	implements Comparable<huffmanTree> {

		private int key;
		private char value;
		/*
		 * 
		 * Construct a tree. A single node binary tree (i.e. only a root node) is
		 * created for each letter,
		 * 
		 * with the letter as the value and the frequency as the key.
		 * 
		 */

		public huffmanTree(Integer key, Character value) {
			this.key = key;
			this.value = value;
		}

		//returns frequency
		public int getKey() {
			return key;
		}

		//returns key
		public char getValue() {
			return value;
		}

		@Override
		public int compareTo(huffmanTree o) {
			if (key > o.key) {
				return -1;
			}
			//if the frequency is same, then compare the key values
			else if (key == o.key) {
				if (value > o.value) {
					return -1;
				} else if (value == o.value) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		}

	}
	/**
	 * Constructs the array to store the frequency values, initializes the queue, and initializes the
	 * array to store the huffman codes for the nodes.
	 */
	public HuffmanCodes() {
		nodes = new int[128];
		queue = new CS232ArrayHeap<huffmanTree, CS232LinkedBinaryTree<Integer, Character>>();
		huffmanCodes = new String[128];
	}

	/**
	 * Reads the input using a scanner and completes the frequency table.
	 */
	public void readInput() {
		Scanner scr = new Scanner(System.in);
		storeInput = "";
		outputFormat = scr.nextLine();

		while (scr.hasNextLine()) {
			input = scr.nextLine();

			if (scr.hasNextLine()) {
				input += "\n";
			}
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);
				nodes[c]++;
			}
			storeInput += input;
		}
		scr.close();
	}

	/**
	 * Prints out the frequency values of each character in the input
	 */
	public void printFrequency() {
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != 0) {
				if (i == 10) {
					if (nodes[i] > 0) {
						System.out.println("\\n:" + nodes[i]);
					}
				}
				else if (i == 9) {
					System.out.println("\\t:" + nodes[i]);
				}
				else {
					System.out.println(Character.toString((char) i) + ":" + nodes[i]);
				}
			}
		}
	}

	/**
	 * Adds the nodes to the queue based on their frequency
	 */
	public void addNodes() {
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != 0) {
				CS232LinkedBinaryTree<Integer, Character> treeNode;
				treeNode = new CS232LinkedBinaryTree<Integer, Character>(nodes[i], (char) i);
				huffmanTree hft = new huffmanTree(nodes[i], (char) i);
				queue.add(hft, treeNode);
			}

		}
	}
	/**
	 * Make the tree by fusing trees into a big one. Queues will only have one tree at last.
	 */
	public void makeTree() {
		while (queue.size() > 1) {
			CS232LinkedBinaryTree<Integer, Character> node1 = queue.remove();
			CS232LinkedBinaryTree<Integer, Character> node2 = queue.remove();

			// if the first node value is greater than the second node value
			if (node1.getRootValue() > node2.getRootValue()) {
				CS232LinkedBinaryTree<Integer, Character> tree = 
						new CS232LinkedBinaryTree<Integer, Character>(node2, node1.getRootKey() + 
								node2.getRootKey(), node2.getRootValue(), node1);

				huffmanTree cur;
				cur = new huffmanTree(tree.getRootKey(), tree.getRootValue());
				queue.add(cur, tree);
			}
			else {
				// if the second node value is greater than the first node value
				CS232LinkedBinaryTree<Integer, Character> tree = 
						new CS232LinkedBinaryTree<Integer, Character>(node2, node2.getRootKey() + 
								node1.getRootKey(), node1.getRootValue(), node1);

				huffmanTree cur;
				cur = new huffmanTree(tree.getRootKey(), tree.getRootValue());
				queue.add(cur, tree);
			}	

		}

	}

	private class NodePrinter implements CS232Visitor<Integer, Character> {

		/**
		 * Print out the key,value pair at each node visited.
		 * 
		 * @param key
		 *            the key for the current node.
		 * @param value
		 *            the value at the current node.
		 */

		@Override
		public void visit(Integer key, Character value) {
			// speical case when there is a tab
			if (value.equals('\t')) {
				System.out.println("\\t" + ":" + key);
			}// special case when there is a new line 
			else if (value.equals('\n')) {
				System.out.println("\\n" + ":" + key);
			}// if no special case just print out value and key 
			else {
				System.out.println(value + ":" + key);
			}
		}

	}

	//prints level order of the tree that was created by combining individual "node" or "tree"
	public void printLevelOrder() {

		CS232LinkedBinaryTree<Integer, Character> tree = queue.remove();
		tree.visitLevelOrder(new NodePrinter());

	}

	/**
	 * Generate huffman codes for the "big" tree created by the method madeTree.  
	 */
	public void getHuffmanCode() {
		CS232LinkedBinaryTree<Integer,Character>tree=queue.remove();
		getHuffmanCodeHelper(tree.root, "", huffmanCodes);
	}

	/**
	 * The helper method is recursive backtracking. 
	 * 
	 * @param node the nodes in the huffman tree that is left in the priority queue
	 * @param str an empty string to begin with, which will be added with 0 or 1
	 * @param huffmanCodes an empty array of string to store the huffman codes
	 */
	public void getHuffmanCodeHelper(CS232LinkedBinaryTree.BTNode<Integer, Character>node, String str, String[]huffmanCodes)  {
		if(node.isLeaf()) {
			huffmanCodes[node.value] = str;
		}
		else {

			if(node.right!=null) {
				//generate codes for the right sub-tree
				getHuffmanCodeHelper(node.right,str+"1",huffmanCodes);
			}
			if(node.left!=null) {
				//generate codes for the left sub-tree
				getHuffmanCodeHelper(node.left,str+"0",huffmanCodes);
			}
		}
	}
	/**
	 * Print Huffman Codes for the characters in the text 
	 */
	public void printHuffmanCode() {
		for(int i=0; i<huffmanCodes.length;i++) {
			if(huffmanCodes[i]!=null) {
				if(huffmanCodes[i]!=null) {
					if((char)i == '\n'){
						System.out.println("\\n:" + huffmanCodes[i]);
					} else if ((char)i == '\t'){
						System.out.println("\\t:" + huffmanCodes[i]);
					} else {
						System.out.println((char)i+":"+huffmanCodes[i]);
					}
				}
			}
		}
	}

	/**
	 * Print the huffman code for a given text
	 */
	public void printHuffmanCodeText() {
		getHuffmanCode();
		for (int i = 0; i < storeInput.length(); i++) {
			System.out.print(huffmanCodes[storeInput.charAt(i)]);
		}
	}

	public static void main(String[] args) {
		HuffmanCodes huff = new HuffmanCodes(); //makes new object
		huff.readInput(); //reads input and makes frequency table
		huff.addNodes(); //makes the values into their own trees
		huff.makeTree(); //makes one big tree from the nodes

		if (outputFormat.equals("F")) {
			//if output format is F, print the frequency values
			huff.printFrequency(); 
		}
		if (outputFormat.equals("T")) {
			//if output format is T, display the key,value pairs of the nodes in the final tree in level order.
			huff.printLevelOrder();
		}
		if(outputFormat.equals("H")) {
			//if output format is H, display the Huffman Code table for the characters in the text.
			huff.getHuffmanCode();
			huff.printHuffmanCode();
		}
		if (outputFormat.equals("M")) {
			//if output format is M, print the Huffman Codes of the original text 
			huff.printHuffmanCodeText();
		}
	}

}


