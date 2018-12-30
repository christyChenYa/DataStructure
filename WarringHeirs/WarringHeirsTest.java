package lab05;

import org.junit.Test;

import testbase.StdioTestBase;

public class WarirngHeirsTest extends StdioTestBase {

	@Test
	public void testSampleInput1() {

		String input = "3 3 3 1\n0 0 1\n0 1 1\n0 2 2";
		String output = "0 0 0\n0 0 1\n2 1 1";

		runTest(WarringHeirs.class, input, output,
				"Incorrect result for sample input 1.");
	}

	@Test
	public void testSampleInput2() {

		String input = "3 4 4 3\n0 1 2 0\n1 0 2 0\n0 1 2 0\n0 1 2 2";
		String output = "2 2 2 0\n2 1 0 1\n2 2 2 0\n0 2 0 0";

		runTest(WarringHeirs.class, input, output,
				"Incorrect result for sample input 2.");
	}
	
	@Test
	public void testAllZero() {

		String input = "3 3 3 1\n0 0 0\n0 0 0\n0 0 0";
		String output = "0 0 0\n0 0 0\n0 0 0";

		runTest(WarringHeirs.class, input, output,
				"Incorrect result for test all zero.");
	}
	
	@Test
	public void testCirculaing() {

		String input = "3 2 2 4\n0 1\n0 2";
		String output = "0 1\n0 2";

		runTest(WarringHeirs.class, input, output,
				"Incorrect result for test circulating.");
	}
}

