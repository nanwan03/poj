import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int nodeNum = in.nextInt();
			if (nodeNum == 0) {
				System.exit(0);
			}
			int[][] map = new int[nodeNum + 1][nodeNum + 1];
			for (int i = 0; i <= nodeNum; ++i) {
				for (int j = 0; j <= nodeNum; ++j) {
					map[i][j] = 999;
				}
			}
			for (int i = 1; i <= nodeNum; ++i) {
				int pairs = in.nextInt();
				while (pairs-- > 0) {
					int j = in.nextInt();
					int dist = in.nextInt();
					map[i][j] = dist;
				}
			}
			for (int k = 1; k <= nodeNum; ++k) {
				for (int i = 1; i <= nodeNum; ++i) {
					for (int j = 1; j <= nodeNum; ++j) {
						if (map[i][k] + map[k][j] < map[i][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
			int sum = 0;
			int id = 0;
			int dist = Integer.MAX_VALUE;
			for (int i = 1; i <= nodeNum; ++i) {
				sum = 0;
				for (int j = 1; j <= nodeNum; ++j) {
					if (i != j && map[i][j] > sum) {
						sum = map[i][j];
					}
				}
				if (sum < dist) {
					dist = sum;
					id = i;
				}
			}
			System.out.println(id + " " + dist);
		}
	}
}