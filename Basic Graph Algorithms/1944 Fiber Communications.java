import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nodeNumber = in.nextInt();
		int pathNumber = in.nextInt();
		int[] nodes = new int[(nodeNumber + 1) * 2];
		int[][] paths = new int[pathNumber + 1][2];
		for (int i = 1; i <= pathNumber; ++i) {
			int u = in.nextInt();
			int v = in.nextInt();
			paths[i][0] = Math.min(u, v);
			paths[i][1] = Math.max(u, v);
			
		}
		int rst = Integer.MAX_VALUE;
		for (int i = 1; i <= nodeNumber; ++i) {
			Arrays.fill(nodes, 0);
			for (int j = 1; j <= pathNumber; ++j) {
				int u = paths[j][0];
				int v = paths[j][1];
				if (u <= i) {
					u += nodeNumber;
				}
				if (v <= i) {
					v += nodeNumber;
				}
				if (u > v) {
					int temp = v;
					v = u;
					u = temp;
				}
				++nodes[u];
				--nodes[v];
			}
			int cur = 0;
			int res = 0;
			for (int j = 1; j <= nodeNumber; ++j) {
				cur += nodes[i + j];
				if (cur > 0) {
					res++;
				}
			}
			rst = Math.min(rst, res);
		}
		System.out.println(rst);
	}
}