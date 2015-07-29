import java.util.*;

class Node {
	int x;
	int number;
	Node(int x, int number) {
		this.x = x;
		this.number = number;
	}
}

class NodeComparator implements Comparator<Node> {
	public int compare(Node a, Node b) {
		return a.number - b.number;
	}
}

public class Main {
	private static final int MAX = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int number = in.nextInt();
		int length = in.nextInt();
		int[] rst = new int[length + 1];
		int[] cows = new int[length + 1];
		int leftBound = in.nextInt();
		int rightBound = in.nextInt();
		leftBound <<= 1;
		rightBound <<= 1;
		Queue<Node> queue = new PriorityQueue<Node>(length, new NodeComparator());
		for (int i = 0; i < number; ++i) {
			int leftRange = in.nextInt();
			int rightRange = in.nextInt();
			++cows[leftRange + 1];
			--cows[rightRange];
		}
		int cowNumber = 0;
		for (int i = 0; i <= length; ++i) {
			rst[i] = MAX;
			cowNumber += cows[i];
			cows[i] = (cowNumber > 0) ? 1 : 0;
		}
		for (int i = leftBound; i <= rightBound; i = i + 2) {
			if (cows[i] == 0) {
				rst[i] = 1;
				if (i <= rightBound - leftBound + 2) {
					queue.offer(new Node(i, 1));
				}
			}
		}
		for (int i = rightBound + 2; i <= length; i = i + 2) {
			if (cows[i] == 0) {
				while (!queue.isEmpty()) {
					Node n = queue.peek();
					if (n.x >= i - rightBound) {
						rst[i] = n.number + 1;
						break;
					} else {
						queue.poll();
					}
				}
			}
			if (rst[i - leftBound + 2] != MAX) {
				queue.offer(new Node(i - leftBound + 2, rst[i - leftBound + 2]));
			}
		}
		if (rst[length] == MAX) {
			System.out.println(-1);
		} else {
			System.out.println(rst[length]);
		}
	}
}