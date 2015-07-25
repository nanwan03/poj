import java.util.*;
public class Main {
	private static int rst = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = Integer.parseInt(in.nextLine());
		for (int i = 1; i <= testcase; ++i) {
			rst = Integer.MAX_VALUE;
			int size = Integer.parseInt(in.nextLine());
			int[][] puzzle = new int[size + 1][size + 2];
			int[][] paint = new int[size + 1][size + 2];
			for (int row = 1; row <= size; ++row) {
				char[] input = in.nextLine().toCharArray();
				for (int col = 1; col <= size; ++col) {
					puzzle[row][col] = (input[col - 1] == 'y' ? 0 : 1);
				}
			}
			enumerate(puzzle, paint, size);
			if (rst == Integer.MAX_VALUE) {
				System.out.println("inf");
			} else {
				System.out.println(rst);
			}
		}
	}
	private static void enumerate(int[][] puzzle, int[][] paint, int size) {
		for (int col = 1; col <= size; ++col) {
			paint[1][col] = 0;
		}
		while (true) {
			boolean flag = guess(puzzle, paint, size);
			if (flag) {
				int temp = 0;
				for (int row = 1; row <= size; ++row) {
					for (int col = 1; col <= size; ++col) {
						temp += paint[row][col];
					}
				}
				rst = Math.min(rst, temp);
			}
			paint[1][1]++;
			int col = 1;
			while (col <= size && paint[1][col] > 1) {
				paint[1][col] = 0;
				col++;
				if (col > size) {
					return;
				}
				paint[1][col]++;
			}
		}
	}
	private static boolean guess(int[][] puzzle, int[][] paint, int size) {
		for (int row = 1; row < size; ++row) {
			for (int col = 1; col <= size; ++col) {
				paint[row + 1][col] = (puzzle[row][col] + paint[row][col] + paint[row - 1][col] + paint[row][col - 1] + paint[row][col + 1]) % 2;
			}
		}
		for (int col = 1; col <= size; ++col) {
			if ((paint[size][col - 1] + paint[size][col] + paint[size][col + 1] + paint[size - 1][col]) % 2 != puzzle[size][col]) {
				return false;
			}
		}
		return true;
	}
}