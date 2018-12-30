package lab08;

import org.junit.Test;

import testbase.StdioTestBase;

public class HuffmanCodesTest extends StdioTestBase {

	@Test
	public void testSampleInput1() {
		String input = "F\nAABBBCDDDDEE";
		String output = "A:2\nB:3\nC:1\nD:4\nE:2\n";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect result for sample input 1.");
	}

	/**
	 * For testing F: Display the frequency count for the characters in the text.
	 * 
	 * special case of tab
	 */
	@Test
	public void testFWithTabs() {
		String input = "F\nAABB\tBCDDDDEE";
		String output = "\\t:1\nA:2\nB:3\nC:1\nD:4\nE:2\n";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect result for test with tabs.");
	}

	/**
	 * For testing F: Display the frequency count for the characters in the text.
	 *
	 * special case of next line
	 */
	@Test
	public void testFWithMultipleLines() {
		String input = "F\nAABB\nBCDDD\nDEE";
		String output = "\\n:2\nA:2\nB:3\nC:1\nD:4\nE:2\n";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect result for test with multiple lines.");
	}

	@Test
	public void testSampleInput2() {
		String input = "T\nAABBBCDDDDEE";
		String output = "A:12\nB:7\nA:5\nD:4\nB:3\nA:3\nE:2\nA:2\nC:1";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect result for sample input 2.");
	}

	/**
	 * For testing T: Display the key,value pairs of the nodes in the final tree in level order.
	 * 
	 * a special case of same frequency	
	 */
	@Test
	public void testMakeTreeSameFrequencies(){
		String input = "T\nABCD";
		String output = "A:4\nC:2\nA:2\nD:1\nC:1\nB:1\nA:1";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect output for make tree same frequencies");

	}

	/**
	 * For testing H - Display the Huffman Code table for the characters in the text.
	 * 
	 * Test for special case in which both letters share the same frequencies. 
	 */
	@Test
	public void testSameWeight() {
		String input = "H\nAAAAABBBBB";
		String output = "A:1\nB:0";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect result for test same weight.");
	}

	/**
	 * For testing H - Display the Huffman Code table for the characters in the text.
	 * 
	 * Test for same weight letters with a lower frequency letter: 3A3B1C.
	 * We reaffirm that a newly fused tree chooses smaller letter, (in this case: A over B).  
	 */
	@Test
	public void testSameWeightSpecial() {
		String input = "H\nAAABBBC";
		String output = "A:00\nB:1\nC:01";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect result for the test of same weight letters with one lower frequency letter.");
	}

	@Test
	public void testSampleInput3() {
		String input = "H\nAABBBCDDDDEE";
		String output = "A:100\nB:01\nC:101\nD:00\nE:11";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect result for sample input 3.");
	}

	/**
	 * For testing H - Display the Huffman Code table for the characters in the text.
	 * a special case of the same characters 
	 */
	@Test
	public void testGetHuffmanCodeOneNode(){
		String input = "H\nAAAAA";
		String output = "A:";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect huffman codes in the output with one node");
	}

	@Test
	public void testSampleInput4() {
		String input = "M\nAABBBCDDDDEE";
		String output = "100100010101101000000001111";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect result for sample input 4.");
	}

	/**
	 * For testing M - The original text encoded using the Huffman Code.
	 * A special case of the same frequency for 
	 */
	@Test
	public void testHuffmanCodeTextSameFrequencies(){
		String input = "M\nABCD";
		String output = "11100100";

		runTest(HuffmanCodes.class, input, output,
				"Incorrect huffman code text output when the frequencies are the same");
	}

}