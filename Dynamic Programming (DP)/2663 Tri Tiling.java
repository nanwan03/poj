import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	private static final int N = 33;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BigInteger[] dp = new BigInteger[N];
		dp[0] = BigInteger.ONE;
		dp[1] = BigInteger.ZERO;
		dp[2] = new BigInteger("3");
		dp[3] = BigInteger.ZERO;
		for (int i = 4; i < N; ++i) {
			int j = i - 2;
			BigInteger rst = dp[j].multiply(new BigInteger("3"));
			j -= 2;
			while (j >= 0) {
				rst = rst.add(dp[j].multiply(new BigInteger("2")));
				j -= 2;
			}
			dp[i] = rst; 
		}
		while (true) {
			int input = in.nextInt();
			if (input == -1) {
				System.exit(0);
			} else {
				System.out.println(dp[input]);
			}
		}
	}
}