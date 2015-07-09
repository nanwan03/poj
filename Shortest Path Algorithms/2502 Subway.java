import java.io.*;
import java.util.*;
import java.math.*;

class Node {
	int x;
	int y;
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	private static final int MAX = 405;
	private static final double EPSILON = 2.7755575615628914E-17;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Node[] nodes = new Node[MAX];
		double[][] map = new double[MAX][MAX];
		int startX = in.nextInt();
		int startY = in.nextInt();
		nodes[0] = new Node(startX, startY);
		int endX = in.nextInt();
		int endY = in.nextInt();
		nodes[1] = new Node(endX, endY);
		int index = 2;
		int count = 2;
		while (in.hasNext()) {
			while (true) {
				int x = in.nextInt();
				int y = in.nextInt();
				if (x == -1 && y == -1) {
					for (int i = index; i < count - 1; ++i) {
						double dis = getDistance(nodes[i].x, nodes[i].y, nodes[i + 1].x, nodes[i + 1].y) / 40000.0;
						map[i][i + 1] = dis;
						map[i + 1][i] = dis;
					}
					index = count;
					break;
				}
				nodes[count++] = new Node(x, y);
			}
		}
		for (int i = 0; i < count; ++i) {
			for (int j = i + 1; j < count; ++j) {
				if (map[i][j] < EPSILON) {
					double dis = getDistance(nodes[i].x, nodes[i].y, nodes[j].x, nodes[j].y) / 10000.0;
					map[i][j] = dis;
					map[j][i] = dis;
				}
			}
		}
		dijkstra(nodes, map, count);
	}
	private static double getDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	private static void dijkstra(Node[] nodes, double[][] map, int count) {
		boolean[] visited = new boolean[count];
		double[] distance = new double[count];
		for (int i = 0; i < count; ++i) {
			distance[i] = map[0][i];
		}
		visited[0] = true;
		int rst = 0;
		for (int i = 0; i < count; ++i) {
			double min = Double.POSITIVE_INFINITY;
			for (int j = 0; j < count; ++j) {
				if (!visited[j] && min > distance[j]) {
					min = distance[j];
					rst = j;
				}
			}
			visited[rst] = true;
			for (int j = 0; j < count; ++j) {
				if (!visited[j] && distance[j] > distance[rst] + map[rst][j]) {
					distance[j] = distance[rst] + map[rst][j];
				}
			}
		}
		System.out.println((int)(distance[1] * 60.0 + 0.5));
	}
}