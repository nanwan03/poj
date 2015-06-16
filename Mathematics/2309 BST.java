import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = Integer.parseInt(in.nextLine());
		while (testcase-- > 0) {
			int input = in.nextInt();
			int level = getRightMostOne(input);
			int diff = (int)Math.pow(2.0, (double)level) - 1;
			System.out.println((input - diff) + " " + (input + diff));
		}
	}
	private static int getRightMostOne(int input) {
		int idx = 0;
		while (input > 0) {
			if ((input & 1) == 1) {
				return idx;
			}
			idx++;
			input >>= 1;
		}
		return idx;
	}
}