import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	private static final int[] DX = {0, -1, 0, 1};
	private static final int[] DY = {-1, 0, 1, 0};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int row = in.nextInt();
		int col = in.nextInt();
		int[][] map = new int[row][col];
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < col; ++j) {
				map[i][j] = in.nextInt();
			}
		}
		int num = 0;
		int max = 0;
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < col; ++j) {
				if (map[i][j] != -1) {
					int sum = helper(row, col, i, j, map);
					num++;
					max = Math.max(max, sum);
				}
			}
		}
		System.out.println(num);
		System.out.println(max);
	}
	private static int helper(int row, int col, int x, int y, int[][] map) {
		int input = map[x][y];
		int sum = 1;
		map[x][y] = -1;
		for (int i = 0; i < 4; ++i, input /= 2) {
			if (input % 2 == 0) {
				int nx = x + DX[i];
				int ny = y + DY[i];
				if (nx < 0 || nx >= row || ny < 0 || ny >= col || map[nx][ny] == -1) {
					continue;
				}
				sum += helper(row, col, nx, ny, map);
			}
		}
		return sum;
	}
}