package lab06;

import org.junit.Test;

import testbase.StdioTestBase;

public class ParadeShuffleTest extends StdioTestBase {

	@Test
	public void testSampleInput1() {
		String input = "4\n1 2 3 4\n1 3 2 4";
		String output = "Yes.";

		runTest(ParadeShuffle.class, input, output,
				"Incorrect result for sample input 1.");
	}

	@Test
	public void testSampleInput2() {
		String input = "4\n1 2 3 4\n1 4 2 3";
		String output = "No.";

		runTest(ParadeShuffle.class, input, output,
				"Incorrect result for sample input 2.");
	}
	
	@Test
	public void testInverse() {
		//We realize that the desired order can be inversely related to the inList as a result of the Last-in-last-out 
		//nature of stack. 
		String input = "3\n1 2 3\n3 2 1";
		String output = "Yes.";

		runTest(ParadeShuffle.class, input, output,
				"Incorrect result for input which has an arrival order inversely relating to the desired order.");
	}
	
	@Test
	public void testNotMatching() {
		//Check whether the algorithm will work when the first element in stack pops out, 
		//the next element in the desired-order is at the bottom part of the stack 
		String input = "5\n1 2 3 4 5\n5 2 3 1 4";
		String output = "No.";

		runTest(ParadeShuffle.class, input, output,
				"The test result is incorrect for the input whose desired order cannot be achieved.");
	}
	
	@Test
	public void testEmptyStackDoesNotWork() {
		//This test checks whether the algorithm will work when the last pop-up element in the stack does not 
		//match with the last element in the desired-order.
		//Because our algorithm uses stack to store elements from the inList when they do not match with the outList
		//we realize that there will be a scenario in which the empty stack does not guarantee the match. 
		//We use this test to revise our codes and use boolean possible in line 85 to check whether it is possible to match
		//rather than use an empty stack to check 
		String input = "3\n6 2 3\n3 2 1";
		String output = "No.";

		runTest(ParadeShuffle.class, input, output,
				"The test result is incorrect for the input that last-in element in stack doest not match with the last element in output.");
	}

	@Test
	public void testBigSize() {
		//We use this test to check whether our algorithm will work with a big size 
		String in ="\n";
		for(int i=0; i<250;i++) {
			in = in +Integer.toString(i+1)+ " ";
		}
		String input = "250" + in+ in;
		String output = "Yes.";

		runTest(ParadeShuffle.class, input, output,
				"Incorrect result for a 250-element test.");
	}

	@Test
	public void testAnotherBigSize() {
		//We use this test to check a bigger size 
		String in ="\n";
		for(int i=0; i<500;i++) {
			in = in +Integer.toString(i+1)+ " ";
		}
		String input = "500" + in+ in;
		String output = "Yes.";

		runTest(ParadeShuffle.class, input, output,
				"Incorrect result for a 500-element test.");
	}
	
	@Test
	public void testBiggestSize() {
		//This test is used to check the biggest size 
		String in ="\n";
		for(int i=0; i<1000;i++) {
			in = in +Integer.toString(i+1)+ " ";
		}
		String input = "1000" + in+ in;
		String output = "Yes.";

		runTest(ParadeShuffle.class, input, output,
				"Incorrect result for a 1000-element test.");
	}
}
