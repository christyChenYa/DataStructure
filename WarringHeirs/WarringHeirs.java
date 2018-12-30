package lab05;

import java.util.Scanner;

public class WarringHeirs {

	public static void main(String[] args) {
		/**
		 *  Solve the problem of warring heirs through brute force. First 
		 *  check if the element is under attack. If so, the element is 
		 *  replaced by another element that is 1 smaller than it. Otherwise,
		 *  the element will stay the same. 
		 */
		Scanner in = new Scanner(System.in);

		//read input
		int N = in.nextInt();
		int R = in.nextInt();
		int C = in.nextInt();
		int K = in.nextInt();
		int[][] grid = new int[R][C];
		for(int i=0; i<R;i++) {
			for(int j=0; j<C; j++) {
				grid[i][j] = in.nextInt();
			}
		}

		for(int i=0;i<K;i++) {
			int[][]copy = new int [R][C];
			for(int row=0; row<R; row++) {
				for(int col=0;col<C;col++) {
					//loop through array and check if each element is under attack
					copy = check(grid,copy,row,col, N, R, C);
				}
			}
			grid =copy;
		}

		//print out solution
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(j<C-1) {
					System.out.print(grid[i][j] + " ");
				}
				else {
					System.out.print(grid[i][j]);

				}
			}
			System.out.println();
		}
	}

	public static int[][] check(int[][]grid, int[][]copy, int row, int col, int N, int R, int C){
		//check up
		if(row-1>=0) {
			if(grid[row-1][col]==grid[row][col]-1) {
				copy[row][col] =grid[row-1][col];
				return copy;

			}
			else if(grid[row][col]==0 && grid[row-1][col]==N-1) {
				copy[row][col] = N-1;
				return copy;

			}
			else {
				copy[row][col]=grid[row][col];
			}
		}

		//check down
		if(row+1<R) {
			if(grid[row+1][col]==grid[row][col]-1) {
				copy[row][col] =grid[row+1][col];
				return copy;

			}
			else if(grid[row][col]==0 && grid[row+1][col]==N-1) {
				copy[row][col] = N-1;
				return copy;

			}
			else {
				copy[row][col]=grid[row][col];
			}
		}

		//check left 
		if(col-1>=0) {
			if(grid[row][col-1]==grid[row][col]-1) {
				copy[row][col] =grid[row][col-1];
				return copy;

			}
			else if(grid[row][col]==0 && grid[row][col-1]==N-1) {
				copy[row][col] = N-1;
				return copy;

			}
			else {
				copy[row][col]=grid[row][col];
			}
		}

		//check right
		if(col+1<C) {
			if(grid[row][col+1]==grid[row][col]-1) {
				copy[row][col] =grid[row][col+1];
				return copy;

			}
			else if(grid[row][col]==0 && grid[row][col+1]==N-1) {
				copy[row][col] = N-1;
				return copy;

			}
			else {
				copy[row][col]=grid[row][col];
			}
		}
		return copy;
	}
}
