/*
 * MLE
 */
import java.io.*;
import java.util.*;
import java.math.*;

class TreeNode {
	boolean isRoot;
	boolean isVisited;
	int value;
	TreeNode ancestor;
	TreeNode parent;
	int rank;
	List<TreeNode> children;
	TreeNode(int value) {
		this.value = value;
		this.parent = this;
		this.ancestor = this;
		isRoot = true;
		isVisited = false;
		children = new ArrayList<TreeNode>();
	}
}
public class Main {
	private static int ret = 1;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = in.nextInt();
		while (testcase-- > 0) {
			int size = in.nextInt();
			TreeNode[] nodes = new TreeNode[size + 1];
			ret = 1;
			for (int index = 0; index <= size; ++index) {
				nodes[index] = new TreeNode(index);
			}
			for (int index = 1; index < size; ++index) {
				int parent = in.nextInt();
				int child = in.nextInt();
				nodes[child].isRoot = false;
				nodes[parent].children.add(nodes[child]);
			}
			TreeNode root = null;
			for (int index = 1; index <= size; ++index) {
				if (nodes[index].isRoot) {
					root = nodes[index];
					break;
				}
			}
			int u = in.nextInt();
			int v = in.nextInt();
			LCA(root, nodes[u], nodes[v]);
		}
		System.out.println(sb.toString());
	}
	private static TreeNode find(TreeNode x) {
		if (x.parent.value == x.value) {
			return x;
		} else {
			x.parent = find(x.parent);
			return x.parent;
		}
	}
	private static void union(TreeNode x, TreeNode y) {
		TreeNode rootX = find(x);
		TreeNode rootY = find(y);
		if (rootX.value == rootY.value) {
			return;
		}
		if (rootX.rank < rootY.rank) {
			rootX.parent = rootY;
		} else if (rootX.rank > rootY.rank) {
			rootY.parent = rootX;
		} else {
			rootX.parent = rootY;
			rootY.rank++;
		}
	}
	private static void LCA(TreeNode root, TreeNode u, TreeNode v) {
		if (root == null) {
			return;
		}
		root.ancestor = root;
		for (int i = 0; i < root.children.size(); ++i) {
			LCA(root.children.get(i), u, v);
			if (ret == 0) {
				return;
			}
			union(root, root.children.get(i));
			find(root).ancestor = root;
		}
		root.isVisited = true;
		if (root == u && v.isVisited && ret == 1) {
			sb.append(find(v).ancestor.value);
			sb.append('\n');
			ret = 0;
			return;
		}
		if (root == v && u.isVisited && ret == 1) {
			sb.append(find(u).ancestor.value);
			sb.append('\n');
			ret = 0;
			return;
		}
	}
}