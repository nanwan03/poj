import java.io.*;
import java.util.*;
import java.math.*;

class Node {
	int index;
	int dis;
	Node(int index, int dis) {
		this.index = index;
		this.dis = dis;
	}
}
public class Main {
	private static int maxDis = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] strs = in.nextLine().split("\\s+");
		int nodeNum = Integer.parseInt(strs[0]);
		int pathNum = Integer.parseInt(strs[1]);
		List<List<Node>> nodes = new ArrayList<List<Node>>();
		for (int i = 0; i <= nodeNum; ++i) {
			nodes.add(new ArrayList<Node>());
		}
		for (int i = 1; i <= pathNum; ++i) {
			strs = in.nextLine().split("\\s+");
			int source = Integer.parseInt(strs[0]);
			int dest = Integer.parseInt(strs[1]);
			int dist = Integer.parseInt(strs[2]);
			nodes.get(source).add(new Node(dest, dist));
			nodes.get(dest).add(new Node(source, dist));
		}
		maxDis = 0;
		int point = bfs(1, nodes);
		maxDis = 0;
		bfs(point, nodes);
		System.out.println(maxDis);
	}
	private static int bfs(int index, List<List<Node>> nodes) {
		int nodeNum = nodes.size();
		int[] dis = new int[nodeNum];
		boolean[] vis = new boolean[nodeNum];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(index);
		vis[index] = true;
		int point = 0;
		while (!queue.isEmpty()) {
			int u = queue.poll();
			if (dis[u] > maxDis) {
				maxDis = dis[u];
				point = u;
			}
			for (int i = 0; i < nodes.get(u).size(); ++i) {
				Node tmp = nodes.get(u).get(i);
				if (!vis[tmp.index]) {
					vis[tmp.index] = true;
					dis[tmp.index] = dis[u] + tmp.dis;
					queue.offer(tmp.index);
				}
			}
		}
		return point;
	}
}