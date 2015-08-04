import java.util.*;

class Node {
	int x;
	int y;
	Node pre;
	Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.pre = null;
	}
	public void print() {
		System.out.println("(" + this.x +", " + this.y + ")");
	}
}

public class Main {
	private static Set<Integer> set = new HashSet<Integer>();
	private static int[][] map = new int[5][5];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				map[i][j] = in.nextInt();
			}
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(new Node(0, 0));
		set.add(0);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.x == 4 && node.y == 4) {
				printPath(node);
				return;
			}
			List<Node> neighbors = getNeighbors(node.x, node.y);
			for (Node neighbor : neighbors) {
				neighbor.pre = node;
				queue.offer(neighbor);
			}
		}
	}
	private static int getInt(int x, int y) {
		return x * 5 + y;
	}
	private static List<Node> getNeighbors(int x, int y) {
		List<Node> list = new ArrayList<Node>();
		if (x - 1 >= 0 && map[x - 1][y] != 1 && set.add(getInt(x - 1, y))) {
			list.add(new Node(x - 1, y));
		}
		if (x + 1 < 5 && map[x + 1][y] != 1 && set.add(getInt(x + 1, y))) {
			list.add(new Node(x + 1, y));
		}
		if (y - 1 >= 0 && map[x][y - 1] != 1 && set.add(getInt(x, y - 1))) {
			list.add(new Node(x, y - 1));
		}
		if (y + 1 < 5 && map[x][y + 1] != 1 && set.add(getInt(x, y + 1))) {
			list.add(new Node(x, y + 1));
		}
		return list;
	}
	private static void printPath(Node tail) {
		List<Node> paths = new ArrayList<Node>();
		while (tail != null) {
			paths.add(tail);
			tail = tail.pre;
		}
		for (int i = paths.size() - 1; i >= 0; --i) {
			paths.get(i).print();
		}
	}
}