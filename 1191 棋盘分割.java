import java.util.*;

public class Main {
	private static int[][] board = new int[9][9];
	private static int[][] sum = new int[9][9];
	private static int[][][][][] rst = new int[15][9][9][9][9];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int piece = in.nextInt();
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				sum[i][j] = 0;
			}
		}
		for (int i = 0; i < 15; ++i) {
			for (int j = 0; j < 9; ++j) {
				for (int k = 0; k < 9; ++k) {
					for (int m = 0; m < 9; ++m) {
						for (int n = 0; n < 9; ++n) {
							rst[i][j][k][m][n] = -1;
						}
					}
				}
			}
		}
		for (int i = 1; i <= 8; ++i) {
			int rowSum = 0;
			for (int j = 1;j <= 8; ++j) {
				int temp = in.nextInt();
				rowSum += temp;
				board[i][j] = temp;
				sum[i][j] = sum[i - 1][j] + rowSum;
			}
		}
		double res = piece * helper(piece, 1, 1, 8, 8) - sum[8][8] * sum[8][8];
		res = Math.sqrt(res / (double)(piece * piece));
		System.out.println(String.format("%.3f", res));
	}
	private static int calSum(int x1, int y1, int x2, int y2) {
		return sum[x2][y2]-sum[x2][y1-1]-sum[x1-1][y2]+sum[x1-1][y1-1];
	}
	private static int helper(int piece, int x1, int y1, int x2, int y2) {
		int min = 0x3f3f3f3f;
		if (rst[piece][x1][y1][x2][y2] != -1) {
			return rst[piece][x1][y1][x2][y2];
		}
		if (piece == 1) {
			int sum = calSum(x1, y1, x2, y2);
			rst[piece][x1][y1][x2][y2] = sum * sum;
			return rst[piece][x1][y1][x2][y2];
		}
		for (int i = x1; i < x2; ++i) {
			int left = calSum(x1, y1, i, y2);
			int right = calSum(i + 1, y1, x2, y2);
			min = Math.min(min, Math.min(helper(piece - 1, x1, y1, i, y2) + right * right, helper(piece - 1, i + 1, y1, x2, y2) + left * left));
		}
		for (int i = y1; i < y2; ++i) {
			int up = calSum(x1, y1, x2, i);
			int bottom = calSum(x1, i + 1, x2, y2);
			min = Math.min(min, Math.min(helper(piece - 1, x1, y1, x2, i) + bottom * bottom, helper(piece - 1, x1, i + 1, x2, y2) + up * up));
		}
		rst[piece][x1][y1][x2][y2] = min;
		return min;
	}
}