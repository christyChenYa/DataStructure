package lab04;

import java.io.File;

import org.junit.Test;

import testbase.StdioTestBase;

public class MazeSolverTest extends StdioTestBase {

	@Test
	public void testSample1() {
		File input = new File("src/lab04/maze1in.txt");
		File output = new File("src/lab04/maze1out.txt");

		runTest(MazeSolver.class, input, output,
				"Incorrect result for sample input 1.");
	}

	@Test
	public void sampleInput2() {
		File input = new File("src/lab04/maze2in.txt");
		String output = "No solution.";

		runTest(MazeSolver.class, input, output,
				"Incorrect result for sample input 2.");
	}
	
	@Test
	public void testNoSolution() {
		File input = new File("src/lab04/input3.txt");
		String output = "No solution.";

		runTest(MazeSolver.class, input, output,
				"Incorrect result for sample input 3.");
	}
}
