import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int num = in.nextInt();
			int seq = in.nextInt();
			if (num == -1) {
				break;
			}
			int total = 0;
			int i = 0;
			for (i = num; i >= 1; --i) {
				total += num - i;
				if (total >= seq) {
					break;
				}
			}
			for (int index = 1; index < i; ++index) {
				System.out.print(index + " ");
			}
			int k = seq + i - (num - i) * (num - i - 1) / 2;
			System.out.print(k + " ");
			for (int j = num; j >= i; --j) {
				if (j != k) {
					System.out.print(j + " ");
				}
			}
			System.out.print("\n");
		}
	}
}