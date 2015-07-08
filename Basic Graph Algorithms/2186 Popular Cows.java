import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	private static int ind = 0;
	private static int index = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cowNumber = in.nextInt();
		int pathNumber = in.nextInt();
		List<List<Integer>> arc = new ArrayList<List<Integer>>();
		boolean[] visited = new boolean[cowNumber + 1];
		int[] dfn = new int[cowNumber + 1];
		int[] low = new int[cowNumber + 1];
		int[] count = new int[cowNumber + 1];
		int[] id = new int[cowNumber + 1];
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i <= cowNumber; ++i) {
			arc.add(new ArrayList<Integer>());
		}
		for (int i = 1; i <= pathNumber; ++i) {
			int u = in.nextInt();
			int v = in.nextInt();
			arc.get(u).add(v);
		}
		for (int i = 0; i <= cowNumber; ++i) {
			visited[i] = false;
			dfn[i] = -1;
			count[i] = 0;
		}
		for (int i = 1; i <= cowNumber; ++i) {
			if (dfn[i] == -1) {
				tarjan(i, visited, stack, dfn, low, count, id, arc);
			}
		}
		for (int i = 0; i < ind; ++i) {
			dfn[i] = 0;
		}
		for (int i = 1; i <= cowNumber; ++i) {
			int size = arc.get(i).size();
			int u = id[i];
			for (int j = 0; j < size; ++j) {
				int v = id[arc.get(i).get(j)];
				if (u != v) {
					dfn[u]++;
				}
			}
		}
		int flag = 0;
		int rst = 0;
		for (int i = 0; i < ind; ++i) {
			if (dfn[i] == 0) {
				rst = count[i];
				flag++;
			}
		}
		if (flag != 1) {
			rst = 0;
		}
		System.out.println(rst);
	}
	private static void tarjan(int u, boolean[] visited, Stack<Integer> stack, int[] dfn, int[] low, int[] count, int[] id, List<List<Integer>> arc) {
		visited[u] = true;
		stack.push(u);
		dfn[u] = index++;
		low[u] = dfn[u];
		int size = arc.get(u).size();
		for (int i = 0; i < size; ++i) {
			int v = arc.get(u).get(i);
			if (dfn[v] == -1) {
				tarjan(v, visited, stack, dfn, low, count, id, arc);
				if (low[u] > low[v]) {
					low[u] = low[v];
				}
			} else if (visited[v] && dfn[v] < low[u]) {
				low[u] = dfn[v];
			}
		}
		if (dfn[u] == low[u]) {
			while (true) {
				int v = stack.pop();
				visited[v] = false;
				count[ind]++;
				id[v] = ind;
				if (u == v) {
					break;
				}
			}
			ind++;
		}
	}
}