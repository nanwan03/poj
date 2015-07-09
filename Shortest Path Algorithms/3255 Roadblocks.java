import java.io.*;
import java.util.*;
import java.math.*;

class Edge {
	int to;
	int weight;
	Edge(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
}

class EdgeComparator implements Comparator<Edge> {
	public int compare(Edge e1, Edge e2) {
		return e1.weight - e2.weight;
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nodeNum = in.nextInt();
		int pathNum = in.nextInt();
		List<List<Edge>> edges = new ArrayList<List<Edge>>();
		for (int i = 0; i < nodeNum; ++i) {
			edges.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < pathNum; ++i) {
			int from = in.nextInt() - 1;
			int to = in.nextInt() - 1;
			int weight = in.nextInt();
			edges.get(from).add(new Edge(to, weight));
			edges.get(to).add(new Edge(from, weight));
		}
		dijkstra(edges, nodeNum, pathNum);
	}
	private static void dijkstra(List<List<Edge>> edges, int nodeNum, int pathNum) {
		Queue<Edge> heap = new PriorityQueue<Edge>(pathNum * 2, new EdgeComparator());
		int[] dist = new int[nodeNum];
		int[] secondShort = new int[nodeNum];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(secondShort, Integer.MAX_VALUE);
		dist[0] = 0;
		heap.offer(new Edge(0, 0));
		while (!heap.isEmpty()) {
			Edge e = heap.poll();
			int to = e.to;
			int weight = e.weight;
			if (secondShort[to] < weight) {
				continue;
			}
			for (int i = 0; i < edges.get(to).size(); ++i) {
				Edge edge = edges.get(to).get(i);
				int distance = weight + edge.weight;
				if (dist[edge.to] > distance) {
					int temp = dist[edge.to];
					dist[edge.to] = distance;
					distance = temp;
					heap.offer(new Edge(edge.to, dist[edge.to]));
				}
				if (secondShort[edge.to] > distance && dist[edge.to] < distance) {
					secondShort[edge.to] = distance;
					heap.offer(new Edge(edge.to, secondShort[edge.to]));
				}
			}
		}
		System.out.println(secondShort[nodeNum - 1]);
	}
}