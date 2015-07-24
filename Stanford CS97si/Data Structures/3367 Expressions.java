import java.io.*;
import java.util.*;

class TreeNode {
	char c;
	TreeNode left;
	TreeNode right;
	TreeNode(char c) {
		this.c = c;
		this.left = null;
		this.right = null;
	}
}
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(cin.readLine());
		while (testcase-- > 0) {
			String str = cin.readLine();
			TreeNode root = buildTree(str.toCharArray());
			traversal(root);
		}
	}
	private static TreeNode buildTree(char[] chars) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		for (char c : chars) {
			TreeNode node = new TreeNode(c);
			if (Character.isLowerCase(c)) {
				stack.push(node);
			} else {
				TreeNode right = stack.pop();
				TreeNode left = stack.pop();
				node.left = left;
				node.right = right;
				stack.push(node);
			}
		}
		return stack.pop();
	}
	private static void traversal(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		StringBuilder rst = new StringBuilder();
		StringBuilder level = new StringBuilder();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			level = new StringBuilder();
			for (int i = 0; i < size; ++i) {
				TreeNode node = queue.poll();
				level.append(node.c);
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			rst.append(level);
		}
		System.out.println(rst.reverse().toString());
	}
}