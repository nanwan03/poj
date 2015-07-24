import java.io.*;
import java.util.*;

class Node {
	Node root;
	int index;
	int distX;
	int distY;
	Node(int index) {
		this.root = this;
		this.index = index;
		this.distX = 0;
		this.distY = 0;
	}
}

class Path {
	Node u;
	Node v;
	int len;
	char dir;
	Path(Node u, Node v, int len, char dir) {
		this.u = u;
		this.v = v;
		this.len = len;
		this.dir = dir;
	}
}

class Query {
	Node u;
	Node v;
	int index;
	Query(Node u, Node v, int index) {
		this.u = u;
		this.v = v;
		this.index = index;
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		int nodeNum = in.nextInt();
		int pathNum = in.nextInt();
		Node[] farm = new Node[nodeNum + 1];
		for (int i = 0; i <= nodeNum; ++i) {
			farm[i] = new Node(i);
		}
		Path[] paths = new Path[pathNum];
		for (int i = 0; i < pathNum; ++i) {
			paths[i] = new Path(farm[in.nextInt()], farm[in.nextInt()], in.nextInt(), in.next().charAt(0));
		}
		int queryNum = in.nextInt();
		Query[] queries = new Query[queryNum];
		for (int i = 0; i < queryNum; ++i) {
			queries[i] = new Query(farm[in.nextInt()], farm[in.nextInt()], in.nextInt());
		}
		int cur = 0;
		for (int i = 0; i < queryNum; ++i) {
			while (cur < queries[i].index) {
				union(paths[cur++]);
			}
			if (find(queries[i].u).index != find(queries[i].v).index) {
				System.out.println("-1");
			} else {
				int dist = Math.abs(queries[i].u.distX - queries[i].v.distX) +
							Math.abs(queries[i].u.distY - queries[i].v.distY);
				System.out.println(dist);
			}
		}
	}
	private static Node find(Node node) {
		if (node.index == node.root.index) {
			return node;
		}
		Node temp = node.root;
		node.root = find(node.root);
		node.distX += temp.distX;
		node.distY += temp.distY;
		return node.root;
	}
	private static void union(Path p) {
		Node rootU = find(p.u);
		Node rootV = find(p.v);
		if (rootU.index == rootV.index) {
			return;
		}
		rootV.root = rootU;
		if (p.dir == 'E') {
			rootV.distX = p.u.distX - p.v.distX + p.len;
			rootV.distY = p.u.distY - p.v.distY;
		} else if (p.dir == 'W') {
			rootV.distX = p.u.distX - p.v.distX - p.len;
			rootV.distY = p.u.distY - p.v.distY; 
		} else if (p.dir == 'N') {
			rootV.distX = p.u.distX - p.v.distX;
			rootV.distY = p.u.distY - p.v.distY + p.len;
		} else {
			rootV.distX = p.u.distX - p.v.distX;
			rootV.distY = p.u.distY - p.v.distY - p.len;
		}
	}
}