import java.util.*;
import java.io.*;

class Edge {
	int from;
	int to;
	int weight;
	Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}

public class Main {
	private static final int MAX = 1000010;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int index = 0;
		int nodeNum = in.nextInt();
		int ml = in.nextInt();
		int md = in.nextInt();
		Edge[] edges = new Edge[ml + md];
		int[] dis = new int[nodeNum + 1];
		Arrays.fill(dis, MAX);
		for (int i = 1; i <= ml; ++i) {
			int u = in.nextInt();
			int v = in.nextInt();
			int weight = in.nextInt();
			edges[index++] = new Edge(u, v, weight);
		}
		for (int i = 1; i <= md; ++i) {
			int u = in.nextInt();
			int v = in.nextInt();
			int weight = in.nextInt();
			edges[index++] = new Edge(v, u, -weight);
		}
		int rst = bellmanFord(nodeNum, index, edges, dis);
		if (rst == 0) {
			System.out.println("-1");
		} else if (dis[nodeNum] == MAX){
			System.out.println("-2");
		} else {
			System.out.println(dis[nodeNum]);
		}
	}
	private static int bellmanFord(int nodeNum, int edgeNum, Edge[] edges, int[] dis) {
		dis[1] = 0;
		for (int i = 1; i <= nodeNum; ++i) {
			for (int j = 0; j < edgeNum; ++j) {
				int u = edges[j].from;
				int v = edges[j].to;
				if (dis[v] > dis[u] + edges[j].weight) {
					dis[v] = dis[u] + edges[j].weight;
					if (i == nodeNum) {
						return 0;
					}
				}
			}
		}
		return 1;
	}
}