import java.io.*;
import java.util.*;

class Node {
	Node root;
	int index;
	int rank;
	int num;
	Node(int index) {
		this.index = index;
		this.root = this;
		this.rank = 0;
		this.num = 1;
	}
}
public class Main {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		List<Node> nodes = new ArrayList<Node>(30010);
		for (int i = 0; i < 30010; ++i) {
			nodes.add(new Node(i));
		}
		int testcase = Integer.parseInt(in.nextLine());
		while (testcase-- > 0) {
			String[] strs = in.nextLine().split("\\s+");
			if (strs[0].equals("M")) {
				int from = Integer.parseInt(strs[1]);
				int to = Integer.parseInt(strs[2]);
				union(nodes.get(from), nodes.get(to));
			} else {
				int query = Integer.parseInt(strs[1]);
				find(nodes.get(query));
				System.out.println(nodes.get(query).rank);
			}
		}
	}
	private static void union(Node from, Node to) {
		Node fromRoot = find(from);
		Node toRoot = find(to);
		if (fromRoot.index != toRoot.index) {
			fromRoot.root = toRoot;
			fromRoot.rank += toRoot.num;
			toRoot.num += fromRoot.num;
		}
	}
	private static Node find(Node node) {
		if (node.root.index != node.index) {
			Node tmp = node.root;
			node.root = find(node.root);
			node.rank += tmp.rank;
		}
		return node.root;
	}
}