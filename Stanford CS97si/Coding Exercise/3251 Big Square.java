import java.io.*;
import java.util.*;
import java.math.*;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int size = Integer.parseInt(in.nextLine());
		char[][] map = new char[size][size];
		int rst = 0;
		for (int i = 0; i < size; ++i) {
			map[i] = in.nextLine().toCharArray();
		}
		for (int x = 0; x < size; ++x) {
			for (int y = 0; y < size; ++y) {
				if (map[x][y] == 'B') {
					continue;
				}
				for (int i = size - 1; i >= 0; --i) {
					for (int j = size - 1; j >= y; --j) {
						if (map[i][j] != 'B' && (map[i][j] != map[x][y] || map[x][y] != '*')) {
							int xDist = i - x;
							int yDist = j - y;
							if (xDist * xDist + yDist * yDist <= rst) {
								continue;
							}
							if ((isValid(size, x - yDist, i - yDist, y + xDist, j + xDist) && 
									map[x - yDist][y + xDist] == 'J' && map[i - yDist][j + xDist] == 'J')
							  ||(isValid(size, x + yDist, i + yDist, y - xDist, j - xDist) &&
									map[x + yDist][y - xDist] == 'J' && map[i + yDist][j - xDist] == 'J')) {
								rst = Math.max(rst, xDist * xDist + yDist * yDist);
							}
						}
					}
				}
			}
		}
		System.out.print(rst + "\n");
	}
	private static boolean isValid(int size, int i, int j, int x, int y) {
		return (i >= 0 && j >= 0 && x >= 0 && y >= 0) && (i < size && j < size && x < size && y < size);
	}
}