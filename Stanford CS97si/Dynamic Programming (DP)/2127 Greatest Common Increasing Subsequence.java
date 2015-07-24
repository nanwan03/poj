import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	private static int flag = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int length1 = in.nextInt();
		int[] input1 = new int[length1 + 1];
		for (int i = 1; i <= length1; ++i) {
			input1[i] = in.nextInt();
		}
		int length2 = in.nextInt();
		int[] input2 = new int[length2 + 1];
		for (int i = 1; i <= length2; ++i) {
			input2[i] = in.nextInt();
		}
		int[][] dp = new int[length1 + 1][length2 + 1];
		int[][] path = new int[length1 + 1][length2 + 1];
		for (int i = 1; i <= length1; ++i) {
			int max = 0;
			int maxVal = 0;
			for (int j = 1; j <= length2; ++j) {
				if (dp[i][j] < dp[i - 1][j]) {
					dp[i][j] = dp[i - 1][j];
					path[i][j] = j;
				}
				if (input1[i] == input2[j]) {
					if (dp[i][j] < max + 1) {
						dp[i][j] = max + 1;
						path[i][j] = maxVal;
					}
				}
				if (input2[j] < input1[i]) {
					if (max < dp[i - 1][j]) {
						max = dp[i - 1][j];
						maxVal = j;
					}
				}
			}
		}
		int rst = 1;
		for (int i = 1; i <= length2; ++i) {
			if (dp[length1][rst] < dp[length1][i]) {
				rst = i;
			}
		}
		System.out.println(dp[length1][rst]);
		output(path, dp, input1, input2, length1, rst);
		System.out.println("");
	}
	private static void output(int[][] path, int[][] dp, int[] input1, int[] input2, int x, int y) {
		if (x == 0) {
			return;
		}
		output(path, dp, input1, input2, x - 1, path[x][y]);
		if (path[x][y] != y) {
			if (flag == 1) {
				System.out.print(" ");
			} else {
				flag = 1;
			}
			System.out.print(input2[y]);
		}
	}
}