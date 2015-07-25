import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int cowNumber = in.nextInt();
			int stallNumber = in.nextInt();
			boolean[][] map = new boolean[cowNumber + 1][stallNumber + 1];
			int[] linker = new int[stallNumber + 1];
			Arrays.fill(linker, -1);
			for (int i = 1; i <= cowNumber; ++i) {
				int preferNumber = in.nextInt();
				for (int j = 0; j < preferNumber; ++j) {
					int prefer = in.nextInt();
					map[i][prefer] = true;
				}
			}
			System.out.println(hungarian(map, linker, cowNumber, stallNumber));
		}
	}
	private static int hungarian(boolean[][] map, int[] linker, int cowNumber, int stallNumber) {
		int rst = 0;
		for (int i = 1; i <= cowNumber; ++i) {
			boolean[] used = new boolean[stallNumber + 1];
			if (dfs(map, linker, used, cowNumber, stallNumber, i)) {
				rst++;
			}
		}
		return rst;
	}
	private static boolean dfs(boolean[][] map, int[] linker, boolean[] used, int cowNumber, int stallNumber, int cow) {
		for (int i = 1; i <= stallNumber; ++i) {
			if (map[cow][i] && !used[i]) {
				used[i] = true;
				if (linker[i] == -1 || dfs(map, linker, used, cowNumber, stallNumber, linker[i])) {
					linker[i] = cow;
					return true;
				}
			}
		}
		return false;
	}
}