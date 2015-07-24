import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			String s = in.nextLine();
			if (s.equals("0")) {
				System.exit(0);
			}
			if (s.charAt(0) == '0') {
				System.out.println("0");
			} else {
				int[] dp = new int[s.length()];
				dp[0] = 1;
				for (int i = 1; i < dp.length; ++i) {
					if (s.charAt(i) != '0') {
						dp[i] = dp[i - 1];
					}
					int temp = Integer.parseInt(s.substring(i - 1, i + 1));
					if (temp <= 26 && temp >= 10) {
						dp[i] = dp[i] + (i == 1 ? 1 : dp[i - 2]);
					}
				}
				System.out.println(dp[s.length() - 1]);
			}
		}
	}
}