import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nodeNum = in.nextInt();
		int A = in.nextInt();
		int B = in.nextInt();
		int[][] map = new int[nodeNum + 1][nodeNum + 1];
		for (int i = 0; i <= nodeNum; ++i) {
			for (int j = 0; j <= nodeNum; ++j) {
				map[i][j] = 0x3f3f3f3f;
			}
		}
		for(int i = 1; i <= nodeNum; ++i) {
			int neighborNum = in.nextInt();
			for (int j = 1; j <= neighborNum; ++j) {
				if (j == 1) {
					map[i][in.nextInt()] = 0;
				} else {
					map[i][in.nextInt()] = 1;
				}
			}
		}
		for (int k = 1; k <= nodeNum; ++k) {
			for (int i = 1; i <= nodeNum; ++i) {
				if (map[i][k] == 0x3f3f3f3f) {
					continue;
				}
				for (int j = 1; j <= nodeNum; ++j) {
					if (i != k && i != j && j != k && map[i][k] + map[k][j] < map[i][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		if (map[A][B] != 0x3f3f3f3f) {
			System.out.println(map[A][B]);
		} else {
			System.out.println("-1");
		}
	}
}