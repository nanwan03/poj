import java.util.*;
import java.io.*;

class Edge {
	int from;
	int to;
	int weight;
	int next;
	Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}

public class Main {
	private static int count = 0;
	private static final int MAX = 1000010;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nodeNum = in.nextInt();
		int ml = in.nextInt();
		int md = in.nextInt();
		int[] head = new int[nodeNum + 1];
		Edge[] edges = new Edge[ml + md];
		int[] dis = new int[nodeNum + 1];
		Arrays.fill(head, -1);
		Arrays.fill(dis, MAX);
		for (int i = 0; i < edges.length; ++i) {
			edges[i] = new Edge(0, 0, 0);
		}
		for (int i = 1; i <= ml; ++i) {
			addEdge(in.nextInt(), in.nextInt(), in.nextInt(), edges, head);
		}
		for (int i = 1; i <= md; ++i) {
			int u = in.nextInt();
			int v = in.nextInt();
			int weight = in.nextInt();
			addEdge(v, u, -weight, edges, head);
		}
		int rst = bellmanFord(nodeNum, edges, dis);
		if (rst == 0) {
			System.out.println("-1");
		} else if (dis[nodeNum] == MAX){
			System.out.println("-2");
		} else {
			System.out.println(dis[nodeNum]);
		}
	}
	private static void addEdge(int from, int to, int weight, Edge[] edges, int[] head) {
		edges[count].from = from;
		edges[count].to = to;
		edges[count].weight = weight;
		edges[count].next = head[from];
		head[from] = count++;
	}
	private static int bellmanFord(int nodeNum, Edge[] edges, int[] dis) {
		dis[1] = 0;
		for (int i = 1; i <= nodeNum; ++i) {
			int flag = 0;
			for (int j = 0; j < count; ++j) {
				int u = edges[j].from;
				int v = edges[j].to;
				if (dis[v] > dis[u] + edges[j].weight) {
					dis[v] = dis[u] + edges[j].weight;
					flag = 1;
				}
			}
			if (flag == 0) {
				break;
			}
		}
		for (int i = 0; i < count; ++i) {
			if (dis[edges[i].to] > dis[edges[i].from] + edges[i].weight) {
				return 0;
			}
		}
		return 1;
	}
}