import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String[] str = in.readLine().split("\\s+");
		int number = Integer.parseInt(str[0]);
		int weightMax = Integer.parseInt(str[1]);
		int[] weight = new int[number + 1];
		int[] value = new int[number + 1];
		for (int i = 1; i <= number; ++i) {
			str = in.readLine().split("\\s+");
			weight[i] = Integer.parseInt(str[0]);
			value[i] = Integer.parseInt(str[1]);
		}
		int[] dp = new int[weightMax + 1];
		for (int i = 1; i <= number; ++i) {
			for (int j = weightMax; j >= weight[i]; --j) {
				dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
			}
		}
		System.out.println(dp[weightMax]);
	}
}