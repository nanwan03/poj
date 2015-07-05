import java.util.*;
import java.io.*;

public class Main {
	private static final int MAX = 10010;
	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);
		while(true) {
			int setSize = in.nextInt();
			if (setSize == 0) {
				System.exit(0);
			}
			int[] set = new int[setSize];
			for (int i = 0; i < setSize; ++i) {
				set[i] = in.nextInt();
			}
			int[] sg = getSG(setSize, set);
			int positionNum = in.nextInt();
			for (int i = 0; i < positionNum; ++i) {
				int heapSize = in.nextInt();
				int xor = 0;
				for (int j = 0; j < heapSize; ++j) {
					int temp = in.nextInt();
					xor ^= sg[temp];
				}
				if (xor == 0) {
					System.out.print("L");
				} else {
					System.out.print("W");
				}
			}
			System.out.println("");
		}
	}
	private static int[] getSG(int size, int[] set) {
		boolean[] hash = new boolean[MAX];
		int[] sg = new int[MAX];
		for (int i = 0; i < MAX; ++i) {
			Arrays.fill(hash, false);
			for (int j = 0; j < size; ++j) {
				if (i - set[j] >= 0) {
					hash[sg[i - set[j]]] = true;
				}
			}
			for (int j = 0; j < MAX; ++j) {
				if (hash[j] == false) {
					sg[i] = j;
					break;
				}
			}
		}
		return sg;
	}
}