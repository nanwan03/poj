import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = in.nextInt();
		int[][] puzzle = new int[6][8];
		int[][] press = new int[6][8];
		for (int i = 1; i <= testcase; ++i) {
			System.out.println("PUZZLE #" + i);
			for (int row = 1; row < 6; ++row) {
				for (int col = 1; col < 7; ++col) {
					puzzle[row][col] = in.nextInt();
				}
			}
			enumerate(puzzle, press);
			for (int row = 1; row < 6; ++row) {
				for (int col = 1; col < 7; ++col) {
					System.out.print(press[row][col] + " ");
				}
				System.out.println("");
			}
		}
	}
	private static void enumerate(int[][] puzzle, int[][] press) {
		for (int col = 1; col < 7; ++col) {
			press[1][col] = 0;
		}
		while (!guess(puzzle, press)) {
			press[1][1]++;
			int col = 1;
			while (press[1][col] > 1) {
				press[1][col] = 0;
				press[1][++col]++;
			}
		}
	}
	private static boolean guess(int[][] puzzle, int[][] press) {
		for (int row = 1; row < 5; ++row) {
			for (int col = 1; col < 7; ++col) {
				press[row + 1][col] = (puzzle[row][col] + press[row][col] + press[row - 1][col] + press[row][col - 1] + press[row][col + 1]) % 2;
			}
		}
		for (int col = 1; col < 7; ++col) {
			if ((press[5][col - 1] + press[5][col] + press[5][col + 1] + press[4][col]) % 2 != puzzle[5][col]) {
				return false;
			}
		}
		return true;
	}
}