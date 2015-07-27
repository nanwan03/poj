import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int size = in.nextInt();
		int[][] input = new int[size + 1][size + 1];
		for (int i = 1; i <= size; ++i) {
			for (int j = 1; j <= i; ++j) {
				input[i][j] = in.nextInt();
			}
		}
		int[] dp = input[size];
		for (int i = size - 1; i >= 1; --i) {
			for (int j = 1; j <= i; ++j) {
				dp[j] = Math.max(dp[j], dp[j + 1]) + input[i][j];
			}
		}
		System.out.println(dp[1]);
	}
}