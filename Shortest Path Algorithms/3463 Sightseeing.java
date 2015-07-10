import java.util.*;
import java.io.*;

class Edge {
	int to;
	int weight;
	Edge(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
}
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = in.nextInt();
		while (testcase-- > 0) {
			int nodeNumber = in.nextInt();
			int pathNumber = in.nextInt();
			List<List<Edge>> edges = new ArrayList<List<Edge>>();
			for (int i = 0; i <= nodeNumber; ++i) {
				edges.add(new ArrayList<Edge>());
			}
			for (int i = 0; i < pathNumber; ++i) {
				int from = in.nextInt();
				int to = in.nextInt();
				int weight = in.nextInt();
				edges.get(from).add(new Edge(to, weight));
			}
			int start = in.nextInt();
			int end = in.nextInt();
			dijkstra(start, end, edges, nodeNumber, pathNumber);
		}
	}
	private static void dijkstra(int start, int end, List<List<Edge>> edges, int nodeNumber, int pathNumber) {
		int[][] distance = new int[2][nodeNumber + 1];
		int[][] count = new int[2][nodeNumber + 1];
		boolean[][] visited = new boolean[2][nodeNumber + 1];
		for (int i = 0; i <= nodeNumber; ++i) {
			distance[0][i] = Integer.MAX_VALUE;
			distance[1][i] = Integer.MAX_VALUE;
		}
		distance[0][start] = 0;
		count[0][start] = 1;
		int flag = 0;
		for (int i = 1; i <= 2 * nodeNumber; ++i) {
			int min = Integer.MAX_VALUE;
			for (int j = 1; j <= nodeNumber; ++j) {
				if (!visited[0][j] && distance[0][j] < min) {
					flag = 0;
					start = j;
					min = distance[0][j];
				} else if (!visited[1][j] && distance[1][j] < min) {
					flag = 1;
					start = j;
					min = distance[1][j];
				}
			}
			if (min == Integer.MAX_VALUE) {
				break;
			}
			visited[flag][start] = true;
			for (int j = 0; j < edges.get(start).size(); ++j) {
				int to = edges.get(start).get(j).to;
				int weight = edges.get(start).get(j).weight;
				if (distance[0][to] > min + weight) {
					distance[1][to] = distance[0][to];
					count[1][to] = count[0][to];
					distance[0][to] = min + weight;
					count[0][to] = count[flag][start];
				} else if (distance[0][to] == min + weight) {
					count[0][to] += count[flag][start];
				} else if (distance[1][to] > min + weight) {
					distance[1][to] = min + weight;
					count[1][to] = count[flag][start];
				} else if (distance[1][to] == min + weight) {
					count[1][to] += count[flag][start];
				}
			}
		}
		int number = count[0][end];
		if (distance[0][end] + 1 == distance[1][end]) {
			number += count[1][end];
		}
		System.out.println(number);
	}
}