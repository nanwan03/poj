import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int villageNum = in.nextInt();
		int postNum = in.nextInt();
		int[][] dp = new int[villageNum][postNum];
		int[][] dis = new int[villageNum][villageNum];
		int[] input = new int[villageNum];
		for (int i = 0; i < villageNum; ++i) {
			input[i] = in.nextInt();
		}
		if (postNum >= villageNum) {
			System.out.println("0");
		} else {
			init(dis, input);
			for (int i = 0; i < villageNum; ++i) {
				Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
			}
			for (int i = 0; i < villageNum; ++i) {
				dp[i][0] = dis[0][i];
			}
			for (int k = 1; k < postNum; ++k) {
				for (int i = 0; i < villageNum; ++i) {
					for (int j = 0; j < i; ++j) {
						if (dp[j][k - 1] + dis[j + 1][i] < dp[i][k]) {
							dp[i][k] = dp[j][k - 1] + dis[j + 1][i];
						}
					}
				}
			}
			System.out.println(dp[villageNum - 1][postNum - 1]);
		}
	}
	private static void init(int[][] dis, int[] input) {
		for (int i = 0; i < dis.length - 1; ++i) {
			for (int j = i + 1; j < dis.length; ++j) {
				int mid = (i + j) / 2;
				for (int k = i; k < mid; ++k) {
					dis[i][j] += input[mid] - input[k];
				}
				for (int k = mid + 1; k <= j; ++k) {
					dis[i][j] += input[k] - input[mid];
				}
			}
		}
	}
}