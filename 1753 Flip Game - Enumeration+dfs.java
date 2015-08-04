import java.util.*;

public class Main {
	private static int steps = 0x3f3f3f3f;
	private static  int[] dx={0,0,0,1,-1};
	private static  int[] dy={0,1,-1,0,0};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int source = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; ++i) {
			 sb.append(in.nextLine().trim());
		}
		for (int i = 0; i < sb.length(); ++i) {
			source = (source << 1) + (sb.substring(i, i + 1).equals("b") ? 1 : 0);
		}
		if (solve(source)) {
			System.out.println(steps);
		} else {
			System.out.println("Impossible");
		}
	}
	private static boolean solve(int source) {
		for (int i = 0; i < 16; ++i) {
			int temp = source;
			int number = 0;
			for (int j = 0; j < 4; ++j) {
				if ((i & (1 << j)) > 0) {
					for (int k = 0; k <= 4; ++k) {
						int x = 0 + dx[k];
						int y = j + dy[k];
						temp = flip(x, y, temp);
					}
					number++;
				}
			}
			dfs(1, number, temp, 0);
			dfs(1, number, temp, 1);
		}
		return steps != 0x3f3f3f3f;
	}
	private static int flip(int x, int y, int source) {
		if (x >= 0 && x < 4 && y >= 0 && y < 4) {
			source ^= 1 << (x * 4 + y);
		}
		return source;
	}
	private static void dfs(int cur, int number, int source, int flag) {
		if (cur == 4) {
			if (source == 0xffff || source == 0) {
				steps = Math.min(number, steps);
			}
			return;
		}
		for (int i = cur - 1, j = 0; j < 4; ++j) {
			if( (((source & (1 << (i * 4 + j))) >> (i * 4 + j)) ^ flag) == 1) {
				for (int k = 0; k <= 4; ++k) {
					int x = cur + dx[k];
					int y = j + dy[k];
					source = flip(x, y, source);
				}
				number++;
			}
		}
		dfs(cur + 1, number, source, flag);
	}
}