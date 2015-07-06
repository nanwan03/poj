import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        while ((input = in.readLine()) != null) {
            if (input.equals("")) {
                break;
            }
            int size = Integer.parseInt(input);
            int[][] map = new int[size][size];
            int temp = size;
            while (temp-- > 0) {
                int index = 0;
                while (index != size) {
                    String[] strs = in.readLine().split(" ");
                    for (int i = 0; i < strs.length; i++, index++) {
                        map[size - temp - 1][index] = Integer.parseInt(strs[i]);
                    }
                }
            }
            System.out.println(prim(map, size));
        }
	}
	private static int prim(int[][] map, int size) {
		int sum = 0;
		int[] lowCost = new int[size];
		boolean[] used = new boolean[size];
		for (int i = 0; i < size; ++i) {
			lowCost[i] = map[0][i];
			used[i] = false;
		}
		used[0] = true;
		for (int i = 1; i < size; ++i) {
			int j = 0;
			while (used[j]) {
				j++;
			}
			for (int k = 0; k < size; ++k) {
				if ((!used[k]) && (lowCost[k] < lowCost[j])) {
					j = k;
				}
			}
			sum += lowCost[j];
			used[j] = true;
			for (int k = 0; k < size; ++k) {
				if (!used[k] && (map[j][k] < lowCost[k])) {
					lowCost[k] = map[j][k];
				}
			}
		}
		return sum;
	}
}