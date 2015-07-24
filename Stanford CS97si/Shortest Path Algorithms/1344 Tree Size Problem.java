import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int n = in.nextInt();
			if (n == 0) {
				System.exit(0);
			}
			int[][] map = new int[n][n];
			for (int i = 0; i < n; ++i) {
				for (int j = i + 1; j < n; ++j) {
					map[i][j] = in.nextInt();
					map[j][i] = map[i][j];
				}
				map[i][i] = 0;
			}
			System.out.println(helper(map, n));
		}
	}
	private static int helper(int[][] map, int n) {
		int rst = 0;
		for (int k = 0; k < n - 2; ++k) {
			int max = Integer.MAX_VALUE;
			for (int i = k + 1; i < n; ++i) {
				for (int j = i + 1; j < n; ++j) {
					int temp = (map[i][k] + map[j][k] - map[i][j]) / 2;
					if (temp < max) {
						max = temp;
					}
				}
			}
			for (int i = k + 1; i < n; ++i) {
				map[i][k] -= max;
				map[k][i] -= max;
			}
			rst += max;
		}
		rst += map[n - 2][n - 1];
		return rst;
	}
}