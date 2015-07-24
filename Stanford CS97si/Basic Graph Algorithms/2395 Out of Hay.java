import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	private static final int MAX = 1000000001;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		int lines = in.nextInt();
		int[][] map = new int[size][size];
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				map[i][j] = MAX;
			}
		}
		for (int i = 1; i <= lines; ++i) {
			int u = in.nextInt() - 1;
			int v = in.nextInt() - 1;
			int dis = in.nextInt();
			map[u][v] = Math.min(map[u][v], dis);
			map[v][u] = Math.min(map[v][u], dis);
		}
		System.out.println(prim(map, size));
	}
	private static int prim(int[][] map, int size) {
		int max = 0;
		int[] lowCost = new int[size];
		boolean[] used = new boolean[size];
		for (int i = 0; i < size; ++i) {
			lowCost[i] = map[0][i];
			used[i] = false;
		}
		used[0] = true;
		for (int i = 1; i < size; ++i) {
			int j = 0;
			while (used[j]) {
				j++;
			}
			for (int k = 0; k < size; ++k) {
				if ((!used[k]) && (lowCost[k] < lowCost[j])) {
					j = k;
				}
			}
			used[j] = true;
			max = Math.max(max, lowCost[j]);
			for (int k = 0; k < size; ++k) {
				if (!used[k] && (map[j][k] < lowCost[k])) {
					lowCost[k] = map[j][k];
				}
			}
		}
		return max;
	}
}