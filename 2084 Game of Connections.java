import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BigInteger[] dp = new BigInteger[105];
		dp[1] = BigInteger.valueOf(1);
		for (int i = 2; i <= 100; ++i) {
			BigInteger t1 = BigInteger.valueOf(4 * i - 2);
			BigInteger t2 = BigInteger.valueOf(i + 1);
			dp[i] = dp[i - 1].multiply(t1).divide(t2);
		}
		while (true) {
			int input = in.nextInt();
			if (input == -1) {
				break;
			}
			System.out.println(dp[input]);
		}
	}
}