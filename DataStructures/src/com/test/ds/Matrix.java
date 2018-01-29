package com.test.ds;

import java.util.Arrays;

public class Matrix {
	public static void main(String[] args) {
		int a[][] = { { 4, 1, 3,11 }, { 9, 6, 8,12 }, { 5, 2, 7 ,14}, { 15, 42, 37, 24} };
		transposeUsingExtraSpace(a);
		System.out.println(Arrays.deepToString(a));
		int c = 10;
		changeValue(c);
		// This shows primitives are pass by value(copy is passed) where as arrays and
		// objects are also pass by value but a copy of pointer is passed hence the
		// original value gets changed of the
		System.out.println("value after changing value from function:" + c);
		printZigZac(a, 3, 3);
		System.out.println();
		printSpiral(a, 4, 4);
	}

	private static void changeValue(int c) {
		c = 11;
		System.out.println("changed value to: " + c);
	}

	public static void transpose(int a[][]) {
		// only for nxn matrix
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j < a[i].length; j++) {// j starts with i such that only half matrix is transformed. It's
													// basically the right diagonal
				// and above matrix if using a[i][j] and a[j][i] for left diagonal and below
				int temp = a[i][j];
				a[i][j] = a[j][i];
				a[j][i] = temp;
			}
		}

	}

	public static void transposeUsingExtraSpace(int a[][]) {
		int transpose[][] = new int[a[0].length][a.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				transpose[j][i] = a[i][j];
			}
		}
	}

	public static void printZigZac(int a[][], int rows, int cols) {
		int evenRow = 0;
		int oddRow = 1;
		while (evenRow < rows && oddRow < rows) {
			for (int i = 0; i < cols; i++) {
				System.out.print(a[evenRow][i]);
			}
			System.out.println();
			evenRow += 2;
			for (int i = cols - 1; i >= 0; i--) {
				System.out.print(a[oddRow][i]);
			}
			oddRow += 2;
			System.out.println();
		}
	}

	public static void printSpiral(int a[][], int rows, int cols) {
		// works with nxn matrix
		int i, startRow = 0, startCol = 0;
		while (startRow < rows && startCol < cols) {
			for (i = startCol; i < cols; i++) {
				System.out.print(a[startRow][i]);
			}
			startRow++;
			System.out.println();
			for (i = startRow; i < rows; i++) {
				System.out.print(a[i][cols - 1]);
			}
			cols--;
			System.out.println();
			for (i = cols - 1; i >= startCol; i--) {
				System.out.print(a[rows - 1][i]);
			}
			rows--;
			System.out.println();
			for (i = rows - 1; i >= startRow; i--) {
				System.out.print(a[i][startCol]);
			}
			startCol++;
			System.out.println();
		}
	}
}
