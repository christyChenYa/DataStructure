package lab11;
import org.junit.Test;

import testbase.StdioTestBase;

public class PlayingWithWheelsTest extends StdioTestBase {
		
	@Test
	public void A1_SampleInput1() {
		String input = "1335\n2244\n3456\n4567";
		String output = "4";

		runTest(PlayingWithWheels.class, input, output,
				"Incorrect result for sample input 1.");
	}

	@Test
	public void A2_SampleInput2() {
		String input = "1234\n1236\n1236";
		String output = "-1";

		runTest(PlayingWithWheels.class, input, output,
				"Incorrect result for sample input 2.");
	}
	
	@Test
	public void A3_SampleInput3() {
		String input = "1234\n1236\n1233\n1235\n1244\n1224\n1134\n1334\n0234\n2234";
		String output = "-1";

		runTest(PlayingWithWheels.class, input, output,
				"Incorrect result for sample input 3.");
	}
	
	//a special case that target is in forbidden list 
	@Test
	public void TestTargetInForbidden() {
		String input = "1335\n2244\n2244\n2245";
		String output = "-1";

		runTest(PlayingWithWheels.class, input, output,
				"Incorrect result for TestTargetInForbidden.");
	}
	
	//a special case that initial is in forbidden list 
	@Test
	public void TestInitialInForbidden() {
		String input = "1335\n2244\n1335\n4567";
		String output = "-1";

		runTest(PlayingWithWheels.class, input, output,
				"Incorrect result for TestInitialInForbidden.");
	}
	
	//a case that the wheel cannot get to the target from the list, given a list of forbidden numbers
	@Test
	public void NoWay() {
		String input = "1335\n1337\n1336\n1334\n1235\n1435\n2335\n0335\n1345\n1325";
		String output = "-1";

		runTest(PlayingWithWheels.class, input, output,
				"Incorrect result for NoWay.");
	}

}