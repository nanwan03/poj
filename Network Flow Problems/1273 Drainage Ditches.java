import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int pathNumber = in.nextInt();
			int nodeNumber = in.nextInt();
			int[][] map = new int[nodeNumber + 1][nodeNumber + 1];
			int[] prev = new int[nodeNumber + 1];
			for (int i = 0; i < pathNumber; ++i) {
				int from = in.nextInt();
				int to = in.nextInt();
				int cap = in.nextInt();
				map[from][to] += cap;
			}
			int start = 1;
			int end = nodeNumber;
			System.out.println(maxFlow(start, end, map, prev));
		}
	}
	private static int maxFlow(int start, int end, int[][] map, int[] prev) {
		int rst = 0;
		while (true) {
			if (!bfs(map, prev, start, end)) {
				return rst;
			}
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
	}
	private static boolean bfs(int[][] map, int[] prev, int start, int nodeNumber) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[nodeNumber + 1];
		queue.offer(start);
		while (!queue.isEmpty()) {
			int first = queue.poll();
			if (first == nodeNumber) {
				return true;
			}
			for (int i = 1; i <= nodeNumber; ++i) {
				if (!visited[i] && map[first][i] != 0) {
					queue.offer(i);
					visited[i] = true;
					prev[i] = first;
				}
			}
		}
		return false;
	}