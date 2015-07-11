import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int pathNumber = in.nextInt();
			int nodeNumber = in.nextInt();
			int[][] map = new int[nodeNumber + 1][nodeNumber + 1];
			for (int i = 0; i < pathNumber; ++i) {
				int from = in.nextInt();
				int to = in.nextInt();
				int cap = in.nextInt();
				map[from][to] += cap;
			}
			int start = 1;
			int end = nodeNumber;
			System.out.println(edmondsKarp(start, end, map));
		}
	}
	private static int edmondsKarp(int start, int end, int[][] map) {
		int rst = 0;
		int[] prev = new int[map.length];
		while (bfs(map, prev, start, end)) {
			int min = 0x3f3f3f3f;
			for (int i = end; i != start; i = prev[i]) {
				min = Math.min(min, map[prev[i]][i]);
			}
			for (int i = end; i != start; i = prev[i]) {
				map[prev[i]][i] -= min;
				map[i][prev[i]] += min;
			}
			rst += min;
		}
		return rst;
	}
	private static boolean bfs(int[][] map, int[] prev, int start, int nodeNumber) {
		Arrays.fill(prev, -1);
		prev[start] = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			int first = queue.poll();
			for (int i = 1; i <= nodeNumber; ++i) {
				if (prev[i] == -1 && map[first][i] != 0) {
					queue.offer(i);
					prev[i] = first;
					if (i == nodeNumber) {
						return true;
					}
				}
			}
		}
		return false;
	}
}