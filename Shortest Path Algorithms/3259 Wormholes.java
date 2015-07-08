import java.io.*;
import java.util.*;
import java.math.*;

class Edge {
	int from;
	int to;
	int cost;
	Edge(int from , int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
}
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = in.nextInt();
		while (testcase-- > 0) {
			int nodeNum = in.nextInt();
			int pathNum = in.nextInt();
			int wormHole = in.nextInt();
			Edge[] edges = new Edge[pathNum * 2 + wormHole];
			int index = 0;
			for (int i = 0; i < pathNum; ++i) {
				int from = in.nextInt() - 1;
				int to = in.nextInt() - 1;
				int dist = in.nextInt();
				edges[index++] = new Edge(from, to, dist);
				edges[index++] = new Edge(to, from, dist);
			}
			for (int i = 0; i < wormHole; ++i) {
				int from = in.nextInt() - 1;
				int to = in.nextInt() - 1;
				int dist = in.nextInt();
				edges[index++] = new Edge(from, to, -dist);
			}
			if (isExistNegativeLoop(nodeNum, index, edges)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
	private static boolean isExistNegativeLoop(int nodeNum, int edgeNum, Edge[] edges) {
		int[] dist = new int[nodeNum];
		for (int i = 0; i < nodeNum; ++i) {
			for (int j = 0; j < edgeNum; ++j) {
				Edge e = edges[j];
				if (dist[e.to] > dist[e.from] + e.cost) {
					dist[e.to] = dist[e.from] + e.cost;
					if (i == nodeNum - 1) {
						return true;
					}
				}
			}
		}
		return false;
	}
}