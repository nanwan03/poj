import java.util.*;
import java.io.*;

public class Main {
	private static final int N = 10010;
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		int testcase = Integer.parseInt(in.nextLine());
		while(testcase-- > 0) {
			String[] strs = in.nextLine().split("\\s+");
			int n = Integer.parseInt(strs[0]);
			int a = Integer.parseInt(strs[1]);
			int b = Integer.parseInt(strs[2]);
			int[] dp = new int[N];
			Arrays.fill(dp, Integer.MIN_VALUE);
			strs = in.nextLine().split("\\s+");
			int[] input = new int[n];
			for (int i = 0; i < n; ++i) {
				input[i] = Integer.parseInt(strs[i]);
			}
			Arrays.sort(input);
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < n; ++i) {
				if (input[i] >= a && input[i] <= b) {
					max = Math.max(max, helper(i, dp, input, n, a, b));
				}
			}
			if (max == Integer.MIN_VALUE) {
				System.out.println("0");
			} else {
				System.out.println(max);
			}
		}
	}
	private static int helper(int index, int[] dp, int[] input, int n, int a, int b) {
		if (dp[index] != Integer.MIN_VALUE) {
			return dp[index];
		}
		int rst = Integer.MIN_VALUE;
		for (int i = index + 1; i < n; ++i) {
			if (input[i] - input[index] >= a && input[i] - input[index] <= b) {
				rst = Math.max(rst, helper(i, dp, input, n, a, b));
			}
		}
		if (rst == Integer.MIN_VALUE) {
			dp[index] = input[index];
		} else {
			dp[index] = input[index] - rst;
		}
		return dp[index];
	}
}