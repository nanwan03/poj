import java.util.*;

public class Main {
	private static final int MAX = 75;
	private static char[][] board = new char[MAX + 2][MAX + 2];
	private static boolean[][] visited = new boolean[MAX + 2][MAX + 2];
	private static int minStep = 0x3f3f3f3f;
	private static int[][] dirs = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = 1;
		while (true) {
			String[] strs = in.nextLine().split("\\s+");
			int col = Integer.parseInt(strs[0]);
			int row = Integer.parseInt(strs[1]);
			if (row == 0 && col == 0) {
				System.exit(0);
			}
			System.out.println("Board #" + testcase++ + ":");
			for (int i = 0; i < board.length; ++i) {
				for (int j = 0; j < board[0].length; ++j) {
					board[i][j] = ' ';
				}
			}
			for (int i = 1; i <= row; ++i) {
				char[] chars = in.nextLine().toCharArray();
				for (int j = 1; j <= col; ++j) {
					board[i][j] = chars[j - 1];
				}
			}
			int pair = 1;
			while (true) {
				strs = in.nextLine().split("\\s+");
				int startX = Integer.parseInt(strs[0]);
				int startY = Integer.parseInt(strs[1]);
				int endX = Integer.parseInt(strs[2]);
				int endY = Integer.parseInt(strs[3]);
				if (startX == 0 && startY == 0 && endX == 0 && endY == 0) {
					break;
				}
				System.out.print("Pair " + pair++ + ": ");
				minStep = 0x3f3f3f3f;
				for (int i = 0; i < visited.length; ++i) {
					for (int j = 0; j < visited[0].length; ++j) {
						visited[i][j] = false;
					}
				}
				visited[startY][startX] = true;
				helper(row, col, startX, startY, endX, endY, 0, -1);
				if (minStep == 0x3f3f3f3f) {
					System.out.println("impossible.");
				} else {
					System.out.println(minStep + " segments.");
				}
			}
			System.out.println("");
		}
	}
	private static void helper(int row, int col, int startX, int startY, int endX, int endY, int step, int prevDir) {
		if (step > minStep) {
			return;
		}
		if (startX == endX && startY == endY) {
			minStep = Math.min(step, minStep);
			return;
		}
		for (int i = 0; i < 4; ++i) {
			int nextX = startX + dirs[i][0];
			int nextY = startY + dirs[i][1];
			if (nextX < 0 || nextX >= col + 2 || nextY < 0 || nextY >= row + 2) {
				continue;
			}
			if ((board[nextY][nextX] == ' ' && visited[nextY][nextX] == false) || (nextX == endX && nextY == endY)) {
				visited[nextY][nextX] = true;
				helper(row, col, nextX, nextY, endX, endY, (i == prevDir ? step : step + 1), i);
				visited[nextY][nextX] = false;
			}
		}
	}
}