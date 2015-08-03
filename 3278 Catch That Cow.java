import java.util.*;

public class Main {
	private static final int MAX = 100000;
	private static boolean[] visited = new boolean[MAX + 1];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int start = in.nextInt();
		int target = in.nextInt();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(start);
		visited[start] = true;
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; ++i) {
				int node = queue.poll();
				if (node == target) {
					System.out.println(level);
					return;
				}
				if (node - 1 >= 0 && !visited[node - 1]) {
					queue.offer(node - 1);
					visited[node - 1] = true;
				}
				if (node + 1 <= MAX && !visited[node + 1]) {
					queue.offer(node + 1);
					visited[node + 1] = true;
				}
				if (node * 2 <= MAX && !visited[node * 2]) {
					queue.offer(node * 2);
					visited[node * 2] = true;
				}
			}
			level++;
		}
	}
}