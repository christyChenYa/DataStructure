package lab04;

import java.util.Scanner;
/**
 * Solve a maze by recursion backtracking. The maze is written in text
 * and the output is either a solution to the maze or "No Solution!"
 * Character 'X' is wall. ' ' is hallways. The starting point is 'S'
 * and the exit is 'E'
 * 
 * 
 * @author Yanxiu Chen & Weiyi Huang 
 *
 */
public class MazeSolver {
	static int column;
	static int row;
	static char[][] list;
	static int startRow, startCol;

	/**
	 * Scanner gets the textual description of the maze. The first line
	 * of input is the number of rows and columns in the maze. A double 
	 * array stores the textual description of the maze. The index position
	 * of the starting point will be stored as startRow and startCol. Then 
	 * isWay method is called to find out whether there is a solution. The 
	 * output will be a solution to the maze, which marks the path 
	 * with "." from the starting point to the exit if the solution exist. 
	 * Otherwise the output should be "No Solution!"
	 * 
	 * @param args input describing the maze 
	 */
	public static void main(String[] args) {
		Scanner scr = new Scanner(System.in);
		String[] scan = scr.nextLine().split(",");
		row = Integer.parseInt(scan[0]);
		column = Integer.parseInt(scan[1]);

		list = new char[row][column];

		for (int i = 0; i < list.length; i++) {
			String data = scr.nextLine();
			for (int j = 0; j < list[i].length; j++) {
				list[i][j] = data.charAt(j);
				if(list[i][j]=='S') {
					startRow = i;
					startCol= j;
				}
			}
		}
		scr.close();

		if(isWay(list, startRow, startCol)) {
			for(int i=0; i<row; i++) {
				for (int j=0; j<column;j++) {
					System.out.print(list[i][j]);
				}
				System.out.println();
			}
		}else {
			System.out.println("No solution.");
		}

	}

	/**
	 * This method takes the double array, and the index positions to find a way out of the maze! 
	 * 
	 * @param list
	 * @param x current position
	 * @param y current position
	 * @return true if the route exist, false if there is no solution
	 */
	public static boolean isWay(char[][]list, int x, int y){
		/*
		 * If there is no way to go, such as index out of bounds, or only wall left, or 
		 * all paths are checked, then there is no solution. 
		 * 
		 * If find the exist, there is a solution!
		 * 
		 * Otherwise, try moving in either of the four directions (up, down, left, right) and see if
		 * that leads to a solution. If so, then there is a solution! If not,
		 * try the other three directions and see if any of those lead to
		 * a solution. If so there is a solution! If not then there is no
		 * solution with the empty space in any direction, thus there is no
		 * solution.
		 */
		if(x<0 || y<0 || x>=row || y>= column){
			return false;
			//No way to go and index out of bound - no solution
		}
		else if(list[x][y] == 'X') {
			return false;
			//No way to go and here is the wall- no solution
		}
		else if(list[x][y] =='.') {
			return false;
			//the position is already checked - no solution
		}
		else if(list[x][y]=='E') {
			return true;
			//find the exist! - solution
		}

		//try moving when the current position is not any of the  
		//cases above, and it is not the starting point
		if(list[x][y]!='S') {
			list[x][y] = '.';
			//mark the path with "."
		}

		//try going up
		if(isWay(list,x,y-1)) {
			//find a solution to rest of problem by going up 
			return true;
		}
		//no solution by going up 

		//try going down
		if(isWay(list,x,y+1)) {
			//find a solution to rest of problem by going down 
			return true;
		}
		//no solution by going down 

		//try going left 
		if(isWay(list,x-1,y)) {
			//find a solution to rest of problem by going left 
			return true;
		}
		//no solution by going left 

		//try going right
		if(isWay(list,x+1,y)) {
			//find a solution to rest of problem by going right
			return true;
		}
		//no solution by going right

		//cannot find a way out of the maze 
		//so no solution - erase the "."
		//backtracking! 
		if(list[x][y] != 'S') {
			list[x][y] = ' ';
		}
		return false;
	}

}