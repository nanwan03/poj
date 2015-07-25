import java.util.*;
class Node {
	int x;
	int y;
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class NodeComparator implements Comparator<Node> {
	public int compare(Node a, Node b) {
		if (a.x == b.x) {
			return a.y - b.y;
		}
		return a.x - b.x;
	}
}
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int row = in.nextInt();
		int col = in.nextInt();
		int step = 2;
		List<Node> nodes = new ArrayList<Node>();
		int number = in.nextInt();
		for (int i = 0; i < number; ++i) {
			nodes.add(new Node(in.nextInt(), in.nextInt()));
		}
		Collections.sort(nodes, new NodeComparator());
		for (int i = 0; i < number - 1; ++i) {
			for (int j = i + 1; j < number; ++j) {
				Node first = nodes.get(i);
				Node sec = nodes.get(j);
				int dx = sec.x - first.x;
				int dy = sec.y - first.y;
				int prevX = first.x - dx;
				int prevY = first.y - dy;
				if (prevX <= row && prevX >= 1 && prevY <= col && prevY >= 1) {
					continue;
				}
				if (first.x + dx * (step - 1) > row) {
					break;
				}
				int nextY = first.y + dy * (step - 1);
				if (nextY > col || nextY < 1) {
					continue;
				}
				step = Math.max(step, searchPath(nodes, sec, dx, dy, row, col));
			}
		}
		if (step == 2) {
			step = 0;
		}
		System.out.println(step);
	}
	private static int searchPath(List<Node> nodes, Node sec, int dx, int dy, int row, int col) {
		Node third = new Node(sec.x + dx, sec.y + dy);
		int steps = 2;
		while (third.x <= row && third.x >= 1 && third.y <= col && third.y >= 1) {
			if (!binarySearch(nodes, third)) {
				steps = 0;
				break;
			}
			third.x += dx;
			third.y += dy;
			steps++;
		}
		return steps;
	}
	private static boolean binarySearch(List<Node> nodes, Node node) {
		int left = 0;
		int right = nodes.size() - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			Node m = nodes.get(mid);
			if (m.x == node.x) {
				if (m.y == node.y) {
					return true;
				} else if (m.y < node.y) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else if (m.x < node.x) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		if (nodes.get(left).x == node.x && nodes.get(left).y == node.y) {
			return true;
		} else if (nodes.get(right).x == node.x && nodes.get(right).y == node.y) {
			return true;
		} else {
			return false;
		}
	}
}