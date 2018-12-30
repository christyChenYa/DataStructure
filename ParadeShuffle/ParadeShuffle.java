package lab06;

import java.util.Scanner;

import java.util.Stack;

/**
 * Solve the Parade Reordering problem for Dickinson College. The parade has an arrival order
 * and a desired order. This algorithm checks whether the two orders can match with each other. 
 * If yes, then it prints "Yes." Otherwise, it prints "No."
 * 
 * @author Noah Hunt-Isaak and Yanxiu Chen
 * @author Dickinson College
 * @version March 1st, 2018
 */
public class ParadeShuffle {

	public static void main(String[] args) {
		/**
		 * Scan the input including three lines.
		 * The first line is the number of floats. The second line is integers in the arrival order
		 * The third line is integers in the desired order. 
		 */
		Scanner scr = new Scanner (System.in);
		int f = scr.nextInt();
		int[] inList = new int[f];
		int[]outList = new int[f];

		//put integers into an array, which is in the arrival order 
		for(int i=0;i<f;i++) {
			inList[i]=scr.nextInt();
		}

		//put integers into the second array, which is in the desired order 
		for(int i=0;i<f;i++) {
			outList[i]=scr.nextInt();
		}

		/**
		 * Use stack to see if it is possible to change the integers in the arrival order to 
		 * desired order.
		 */

		//Keep tracking the arrival-order's index  
		int inDex =0;
		//Keep tracking the desired-order's index 
		int outDex =0;

		Stack <Integer> stack = new Stack<Integer>();
		while(inDex<f) {
			//First check if elements in inList matches with the outList
			//If yes, then move to the next element
			if(inList[inDex]==outList[outDex]) {
				inDex+=1;
				outDex+=1;
			}
			//If they do not match, we will use the stack
			//We will move the not-matched element from the inList to stack first
			//Put elements into the stack until the latest-in element matches with the next element in the desired order, 
			//then pop the latest-in element(s) from the stack as long as the stack is not empty
			else {
				if(!stack.empty()) {
					int stackFront = (int) stack.peek();
					if(stackFront == outList[outDex]) {
						//If on stack, then remove the element from the top 
						//and check the next element in the outList with stack
						outDex+=1;
						stack.pop();
					}
					else {
						//If the targeted element is not on stack, then add it to the stack
						//and check the next element in the inList
						stack.add(inList[inDex]);
						inDex+=1;
					}
				}
				else {
					//This step takes place when the inList does not match with the outList
					stack.push(inList[inDex]);
					inDex+=1;
				}
			}
		}

		//After everything is removed from the input list, we empty the remaining stack. 
		boolean possible = true;
		while(!stack.empty()) {
			if(stack.pop()!=outList[outDex]) {
				//If the elements in the stack does not match, it is not possible
				possible = false;
				break;
			}
			else {
				outDex+=1;
			}
		}

		if(possible) {
			System.out.println("Yes.");
		}else {
			System.out.println("No.");
		}
	}
}
