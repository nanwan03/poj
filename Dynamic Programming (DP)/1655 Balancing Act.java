import java.util.*;
import java.io.*;

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
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int testcase = Integer.parseInt(in.nextLine());
		while (testcase-- > 0) {
			int nodeNum = Integer.parseInt(in.nextLine());
			int[] nodes = new int[nodeNum + 1];
			Edge[] edges = new Edge[(nodeNum + 1) * 2];
			Arrays.fill(nodes, -1);
			for (int i = 0; i < edges.length; ++i) {
				edges[i] = new Edge(0, 0);
			}
			index = 0;
			for (int i = 1; i < nodeNum; ++i) {
				String[] strs = in.nextLine().split("\\s+");
				int u = Integer.parseInt(strs[0]);
				int v = Integer.parseInt(strs[1]);
				addEdge(edges, nodes, u, v);
				addEdge(edges, nodes, v, u);
			}
			int[] dp = new int[nodeNum + 1];
			int[] num = new int[nodeNum + 1];
			dfs(nodeNum, dp, num, nodes, edges, 1, -1);
			int rst1 = 1;
			int rst2 = dp[1];
			for (int i = 2; i <= nodeNum; ++i) {
				if (dp[i] < rst2) {
					rst1 = i;
					rst2 = dp[i];
				}
			}
			System.out.println(rst1 + " " + rst2);
		}
	}
	private static void addEdge(Edge[] edges, int[] nodes, int u, int v) {
		edges[index].to = v;
		edges[index].next = nodes[u];
		nodes[u] = index++;
	}
	private static void dfs(int nodeNum, int[] dp, int [] num, int[] nodes, Edge[] edges, int u, int prev) {
		dp[u] = 0;
		num[u] = 1;
		for (int i = nodes[u]; i != -1; i = edges[i].next) {
			int v = edges[i].to;
			if (v == prev) {
				continue;
			}
			dfs(nodeNum, dp, num, nodes, edges, v, u);
			dp[u] = Math.max(dp[u], num[v]);
			num[u] += num[v];
		}
		dp[u] = Math.max(dp[u], nodeNum - num[u]);
	}
}