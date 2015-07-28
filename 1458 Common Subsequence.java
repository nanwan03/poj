import java.util.*;

public class Main {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 while (in.hasNext()) {
			 String[] strs = in.nextLine().split("\\s+");
			 System.out.println(solve(strs[0], strs[1]));
		 }
	 }
	 private static int solve(String str1, String str2) {
		 int[][] dp = new int[str1.length() + 1][str2.length() + 1];
		 for (int i = 0; i <= str1.length(); ++i) {
			 dp[i][0] = 0;
		 }
		 for (int i = 0; i <= str2.length(); ++i) {
			 dp[0][i] = 0;
		 }
		 for (int i = 1; i <= str1.length(); ++i) {
			 for (int j = 1; j <= str2.length(); ++j) {
				 if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					 dp[i][j] = dp[i - 1][j - 1] + 1;
				 } else {
					 dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				 }
			 }
		 }
		 return dp[str1.length()][str2.length()];
	 }
}