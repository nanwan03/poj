import java.util.*;

public class Main {
	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int MAX = 25;
	private static long[][][] rst = new long[MAX][MAX][2];
	public static void main(String[] args) {
		init(20);
		Scanner in = new Scanner(System.in);
		int testcase = in.nextInt();
		while (testcase-- > 0) {
			int number = in.nextInt();
			long index = in.nextLong();
			print(number, index);
		}
		
	}
	private static void init(int n) {
		rst[1][1][UP] = 1;
		rst[1][1][DOWN] = 1;
		for (int i = 2; i <= n; ++i) {
			for (int j = 1; j <= i; ++j) {
				for (int k = j; k < i; ++k) {
					rst[i][j][UP] += rst[i - 1][k][DOWN];
				}
				for (int k = 1; k <= j - 1; ++k) {
					rst[i][j][DOWN] += rst[i - 1][k][UP];
				}
			}
		}
	}
	private static void print(int number, long index) {
		long skipped = 0;
		int[] seq = new int[MAX];
		boolean[] used = new boolean[MAX];
		for (int i = 1; i <= number; ++i) {
			int j = 0;
			int order = 0;
			for (j = 1; j <= number; ++j) {
				skipped = 0;
				if (!used[j]) {
					++order;
					if (i == 1) {
						skipped = rst[number][order][UP] + rst[number][order][DOWN];
					} else {
						if (j > seq[i - 1] && (i <= 2 || seq[i - 2] > seq[i - 1])) {
							skipped = rst[number - i + 1][order][DOWN];
						} else if (j < seq[i - 1] && (i <= 2 || seq[i - 2] < seq[i - 1])) {
							skipped = rst[number - i + 1][order][UP];
						}
					}
					if (skipped >= index) {
						break;
					} else {
						index -= skipped;
					}
				}
			}
			used[j] = true;
			seq[i] = j;
		}
		for (int i = 1; i <= number; ++i) {
			if (i < number) {
				System.out.print(seq[i] + " ");
			} else {
				System.out.println(seq[i]);
			}
		}
	}
}