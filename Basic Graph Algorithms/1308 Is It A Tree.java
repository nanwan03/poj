import java.util.*;
import java.io.*;

public class Main {
	private static final int N = 11000;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = 0;
		boolean[] visited = new boolean[N];
		int[] inDegree = new int[N];
		while(true) {
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			boolean isTree = true;
			testcase++;
			Arrays.fill(visited, false);
			Arrays.fill(inDegree, 0);
			while (true) {
				int a = in.nextInt();
				int b = in.nextInt();
				if (a < 0 || b < 0) {
					System.exit(0);
				}
				min = Math.min(Math.min(a, b), min);
				max = Math.max(Math.max(a, b), max);
				if (a == 0 && b == 0) {
					if (min == 0 && max == 0) {
						output(isTree, testcase);
					} else {
						if (!isTree) {
							output(isTree, testcase);
						} else {
							int zeroInDegree = 0;
							int moreThan2Degree = 0;
							for (int i = min; i <= max; ++i) {
								if (visited[i] && inDegree[i] == 0) {
									zeroInDegree++;
								}
								if (visited[i] && inDegree[i] > 1) {
									moreThan2Degree++;
								}
							}
							if (zeroInDegree == 1 && moreThan2Degree == 0) {
								isTree = true;
							} else {
								isTree = false;
							}
							output(isTree, testcase);
						}
					}
					break;
				}
				if (a == b) {
					isTree = false;
				}
				inDegree[b]++;
				visited[a] = true;
				visited[b] = true;
			}
		}
	}
	private static void output(boolean isTree, int testcase) {
		if (isTree) {
			System.out.println("Case " + testcase + " is a tree.");
		} else {
			System.out.println("Case " + testcase + " is not a tree.");
		}
	}
}