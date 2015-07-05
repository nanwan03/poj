import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int number = in.nextInt();
			if (number == 0) {
				System.exit(0);
			}
			int[] input = new int[number];
			int rst = 0;
			for (int i = 0; i < number; ++i) {
				input[i] = in.nextInt();
			}
			if ((number & 1) == 1) {
				System.out.println("1");
			} else {
				Arrays.sort(input);
				for (int i = 0; i < number - 1; i = i + 2) {
					if (input[i] != input[i + 1]) {
						rst = 1;
					}
				}
				System.out.println(rst);
			}
		}
	}
}