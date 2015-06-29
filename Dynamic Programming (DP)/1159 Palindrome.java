import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int length = Integer.parseInt(in.nextLine());
		short[][] dp = new short[2][length];
		String str = in.nextLine();
		for (int start = length - 2; start >= 0; --start) {
			for (int end = start + 1; end < length; ++end) {
				if (str.charAt(start) == str.charAt(end)) {
					dp[start % 2][end] = dp[(start + 1) % 2][end - 1];
				} else {
					dp[start % 2][end] = (short)(Math.min(dp[(start + 1) % 2][end], dp[start % 2][end - 1]) + 1);
				}
			}
		}
		System.out.println(dp[0][length - 1]);
	}
}