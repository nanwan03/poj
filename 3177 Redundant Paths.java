import java.io.*;
import java.util.*;
import java.math.*;

class Edge {
	int to;
	int next;
	Edge(int to, int next) {
		this.to = to;
		this.next = next;
	}
}

public class Main {
	private static int index = 0;
	private static int order = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nodeNum = in.nextInt();
		int edgeNum = in.nextInt();
		Edge[] edges = new Edge[(edgeNum + 1) * 2];
		int[] nodes = new int[nodeNum + 1];
		int[] vis = new int[nodeNum + 1];
		int[] dfn = new int[nodeNum + 1];
		int[] low = new int[nodeNum + 1];
		int[] inDegree = new int[nodeNum + 1];
		int[][] map = new int[nodeNum + 1][nodeNum + 1];
		Arrays.fill(nodes, -1);
		for (int i = 0; i < edges.length; ++i) {
			edges[i] = new Edge(0, 0);
		}
		index = 0;
		for (int i = 1; i <= edgeNum; ++i) {
			int u = in.nextInt();
			int v = in.nextInt();
			if (map[u][v] == 0) {
				addEdge(nodes, edges, u, v);
				addEdge(nodes, edges, v, u);
				map[u][v] = 1;
				map[v][u] = 1;
			}
		}
		dfs(nodes, edges, vis, dfn, low, 1, 0);
		for (int i = 1; i <= nodeNum; ++i) {
			for (int j = nodes[i]; j != -1; j = edges[j].next) {
				if (low[i] != low[edges[j].to]) {
					inDegree[low[i]]++;
				}
			}
		}
		int rst = 0;
		for (int i = 1; i <= nodeNum; ++i) {
			if (inDegree[i] == 1) {
				rst++;
			}
		}
		System.out.println((rst + 1) / 2);
	}
	private static void addEdge(int[] nodes, Edge[] edges, int u, int v) {
		edges[index].to = v;
		edges[index].next = nodes[u];
		nodes[u] = index++;
	}
	private static void dfs(int[] nodes, Edge[] edges, int[] vis, int[] dfn, int[] low, int u, int prev) {
		vis[u] = 1;
		dfn[u] = ++order;
		low[u] = dfn[u];
		for (int i = nodes[u]; i != -1; i = edges[i].next) {
			int v = edges[i].to;
			if (vis[v] == 0) {
				dfs(nodes, edges, vis, dfn, low, v, u);
				low[u] = Math.min(low[u], low[v]);
			} else if (vis[v] == 1 && v != prev) {
				low[u] = Math.min(low[u], dfn[v]);
			}
		}
		vis[u] = 2;
	} 
}