import java.io.*;
import java.util.*;

class TreapNode {
	String label;
	int priority;
	TreapNode left;
	TreapNode right;
	TreapNode father;
	TreapNode(String label, int priority) {
		this.label = label;
		this.priority = priority;
		this.left = null;
		this.right = null;
		this.father = null;
	}
	public String toString() {
		return label + "/" + priority;
	}
}

class TreapNodeComparator implements Comparator<TreapNode> {
	public int compare(TreapNode a, TreapNode b) {
		return a.label.compareTo(b.label);
	}
}
public class Main {
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		List<TreapNode> nodes = new ArrayList<TreapNode>();
		while (true) {
			nodes.clear();
			nodes.add(new TreapNode("", Integer.MAX_VALUE));
			String[] strs = in.nextLine().split("\\s+");
			int number = Integer.parseInt(strs[0]);
			if (number == 0) {
				System.exit(0);
			}
			for (int i = 1; i <= number; ++i) {
				String[] attrs = strs[i].split("/");
				int priority = Integer.parseInt(attrs[1]);
				nodes.add(new TreapNode(attrs[0], priority));
			}
			Collections.sort(nodes, new TreapNodeComparator());
			for (int i = 1; i <= number; ++i) {
				insert(nodes.get(i), nodes.get(i - 1));
			}
			printNode(nodes.get(0).right);
			System.out.print("\n");
		}
	}
	private static void insert(TreapNode cur, TreapNode prev) {
		while (prev.priority < cur.priority) {
			prev = prev.father;
		}
		cur.left = prev.right;
		prev.right = cur;
		cur.father = prev;
	}
	private static void printNode(TreapNode node) {
		if (node == null) {
			return;
		}
		System.out.print("(");
		printNode(node.left);
		System.out.print(node.toString());
		printNode(node.right);
		System.out.print(")");
	}
}