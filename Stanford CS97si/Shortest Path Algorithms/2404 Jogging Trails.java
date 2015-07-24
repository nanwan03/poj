import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int nodeNumber = in.nextInt();
			if (nodeNumber == 0) {
				System.exit(0);
			}
			int pathNumber = in.nextInt();
			int[][] map = new int[nodeNumber][nodeNumber];
			int[] degree = new int[nodeNumber];
			int[] dp = new int[1 << 16];
			int sum = 0;
			for (int i = 0; i < map.length; ++i) {
				for (int j = 0; j < map[0].length; ++j) {
					map[i][j] = 0x3f3f3f3f;
				}
			}
			for (int i = 0; i < pathNumber; ++i) {
				int from = in.nextInt() - 1;
				int to = in.nextInt() - 1;
				int weight = in.nextInt();
				if (map[from][to] > weight) {
					map[from][to] = weight;
					map[to][from] = weight;
				}
				sum += weight;
				++degree[from];
				++degree[to];
			}
			for (int k = 0; k < nodeNumber; ++k) {
				for (int i = 0; i < nodeNumber; ++i) {
					for (int j = 0; j < nodeNumber; ++j) {
						if (i == j || i == k || j == k) {
							continue;
						}
						map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
					}
				}
			}
			int mask = 0;
			for (int i = 0; i < nodeNumber; ++i) {
				if (degree[i] % 2 == 1) {
					mask |= (1 << i);
				}
			}
			sum += search(mask, map, degree, dp, nodeNumber);
			System.out.println(sum);
		}
	}
	private static int search(int mask, int[][] map, int[] degree, int[] dp, int nodeNumber) {
		if (mask == 0) {
			return 0;
		}
		if (dp[mask] != 0) {
			return dp[mask];
		}
		int rst = 0x3f3f3f3f;
		int temp = 0;
		for (int i = 0; i < nodeNumber - 1; ++i) {
			if ((mask & (1 << i)) != 0) {
				for (int j = i + 1; j < nodeNumber; ++j) {
					if ((mask & (1 << j)) != 0) {
						temp = search(mask - (1 << i) - (1 << j), map, degree, dp, nodeNumber) + map[i][j];
						rst = Math.min(temp, rst);
					}
				}
			}
		}
		dp[mask] = rst;
		return rst;
	}
}