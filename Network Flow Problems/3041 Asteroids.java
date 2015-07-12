import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		int number = in.nextInt();
		boolean[][] map = new boolean[size + 1][size + 1];
		int[] linker = new int[size + 1];
		boolean[] used = new boolean[size + 1];
		Arrays.fill(linker, -1);
		for (int i = 0; i < number; ++i) {
			int x = in.nextInt();
			int y = in.nextInt();
			map[x][y] = true;
		}
		System.out.println(hungarian(map, linker, size));
	}
	private static int hungarian(boolean[][] map, int[] linker, int size) {
		int rst = 0;
		for (int i = 1; i <= size; ++i) {
			boolean[] used = new boolean[size + 1];
			if (dfs(map, linker, used, size, i)) {
				rst++;
			}
		}
		return rst;
	}
	private static boolean dfs(boolean[][] map, int[] linker, boolean[] used, int size, int index) {
		for (int i = 1; i <= size; ++i) {
			if (map[index][i] && !used[i]) {
				used[i] = true;
				if (linker[i] == -1 || dfs(map, linker, used, size, linker[i])) {
					linker[i] = index;
					return true;
				}
			}
		}
		return false;
	}
}