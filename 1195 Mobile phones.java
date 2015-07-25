import java.io.*;
import java.util.*;

public class Main {
	private static int cell[][];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int instruct = in.nextInt();
			if (instruct == 3) {
				System.exit(0);
			} else if (instruct == 0) {
				int number = in.nextInt();
				initCells(number);
			} else if (instruct == 1) {
				int row = in.nextInt() + 1;
				int col = in.nextInt() + 1;
				int diff = in.nextInt();
				update(row, col, diff);
			} else {
				int left = in.nextInt() + 1;
				int bottom = in.nextInt() + 1;
				int right = in.nextInt() + 1;
				int up = in.nextInt() + 1;
				int rst = sum(right, up) + sum(left - 1, bottom - 1) - sum(right, bottom - 1) - sum(left - 1, up);
				System.out.println(rst);
			}
		}
	}
	private static void initCells(int size) {
		cell = new int[size + 1][size + 1];
	}
	private static int lowBit(int i) {
		return i & (-i);
	}
	private static void update(int row, int col, int diff) {
		int size = cell.length;
		for (int i = row; i < size; i += lowBit(i)) {
			for (int j = col; j < size; j += lowBit(j)) {
				cell[i][j] += diff;
			}
		}
	}
	private static int sum(int row, int col) {
		int rst = 0;
		for (int i = row; i > 0; i -= lowBit(i)) {
			for (int j = col; j > 0; j -= lowBit(j)) {
				rst += cell[i][j];
			}
		}
		return rst;
	}
}