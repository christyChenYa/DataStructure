package lab11;

import java.util.ArrayList;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Calculate the steps from the initial four-digit number to the target one in
 * a wheel. Digits ranging from 0 to 9 are printed consecutively (clockwise) on 
 * the periphery of each wheel. The topmost digits of the wheels form a four-digit 
 * integer. For example, in the following figure the wheels form the integer 8056. 
 * Each wheel has two buttons associated with it. Pressing the button marked with a 
 * left arrow rotates the wheel one digit in the clockwise direction and pressing the
 * one marked with the right arrow rotates it by one digit in the opposite direction. 
 * Each wheel operates independently of the others (i.e. if the first goes from 9 back 
 * around to 0, the second wheel is not affected.)


 * 
 * @author Yanxiu Chen and Weiyi Huang
 * @version May 6th, 2018
 * 
 */
public class PlayingWithWheels {

	private static ArrayList<String> getNeighbors(String input, Hashtable<String, Integer>list){
		ArrayList<String> neighbors = new ArrayList<String>();
		for (int i=0;i<4;i++) {
			//+1
			int index = Integer.parseInt(input.substring(i, i+1));
			int indexA = (index+1)%10;
			String neighbor1 = input.substring(0, i) + Integer.toString(indexA) + input.substring(i+1, 4);
			if(!list.containsKey(neighbor1)){
				neighbors.add(neighbor1);
			}

			//-1
			int indexS = (index+9)%10;
			String neighbor2 = input.substring(0, i) + Integer.toString(indexS) + input.substring(i+1, 4);
			if(!list.containsKey(neighbor2)){
				neighbors.add(neighbor2);
			}
		}
		return neighbors;
	}

	private static int step(String initial, String target, Hashtable<String, Integer>forbidden, CS232AbstractGraph graph) {
		if(forbidden.containsKey(target)|| forbidden.containsKey(initial)) {
			return -1; 
		}
		else if(initial.equals(target)) {
			return 0;
		}
		Queue<String>queue = new LinkedList<String>();
		//put the first vertex into the queue and mark it as VISITED. 
		queue.add(initial);
		graph.setVertexMark(Integer.parseInt(initial), 1);
		
		while(!queue.isEmpty()) {
			String curCon = queue.remove();
			//find all neighbors of this vertex
			ArrayList<String>neighbors = getNeighbors(curCon, forbidden);

			for(String neighbor: neighbors) {
				//for each neighbor of current vertex
				int parentMark = graph.getVertexMark(Integer.parseInt(curCon));
				
				//check if we visited it 
				if(graph.getVertexMark(Integer.parseInt(neighbor))==0){
					//not, mark it and add it to queue
					//keep tracking the number of steps 
					graph.setVertexMark(Integer.parseInt(neighbor), parentMark+1);
					queue.add(neighbor);
				}
				//it is marked, so we will leave it along
				if(neighbor.equals(target)) {
					return parentMark;
				}
			}
		}
		//should never come here
		return -1;
	}
	public static void main(String[] args) {
		Scanner scr = new Scanner (System.in);
		String initial = scr.nextLine();
		String target = scr.nextLine();
		CS232AbstractGraph<Integer, Integer> list = new CS232DirectedAdjacencyMatrixGraph<Integer, Integer> (10000);

		Hashtable<String, Integer> forbidden = new Hashtable<String, Integer>();
		while (scr.hasNextLine()) {
			forbidden.put(scr.nextLine(),0);
		}
		scr.close();
		System.out.println(step(initial,target,forbidden,list));
	}
}


