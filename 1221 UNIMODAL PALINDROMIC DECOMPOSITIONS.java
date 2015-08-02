import java.util.*;

public class Main {
	private static final int MAX = 500;
	private static long[][] dp = new long[MAX][MAX];
	public static void main(String[] args) {
		init();
		Scanner in = new Scanner(System.in);
		while (true) {
			int number = in.nextInt();
			if (number == 0) {
				System.exit(0);
			}
			System.out.println(number + " " + dp[number][1]);
		}
	}
	private static void init() {
		for (int i = 1; i < MAX; ++i) {
			for (int j = i; j >= 0; --j) {
				dp[i][j] = 1;
			}
		}
		Arrays.fill(dp[0], 1);
		for (int i = 2; i < MAX; ++i) {
			for (int j = i / 2; j >= 1; --j) {
				dp[i][j] = dp[i - 2 * j][j] + dp[i][j + 1];
			}
		}
	}
}