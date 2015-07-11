import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int machineNumber = in.nextInt();
		int cowNumber = in.nextInt();
		int maxCap = in.nextInt();
		int[][] map = new int[machineNumber + cowNumber  + 2][machineNumber + cowNumber + 2];
		int start = 0;
		int end = machineNumber + cowNumber + 1;
		for (int i = 1; i < end; ++i) {
			for (int j = 1; j < end; ++j) {
				map[i][j] = in.nextInt();
				if (map[i][j] == 0) {
					map[i][j] = 0x3f3f3f3f;
				}
			}
		}
		for (int k = 1; k < end; ++k) {
			for (int i = 1; i < end; ++i) {
				for (int j = 1; j < end; ++j) {
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		int left = 0;
		int right = 10000;
		int rst = 0;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			int[][] matrix = build(mid, map, machineNumber, cowNumber, start, end, maxCap);
			int sum = edmondsKarp(start, end, matrix);
			if (sum == cowNumber) {
				rst = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(rst);
	}
	private static int[][] build(int distance, int[][] map, int machineNumber, int cowNumber, int begin, int end, int maxCap) {
		int[][] matrix = new int[map.length][map[0].length];
		for (int i = 1 + machineNumber; i < end; ++i) {
			for (int j = 1; j <= machineNumber; ++j) {
				if (map[i][j] <= distance) {
					matrix[i][j] = 1;
				}
			}
		}
		for (int i = 1; i <= machineNumber; ++i) {
			matrix[i][end] = maxCap;
		}
		for (int i = 1 + machineNumber; i < end; ++i) {
			matrix[begin][i] = 1;
		}
		return matrix;
	}
	private static int edmondsKarp(int start, int end, int[][] matrix) {
		int rst = 0;
		int[] prev = new int[matrix.length];
		while (bfs(matrix, prev, start, end)) {
			for (int i = end; i != start; i = prev[i]) {
				matrix[prev[i]][i] -= 1;
				matrix[i][prev[i]] += 1;
			}
			rst += 1;
		}
		return rst;
	}
	private static boolean bfs(int[][] map, int[] prev, int start, int end) {
		Arrays.fill(prev, -1);
		prev[start] = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			int first = queue.poll();
			for (int i = 1; i <= end; ++i) {
				if (prev[i] == -1 && map[first][i] != 0) {
					queue.offer(i);
					prev[i] = first;
					if (i == end) {
						return true;
					}
				}
			}
		}
		return false;
	}
}