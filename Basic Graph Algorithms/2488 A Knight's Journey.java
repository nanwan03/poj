import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	private static final int[] DX = {-1,1,-2,2,-2,2,-1,1};
	private static final int[] DY = {-2,-2,-1,-1,1,1,2,2};
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int testcase = in.nextInt();
		for (int i = 1; i <= testcase; ++i) {
			int row = in.nextInt();
			int col = in.nextInt();
			boolean[][] visited = new boolean[row][col];
			boolean isPossible = false;
			visited[0][0] = true;
			List<String> items = new ArrayList<String>();
			items.add("A1");
			isPossible = helper(row, col, 0, 0, 1, items, visited);
			StringBuilder sb = new StringBuilder();
			if (isPossible) {
				for (String str : items) {
					sb.append(str);
				}
			} else {
				sb.append("impossible");
			}
			System.out.println("Scenario #" + i + ":");
			System.out.println(sb.toString() + "\n");
		}
	}
	private static boolean helper(int row, int col, int x, int y, int num, List<String> items, boolean[][] visited) {
		if (num == row * col) {
			return true;
		}
		for (int i = 0; i < 8; ++i) {
			int nx = x + DX[i];
			int ny = y + DY[i];
			if (nx < 0 || nx >= row || ny < 0 || ny >= col || visited[nx][ny]) {
				continue;
			}
			items.add(new String((char)('A' + ny) + "" + (nx + 1)));
			visited[nx][ny] = true;
			num++;
			boolean isPossible = helper(row, col, nx, ny, num, items, visited);
			if (isPossible) {
				return true;
			}
			visited[nx][ny] = false;
			num--;
			items.remove(items.size() - 1);
		}
		return false;
	}
}